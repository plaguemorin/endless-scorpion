package ca.screenshot.endlessscorpion.beans.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by plaguemorin on 20/07/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class OrderItem {
	@XmlElement
	private Long quantity;

	@XmlElement
	private String unit;
}
