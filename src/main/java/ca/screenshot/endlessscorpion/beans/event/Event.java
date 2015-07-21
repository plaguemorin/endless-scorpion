package ca.screenshot.endlessscorpion.beans.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by plaguemorin on 15/07/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Event {


	@XmlEnum
	public enum EventType {
		SUBSCRIPTION_ORDER, SUBSCRIPTION_CHANGE, SUBSCRIPTION_CANCEL, SUBSCRIPTION_NOTICE, USER_ASSIGNMENT, USER_UNASSIGNMENT
	}

	@XmlEnum
	public enum EventFlag {
		STATELESS, DEVELOPMENT
	}

	@XmlElement
	private EventType type;
	@XmlElement
	private EventFlag flag;
	@XmlElement
	private Marketplace marketplace;
	@XmlElement
	private Creator creator;
	@XmlElement
	private Payload payload;
	@XmlElement
	private String returnUrl;

	public EventType getType() {
		return this.type;
	}

	public boolean isStateless() {
		return this.flag == EventFlag.STATELESS;
	}

	public boolean isDevelopment() {
		return this.flag == EventFlag.DEVELOPMENT;
	}

	public boolean isRealProductionEvent() {
		return !this.isStateless() && !this.isDevelopment();
	}

	public Payload getPayload() {
		return this.payload;
	}


}
