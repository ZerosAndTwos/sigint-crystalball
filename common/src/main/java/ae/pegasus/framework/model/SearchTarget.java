package ae.pegasus.framework.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SearchTarget extends SearchEntity {

    private String name;
    private TargetStatus active;
    private String category;
    private Integer entityCount;
    private Date createdOn;
    private ArrayList<SearchFilters.TargetGroup> groups = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<Identifier> identifiers = new ArrayList<>();
    private Integer identifierCount;


    public ArrayList<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TargetStatus getActive() {
        return active;
    }

    public void setActive(TargetStatus active) {
        this.active = active;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getEntityCount() {
        return entityCount;
    }

    public void setEntityCount(Integer entityCount) {
        this.entityCount = entityCount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getIdentifierCount() {
        return identifierCount;
    }

    public void setIdentifierCount(Integer identifierCount) {
        this.identifierCount = identifierCount;
    }

    public ArrayList<SearchFilters.TargetGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<SearchFilters.TargetGroup> groups) {
        this.groups = groups;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
