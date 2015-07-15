package ca.screenshot.endlessscorpion.services;

/**
 * Created by plaguemorin on 15/07/15.
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = 5488046623051581035L;

	public ServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
