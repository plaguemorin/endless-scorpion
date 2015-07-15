package ca.screenshot.endlessscorpion.beans;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * Created by plaguemorin on 11/07/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Order extends RemoteBean {
	@XmlEnum
	public enum OrderStatus {
		INITIALIZED, PENDING_REMOTE_CREATION, FREE_TRIAL, ACTIVE, FINISHED, ONE_TIME, CANCELLED, SUSPENDED, FREE_TRIAL_EXPIRED, FREE_TRIAL_CANCELLED, DELETED, FAILED, UPCOMING
	}


	@XmlElement
	private Date startDate;
	@XmlElement
	private Date endDate;
	@XmlElement
	private Date nextBillingDate;
	@XmlElement
	private OrderStatus status;
	@XmlElement
	private String frequency;
	@XmlElement
	private String type;

	private final Amount totalPrice = new Amount();

	@XmlElement
	private User user;
	@XmlElement
	private Company company;

	@XmlElementWrapper(name = "orderLines")
	@XmlElement(name = "orderLine")
	private List<OrderLine> orderLines;

	@XmlElement
	public double getTotalPrice() {
		return this.totalPrice.amount;
	}
	public void setTotalPrice(final double totalPrice) {
		this.totalPrice.amount = totalPrice;
	}

	@XmlElement
	public String getCurrency() {
		return this.totalPrice.currency;
	}
	public void setCurrency(final String currency) {
		this.totalPrice.currency = currency;
	}
}
