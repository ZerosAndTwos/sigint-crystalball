package errors;

/**
 * Created by dm on 4/7/16.
 */
public class NullReturnException extends Exception {

    public NullReturnException(String message) {

        super(message);
    }

    public NullReturnException(String message, Throwable throwable) {

        super(message, throwable);
    }

    public NullReturnException(Throwable throwable) {
        super(throwable);
    }
}
