package ae.pegasus.framework.controllers.records;

import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.controllers.PageController;
import ae.pegasus.framework.model.Record;
import ae.pegasus.framework.pages.records.RecordsCreatePage;

public class RecordAddController extends PageController<RecordsCreatePage> {

    public RecordAddController() {
        super(Pages.recordsCreatePage(), RecordsCreatePage.class);
    }

    private Record fillSMS(Record record) {
        getPage()
                .clickSMSRecordType()
                .typeSMSText(record.getText());

        return record;
    }

    private Record fillCall(Record record) {

        getPage()
                .clickCallRecordType()
                .typeCallDuration(record.getDuration());
        return record;
    }


    public Record fillForm(Record record) {
            getPage()
                .selectSource(record.getSourceName())
                .selectFromCountry(record.getFromCountry())
                .selectToCountry(record.getToCountry())
                .selectLanguage(record.getLanguage())
                .typeDataAndTime(record.getDateAndTime())
                .typeFromNumber(record.getFromNumber())
                .typeTmsi(record.getTmsi())
                .typeImsi(record.getImsi())
                .typeRecordId(record.getOriginalId())
                .typeToNumber(record.getToNumber());

        String recordType = record.getType();
        switch (recordType) {
            case "SMS":
                fillSMS(record);
                break;
            case "Voice":
                fillCall(record);
                break;
            default:
                throw new AssertionError(String.format("Incorrect record type %s", recordType));
        }
        getPage().clickCreateRecordButton();
        return record;
    }
}
