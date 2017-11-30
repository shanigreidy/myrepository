package il.ac.hit.model;

/**
 * class FinancialManagementException represents the project Exception
 */

public class FinancialManagementException extends Exception
{
	/**
	 * 
	 * @param msg, the exception message
	 * @param rootcause, Throwable object
	 */
	FinancialManagementException(String msg, Throwable rootcause) {
		super(msg, rootcause);
	}
/**
 * 
 * @param msg, the exception message
 */
	FinancialManagementException(String msg) {
		super(msg);
	}
}
