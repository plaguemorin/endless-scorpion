package ca.screenshot.endlessscorpion.beans.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by plaguemorin on 15/07/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Creator {
	@XmlElement
	private String uuid;

	@XmlElement
	private String email;

	@XmlElement
	private String firstName;

	@XmlElement
	private String lastName;

	@XmlElement
	private String language;

	@XmlElement
	private String openId;
}
