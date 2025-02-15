package model.phonebook;

import model.SearchFilter;
import model.DuSubscriberEntry;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DuSubscriberFilter extends SearchFilter<DuSubscriberEntry> {

    private String phoneNumber;
    private String name;
    private String address;
    // To search all fields using the query string syntax, phone number is the default field:
    // https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-query-string-query.html
    private String queryString;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public String toString() {
        return String.format("phoneNumber: %s, name: %s, address: %s, queryString: %s",
                phoneNumber, name, address, queryString);
    }

    @Override
    public boolean isAppliedToEntity(DuSubscriberEntry entity) {
        return activeFilter.isAppliedToEntity(entity);
    }


    private class AddressFilter extends SearchFilter<DuSubscriberEntry> {

        AddressFilter(String value) {
            address = value;
        }

        @Override
        public boolean isAppliedToEntity(DuSubscriberEntry entity) {
            return entity.getAddress().equals(address);
        }
    }

    private class PhoneNumberFilter extends SearchFilter<DuSubscriberEntry> {

        PhoneNumberFilter(String value) {
            phoneNumber = value;
        }

        @Override
        public boolean isAppliedToEntity(DuSubscriberEntry entity) {
            return entity.getPhoneNumber().equals(phoneNumber);
        }
    }

    private class NameFilter extends SearchFilter<DuSubscriberEntry> {

        NameFilter(String value) {
            name = value;
        }

        @Override
        public boolean isAppliedToEntity(DuSubscriberEntry entity) {
            return entity.getName().equals(name);
        }
    }

    private class QueryStringFilter extends SearchFilter<DuSubscriberEntry> {

        QueryStringFilter(String value) {
            queryString = value;
        }

        @Override
        public boolean isAppliedToEntity(DuSubscriberEntry entity) {
            return entity.getPhoneNumber().equals(queryString);
        }
    }


    public DuSubscriberFilter filterBy(String criteria, String value) {
        if (criteria.toLowerCase().equals("address")) {
            this.setActiveFilter(this.new AddressFilter(value));
        } else if (criteria.toLowerCase().equals("name")) {
            this.setActiveFilter(this.new NameFilter(value));
        } else if (criteria.toLowerCase().equals("phonenumber")) {
            this.setActiveFilter(this.new PhoneNumberFilter(value));
        } else if (criteria.toLowerCase().equals("querystring")) {
            this.setActiveFilter(this.new QueryStringFilter(value));
        } else {
            throw new AssertionError("Unknown isAppliedToEntity type");
        }
        return this;
    }
}
