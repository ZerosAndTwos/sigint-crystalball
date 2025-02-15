package ae.pegasus.framework.steps.special.search_result.details;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField;
import ae.pegasus.framework.constants.special.results_details.SearchResultsDetailsField;
import ae.pegasus.framework.constants.special.results_details.SearchResultsDetailsSection;
import ae.pegasus.framework.context.Context;
import ae.pegasus.framework.context.CreatedRecord;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.utils.TimeUtils;
import org.apache.commons.lang.NotImplementedException;
import org.jbehave.core.annotations.Then;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField.AUDIO_DURATION;
import static ae.pegasus.framework.constants.special.results_details.SearchResultsDetailsField.*;
import static ae.pegasus.framework.constants.special.results_details.SearchResultsDetailsSection.*;

public class UISearchResultsDetailsCheckFieldsSteps {
    @Then("I should see Owner field's value ($expectedOwnerValue) on the search result Details page")
    public void iShouldSeeOwnerValue(String expectedOwnerValue) {
        Asserter.getAsserter().softAssertEquals(
                Pages.searchResultDetailsPage().getFieldValue(RECORD_ASSESSMENT, OWNER),
                expectedOwnerValue,
                "Owner field's value");
    }

    @Then("I should not see Owner field")
    public void iShouldNotSeeOwner() {
        Asserter.getAsserter().softAssertFalse(
                Pages.searchResultDetailsPage().isFieldDisplayed(RECORD_ASSESSMENT, OWNER),
                "Owner field is not displayed",
                "Owner field is displayed");
    }



    private void checkStringValueField(SearchResultsDetailsSection section, SearchResultsDetailsField mainField, SearchResultsDetailsField alternateField, String expectedValue, CreatedRecordField verifiedField, boolean removeSpaces) {
        SearchResultsDetailsField fieldToCheck = mainField;
        while (true) {
            try {
/*
if (mainField.getFieldName().equalsIgnoreCase("Language") & expectedValue.equals("Voice"))
{
    Asserter.getAsserter().softAssertEquals(
            true,
            true,
            "language is no present for call hence skipping it");
    break;
}
*/

                expectedValue = expectedValue.replace("Voice", "Call");
                expectedValue = expectedValue.replace("SMS", "Texting Event");

                String actualValue = Pages.searchResultDetailsPage().getFieldValue(section, fieldToCheck);
                actualValue = actualValue.replace("E-mail", "Email");
                if (removeSpaces) {
                    actualValue = actualValue.replace(" ", "");
                    actualValue = actualValue.replace("+", "");
                    expectedValue = expectedValue.replace("+", "");
                }
                Asserter.getAsserter().softAssertEquals(
                        actualValue,
                        expectedValue,
                        "Value of " + verifiedField.getFieldName());
                break;
            } catch (Throwable e) {
                if (alternateField != null && fieldToCheck != alternateField) {
                    fieldToCheck = alternateField;
                } else {
                    fieldIsNotDisplayed(verifiedField);
                    break;
                }
            }
        }
    }

    private void checkDateValueField(SearchResultsDetailsSection section, SearchResultsDetailsField field, LocalDateTime expectedValue) {
        LocalDateTime actualDate = TimeUtils.convertToLocalDateTime(
                Pages.searchResultDetailsPage().getFieldValue(section, field),
                "dd/MM/yyyy HH:mm");
        Asserter.getAsserter().softAssertEqualsDateWithTolerance(
                actualDate,
                expectedValue,
                Context.getContext().getToleranceBasedOnScenarioStartTime(TimeUnit.MINUTES),
                "Value of " + field.getFieldName());
    }

    private void fieldIsNotDisplayed(CreatedRecordField field) {
        Asserter.getAsserter().softAssertTrue(false,
                "",
                field.getFieldName() + " field is not displayed on the search result Details page");
    }


    @Then("I should not see From Phone Number field and value")
    public void isFromNumberDisplayed() {
        checkStringValueField(FROM, FROM_PHONE_NUMBER, null, "23156403410", CreatedRecordField.FROM_NUMBER, true);
    }

    @Then("I should not see To Phone Number field and value")
    public void isToNumberDisplayed() {
        checkStringValueField(TO, TO_PHONE_NUMBER, null, "994269292566", CreatedRecordField.TO_NUMBER, true);
    }

