package ca.screenshot.endlessscorpion.beans.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by plaguemorin on 20/07/15.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class Account {
	@XmlElement
	private String accountIdentifier;

	// TODO: this should probably be an ENUM, can't find it in the docs however
	@XmlElement
	private String status;

	public String getAccountIdentifier() {
		return this.accountIdentifier;
	}

	public void setAccountIdentifier(final String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}
}
