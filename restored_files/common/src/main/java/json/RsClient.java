package json;

import errors.NullReturnException;
import model.AppContext;
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
import java.util.Properties;

import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.*;

public class RsClient {

    public static Logger log = Logger.getRootLogger();
    private Client client;
    private Properties connectionProperties = AppContext.getContext().getJiraConnection();

    public RsClient(){
        initClient();
    }

    public Client client() {
        return client;
    }


    /**
     * Initialization JAX-RS client with HttpAuthenticationFeature.basic(user, pass)
     * <br>user and pass it properties from jiraConnection.properties
     */
    public void initClient(){
        String user = connectionProperties.getProperty("username");
        String pass = connectionProperties.getProperty("password");
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
     * @return JAX-RS client response
     */
    public Response get(String url){
        log.debug("Sending get request...");
        return buildRequest(url).get();
    }

    public Response get(String url, Cookie cookie) {
        log.debug("Sending get request");
        return buildRequest(url).cookie(cookie).get();
    }

    public Response get(String url, Cookie cookie, String mediaType) {
        log.debug("Sending get request");
        return buildRequest(url, mediaType).cookie(cookie).get();
    }
    
    public Response get(String url, String username, String password) {
        return buildRequest(url, username, password).get();
        
    }

    /**
     * HTTP PUT request
     *
     * @param url target URL
     * @return JAX-RS client response
     */
    public Response put(String url, Object object){
        Entity payload = convertToJson(object);
        log.debug("Sending PUT request with payload:"+ payload);
        return buildRequest(url).put(payload);
    }

    public Response put(String url, Object object, Cookie cookie) {
        Entity payload = convertToJson(object);
        log.debug("Sending PUT request with payload:"+ payload);
        return buildRequest(url).cookie(cookie).put(payload);
    }

    public Response put(String url, Object object, Cookie cookie, String mediaType) {
        Entity payload = convertToJson(object);
        log.debug("Sending PUT request with payload:"+ payload);
        return buildRequest(url, mediaType).cookie(cookie).put(payload);
    }

    /**
     * HTTP POST request
     *
     * @param url target URL
     * @param multiPart Jersey MultiPart for POST request
     * @return JAX-RS client response
     */
    public Response post(String url, MultiPart multiPart, Cookie cookie) {
        Entity payload = Entity.entity(multiPart, multiPart.getMediaType());
        log.debug("Sending POST request with Multipart payload:" + payload);
        return buildRequest(url).cookie(cookie).post(payload);
    }

    public Response post(String url, Cookie cookie) {
        Entity payload = convertToJson(null);
        log.debug("Sending POST request with payload:" + payload);
        return buildRequest(url).cookie(cookie).post(payload);
    }

    public Response post(String url, Object object) {
        Entity payload = convertToJson(object);
        log.debug("Sending POST request with payload:" + object);
        return buildRequest(url).post(payload);
    }

    public Response post(String url, Object object, Cookie cookie) {
        Entity payload = convertToJson(object);
        log.debug("Sending POST request with payload:" + object);
        return buildRequest(url).cookie(cookie).post(payload);
    }

    public Response post(String url, Object object, Cookie cookie, String mediaType) {
        Entity payload = convertToJson(object);
        log.debug("Sending POST request with payload:" + object);
        return buildRequest(url, mediaType).cookie(cookie).post(payload);
    }

    /**
     * HTTP DELETE request
     *
     * @param url target URL
     * @return JAX-RS client response
     */
    public Response delete(String url){
        log.debug("Sending Delete request");
        return buildRequest(url).delete();
    }

    public Response delete(String url, Cookie cookie) {
        log.debug("Sending Delete request");
        return buildRequest(url).cookie(cookie).delete();
    }


}
