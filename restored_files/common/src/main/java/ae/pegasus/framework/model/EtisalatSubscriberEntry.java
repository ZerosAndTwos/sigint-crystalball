package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.*;
import ae.pegasus.framework.data_for_entity.data_providers.LongData;
import ae.pegasus.framework.data_for_entity.data_providers.SequenceDataProvider;
import ae.pegasus.framework.data_for_entity.data_providers.country_info.CountryCode;
import ae.pegasus.framework.data_for_entity.data_providers.country_info.CountryNameByCode;
import ae.pegasus.framework.data_for_entity.data_providers.provision.ProvisionRegionCode;
import ae.pegasus.framework.data_for_entity.data_types.FieldDataType;
import ae.pegasus.framework.utils.RandomGenerator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@Deprecated
public class EtisalatSubscriberEntry extends G4Entity {
    
    
    private String sourceId="etisalat";
    
    @DataIgnore
    private Integer fileNumber;
    
    /**
     * Date extracted from uploaded file name
     */
    @DataIgnore
    private Date fileDate;
    
    /**
     * Region code extracted from uploaded file name
     */
    @DataIgnore
    private String regionCode;
    
    /**
     * Indicator for the record is added, modified or deleted
     */
    
    private String action = "ADDED";
    
    /**
     * GSM, PSTN or Account No. (Format will be CC-NDC-SN. Example: 971508112562, 9712618XXXX)
     */
    @WithDataSize(12)
    @WithFieldDataType(FieldDataType.NUMERIC)
    private String phoneNumber; //nationalNumber
    
    /**
     * When the No. is created and provisioned this field has a value 0, after the No.
     * is ceased and passed to a 2nd customer the value of this field is 1,
     * and after it is passed to the 3rd customer it is 2 and so on.
     */
    @WithDataSize(1)
    @WithFieldDataType(FieldDataType.NUMERIC)
    private String accountSuffix;
    
    /**
     * To identify the customer and his products.
     */
    private String partyId;
    
    /**
     * Name of the customer in English
     */
    private String name; //accountName
    
    /**
     * Name of the customer in Arabic
     */
    private String accountNameArabic;
    
    /**
     * Internet Subscriber (e.g. abcde), in case of GSM or PSTN No. it will have the National Number.
     */
    private String userIdOrName;
    
    /**
     * Installation Address Building Name
     */
    private String installationBuilding;
    
    /**
     * Installation Address Flat or House No.
     */
    @WithDataSize(3)
    private String installationFlatNumber;
    
    /**
     * Installation Address Floor
     */
    private String installationFloor;
    
    /**
     * Installation Street Name
     */
    private String installationStreetName;
    
    /**
     * Installation Plot No.
     */
    @WithDataSize(3)
    @WithFieldDataType(FieldDataType.NUMERIC)
    private String installationPlotNumber;
    
    /**
     * Installation Map No.
     */
    @WithDataSize(3)
    @WithFieldDataType(FieldDataType.NUMERIC)
    private String installationMapNumber;
    
    /**
     * Installation Sector
     */
    @WithDataSize(4)
    @WithFieldDataType(FieldDataType.NUMERIC)
    private String installationSector;
    
    /**
     * Installation Town Code
     */
    private String installationTownCode;
    
    /**
     * Installation Town Name
     */
    private String installationTownName;
    
    /**
     * Installation Emirate
     */
    private String installationTownEmirate;
    
    /**
     * Combined firstAddressLine + secondAddressLine
     */
    
    @WithDataDependencies(provider = SequenceDataProvider.class, fields = {"firstAddressLine", "secondAddressLine"})
    private String address;
    
    /**
     * Address Line 1 (Billing Address)
     */
    private String firstAddressLine;
    
    /**
     * Address Line 2 (Billing Address)
     */
    private String secondAddressLine;
    
    /**
     * P.O Box No. of the customer
     */
    private String poBoxNumber;
    
    /**
     * Category of the customer
     */
    private String customerCategoryCode;
    
    /**
     * Description for Category Code of the customer
     */
    
    private String customerCategoryCodeDesc;
    
    /**
     * Date & Time of Installation
     */
    @WithFieldDataType(FieldDataType.DATE)
    private Date dateOfInstallation;
    
    /**
     * Country Code of the Customer (2 symbols)
     */
    
    @WithDataDependencies(provider = SequenceDataProvider.class, fields = {"countryCodeOriginal"})
    private String countryCode;
    
    /**
     * Country Code of the Customer (as in source csv files)
     */
    @DataProvider(CountryCode.class)
    private String countryCodeOriginal; //???
    
    /**
     * Country Name of the Customer
     */
    @WithDataDependencies(provider = CountryNameByCode.class, fields = {"countryCodeOriginal"})
    private String country; //countryCodeDescription
    
    /**
     * Customer Account Status Code/ID
     */
    @DataProvider(LongData.class)
    private Long subscriberAccountStatusCode;
    