    @Then("I should see fields from manually created record identified as ($recordID) on the search result Details page")
    public void iShouldSeeFieldsFromRecord(String recordID) {
        CreatedRecord record = Context.getContext().getCreatedRecord(recordID);
        if (record == null) {
            Asserter.getAsserter().softAssertTrue(false, "",
                    "Manually created record identified as '" + recordID + "' is absent in context");
        } else {
            for (CreatedRecordField recordField : record.getRecordFields()) {
                switch (recordField) {
                    case FROM_NUMBER:
                        checkStringValueField(FROM, FROM_PHONE_NUMBER, null, (String) record.getRecordFieldValue(recordField), recordField, true);
                        break;
                    case FROM_COUNTRY:
                        checkStringValueField(FROM, FROM_COUNTRY, null, (String) record.getRecordFieldValue(recordField), recordField, false);
                        break;
                    case FROM_EMAIL:
                        checkStringValueField(FROM, FROM_EMAIL, null, (String) record.getRecordFieldValue(recordField), recordField, false);
                        break;
                    case IMSI:
                        checkStringValueField(FROM, IMSI, null, (String) record.getRecordFieldValue(recordField), recordField, false);
                        break;
                    case TMSI:
                        checkStringValueField(FROM, TMSI, null, (String) record.getRecordFieldValue(recordField), recordField, false);
                        break;
                    case TO_NUMBER:
                        checkStringValueField(TO, TO_PHONE_NUMBER, null, (String) record.getRecordFieldValue(recordField), recordField, true);
                        break;
                    case TO_COUNTRY:
                        checkStringValueField(TO, TO_COUNTRY, null, (String) record.getRecordFieldValue(recordField), recordField, false);
                        break;
                    case TO_EMAIL:
                        checkStringValueField(TO, TO_EMAIL, null, (String) record.getRecordFieldValue(recordField), recordField, false);
                        break;
                    case SOURCE_TYPE:
                        checkStringValueField(RECORD_METADATA, SOURCE, null, (String) record.getRecordFieldValue(recordField), recordField, false);
                        break;
                    case RECORD_TYPE:
                        checkStringValueField(RECORD_METADATA, SUBTYPE, TYPE, (String) record.getRecordFieldValue(recordField), recordField, false);
                        break;
                    case LANGUAGE:
                       // checkStringValueField(RECORD_DETAILS, LANGUAGE, null, (String) record.getRecordFieldValue(recordField), recordField, false);
                        break;
                    case DATE_AND_TIME:
                        checkDateValueField(RECORD_METADATA, EVENT_TIME, (LocalDateTime) record.getRecordFieldValue(recordField));
                        LocalDateTime expectedEndDate = (LocalDateTime) record.getRecordFieldValue(recordField);
                        if (record.hasRecordField(AUDIO_DURATION)) {
                            expectedEndDate = expectedEndDate.plusSeconds(Long.valueOf((String) record.getRecordFieldValue(AUDIO_DURATION)));
                        }
                        checkDateValueField(RECORD_METADATA, END_TIME, expectedEndDate);
                        break;
                    case RECORD_ID:
                        //fieldIsNotDisplayed(recordField);
                        break;
                    case AUDIO_DURATION:
                        Duration expectedDuration = Duration.ofSeconds(Long.valueOf((String) record.getRecordFieldValue(recordField)));
                        String patternOfDuration = "mm:ss";
                        if (expectedDuration.toHours() > 0) {
                            patternOfDuration = "HH:" + patternOfDuration;
                        }

                        checkStringValueField(ATTRIBUTES, DURATION, null,
                                TimeUtils.convertDurationToStringWithPattern(expectedDuration,
                                        patternOfDuration),
                                recordField, false);
                        break;
                    case SMS_TEXT:
                        Asserter.getAsserter().softAssertEquals(Pages.searchResultDetailsPage().getSMSText(),
                                record.getRecordFieldValue(recordField),
                                "SMS text");
                        break;
                    case ATTACHMENT_DETAILS:
                        Asserter.getAsserter().softAssertEquals(Pages.searchResultDetailsPage().getAttachmentDetails(),
                                record.getRecordFieldValue(recordField),
                                "Attachment details");
                        break;
                    default:
                        throw new NotImplementedException("Unknown field '" + recordField.getFieldName() + "'");
                }
            }
        }
    }
}
