package ee.taltech.inbankbackend.exceptions;

/**
 * Thrown when no valid loan is found.
 */
public class NoValidLoanException extends Throwable {

    public NoValidLoanException(String message) {
        super(message);
    }

    public NoValidLoanException(String message, Throwable cause) {
        super(message, cause);
    }
}
