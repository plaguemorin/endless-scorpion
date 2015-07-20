package ca.screenshot.endlessscorpion.services;

import ca.screenshot.endlessscorpion.beans.event.Event;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by plaguemorin on 15/07/15.
 * <p/>

 * Process the event by `getting` the URL
 *
 * Normally, I'd put this in a JMS queue and have another
 * worker process it so that we don't block the
 * reply to the server, but dependencies are a pain so I'll use
 * a simple task executor.
 */
public class SimpleTaskExecutorEventDispatchService implements EventDispatchService {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleTaskExecutorEventDispatchService.class);

	@Autowired
	private OAuthConsumer authConsumer;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TaskExecutor executor;

	@Autowired
	private EventProcessService eventProcessService;

	@Override
	public void doSomething(final String url) throws ServiceException {
		try {
			final String signedUrl = this.authConsumer.sign(url);
			final URI uri = new URI(signedUrl);
			LOG.info("Signed URL = {}", signedUrl);
			this.executor.execute(new Runnable() {
				@Override
				public void run() {
					SimpleTaskExecutorEventDispatchService.this.eventProcessService.processEvent(
							SimpleTaskExecutorEventDispatchService.this.restTemplate.getForObject(uri, Event.class)
					);
				}
			});
		} catch (OAuthExpectationFailedException | OAuthCommunicationException | URISyntaxException | OAuthMessageSignerException e) {
			// Yes, I'm putting everything into one super exception. Deal with it.
			throw new ServiceException("Unable to process event", e);
		}
	}
}
