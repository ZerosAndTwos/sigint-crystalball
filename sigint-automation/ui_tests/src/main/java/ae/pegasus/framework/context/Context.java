package ae.pegasus.framework.context;

import ae.pegasus.framework.assertion.DateTimeTolerance;
import ae.pegasus.framework.utils.TimeUtils;
import ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Context {

    private static ThreadLocal<Context> contextThreadLocal = ThreadLocal.withInitial(Context::new);

    public static Context getContext() {
        return contextThreadLocal.get();
    }

    private Map<String, CreatedRecord> createdRecordsMap = new HashMap<>();
    private String currentlyCreatedRecord = "";

    private String reportNumber = "";

    private LocalDateTime scenarioStartTime = TimeUtils.getCurrentTime();

    public void rememberScenarioStartTime() {
        scenarioStartTime = TimeUtils.getCurrentTime();
    }

    public DateTimeTolerance getToleranceBasedOnScenarioStartTime() {
        return getToleranceBasedOnScenarioStartTime(TimeUnit.SECONDS);
    }

    public DateTimeTolerance getToleranceBasedOnScenarioStartTime(TimeUnit toleranceUnit) {
        long toleranceValue = toleranceUnit.convert(TimeUtils.getDeltaTimeFromCurrent(scenarioStartTime).getSeconds(), TimeUnit.SECONDS);
        return new DateTimeTolerance(toleranceValue, toleranceUnit);
    }

    public void startNewRecordCreation(String recordIdentifier) {
        if (createdRecordsMap.containsKey(recordIdentifier)) {
            throw new IllegalArgumentException("Record with key '" + recordIdentifier + "' was already created");
        }
        createdRecordsMap.put(recordIdentifier, new CreatedRecord());
        currentlyCreatedRecord = recordIdentifier;
    }

    public void endNewRecordCreation() {
        currentlyCreatedRecord = "";
    }

    public boolean isRecordCreationInProgress() {
        return currentlyCreatedRecord != null && !currentlyCreatedRecord.isEmpty() && createdRecordsMap.containsKey(currentlyCreatedRecord);
    }

    public void setFieldValueForCurrentlyCreatedRecord(CreatedRecordField field, Object value) {
        if (!isRecordCreationInProgress()) {
            throw new IllegalStateException("There is no records which is currently created");
        }
        CreatedRecord currentRecord = createdRecordsMap.get(currentlyCreatedRecord);
        currentRecord.setRecordFieldValue(field, value);
        createdRecordsMap.put(currentlyCreatedRecord, currentRecord);
    }

    public CreatedRecord getCreatedRecord(String recordIdentifier) {
        return createdRecordsMap.get(recordIdentifier);
    }

    public void clearContext() {
        createdRecordsMap.clear();
        currentlyCreatedRecord = "";
        reportNumber = "";
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }
}