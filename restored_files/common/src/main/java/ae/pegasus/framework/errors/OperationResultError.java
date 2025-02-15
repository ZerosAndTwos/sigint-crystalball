package ae.pegasus.framework.errors;

import ae.pegasus.framework.http.OperationResult;

public class OperationResultError extends AssertionError {

    public OperationResultError(String message) {
        super(message);
    }

    public OperationResultError(String message, Throwable e) {
        super(message, e);
    }

    public OperationResultError(OperationResult result) {
        super(String.format("%s code in response: \n%s", result.getCode(), result.getMessage()));
    }
}