    /**
     * Customer Account Status
     */
    private String subscriberAccountStatusDesc;
    
    /**
     * The code/id of the group to which this Product belongs.
     */
    @DataProvider(LongData.class)
    private Long productGroupCode;
    
    /**
     * The description of the group to which this Product belongs to. E.g. MOBILE Products
     */
    private String productGroupDesc;
    
    /**
     * The code/id of the services associated with this Product.
     */
    @DataProvider(LongData.class)
    private Long productCode;
    
    /**
     * The description of the services associated with this Product. e.g. GSM Post Paid
     */
    private String productDesc;
    
    /**
     * International Mobile Subscriber Identity
     */
    @WithDataSize(15)
    @WithFieldDataType(FieldDataType.NUMERIC)
    private String imsi;
    
    /**
     * The identification type code/id submitted by the subscriber at the time of subscription of a services.
     */
    @DataProvider(LongData.class)
    private Long identificationTypeCode;
    
    /**
     * The identification type code description submitted by the subscriber at the time of subscription of a services.
     * (E.g. Passport, UAE Identity card, Business document.)
     */
    private String identificationTypeDesc;
    
    /**
     * Identification Info. (e.g. Passport No, UAE National Id, Business Document etc.)
     */
    private String identificationInfo;
    
    /**
     * The region where this Product was Provisioned From. (e.g. AU, AL, DX, SH, EC, RA)
     */
    @DataProvider(CountryCode.class)
    private String provisionedRegionCode;
    
    /**
     * The Description of the Provisioned Region Code
     */
    @WithDataDependencies(provider = ProvisionRegionCode.class, fields = "provisionedRegionCode")
    private String provisionedRegionCodeDesc;
    
    /**
     * The City ID used in the billing Address
     * The ID for City Name.
     */
    @WithDataSize(2)
    @WithFieldDataType(FieldDataType.NUMERIC)
    private String cityId;
    
    /**
     * The City name used in the billing Address.
     */
    private String cityName;
    
    /**
     * Date of Action
     */
    @WithFieldDataType(FieldDataType.DATE)
    private Date updatedDate;
    
    /**
     * Date & Time of DeActivation
     */
    @WithFieldDataType(FieldDataType.DATE)
    private Date dateOfDeactivation;
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getAccountSuffix() {
        return accountSuffix;
    }
    
    public void setAccountSuffix(String accountSuffix) {
        this.accountSuffix = accountSuffix;
    }
    
    public String getPartyId() {
        return partyId;
    }
    
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAccountNameArabic() {
        return accountNameArabic;
    }
    
    public void setAccountNameArabic(String accountNameArabic) {
        this.accountNameArabic = accountNameArabic;
    }
    
    public String getUserIdOrName() {
        return userIdOrName;
    }
    
    public void setUserIdOrName(String userIdOrName) {
        this.userIdOrName = userIdOrName;
    }
    
    public String getInstallationBuilding() {
        return installationBuilding;
    }
    
    public void setInstallationBuilding(String installationBuilding) {
        this.installationBuilding = installationBuilding;
    }
    
    public String getInstallationFlatNumber() {
        return installationFlatNumber;
    }
    
    public void setInstallationFlatNumber(String installationFlatNumber) {
        this.installationFlatNumber = installationFlatNumber;
    }
    
    public String getInstallationFloor() {
        return installationFloor;
    }
    
    public void setInstallationFloor(String installationFloor) {
        this.installationFloor = installationFloor;
    }
    
    public String getInstallationStreetName() {
        return installationStreetName;
    }
    
    public void setInstallationStreetName(String installationStreetName) {
        this.installationStreetName = installationStreetName;
    }
    
    public String getInstallationPlotNumber() {
        return installationPlotNumber;
    }
    
    public void setInstallationPlotNumber(String installationPlotNumber) {
        this.installationPlotNumber = installationPlotNumber;
    }
    
    public String getInstallationMapNumber() {
        return installationMapNumber;
    }
    
    public void setInstallationMapNumber(String installationMapNumber) {
        this.installationMapNumber = installationMapNumber;
    }
    
    public String getInstallationSector() {
        return installationSector;
    }
    
    public void setInstallationSector(String installationSector) {
        this.installationSector = installationSector;
    }
    
    public String getInstallationTownCode() {
        return installationTownCode;
    }
    
    public void setInstallationTownCode(String installationTownCode) {
        this.installationTownCode = installationTownCode;
    }
    
    public String getInstallationTownName() {
        return installationTownName;
    }
    
    public void setInstallationTownName(String installationTownName) {
        this.installationTownName = installationTownName;
    }
    
    public String getInstallationTownEmirate() {
        return installationTownEmirate;
    }
    
    public void setInstallationTownEmirate(String installationTownEmirate) {
        this.installationTownEmirate = installationTownEmirate;
    }
    
    public String getFirstAddressLine() {
        return firstAddressLine;
    }
    
