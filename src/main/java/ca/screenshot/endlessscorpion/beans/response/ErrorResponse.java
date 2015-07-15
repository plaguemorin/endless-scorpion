package ca.screenshot.endlessscorpion.beans.response;

/**
 * Created by plaguemorin on 15/07/15.
 */
public class ErrorResponse extends ResponseBody {
	public ErrorResponse(final String message) {
		setSuccess(false);
		setMessage(message);
	}
}
