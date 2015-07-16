package ca.screenshot.endlessscorpion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by plaguemorin on 13/07/15.
 */
@RestController
public class RestEndpoint {
	private static final Logger LOG = LoggerFactory.getLogger(RestEndpoint.class);

	@RequestMapping("/")
	public String index() {
		LOG.info("Index requested");
		return "Hello World";
	}
}
