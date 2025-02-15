package http.client;

import app_context.properties.G4Properties;
import errors.NullReturnException;
import http.G4Response;
import json.JsonCoverter;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD;
import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME;

public class G4Client {

    private static Logger log = Logger.getLogger(G4Client.class);
    private Client client;

    /**
     * Initialization JAX-RS client with HttpAuthenticationFeature.basic(user, pass)
     * <br>user and pass properties defined in the jiraConnection.properties
     */
    public G4Client(){
        String user = G4Properties.getJiraProperties().getUsername();
        String pass = G4Properties.getJiraProperties().getPassword();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(user, pass);
        client = ClientBuilder.newClient();
        client.register(feature);
        client.register(MultiPartFeature.class);
    }


    private Invocation.Builder buildRequest(String url) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
            throw new AssertionError("Request cannot be built");
        }
        log.debug("Building request to url:" + uri);
        return client
                .target(uri)
                .request(MediaType.APPLICATION_JSON_TYPE);
    }

    private Invocation.Builder buildRequest(String url, String mediaType) {
        log.debug("Building request to url:" + url);
        return client
                .target(url)
                .request(mediaType);
    }
    
    private Invocation.Builder buildRequest(String url, String username, String password) {
        log.debug("Building request to url: + url");
        return client.target(url)
                .request()
                .property(HTTP_AUTHENTICATION_BASIC_USERNAME, username)
                .property(HTTP_AUTHENTICATION_BASIC_PASSWORD, password);
    }

    private Entity convertToJson(Object object) {
        Entity payload;
        try {
            payload = Entity.json(JsonCoverter.toJsonString(object));
        } catch (NullReturnException e) {
            throw new AssertionError("Cannot convert payload object to JSON");
        }
        return payload;
    }

    /**
     * HTTP GET request
     *
     * @param url target URL
     * @return javax.ws.rs.core.Response
     */
    public G4Response get(String url){
        log.debug("Sending GET request...");
        Response response = buildRequest(url).get();
        return new G4Response(response);
    }

    /**
     * HTTP GET request
     *
     * @param url target URL
     * @param cookie cookie
     * @return javax.ws.rs.core.Response
     */
    public G4Response get(String url, Cookie cookie) {
        log.debug("Sending GET request");
        Response response = buildRequest(url).cookie(cookie).get();
        return new G4Response(response);
    }

    /**
     /**
     * HTTP GET request
     *
     * @param url target URL
     * @param cookie cookie
     * @param mediaType mediaType
     * @return javax.ws.rs.core.Response
     */
    public G4Response get(String url, Cookie cookie, String mediaType) {
        log.debug("Sending get request");
        Response response = buildRequest(url, mediaType).cookie(cookie).get();
        return new G4Response(response);
    }
    
    public G4Response get(String url, String username, String password) {
        Response response = buildRequest(url, username, password).get();
        return new G4Response(response);
    }

    /**
     * HTTP PUT request
     *
     * @param url target URL
     * @return javax.ws.rs.core.Response
     */
    public G4Response put(String url, Object object){
        Entity payload = convertToJson(object);
        log.debug("Sending PUT request with payload: " + payload);
        Response response = buildRequest(url).put(payload);
        return new G4Response(response);
    }

    public G4Response put(String url, Object object, Cookie cookie) {
        Entity payload = convertToJson(object);
        log.debug("Sending PUT request with payload: " + payload);
        Response response = buildRequest(url).cookie(cookie).put(payload);
        return new G4Response(response);
    }

    public G4Response put(String url, Object object, Cookie cookie, String mediaType) {
        Entity payload = convertToJson(object);
        log.debug("Sending PUT request with payload:"+ payload);
        Response response = buildRequest(url, mediaType).cookie(cookie).put(payload);
        return new G4Response(response);
    }

    /**
     * HTTP POST request
     *
     * @param url target URL
     * @param multiPart Jersey MultiPart for POST request
     * @return javax.ws.rs.core.Response
     */
    public G4Response post(String url, MultiPart multiPart, Cookie cookie) {
        Entity payload = Entity.entity(multiPart, multiPart.getMediaType());
        log.debug("Sending POST request with Multipart payload:" + payload);
        Response response = buildRequest(url).cookie(cookie).post(payload);
        return new G4Response(response);
    }

    public G4Response post(String url, Cookie cookie) {
        Entity payload = convertToJson(null);
        log.debug("Sending POST request with payload:" + payload);
        Response response = buildRequest(url).cookie(cookie).post(payload);
        return new G4Response(response);
    }

    public G4Response post(String url, Object object) {
        Entity payload = convertToJson(object);
        log.debug("Sending POST request with payload:" + object);
        Response response = buildRequest(url).post(payload);
        return new G4Response(response);
    }

    public G4Response post(String url, Object object, Cookie cookie) {
        Entity payload = convertToJson(object);
        log.debug("Sending POST request with payload:" + object);
        Response response = buildRequest(url).cookie(cookie).post(payload);
        return new G4Response(response);
    }

    public G4Response post(String url, Object object, Cookie cookie, String mediaType) {
        Entity payload = convertToJson(object);
        log.debug("Sending POST request with payload:" + object);
        Response response = buildRequest(url, mediaType).cookie(cookie).post(payload);
        return new G4Response(response);
    }

    /**
     * HTTP DELETE request
     *
     * @param url target URL
     * @return javax.ws.rs.core.Response
     */
    public G4Response delete(String url){
        log.debug("Sending Delete request");
        Response response = buildRequest(url).delete();
        return new G4Response(response);
    }

    /**
     * HTTP DELETE request
     *
     * @param url target URL
     * @param cookie cookie
     * @return javax.ws.rs.core.Response
     */
    public G4Response delete(String url, Cookie cookie) {
        log.debug("Sending Delete request");
        Response response = buildRequest(url).cookie(cookie).delete();
        return new G4Response(response);
    }


}
