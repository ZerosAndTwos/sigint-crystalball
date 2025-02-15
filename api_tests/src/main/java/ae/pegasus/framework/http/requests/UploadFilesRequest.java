package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.*;

import javax.ws.rs.core.MediaType;
import java.util.Objects;

import static ae.pegasus.framework.utils.StringUtils.stringContainsAny;

public class UploadFilesRequest extends HttpRequest {

    private final static String URI = "/api/upload-sigint/files";

    public UploadFilesRequest() {
        super(URI);
        // API works only with specified Content-Type
        this.setMediaType(PegasusMediaType.PEGASUS_JSON_V1);
    }

    /**
     * GET meta of uploaded file
     * API: GET /api/upload/files/{id}/meta
     *
     * @param id id of uploaded file
     * @return GET meta of uploaded file request
     */
    public UploadFilesRequest meta(String id) {
        setURI(URI + "/" + id + "/meta")
                .setHttpMethod(HttpMethod.GET) ;

        return this;
    }
    
    /**
     * Send POST request to notify sigint that file is uploaded
     * @param fileMeta {@link FileMeta} of the file to be notified
     * @return formed notify request
     */
    public UploadFilesRequest notify(FileMeta fileMeta) {
        setURI("/api/sigint/upload/notify")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(fileMeta);
        
        return this;
    }

    /**
     * Multipart UPLOAD file request
     * API: POST /api/upload/files
     *
     * @param file file for upload;
     * @param source of file;
     * @param ownerId of file;
     * @return UploadFilesRequest
     */
    public UploadFilesRequest upload(G4File file, Source source, String ownerId, String remotePath) {
        FileMeta fileMeta = initFileMeta(file, source, ownerId, remotePath);
        String meta = JsonConverter.toJsonString(fileMeta);
        addBodyFile("file", file, MediaType.APPLICATION_JSON_TYPE);
        addBodyString("meta", meta);

        this.setURI(URI).setHttpMethod(HttpMethod.POST);

        return this;
    }

    /**
     * Generate meta entity for uploaded file
     *
     * @param file file for upload
     * @return FileMeta model for uploaded file
     */
    private FileMeta initFileMeta(G4File file, Source source, String ownerId, String remotePath) {
        String name;
        /*  Meta file contains relative paths of audio files.
            Place audio files into parentRemotePath subfolder to bind them together */
        String filename = file.getName();
        if ((stringContainsAny(filename, ".wav")) && (Objects.equals(source.getType(), "T"))) {
            name = remotePath + file.getParentFile().getName() + "/" + filename;
        } else {
            name = remotePath + filename;
        }

        FileMeta fileMeta = new FileMeta();
        fileMeta.setName(name);
        fileMeta.setOwner(ownerId);
        fileMeta.setSourceId(source.getId());
        Meta meta = new Meta();
        MetaProperties properties = new MetaProperties();
        properties.setSubSourceType(source.getRecordType());
        properties.setDataSourceType(source.getType());
        meta.setProperties(properties);
        fileMeta.setMeta(meta);
        fileMeta.setType(file.getMediaType().toString());

        return fileMeta;
    }

    public UploadFilesRequest search(FileMetaFilter filter) {
        this
            .setURI(URI + "/_search")
            .setHttpMethod(HttpMethod.POST)
            .setPayload(filter);

        return this;
    }

    public UploadFilesRequest count(FileMetaFilter filter) {
        this
            .setURI(URI + "/_count")
            .setHttpMethod(HttpMethod.POST)
            .setPayload(filter);

        return this;
    }

    public UploadFilesRequest getContent(String fileId) {
        this
                .setURI(URI + "/" + fileId + "/content")
                .setHttpMethod(HttpMethod.GET)
                .setMediaType(MediaType.APPLICATION_OCTET_STREAM);
        return this;
    }
}
