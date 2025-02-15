package steps;

import conditions.Conditions;
import conditions.Verify;
import json.JsonConverter;
import http.OperationResult;
import model.EtisalatSubscriberEntry;
import model.UploadResult;
import model.phonebook.EtisalatSubscriberFilter;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import services.EtisalatSubscriberService;

import java.util.ArrayList;
import java.util.List;

import static conditions.Conditions.isTrue;
import static utils.DateHelper.dateToFormat;

public class APIEtisalatSubscriberDataSteps extends APISteps {

    private static final Logger log = Logger.getLogger(APIEtisalatSubscriberDataSteps.class);
    private static final EtisalatSubscriberService service = new EtisalatSubscriberService();

    @When("I send upload EtisalatSubscriberData entry request with all fields")
    public void sendUploadEtisalatSubscriberDataEntryRequest() {
        EtisalatSubscriberEntry entry = getRandomEtisalatEntry();
        context.put("etisalatSubscriberEntry", entry);

        OperationResult<UploadResult> operationResult = service.add(entry);
        context.put("uploadResult", operationResult.getEntity());
    }

    @When("I send search EtisalatSubscriberData by $criteria and value $value")
    public void sendSearchEtisalatListByCriteriaAndValue(String criteria, String value) {
        log.info("Searching EtisalatSubscriberData by criteria:" + criteria + " and value:" + value);
        EtisalatSubscriberEntry entry = context.get("etisalatSubscriberEntry", EtisalatSubscriberEntry.class);

        if (criteria.toLowerCase().equals("address")) {
            value = value.equals("random") ? entry.getAddress() : value;
        } else if (criteria.toLowerCase().equals("name")) {
            value = value.equals("random") ? entry.getName() : value;
        } else if (criteria.toLowerCase().equals("useridorname")) {
            value = value.equals("random") ? entry.getUserIdOrName() : value;
        } else if (criteria.toLowerCase().equals("accountnamearabic")) {
            value = value.equals("random") ? entry.getAccountNameArabic() : value;
        } else if (criteria.toLowerCase().equals("phonenumber")) {
            value = value.equals("random") ? entry.getPhoneNumber() : value;
        } else if (criteria.toLowerCase().equals("imsi")) {
            value = value.equals("random") ? entry.getImsi() : value;
        } else if (criteria.toLowerCase().equals("firstaddressline")) {
            value = value.equals("random") ? entry.getFirstAddressLine() : value;
        } else if (criteria.toLowerCase().equals("secondaddressline")) {
            value = value.equals("random") ? entry.getSecondAddressLine() : value;
        } else if (criteria.toLowerCase().equals("cityname")) {
            value = value.equals("random") ? entry.getCityName() : value;
        } else if (criteria.toLowerCase().equals("querystring")) {
            value = value.equals("random") ? entry.getPhoneNumber() : value;
        } else {
            throw new AssertionError("Unknown isAppliedToEntity type");
        }

        EtisalatSubscriberFilter searchFilter = new EtisalatSubscriberFilter().filterBy(criteria, value);
        log.info("Search isAppliedToEntity: " + JsonConverter.toJsonString(searchFilter));
        OperationResult<List<EtisalatSubscriberEntry>> operationResult = service.search(searchFilter);

        context.put("searchFilter", searchFilter);
        context.put("searchResult", operationResult.getEntity());
    }

    @Then("EtisalatSubscriberData search result are correct")
    public void searchEtisalatResultsCorrect() {
        log.info("Checking if etisalatSubscriberData search result is correct");
        EtisalatSubscriberFilter searchFilter = context.get("searchFilter", EtisalatSubscriberFilter.class);
        List<EtisalatSubscriberEntry> searchResults = context.get("searchResult", List.class);

        if (searchResults.size() == 0) {
            log.warn("Search result can be incorrect. There are not records in it");
        } else {
            log.info("Search result size: " + searchResults.size());
        }
        for (EtisalatSubscriberEntry entry : searchResults) {
            log.info("Checking search result");
            log.debug("Search result: " + JsonConverter.toJsonString(entry));
            Assert.assertTrue(searchFilter.isAppliedToEntity(entry));
        }
    }

