package ca.screenshot.endlessscorpion.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by plaguemorin on 15/07/15.
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseBody {
	@XmlElement
	private boolean success;
	@XmlElement
	private String message;
	@XmlElement
	private String accountIdentifier;

	public boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccountIdentifier() {
		return this.accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
}
