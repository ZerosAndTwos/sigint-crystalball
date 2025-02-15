package http.requests.rfi;


import http.requests.HttpRequest;
import http.HttpMethod;
import json.JsonConverter;
import model.FileAttachment;
import model.InformationRequest;
import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RFIUploadRequest extends HttpRequest {

    private static final String URI = "/api/rfi/upload";
    private Logger log = Logger.getRootLogger();

    public RFIUploadRequest(InformationRequest entity) {
        super(URI);
        this.setHttpMethod(HttpMethod.POST);

        try {
            log.debug("Writing InformationRequest to json file...");
            File file = File.createTempFile("blob", ".json");
            JsonConverter.mapper.writeValue(file, entity);
            addBodyFile("json", file, MediaType.APPLICATION_JSON_TYPE);
            file.deleteOnExit();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new AssertionError("Failed to create json file");
        }

        List<FileAttachment> attachments = new ArrayList<>();
        if (entity.getApprovedCopy() != null) {
            attachments.add(entity.getApprovedCopy());
        }
        if (entity.getOriginalDocument() != null) {
            attachments.add(entity.getOriginalDocument());
        }

        for (FileAttachment attachment : attachments) {
            log.debug("Attaching files to request");
            addBodyFile(attachment.getTitle(), attachment.getFile(), MediaType.APPLICATION_OCTET_STREAM_TYPE);
            attachment.getFile().deleteOnExit();
        }
    }

}

