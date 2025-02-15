package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataProvider;
import ae.pegasus.framework.data_for_entity.annotations.WithDataDependencies;
import ae.pegasus.framework.data_for_entity.data_providers.country_info.CountryCode;
import ae.pegasus.framework.data_for_entity.data_providers.phonebook.PhonebookCountryName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ae.pegasus.framework.utils.RandomGenerator;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@Deprecated
public class Phonebook extends G4Entity {
    
    private String phoneNumber;
    private String name;
    private String address;
    @WithDataDependencies(provider = PhonebookCountryName.class, fields = {"countryCode"})
    private String country;
    @DataProvider(CountryCode.class)
    private String countryCode;
    private String provider;
    private String location;
    private String imsi;
    private boolean manualEntry=true;
    
    @Override
    public String toString() {
        return String.format("Phone:%s, name:%s, address:%s, country:%s," +
                "countryCode:%s, provider:%s, location:%s, imsi:%s",
                phoneNumber, name, address, country, countryCode, provider, location, imsi);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        
    }

    public String getCountry() {
        if (country == null) {
            country = RandomGenerator.getCountryName(this.getCountryCode());
        }
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public boolean isManualEntry() {
        return manualEntry;
    }

    public void setManualEntry(boolean manualEntry) {
        this.manualEntry = manualEntry;
    }
    
}
