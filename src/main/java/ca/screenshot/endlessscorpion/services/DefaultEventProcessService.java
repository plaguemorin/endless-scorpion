package ca.screenshot.endlessscorpion.services;

import ca.screenshot.endlessscorpion.beans.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by plaguemorin on 15/07/15.
 */
public class DefaultEventProcessService implements EventProcessService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultEventProcessService.class);

	@Override
	public void processEvent(final Event event) {
		LOG.info("Event Type = {}", event.getType());
	}
}
