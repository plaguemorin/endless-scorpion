package ca.screenshot.endlessscorpion.beans;

import javax.xml.bind.annotation.*;

/**
 * Created by plaguemorin on 12/07/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class OrderLine {
	@XmlEnum
	public enum OrderLineType { ITEM, TAX, DISCOUNT, PENALTY };

	@XmlElement
	private OrderLineType type;

	@XmlElement
	private String unit;

	@XmlElement
	private long quantity;

	@XmlElement
	private double price;

	@XmlElement
	private Double percentage;

	@XmlElement
	private double totalPrice;

	@XmlElement
	private String description;
}
