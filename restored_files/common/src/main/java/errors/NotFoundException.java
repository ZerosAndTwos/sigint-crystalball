package errors;

public class NotFoundException extends Exception {


    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message, Throwable e) {
        super(message, e);
    }
}
