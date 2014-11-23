package mta.se.tema6.mvc.exceptions;

/**
 * Created by Scurtu Petru on 22/11/2014.
 * 
 * This class implements the exception thrown when the format of the input is
 * inappropriate
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor for the input exception
	 * 
	 * @param input
	 *            The input on witch the exception was obtained
	 * @param errorMessage
	 *            The default exception message
	 */
	public CustomException(String input, String errorMessage) {
		super("Input Format exception on \"" + input + "\" : " + errorMessage);
	}


}
