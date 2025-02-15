package ae.pegasus.framework.services;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4HttpClient;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.UploadFilesRequest;
import ae.pegasus.framework.http.requests.UploadRequest;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.Process;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static ae.pegasus.framework.http.G4HttpClient.Protocol.HTTP;

public class UploadFilesService {

    private static final Logger log = Logger.getLogger(UploadFilesService.class);
    private static G4HttpClient g4HttpClient = new G4HttpClient(G4Properties.getRunProperties().getApplicationURL(), HTTP);
    private static UploadFilesRequest uploadFilesRequest = new UploadFilesRequest();
    private static UploadRequest uploadRequest = new UploadRequest();

    /**
     * Multipart UPLOAD file
     * API: POST /api/upload/files
     *
     * @param file G4file (File with MediaType field)
     * @return {@link OperationResult}
     */
    public OperationResult<FileMeta> upload(G4File file, Source source, String ownerId, String remotePath) {
        log.info("Uploading file " + file.getName() + " with 'meta' string");

        G4Response uploadResponse = g4HttpClient.sendRequest(uploadFilesRequest.upload(file, source, ownerId, remotePath));

        return new OperationResult<>(uploadResponse, FileMeta.class);
    }

    /**
     * GET meta of uploaded file
     * API: GET /api/upload/files/{id}/meta
     *
     * @param id id of uploaded file
     * @return meta of uploaded file
     */
    public OperationResult<FileMeta> meta(String id) {
        log.info("Get Meta of uploaded file id:" + id);
        G4Response response = g4HttpClient.sendRequest(uploadFilesRequest.meta(id));

        return new OperationResult<>(response, FileMeta.class);
    }

    /**
     * GET upload details of uploaded file
     * API: GET /api/sigint/upload/{id}/details
     *
     * @param id file id
     * @return upload details model
     */
    public OperationResult<UploadDetails> details(String id) {
        log.info("Get UploadDetails of uploaded file id:" + id);
        G4Response response = g4HttpClient.sendRequest(uploadRequest.details(id));

        return new OperationResult<>(response, UploadDetails.class);
    }

    /**
     * Search list of process details uploaded files
     *
     * @param filter search filter
     * @return List of process details uploaded files
     */
    public List<Process> search(SearchFilter filter) {
        log.info("Get Ingestion History list..");
        G4Response response = g4HttpClient.sendRequest(uploadRequest.search(filter));

        OperationResult<UploadSearchResult> operationResult = new OperationResult<>(response, UploadSearchResult.class);
        if (operationResult.isSuccess() && operationResult.getEntity() != null) {
            return operationResult.getEntity().getResult();
        } else {
            log.error("Unable to read search results from Upload search");
            throw new OperationResultError(operationResult);
        }
    }

    public List<FileMeta> searchDataFiles(FileMetaFilter filter) {
        log.info("Search Data Files..");
        G4Response response = g4HttpClient.sendRequest(uploadFilesRequest.search(filter));

        OperationResult<FileMeta[]> operationResult = new OperationResult<>(response, FileMeta[].class);
        if (operationResult.isSuccess() && operationResult.getEntity() != null) {
            return Arrays.asList(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    public OperationResult<Integer> countFiles(FileMetaFilter filter) {
        log.info("Count Data Files..");
        G4Response response = g4HttpClient.sendRequest(uploadFilesRequest.count(filter));

        return new OperationResult<>(response, Integer.class);
    }

    public OperationResult<File> getContent(String fileId)  {
        G4Response response = g4HttpClient.sendRequest(uploadFilesRequest.getContent(fileId));
        OperationResult<File> result = new OperationResult<>(response, File.class);
        if (result.isSuccess()) {
            return result;
        } else {
            throw  new OperationResultError(result);
        }
    }
}
