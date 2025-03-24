package ee.taltech.inbankbackend.exceptions;

/**
 * Thrown when requested loan period is invalid.
 */
public class InvalidLoanPeriodException extends Throwable {

    public InvalidLoanPeriodException(String message) {
        super(message);
    }

    public InvalidLoanPeriodException(String message, Throwable cause) {
        super(message, cause);
    }
}