    @Then("Searched EtisalatSubscriberData Entry $critera list")
    public void checkEtisalatSubscriberEntryInResults(String criteria) {
        EtisalatSubscriberEntry entry = context.get("etisalatSubscriberEntry", EtisalatSubscriberEntry.class);
        List<EtisalatSubscriberEntry> List = context.get("searchResult", List.class);

        log.info("Checking if etisalatSubscriberData entry " + criteria + " list");
        Boolean contains = false;
        for (EtisalatSubscriberEntry entity : List) {
            if (equalsEntries(entry, entity)) {
                contains = true;
                break;
            }
        }

        if (criteria.toLowerCase().equals("in")) {
            Verify.shouldBe(isTrue.element(contains));
        } else if (criteria.toLowerCase().equals("not in")) {
            Verify.shouldNotBe(isTrue.element(contains));
        } else {
            throw new AssertionError("Incorrect argument passed to step");
        }
    }

    private boolean equalsEntries(EtisalatSubscriberEntry entry, EtisalatSubscriberEntry entity) {
        String dateTimestring = "dd/MM/yy HH:mm:ss";
        String dateString = "dd/MM/yy";

        return Verify.isTrue(Conditions.equals(entry.getPhoneNumber(), entity.getPhoneNumber())) &&
                Verify.isTrue(Conditions.equals(entry.getName(), entity.getName())) &&
                Verify.isTrue(Conditions.equals(entry.getAddress(), entity.getAddress())) &&
                Verify.isTrue(Conditions.equals(entry.getSourceId(), entity.getSourceId())) &&
                Verify.isTrue(Conditions.equals(entry.getAction(), entity.getAction())) &&
                Verify.isTrue(Conditions.equals(entry.getAccountSuffix(), entity.getAccountSuffix())) &&
                Verify.isTrue(Conditions.equals(entry.getPartyId(), entity.getPartyId())) &&
                Verify.isTrue(Conditions.equals(entry.getAccountNameArabic(), entity.getAccountNameArabic())) &&
                Verify.isTrue(Conditions.equals(entry.getUserIdOrName(), entity.getUserIdOrName())) &&
                Verify.isTrue(Conditions.equals(entry.getInstallationBuilding(), entity.getInstallationBuilding())) &&
                Verify.isTrue(Conditions.equals(entry.getInstallationFlatNumber(), entity.getInstallationFlatNumber())) &&
                Verify.isTrue(Conditions.equals(entry.getInstallationFloor(), entity.getInstallationFloor())) &&
                Verify.isTrue(Conditions.equals(entry.getInstallationStreetName(), entity.getInstallationStreetName())) &&
                Verify.isTrue(Conditions.equals(entry.getInstallationPlotNumber(), entity.getInstallationPlotNumber())) &&
                Verify.isTrue(Conditions.equals(entry.getInstallationMapNumber(), entity.getInstallationMapNumber())) &&
                Verify.isTrue(Conditions.equals(entry.getInstallationSector(), entity.getInstallationSector())) &&
                Verify.isTrue(Conditions.equals(entry.getInstallationTownCode(), entity.getInstallationTownCode())) &&
                Verify.isTrue(Conditions.equals(entry.getInstallationTownName(), entity.getInstallationTownName())) &&
                Verify.isTrue(Conditions.equals(entry.getInstallationTownEmirate(), entity.getInstallationTownEmirate())) &&
                Verify.isTrue(Conditions.equals(entry.getAddress(), entity.getAddress())) &&
                Verify.isTrue(Conditions.equals(entry.getFirstAddressLine(), entity.getFirstAddressLine())) &&
                Verify.isTrue(Conditions.equals(entry.getSecondAddressLine(), entity.getSecondAddressLine())) &&
                Verify.isTrue(Conditions.equals(entry.getPoBoxNumber(), entity.getPoBoxNumber())) &&
                Verify.isTrue(Conditions.equals(entry.getCustomerCategoryCode(), entity.getCustomerCategoryCode())) &&
                Verify.isTrue(Conditions.equals(entry.getCustomerCategoryCodeDesc(), entity.getCustomerCategoryCodeDesc())) &&
                Verify.isTrue(Conditions.equals(
                        dateToFormat(entry.getDateOfInstallation(), dateTimestring),
                        dateToFormat(entity.getDateOfInstallation(), dateTimestring)
                )) &&
                Verify.isTrue(Conditions.equals(entry.getCountryCode(), entity.getCountryCode())) &&
                Verify.isTrue(Conditions.equals(entry.getCountryCodeOriginal(), entity.getCountryCodeOriginal())) &&
                Verify.isTrue(Conditions.equals(entry.getCountry(), entity.getCountry())) &&
                Verify.isTrue(Conditions.equals(entry.getSubscriberAccountStatusCode(), entity.getSubscriberAccountStatusCode())) &&
                Verify.isTrue(Conditions.equals(entry.getSubscriberAccountStatusDesc(), entity.getSubscriberAccountStatusDesc())) &&
                Verify.isTrue(Conditions.equals(entry.getProductGroupCode(), entity.getProductGroupCode())) &&
                Verify.isTrue(Conditions.equals(entry.getProductGroupDesc(), entity.getProductGroupDesc())) &&
                Verify.isTrue(Conditions.equals(entry.getProductCode(), entity.getProductCode())) &&
                Verify.isTrue(Conditions.equals(entry.getProductDesc(), entity.getProductDesc())) &&
                Verify.isTrue(Conditions.equals(entry.getImsi(), entity.getImsi())) &&
                Verify.isTrue(Conditions.equals(entry.getIdentificationTypeCode(), entity.getIdentificationTypeCode())) &&
                Verify.isTrue(Conditions.equals(entry.getIdentificationTypeDesc(), entity.getIdentificationTypeDesc())) &&
                Verify.isTrue(Conditions.equals(entry.getIdentificationInfo(), entity.getIdentificationInfo())) &&
                Verify.isTrue(Conditions.equals(entry.getProvisionedRegionCode(), entity.getProvisionedRegionCode())) &&
                Verify.isTrue(Conditions.equals(entry.getProvisionedRegionCodeDesc(), entity.getProvisionedRegionCodeDesc())) &&
                Verify.isTrue(Conditions.equals(entry.getCityId(), entity.getCityId())) &&
                Verify.isTrue(Conditions.equals(entry.getCityName(), entity.getCityName())) &&
                Verify.isTrue(Conditions.equals(
                        dateToFormat(entry.getUpdatedDate(), dateString),
                        dateToFormat(entity.getUpdatedDate(), dateString)
                )) &&
                Verify.isTrue(Conditions.equals(
                        dateToFormat(entry.getDateOfDeactivation(), dateTimestring),
                        dateToFormat(entity.getDateOfDeactivation(), dateTimestring)
                ));
    }

