package ae.pegasus.framework.http;

import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.json.JsonConverter;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OperationResult<T> {

    private Logger log = Logger.getLogger(OperationResult.class);
    private static final List<Integer> successfulCodes = new ArrayList<>();

    static {
        successfulCodes.add(200);
        successfulCodes.add(201);
        successfulCodes.add(204);
    }

    private G4Response response;
    private T responseObject;
    private Class<T> objectTypeClass;
    private String wrappedField;

    public OperationResult(G4Response response, Class<T> resultCastType) {
        this.response = response;
        this.objectTypeClass = resultCastType;
        OperationsResults.setResult(this);
    }

    public OperationResult(G4Response response, Class<T> resultCastType, String wrappedField) {
        this.response = response;
        this.objectTypeClass = resultCastType;
        this.wrappedField = wrappedField;
        OperationsResults.setResult(this);
    }

    public OperationResult(G4Response response) {
        this.response = response;
        OperationsResults.setResult(this);
    }

    public OperationResult(G4Response response, T responseObject) {
        this.response = response;
        this.responseObject = responseObject;
        OperationsResults.setResult(this);
    }

    private T getObject(Class<T> castType) {
        try {
            if (wrappedField == null) {
                responseObject = JsonConverter.jsonToObject(response.getMessage(), castType);
            } else {
                responseObject = JsonConverter.jsonToObject(response.getMessage(), castType, wrappedField);
            }
            return responseObject;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OperationResultError("Cannot get result of type: " + castType, e);
        }
    }

    public boolean isSuccess() {
        return successfulCodes.contains(response.getCode());
    }

    public T getEntity() {
        if (responseObject == null) {
            responseObject = getObject(objectTypeClass);
        }
        return responseObject;
    }

    public String getMessage() {
        return response.getMessage();
    }

    public int getCode() {
        return response.getCode();
    }
}
