package ae.pegasus.framework.errors;

public class VerificationError extends AssertionError {
    
    public VerificationError(String message) {
        super(message);
    }
}
