package ca.screenshot.endlessscorpion.services;

import ca.screenshot.endlessscorpion.beans.event.Event;

/**
 * Created by plaguemorin on 15/07/15.
 * --
 */
public interface EventProcessService {
	void processEvent(final Event event);
}
