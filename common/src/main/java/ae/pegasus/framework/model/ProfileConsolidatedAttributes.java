package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProfileConsolidatedAttributes {

    @DataIgnore
    private ArrayList<ProfileAttribute> image = new ArrayList<>();
    @DataIgnore
    private ArrayList<ProfileAttribute> gender = new ArrayList<>();
    @DataIgnore
    private ArrayList<ProfileAttribute> nationality = new ArrayList<>();
    @DataIgnore
    private ArrayList<ProfileAttribute> document = new ArrayList<>();
    @DataIgnore
    private ArrayList<ProfileAttribute> name = new ArrayList<>();
    @DataIgnore
    private ArrayList<ProfileAttribute> dateOfBirth = new ArrayList<>();
    @DataIgnore
    private ArrayList<ProfileAttribute> image_large = new ArrayList<>();

    public ArrayList<ProfileAttribute> getImage() {
        return image;
    }

    public void setImage(ArrayList<ProfileAttribute> image) {
        this.image = image;
    }

    public ArrayList<ProfileAttribute> getGender() {
        return gender;
    }

    public void setGender(ArrayList<ProfileAttribute> gender) {
        this.gender = gender;
    }

    public ArrayList<ProfileAttribute> getNationality() {
        return nationality;
    }

    public void setNationality(ArrayList<ProfileAttribute> nationality) {
        this.nationality = nationality;
    }

    public ArrayList<ProfileAttribute> getDocument() {
        return document;
    }

    public void setDocument(ArrayList<ProfileAttribute> document) {
        this.document = document;
    }

    public ArrayList<ProfileAttribute> getName() {
        return name;
    }

    public void setName(ArrayList<ProfileAttribute> name) {
        this.name = name;
    }

    public ArrayList<ProfileAttribute> getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(ArrayList<ProfileAttribute> dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ArrayList<ProfileAttribute> getImage_large() {
        return image_large;
    }

    public void setImage_large(ArrayList<ProfileAttribute> image_large) {
        this.image_large = image_large;
    }
}
