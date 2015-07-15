package ca.screenshot.endlessscorpion.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by plaguemorin on 11/07/15.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class Subscription extends RemoteBean {
	@XmlEnum
	public enum SubscriptionStatus {
		ACTIVE, SUSPENDED, CANCELLED, FREE_TRIAL, FREE_TRIAL_EXPIRED
	}

	@XmlElement
	private Date creationDate;
	@XmlElement
	private Date endDate;
	@XmlElement(name = "externalAccountIdentifier")
	private String externalAccountId;
	@XmlElement
	private SubscriptionStatus status;
	@XmlElement(required = false)
	private Long maxUsers;

	@XmlElement(required = false)
	private User user;
	@XmlElement(required = false)
	private Company company;
	@XmlElement(required = false)
	private Product product;
	@XmlElement(required = false)
	private Order order;

}
