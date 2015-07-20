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
public class Marketplace {
	@XmlElement
	private String baseUrl;

	@XmlElement
	private String partner;

	public String getBaseUrl() {
		return this.baseUrl;
	}

	public void setBaseUrl(final String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getPartner() {
		return this.partner;
	}

	public void setPartner(final String partner) {
		this.partner = partner;
	}
}
