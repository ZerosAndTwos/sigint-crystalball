package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DataInjection {

    private List<String> phones = new ArrayList<>();
    private List<String> phrases = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    @JsonProperty("family_names")
    private List<String> familyNames = new ArrayList<>();
    private List<String> addresses = new ArrayList<>();
    private List<Long> imsis = new ArrayList<>();
    private List<Long> imeis = new ArrayList<>();

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<String> phrases) {
        this.phrases = phrases;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getFamilyNames() {
        return familyNames;
    }

    public void setFamilyNames(List<String> familyNames) {
        this.familyNames = familyNames;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<Long> getImsis() {
        return imsis;
    }

    public void setImsis(List<Long> imsis) {
        this.imsis = imsis;
    }

    public List<Long> getImeis() {
        return imeis;
    }

    public void setImeis(List<Long> imeis) {
        this.imeis = imeis;
    }
}
