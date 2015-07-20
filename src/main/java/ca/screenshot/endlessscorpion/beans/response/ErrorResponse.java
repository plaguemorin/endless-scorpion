package ca.screenshot.endlessscorpion.beans.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by plaguemorin on 15/07/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ErrorResponse extends ResponseBody {
	public ErrorResponse(final String message) {
		setSuccess(false);
		setMessage(message);
	}
}
