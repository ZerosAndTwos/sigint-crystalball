package ae.pegasus.framework.http;

import ae.pegasus.framework.errors.OperationResultError;
import org.apache.log4j.Logger;

public class OperationsResults {

    private static OperationResult operationResult;
    private static Logger logger = Logger.getLogger(OperationsResults.class);

    static void setResult(OperationResult result) {
        operationResult = result;
    }

    public static OperationResult getResult() {
        if (operationResult == null) {
            throw new OperationResultError("There are no operations results available!");
        }
        return operationResult;
    }

    public static void logError(OperationResult result) {
        logger.error("Code: " + result.getCode());
        logger.error("Message: " + result.getMessage());
    }
}
