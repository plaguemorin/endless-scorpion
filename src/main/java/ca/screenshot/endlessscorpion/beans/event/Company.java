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
public class Company {
	@XmlElement
	private String country;

	@XmlElement
	private String email;

	@XmlElement
	private String name;

	@XmlElement
	private String phoneNumber;

	@XmlElement
	private String uuid;

	@XmlElement
	private String website;

	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUuid() {
		return this.uuid;
	}

	private void setUuid(final String uuid) {
		this.uuid = uuid;
	}

	private String getWebsite() {
		return this.website;
	}

	private void setWebsite(final String website) {
		this.website = website;
	}
}
