package ca.screenshot.endlessscorpion.controllers;

import ca.screenshot.endlessscorpion.remote.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by plaguemorin on 13/07/15.
 */
@RestController
public class RestEndpoint {
	private static final Logger LOG = LoggerFactory.getLogger(RestEndpoint.class);

	@Autowired
	private SubscriptionService subscriptionService;

	@RequestMapping("/")
	public String index() {
		LOG.info("Index requested");
		return "Hello World";
	}

	@RequestMapping(name = "/event", method = RequestMethod.POST)
	public String event(@RequestBody final String token) {
		LOG.info("token = {}", token);
		return "OK";
	}
}
