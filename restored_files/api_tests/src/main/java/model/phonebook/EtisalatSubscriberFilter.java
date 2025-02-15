package model.phonebook;

import model.SearchFilter;
import model.EtisalatSubscriberEntry;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class EtisalatSubscriberFilter extends SearchFilter<EtisalatSubscriberEntry> {

    private String queryString;

    private String phoneNumber;
    private String name;
    private String accountNameArabic;
    private String userIdOrName;
    private String address;
    private String firstAddressLine;
    private String secondAddressLine;
    private String cityName;
    private String imsi;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    @Override
    public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
        return activeFilter.isAppliedToEntity(entity);
    }


    private class AddressFilter extends SearchFilter<EtisalatSubscriberEntry> {

        AddressFilter(String value) {
            address = value;
        }

        @Override
        public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
            return entity.getAddress().equals(address);
        }
    }

    private class PhoneNumberFilter extends SearchFilter<EtisalatSubscriberEntry> {

        PhoneNumberFilter(String value) {
            phoneNumber = value;
        }

        @Override
        public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
            return entity.getPhoneNumber().equals(phoneNumber);
        }
    }

    private class NameFilter extends SearchFilter<EtisalatSubscriberEntry> {

        NameFilter(String value) {
            name = value;
        }

        @Override
        public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
            return entity.getName().equals(name);
        }
    }

    private class AccountNameArabicFilter extends SearchFilter<EtisalatSubscriberEntry> {

        AccountNameArabicFilter(String value) {
            accountNameArabic = value;
        }

        @Override
        public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
            return entity.getAccountNameArabic().equals(accountNameArabic);
        }
    }

    private class UserIdOrNameFilter extends SearchFilter<EtisalatSubscriberEntry> {

        UserIdOrNameFilter(String value) {
            userIdOrName = value;
        }

        @Override
        public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
            return entity.getUserIdOrName().equals(userIdOrName);
        }
    }

    private class FirstAddressLineFilter extends SearchFilter<EtisalatSubscriberEntry> {

        FirstAddressLineFilter(String value) {
            firstAddressLine = value;
        }

        @Override
        public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
            return entity.getFirstAddressLine().equals(firstAddressLine);
        }
    }

    private class SecondAddressLineFilter extends SearchFilter<EtisalatSubscriberEntry> {

        SecondAddressLineFilter(String value) {
            secondAddressLine = value;
        }

        @Override
        public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
            return entity.getSecondAddressLine().equals(secondAddressLine);
        }
    }

    private class CityNameFilter extends SearchFilter<EtisalatSubscriberEntry> {

        CityNameFilter(String value) {
            cityName = value;
        }

        @Override
        public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
            return entity.getCityName().equals(cityName);
        }
    }

    private class ImsiFilter extends SearchFilter<EtisalatSubscriberEntry> {

        ImsiFilter(String value) {
            imsi = value;
        }

        @Override
        public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
            return entity.getImsi().equals(imsi);
        }
    }

    private class QueryStringFilter extends SearchFilter<EtisalatSubscriberEntry> {

        QueryStringFilter(String value) {
            queryString = value;
        }

        @Override
        public boolean isAppliedToEntity(EtisalatSubscriberEntry entity) {
            return entity.getPhoneNumber().equals(queryString);
        }
    }

    public EtisalatSubscriberFilter filterBy(String criteria, String value) {
        if (criteria.toLowerCase().equals("address")) {
            this.setActiveFilter(this.new AddressFilter(value));
        } else if (criteria.toLowerCase().equals("name")) {
            this.setActiveFilter(this.new NameFilter(value));
        } else if (criteria.toLowerCase().equals("useridorname")) {
            this.setActiveFilter(this.new UserIdOrNameFilter(value));
        } else if (criteria.toLowerCase().equals("accountnamearabic")) {
            this.setActiveFilter(this.new AccountNameArabicFilter(value));
        } else if (criteria.toLowerCase().equals("phonenumber")) {
            this.setActiveFilter(this.new PhoneNumberFilter(value));
        } else if (criteria.toLowerCase().equals("imsi")) {
            this.setActiveFilter(this.new ImsiFilter(value));
        } else if (criteria.toLowerCase().equals("firstaddressline")) {
            this.setActiveFilter(this.new FirstAddressLineFilter(value));
        } else if (criteria.toLowerCase().equals("secondaddressline")) {
            this.setActiveFilter(this.new SecondAddressLineFilter(value));
        } else if (criteria.toLowerCase().equals("cityname")) {
            this.setActiveFilter(this.new CityNameFilter(value));
        } else if (criteria.toLowerCase().equals("querystring")) {
            this.setActiveFilter(this.new QueryStringFilter(value));
        } else {
            throw new AssertionError("Unknown isAppliedToEntity type");
        }
        return this;
    }
}
