package ae.pegasus.framework.steps;

import ae.pegasus.framework.conditions.Conditions;
import ae.pegasus.framework.conditions.Verify;
import ae.pegasus.framework.error_reporter.ErrorReporter;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.Process;
import ae.pegasus.framework.services.RecordService;
import ae.pegasus.framework.services.UploadFilesService;
import ae.pegasus.framework.utils.DateHelper;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ae.pegasus.framework.utils.StringUtils.splitToArray;
import static ae.pegasus.framework.utils.StringUtils.stringContainsAny;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class APIUploadFilesSteps extends APISteps {

    private UploadFilesService service = new UploadFilesService();
    private static final int WAITING_TIME = 6 * 60; // in seconds
    private static final int POLLING_INTERVAL = 20; // in seconds


    private void uploadFiles(final String key, List<File> files) {
        if (files.isEmpty()) {
            log.error("No files for uploading");
        }

        Source source = context.get("source", Source.class);
        LoggedUser user = appContext.getLoggedUser();
        String remotePath = context.get("remotePath", String.class);

        List<FileMeta> fileMetas = new ArrayList<>();
        for (File file: files) {
            G4File g4file = new G4File(file.getPath());
            g4file.setMediaTypeByFileExtension();

            OperationResult<FileMeta> uploadResult = service.upload(g4file, source, user.getId(), remotePath);
            fileMetas.add(uploadResult.getEntity());
            context.put("meta", uploadResult.getEntity());
            if (!uploadResult.isSuccess()) {
                log.error("Can't upload " + file.getName());
                break;
            }
        }
        context.put(key, fileMetas);
    }

    @When("I upload files")
    public void uploadFiles() {
        List<File> files = context.get("g4files", List.class);
        uploadFiles("fileMetas", files);
    }

    @Then("Uploaded files are processed")
    public void uploadedFilesAreProcessed() {
        List<FileMeta> fileMetas = context.get("fileMetas", List.class);
        checkFilesProcessing(fileMetas);
    }

    @Then("Uploaded files are processed, $criteria: $values")
    public void uploadedFilesAreProcessedwithCriteria(String criteria, String values) {
        List<FileMeta> fileMetas = context.get("fileMetas", List.class);
        String[] extensions = splitToArray(values);
        final boolean condition = criteria.equalsIgnoreCase("include");

        List<FileMeta> filtered = fileMetas.stream()
                .filter(fm -> condition == stringContainsAny(fm.getExtension(), extensions))
                .collect(Collectors.toList());

        log.debug("File list size after filtering: " + filtered.size());
        checkFilesProcessing(filtered);
    }

    private void checkFilesProcessing(List<FileMeta> fileMetas) {
        DateHelper.setStartTime();
        for (FileMeta uploadedFileMeta: fileMetas) {
            context.put("meta", uploadedFileMeta);
            fileIsProcessed();
        }
        log.info(String.format("Files processing time: %d seconds", DateHelper.getDuration()));
    }

    @When("I send get file meta request")
    public void sendMetaRequest() {
        FileMeta fileMeta = context.get("meta", FileMeta.class);
        String fileId = fileMeta.getId();
        service.meta(fileId);
    }

    @Then("Uploaded file is processed")
    public void fileIsProcessed() {
        String fileProcesingError = String.format(
            "Uploaded file is not processed during %d seconds", WAITING_TIME);

        // if file isn't processed
        // wait, update meta and check again
        Date deadline = DateHelper.getDateWithShift(WAITING_TIME);
        FileMeta fileMeta = context.get("meta", FileMeta.class);
        String fileId = fileMeta.getId();
        Boolean fileProcessed = isProcessed(fileId);
        while (!fileProcessed && !isTimeout(deadline)) {
            DateHelper.waitTime(POLLING_INTERVAL);
            fileProcessed = isProcessed(fileId);
        }
        if (!fileProcessed) {
            ErrorReporter.reportAndRaiseError(fileProcesingError);
        }
    }

    private boolean isTimeout(Date deadline) {
        return DateHelper.isTimeout(deadline);
    }

    private boolean isProcessed(String fileId) {
        OperationResult<FileMeta> fileMetaOperationResult = service.meta(fileId);
        if (!fileMetaOperationResult.isSuccess()) {
            log.error(fileMetaOperationResult.getMessage());
            ErrorReporter.reportAndRaiseError("Can't get meta details about file");
        }
        FileMeta fileMeta = fileMetaOperationResult.getEntity();

        if (fileMeta != null && fileMeta.getMeta() != null) {
            MetaProperties properties = fileMeta.getMeta().getProperties();
            if (properties.getError() != null && !properties.getError().isEmpty()) {
                ErrorReporter.reportAndRaiseError("Uploaded file MetaProperties: " + JsonConverter.toJsonString(properties));
            }
            // FileMeta.etag == Process.md5
            String filename = fileMeta.getFile() + fileMeta.getExtension();
            String processId = fileMeta.getMeta().getProperties().getProcessId();
            log.info(String.format("%s processed: %s", filename, fileMeta.getMeta().getIsProcessed()));
            if (fileMeta.getEtag().length() > 0 & fileMeta.getMeta().getIsProcessed()) {
                if (processId != null) {
                    log.info(String.format("%s processId: %s", filename, processId));
                }
                context.put("meta", fileMeta);
                return true;
            }
        }
        return false;
    }

    @Then("$rCount records are ingested")
    public void waitForIngestMatchingComplete(String rCount) {
        int recordsCount = Integer.valueOf(rCount);
        String ingestionMatchingError = String.format(
            "Ingest matching was not completed during %d seconds", WAITING_TIME);
        DateHelper.setStartTime();
        // if target matching isn't complete
        // wait, update meta and check again
        Date deadline = DateHelper.getDateWithShift(WAITING_TIME);
        boolean ingestCompleted = isIngestMatchingComplete(recordsCount);
        while (!ingestCompleted && !isTimeout(deadline)) {
            log.info("Ingest isn't processed yet");
            DateHelper.waitTime(POLLING_INTERVAL);
            ingestCompleted = isIngestMatchingComplete(recordsCount);
        }
        if (!ingestCompleted) {
            ErrorReporter.reportAndRaiseError(ingestionMatchingError);
        }
        log.info(String.format("Ingestion time: %d seconds", DateHelper.getDuration()));
    }

    private boolean isIngestMatchingComplete(int recordsCount) {
        // data range for Upload history filter
        Date minDate = DateHelper.getDateWithShift(-WAITING_TIME);

        // get upload history list
        UploadFilter filter = new UploadFilter().setMinCreatedDate(minDate);
        List<Process> processList = service.search(filter);

        // find uploaded file in history list
        FileMeta fileMeta = context.get("meta", FileMeta.class);
        for (Process process : processList) {
            if (!process.getMd5().equals(fileMeta.getEtag())) {
                continue;
            }
            log.debug(JsonConverter.toJsonString(process));
            if (process.isIngestMatchingComplete() && (process.getRecordsCount() > 0)) {
                log.info(String.format("%s records are ingested", process.getRecordsCount()));
                if (process.getRecordsCount() == recordsCount) {
                    context.put("process", process);
                    return true;
                }
            }
            return false;
        }
        log.error("Can't find uploaded file " + fileMeta.getName());
        return false;
    }

    @When("I send get upload details request")
    public void getUploadDetails() {
        Process process = context.get("process", Process.class);
        UploadDetails uploadDetails = service.details(process.getId()).getEntity();
        context.put("uploadDetails", uploadDetails);
    }

    @Then("matching results contain correct Matching results: total hit&Mention records, list of hit/mention targets with hit/mention records counts for each")
    public void matchingResultShouldContainCorrectMatchingResult() {
        UploadDetails uploadDetails = context.get("uploadDetails", UploadDetails.class);
        GenerationMatrix matrix = context.get("generationMatrix", GenerationMatrix.class);

        for (GenerationMatrixRow row : matrix.getRows()) {
            MatchingResult result = null;
            String targetName = row.getTarget().getName();

            // verify target HIT
            if (row.getTotalRecordsHit() > 0) {
                result = uploadDetails.findMatchingResultByTargetNameAndTargetResultType(targetName, TargetResultType.HIT);
                Verify.shouldBe(Conditions.equals(result.getNumRecords(), row.getTotalRecordsHit()));
            }

            // verify target Mention
            Source source = context.get("source", Source.class);
            switch (source.getRecordType()) {
                case "SMS":
                    if (row.getTotalRecordsMention() > 0) {
                        result = uploadDetails.findMatchingResultByTargetNameAndTargetResultType(targetName, TargetResultType.MENTION);
                        Verify.shouldBe(Conditions.equals(result.getNumRecords(), row.getTotalRecordsMention()));
                    }
                    break;
            }

            //TODO add verification for target with group
            if (result != null) {
                Verify.shouldBe(Conditions.equals(result.getSearchResultType(), SearchResultType.Target));
            }
        }
    }

    @Then("all uploaded records ingested to system with targets Hits & Mentions")
    public void allRecordsIngestedToSystem() {
        List<G4Record> uploadedRecords = context.get("entitiesList", List.class);
        GenerationMatrix matrix = context.get("generationMatrix", GenerationMatrix.class);
        Process process = context.get("process", Process.class);

        int totalRecordsHit = 0;
        int totalMentionsHit = 0;

        RecordFilter filter = new RecordFilter();
        ArrayList<String> ids = new ArrayList<>();
        ids.add(process.getId());
        filter.setProcessIds(ids);

        RecordService recordService = new RecordService();
        OperationResult<List<Record>> searchResult = recordService.search(filter);
        List<Record> ingestedRecords = searchResult.getEntity();

        // for each uploaded records
        for (G4Record g4Record : uploadedRecords) {
            boolean isIngested = false;
            // for each founded records
            for (Record record : ingestedRecords) {
                // check uploaded record isFound
                if (Objects.equals(record.getFromNumber(), g4Record.getFromNumber())) {
                    isIngested = true;

                    // for each ingested record check and calculate target Hits & Mention
                    if (record.getTargetHitIds() != null && record.getTargetHitIds().size() > 0) {
                        totalRecordsHit++;
                    }
                    if (record.getTargetMentionIds() != null && record.getTargetMentionIds().size() > 0) {
                        totalMentionsHit++;
                    }
                    break;
                }
            }
            Assert.assertTrue("Uploaded record does not found after ingest," +
                    " fromNumber=" + g4Record.getFromNumber(), isIngested);
        }
        assertEquals("totalRecordsHit value is not correct", matrix.getTotalRecordsHit(), totalRecordsHit);
        assertEquals("totalMentionsHit value is not correct", matrix.getTotalRecordsMention(), totalMentionsHit);
    }

    @When("I download audioFile of call event from search results")
    public void downloadFileContent() {
        List<SearchRecord> searchResults = context.get("searchEntities", List.class);

        SearchRecord call = searchResults.stream()
                .filter(searchRecord -> searchRecord.getAttributes() != null
                        && !((List<String>) searchRecord.getAttributes().get("UPLOAD_M4A_FILE_ID")).isEmpty())
                .findAny().orElse(null);

        String upload_m4A_file_id;
        if (call != null) {
            upload_m4A_file_id = ((List<String>) call.getAttributes().get("UPLOAD_M4A_FILE_ID")).get(0);
            OperationResult<File> operationResult = service.getContent(upload_m4A_file_id);
            context.put("audioFile", operationResult.getEntity());
        } else {
            throw new AssertionError("Call with UPLOAD_M4A_FILE_ID not found");
        }
    }

}