    public void setFirstAddressLine(String firstAddressLine) {
        this.firstAddressLine = firstAddressLine;
    }
    
    public String getSecondAddressLine() {
        return secondAddressLine;
    }
    
    public void setSecondAddressLine(String secondAddressLine) {
        this.secondAddressLine = secondAddressLine;
    }
    
    public String getPoBoxNumber() {
        return poBoxNumber;
    }
    
    public void setPoBoxNumber(String poBoxNumber) {
        this.poBoxNumber = poBoxNumber;
    }
    
    public String getCustomerCategoryCode() {
        return customerCategoryCode;
    }
    
    public void setCustomerCategoryCode(String customerCategoryCode) {
        this.customerCategoryCode = customerCategoryCode;
    }
    
    public String getCustomerCategoryCodeDesc() {
        return customerCategoryCodeDesc;
    }
    
    public void setCustomerCategoryCodeDesc(String customerCategoryCodeDesc) {
        this.customerCategoryCodeDesc = customerCategoryCodeDesc;
    }
    
    public Date getDateOfInstallation() {
        return dateOfInstallation;
    }
    
    public void setDateOfInstallation(Date dateOfInstallation) {
        this.dateOfInstallation = dateOfInstallation;
    }
    
    public String getCountryCode() {
        if (countryCode == null) {
            countryCode = this.getCountryCodeOriginal();
        }
        
        return countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public Long getSubscriberAccountStatusCode() {
        return subscriberAccountStatusCode;
    }
    
    public void setSubscriberAccountStatusCode(Long subscriberAccountStatusCode) {
        this.subscriberAccountStatusCode = subscriberAccountStatusCode;
    }
    
    public String getSubscriberAccountStatusDesc() {
        return subscriberAccountStatusDesc;
    }
    
    public void setSubscriberAccountStatusDesc(String subscriberAccountStatusDesc) {
        this.subscriberAccountStatusDesc = subscriberAccountStatusDesc;
    }
    
    public Long getProductGroupCode() {
        return productGroupCode;
    }
    
    public void setProductGroupCode(Long productGroupCode) {
        this.productGroupCode = productGroupCode;
    }
    
    public String getProductGroupDesc() {
        return productGroupDesc;
    }
    
    public void setProductGroupDesc(String productGroupDesc) {
        this.productGroupDesc = productGroupDesc;
    }
    
    public Long getProductCode() {
        return productCode;
    }
    
    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }
    
    public String getProductDesc() {
        return productDesc;
    }
    
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    
    public String getImsi() {
        return imsi;
    }
    
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
    
    public Long getIdentificationTypeCode() {
        return identificationTypeCode;
    }
    
    public void setIdentificationTypeCode(Long identificationTypeCode) {
        this.identificationTypeCode = identificationTypeCode;
    }
    
    public String getIdentificationTypeDesc() {
        return identificationTypeDesc;
    }
    
    public void setIdentificationTypeDesc(String identificationTypeDesc) {
        this.identificationTypeDesc = identificationTypeDesc;
    }
    
    public String getIdentificationInfo() {
        return identificationInfo;
    }
    
    public void setIdentificationInfo(String identificationInfo) {
        this.identificationInfo = identificationInfo;
    }
    
    public String getProvisionedRegionCode() {
        return provisionedRegionCode;
    }
    
    public void setProvisionedRegionCode(String provisionedRegionCode) {
        this.provisionedRegionCode = provisionedRegionCode;
    }
    
    public String getProvisionedRegionCodeDesc() {
        if (provisionedRegionCodeDesc == null) {
            provisionedRegionCodeDesc = RandomGenerator.getCountryName(this.getProvisionedRegionCode());
        }
        return provisionedRegionCodeDesc;
    }
    
    public void setProvisionedRegionCodeDesc(String provisionedRegionCodeDesc) {
        this.provisionedRegionCodeDesc = provisionedRegionCodeDesc;
    }
    
    public String getCityId() {
        return cityId;
    }
    
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    
    public String getCityName() {
        return cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public Date getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    public Date getDateOfDeactivation() {
        return dateOfDeactivation;
    }
    
    public void setDateOfDeactivation(Date dateOfDeactivation) {
        this.dateOfDeactivation = dateOfDeactivation;
    }
    
    public String getSourceId() {
        return sourceId;
    }
    
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
    
    public Date getFileDate() {
        return fileDate;
    }
    
    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }
    
    public String getRegionCode() {
        return regionCode;
    }
    
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    
    public Integer getFileNumber() {
        return fileNumber;
    }
    
    public void setFileNumber(Integer fileNumber) {
        this.fileNumber = fileNumber;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCountryCodeOriginal() {
        return countryCodeOriginal;
    }
    
    public void setCountryCodeOriginal(String countryCodeOriginal) {
        this.countryCodeOriginal = countryCodeOriginal;
    }
}


        
