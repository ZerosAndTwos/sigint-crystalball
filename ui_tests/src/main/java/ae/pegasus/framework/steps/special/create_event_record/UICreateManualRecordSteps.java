package ae.pegasus.framework.steps.special.create_event_record;

import ae.pegasus.framework.data_generators.*;
import ae.pegasus.framework.utils.TimeUtils;
import ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField;
import org.apache.commons.lang.StringUtils;
import org.jbehave.core.annotations.Given;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField.*;

public class UICreateManualRecordSteps {
    private void setFieldValue(CreatedRecordField field, String fieldValue) {
        switch (field.getControlType()) {
            case DATE_TIME_SELECTOR:
                Pages.manualRecordPage().setDate(field, TimeUtils.convertToLocalDateTime(fieldValue));
                break;
            default:
                Pages.manualRecordPage().setFieldValue(field, fieldValue);
                break;
        }
    }

    @Given("I set Record Type to ($recordType) in the Manual Event Record dialog")
    public void iSetRecordType(String recordType) {
        setFieldValue(RECORD_TYPE, recordType);
    }

    @Given("I set Date and Time to ($dateAndTime) in the Manual Event Record dialog")
    public void iSetDateAndTime(String dateAndTime) {
        setFieldValue(DATE_AND_TIME, dateAndTime);
    }

    @Given("I set Language to ($language) in the Manual Event Record dialog")
    public void iSetLanguage(String language) {
        setFieldValue(LANGUAGE, language);
    }

    @Given("I set From Country to ($fromCountry) in the Manual Event Record dialog")
    public void iSetFromCountry(String fromCountry) {
        setFieldValue(FROM_COUNTRY, fromCountry);
    }

    @Given("I set To Country to ($toCountry) in the Manual Event Record dialog")
    public void iSetToCountry(String toCountry) {
        setFieldValue(TO_COUNTRY, toCountry);
    }

    @Given("I set Audio Duration to ($audioDuration) in the Manual Event Record dialog")
    public void iSetAudioDuration(int audioDuration) {
        setFieldValue(AUDIO_DURATION, String.valueOf(audioDuration));
    }

    @Given("I generate and set random From Number in the Manual Event Record dialog")
    public void iSetRandomFromNumber() {
        setFieldValue(FROM_NUMBER, PhoneNumberGenerator.getPhoneNumber());
    }

    @Given("I generate and set random To Number in the Manual Event Record dialog")
    public void iSetRandomToNumber() {
        setFieldValue(TO_NUMBER, PhoneNumberGenerator.getPhoneNumber());
    }

    @Given("I generate and set random From E-mail in the Manual Event Record dialog")
    public void iSetRandomFromEmail() {
        setFieldValue(FROM_EMAIL, EmailGenerator.getRandomEmail());
    }

    @Given("I generate and set random To E-mail in the Manual Event Record dialog")
    public void iSetRandomToEmail() {
        setFieldValue(TO_EMAIL, EmailGenerator.getRandomEmail());
    }

    @Given("I generate and set random IMSI in the Manual Event Record dialog")
    public void iSetRandomIMSI() {
        //See https://en.wikipedia.org/wiki/International_mobile_subscriber_identity for details
        setFieldValue(IMSI,
                StringGenerator.getNumericString(BasicGenerator.getRand().nextSecureInt(14, 15)));
    }

    @Given("I generate and set random TMSI in the Manual Event Record dialog")
    public void iSetRandomTMSI() {
        //See https://en.wikipedia.org/wiki/Mobility_management#TMSI for details
        final int SIZE_OF_TMSI = 4*2;
        final String TMSI_UPPER_BOUNDARY = StringUtils.repeat("F", SIZE_OF_TMSI);
        String tmsiValue = BasicGenerator.getRand().nextSecureHexString(SIZE_OF_TMSI);
        while(tmsiValue.equalsIgnoreCase(TMSI_UPPER_BOUNDARY)) {
            tmsiValue = BasicGenerator.getRand().nextSecureHexString(SIZE_OF_TMSI);
        }
        setFieldValue(TMSI, tmsiValue);
    }

    @Given("I generate and set random Record ID in the Manual Event Record dialog")
    public void iSetRandomRecordID() {
        setFieldValue(RECORD_ID,
                StringGenerator.getAlphaNumericString(BasicGenerator.getRand().nextSecureInt(10, 30)));
    }

    @Given("I generate and set random Attachment Details in the Manual Event Record dialog")
    public void iSetRandomAttachmentDetails() {
        setFieldValue(ATTACHMENT_DETAILS,
                TextLinesGenerator.getText(BasicGenerator.getRand().nextSecureInt(2, 4)));
    }

    @Given("I generate and set random SMS Text in the Manual Event Record dialog")
    public void iSetRandomSMSText() {
        setFieldValue(SMS_TEXT,
                TextLinesGenerator.getText(BasicGenerator.getRand().nextSecureInt(2, 5)));
    }

    @Given("I save record created in the Manual Event Record dialog")
    public void iSaveCreatedRecord() {
        Pages.manualRecordPage().saveCreatedRecord();
        Pages.searchResultsPage().waitForPageLoading();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

