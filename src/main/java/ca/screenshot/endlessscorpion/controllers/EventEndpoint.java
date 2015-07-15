package ca.screenshot.endlessscorpion.controllers;

import ca.screenshot.endlessscorpion.beans.response.ErrorResponse;
import ca.screenshot.endlessscorpion.beans.response.ResponseBody;
import ca.screenshot.endlessscorpion.beans.response.SimpleSuccessResponse;
import ca.screenshot.endlessscorpion.services.EventDispatchService;
import ca.screenshot.endlessscorpion.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by plaguemorin on 15/07/15.
 * <p/>
 */
@RestController
@RequestMapping("/event")
public class EventEndpoint {
	private static final Logger LOG = LoggerFactory.getLogger(EventEndpoint.class);

	@Autowired
	private EventDispatchService eventDispatchService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseBody eventGet(@RequestParam("url") final String url) {
		ResponseBody response;

		try {
			this.eventDispatchService.doSomething(url);
			response = new SimpleSuccessResponse();
		} catch (final ServiceException e) {
			LOG.error("Unable to sign message: {0}", e);
			response = new ErrorResponse(e.getMessage());
		}

		return response;
	}
}
