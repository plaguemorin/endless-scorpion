package ca.screenshot.endlessscorpion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by plaguemorin on 15/07/15.
 *
 */
@RestController
@RequestMapping("/event")
public class EventEndpoint {
	private static final Logger LOG = LoggerFactory.getLogger(RestEndpoint.class);

	@RequestMapping(method = RequestMethod.POST)
	public String eventPost(@RequestBody final String token) {
		LOG.info("token = {}", token);
		return "OK";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String eventGet(@RequestParam("url") final String url) {
		LOG.info("URL = {}", url);
		return "OK";
	}
}
