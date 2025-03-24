package ee.taltech.inbankbackend.exceptions;

/**
 * Thrown when requested loan amount is invalid.
 */
public class InvalidLoanAmountException extends Throwable {


    public InvalidLoanAmountException(String message) {
        super(message);
    }

    public InvalidLoanAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    /*
    @Override
    public Throwable getCause() {
        return cause;
    }

    @Override
    public String getMessage() {
        return message;
    }
     */

}
