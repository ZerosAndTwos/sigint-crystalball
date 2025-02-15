package ae.pegasus.framework.controllers;

import ae.pegasus.framework.http.G4HttpClient;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.AuthRequest;
import ae.pegasus.framework.model.Classification;
import ae.pegasus.framework.model.DataSource;
import ae.pegasus.framework.model.RequestResult;
import ae.pegasus.framework.model.Token;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

import static ae.pegasus.framework.services.EntityService.g4HttpClient;

public class AuthService {

    private static AuthRequest request = new AuthRequest();
    private static final Logger log = Logger.getLogger(AuthService.class);

    OperationResult<Token> signIn(String name, String password) {
        G4Response response = g4HttpClient.sendRequest(request.signIn(name, password));
        return new OperationResult<>(response, Token.class);
    }

    OperationResult<RequestResult> signOut() {
        G4Response response = g4HttpClient.sendRequest(request.singOut());
        OperationResult<RequestResult> operationResult = new OperationResult<>(response, RequestResult.class);
        if (operationResult.isSuccess()) {
            G4HttpClient.removeCookie();
        }
        return operationResult;
    }

    public OperationResult<List<DataSource>> dataSources() {
        log.info("Get /api/auth/datasources");
        G4Response response = g4HttpClient.sendRequest(request.dataSources());

        OperationResult<DataSource[]> operationResult = new OperationResult<>(response, DataSource[].class);
        return operationResult.isSuccess() ?
                new OperationResult<>(response, Arrays.asList(operationResult.getEntity())) :
                new OperationResult<>(response);
    }

    public OperationResult<List<Classification>> classifications() {
        log.info("Get /api/auth/classifications");
        G4Response response = g4HttpClient.sendRequest(request.classifications());

        OperationResult<Classification[]> operationResult = new OperationResult<>(response, Classification[].class);
        return operationResult.isSuccess() ?
                new OperationResult<>(response, Arrays.asList(operationResult.getEntity())) :
                new OperationResult<>(response);
    }
}