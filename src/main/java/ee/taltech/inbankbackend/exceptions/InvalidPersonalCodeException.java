package ee.taltech.inbankbackend.exceptions;

/**
 * Thrown when provided personal ID code is invalid.
 */
public class InvalidPersonalCodeException extends Throwable {

    public InvalidPersonalCodeException(String message) {
        super(message);
    }

    public InvalidPersonalCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
