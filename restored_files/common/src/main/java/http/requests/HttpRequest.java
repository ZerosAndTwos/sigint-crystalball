package http.requests;

import http.HttpMethod;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.core.MediaType;
import java.io.File;

/**
 * Http request model.
 */
@JsonIgnoreProperties(value = {"URI", "context", "httpMethod", "mediaType", "payload"})
public class HttpRequest {

    private static final Logger log = Logger.getLogger(HttpRequest.class);
    private String URI; // URL path
    private HttpMethod httpMethod; // HTTP request httpMethod, such as: GET/PUT/POST/DELETE
    private String mediaType;
    private Object payload;
    private MultiPart multiPart;

    /**
     * Build HTTP request.
     * By Default: HttpMethod = GET, MediaType = APPLICATION_JSON.
     *
     * @param URI path string
     */
    public HttpRequest(String URI) {
        this.URI = URI;
        httpMethod = HttpMethod.GET;
        mediaType = MediaType.APPLICATION_JSON;
        cleanMultiPart();
    }

    /**
     * Clean Miltipart payload to avoid problems with static instance reuse for upload
     */
    public void cleanMultiPart() {
        multiPart = new MultiPart();
        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
    }

    public String getURI() {
        return URI;
    }

    public HttpRequest setURI(String URI) {
        this.URI = URI;
        return this;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public HttpRequest setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public String getMediaType() {
        return mediaType;
    }

    public HttpRequest setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public Object getPayload() {
        return payload;
    }

    public HttpRequest setPayload(Object payload) {
        this.payload = payload;
        return this;
    }

    /**
     * Add file to Miltipart payload
     *
     * @param name name of FileDataBodyPart
     * @param file file entity
     * @param type MediaType of file
     * @return HttpRequest
     */
    protected HttpRequest addBodyFile(String name, File file, MediaType type) {
        log.debug("Adding file " + file.getName() + " to multipart body...");

        FileDataBodyPart filePart = new FileDataBodyPart(name, file, type);
        multiPart.bodyPart(filePart);
        setPayload(multiPart);

        return this;
    }

    /**
     * Add string field to Miltipart payload
     *
     * @param fieldName field name
     * @param value     string value
     * @return HttpRequest
     */
    protected HttpRequest addBodyString(String fieldName, String value) {
        log.debug("Adding string to multipart body: " + value);

        FormDataBodyPart part = new FormDataBodyPart(fieldName, value);
        multiPart.bodyPart(part);
        setPayload(multiPart);

        return this;
    }
}
