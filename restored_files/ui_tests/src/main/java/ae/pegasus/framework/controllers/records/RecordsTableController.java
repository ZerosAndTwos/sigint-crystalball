package ae.pegasus.framework.controllers.records;

import ae.pegasus.framework.blocks.context.tables.Row;
import ae.pegasus.framework.blocks.context.tables.records.RecordRow;
import ae.pegasus.framework.controllers.TableController;
import ae.pegasus.framework.errors.NotFoundException;
import ae.pegasus.framework.model.Record;
import ae.pegasus.framework.model.UIRecord;
import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.utils.StringUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class RecordsTableController extends TableController {

    private Logger log = Logger.getLogger(RecordsTableController.class);

    public RecordsTableController(SigintPage page) {
        super(page);
    }

    public Record findRecordById(String recordId) throws NotFoundException {
        Row recordRow = table.getRecordByColumnNameAndValue("Record ID", recordId);
        return initFromRow(recordRow);
    }

    public Record selectRecordbyStatus(String status) {
        Row recordRow;
        try {
            recordRow = table.getRecordByColumnNameAndValue("Status", status);
        } catch (NotFoundException e) {
            log.error(e.getMessage(), e);
            throw new AssertionError(e.getMessage());
        }
        selectRecordInTable(recordRow);
        return initFromRow(recordRow);
    }


    public void openCreateReportForm(String recordId) {
        RecordRow recordRow;
        try {
            recordRow = (RecordRow)table.getRecordByColumnNameAndValue("Record ID", recordId);
        } catch (NotFoundException e) {
            log.error(e.getMessage(), e);
            throw new AssertionError(e.getMessage());
        }
        recordRow.clickCreateReportButton();
    }

    protected Record  initFromRow(Row recordRow) {
        RecordRow row = (RecordRow) recordRow;
        Record newRecord = new UIRecord();
        try {
            Date date = new SimpleDateFormat("d MMM, HH:mm yyyy").parse(row.getDate().replace("GMT", "") + "2016");
            newRecord.setDateAndTime(date);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            throw new AssertionError("Was unable to set G4 date from ui Row date");
        }
        newRecord.setTmsi(row.getTMSI());
        newRecord.setImsi(row.getIMSI());
        newRecord.setLanguage(row.getLanguage());
        newRecord.setProcessedStatus(row.getStatus());
        newRecord.setOriginalId(row.getRecordID());
        newRecord.setType(row.getType());
        if (row.getType().equalsIgnoreCase("voice")) {
            newRecord.setDuration(StringUtils.getDurationFromString(row.getDetails()));
        } else {
            newRecord.setText(row.getDetails());
        }
        return newRecord;
    }

}