    @When("I send get EtisalatSubscriberData Entry request")
    public void getEtisalatSubscriberRequest() {
        List<EtisalatSubscriberEntry> searchResults = context.get("searchResult", List.class);
        EtisalatSubscriberEntry etalonEntry = searchResults.get(0);

        OperationResult<EtisalatSubscriberEntry> operationResult = service.view(etalonEntry.getId());

        context.put("etisalatSubscriberEntry", operationResult.getEntity());
        context.put("etalonEntry", etalonEntry);
    }

    @Then("EtisalatSubscriberData Entry is correct")
    public void checkDuSubscriberEntryIsCorrect() {
        EtisalatSubscriberEntry entry = context.get("etisalatSubscriberEntry", EtisalatSubscriberEntry.class);
        EtisalatSubscriberEntry etalonEntry = context.get("etalonEntry", EtisalatSubscriberEntry.class);

        Verify.shouldBe(Conditions.equals(entry, etalonEntry));
    }

    @When("I send upload $count EtisalatSubscriber entries request")
    public void uploadMultipleEtisalatSubscriberEntries(String count) {
        int numEntries = Integer.valueOf(count);
        List<EtisalatSubscriberEntry> entries = new ArrayList<>();
        for (int i = 0; i < numEntries; i++) {
            EtisalatSubscriberEntry entry = getRandomEtisalatEntry();
            entries.add(entry);
        }
        context.put("uploadedEtisalatSubscriberEntries", entries);

        OperationResult<UploadResult> operationResult = service.add(entries);
        context.put("uploadResult", operationResult.getEntity());
    }

    static EtisalatSubscriberEntry getRandomEtisalatEntry() {
        return objectInitializer.randomEntity(EtisalatSubscriberEntry.class);
    }
}
