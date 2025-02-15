package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.data_providers.du_subscriber.DuSubcriberAddressProvider;
import ae.pegasus.framework.data_for_entity.annotations.*;
import ae.pegasus.framework.data_for_entity.data_providers.country_info.CountryName;
import ae.pegasus.framework.data_for_entity.data_providers.du_subscriber.DuSubscriberNameProvider;
import ae.pegasus.framework.data_for_entity.data_providers.PhonesProvider;
import ae.pegasus.framework.data_for_entity.data_types.FieldDataType;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@Deprecated
public class DuSubscriberEntry extends G4Entity {
    
    private String sourceId="du";
    @DataIgnore
    private String fileName;
    @DataIgnore
    private Date fileUploadDate;
    @DataProvider(PhonesProvider.class)
    private String phoneNumber;
    private String title;
    @WithDataDependencies(provider = DuSubscriberNameProvider.class, fields = {"firstName", "middleName", "lastName"})
    private String name; //firstName + " " + middleName + " " + lastName
    private String firstName;
    private String middleName;
    private String lastName;
    private String poBox;
    private String city;
    @WithDataDependencies(provider = DuSubcriberAddressProvider.class, fields = {"city", "poBox"})
    private String address; //city + ", " + poBox
    @DataProvider(CountryName.class)
    private String nationality;
    @WithDataSize(4)
    private String visaType;
    @WithFieldDataType(FieldDataType.NUMERIC)
    private String visaNumber;
    @WithDataSize(4)
    private String idType;
    private String idNumber;
    private String status;
    @WithDataSize(4)
    private String customerType;
    @WithDataSize(4)
    private String serviceType;
    private String customerCode;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
        
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public String getVisaNumber() {
        return visaNumber;
    }

    public void setVisaNumber(String visaNumber) {
        this.visaNumber = visaNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Date getFileUploadDate() {
        return fileUploadDate;
    }

    public void setFileUploadDate(Date fileUploadDate) {
        this.fileUploadDate = fileUploadDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    
}
