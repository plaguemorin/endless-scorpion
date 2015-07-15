package ca.screenshot.endlessscorpion.controllers;

import ca.screenshot.endlessscorpion.beans.Event;
import ca.screenshot.endlessscorpion.beans.ResponseBody;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by plaguemorin on 15/07/15.
 */
@RestController
@RequestMapping("/event")
public class EventEndpoint {
	private static final Logger LOG = LoggerFactory.getLogger(EventEndpoint.class);

	@Autowired
	private OAuthConsumer authConsumer;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TaskExecutor executor;

	@RequestMapping(method = RequestMethod.POST)
	public String eventPost(@RequestBody final String token) {
		LOG.info("token = {}", token);
		return "OK";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseBody eventGet(@RequestParam("url") final String url) {
		final ResponseBody responseBody = new ResponseBody();
		responseBody.setSuccess(true);

		// Process the event by getting the URL
		// Normally, I'd put this in a JMS queue and have another
		// worker process it so that we don't block the
		// reply to the server, but dependencies are a pain so I'll use
		// a simple task executor
		try {
			final String signedUrl = this.authConsumer.sign(url);
			final URI uri = new URI(signedUrl);
			LOG.info("Signed URL = {}", signedUrl);
			this.executor.execute(new Runnable() {
				@Override
				public void run() {
					final Event event = EventEndpoint.this.restTemplate.getForObject(uri, Event.class);

					LOG.info("Event Type = {}", event.getType());
				}
			});

		} catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException | URISyntaxException e) {
			LOG.error("Unable to sign message: {0}", e);
			responseBody.setSuccess(false);
			responseBody.setMessage(e.getMessage());
		}

		return responseBody;
	}
}
