package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DesignationMappingFilter extends SearchFilter<DesignationMapping> {

  private String identifier;
  private List<String> types;
  private List<String> designations;
  private Date updatedAfter;
  private Boolean spam;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public List<String> getTypes() {
    return types;
  }

  public void setTypes(List<String> types) {
    this.types = types;
  }

  public List<String> getDesignations() {
    return designations;
  }

  public void setDesignations(List<String> designations) {
    this.designations = designations;
  }

  public Date getUpdatedAfter() {
    return updatedAfter;
  }

  public void setUpdatedAfter(Date updatedAfter) {
    this.updatedAfter = updatedAfter;
  }

  public Boolean getSpam() {
    return spam;
  }

  public void setSpam(Boolean spam) {
    this.spam = spam;
  }

  @Override
  public boolean isAppliedToEntity(DesignationMapping entity) {
    return activeFilter.isAppliedToEntity(entity);
  }

  private class UpdateAfterFilter extends SearchFilter<DesignationMapping> {

    UpdateAfterFilter(Date value) {
      updatedAfter = value;
    }

    @Override
    public boolean isAppliedToEntity(DesignationMapping entity) {
      return entity.getModifiedAt().after(updatedAfter);
    }
  }

  private class IdentifierFilter extends SearchFilter<DesignationMapping> {

    IdentifierFilter(String value) {
      identifier = value;
    }

    @Override
    public boolean isAppliedToEntity(DesignationMapping entity) {
      return entity.getIdentifier().contains(identifier);
    }
  }

  private class TypeFilter extends SearchFilter<DesignationMapping> {

    TypeFilter(String value) {
      types = new ArrayList<>();
      types.add(value);
    }

    @Override
    public boolean isAppliedToEntity(DesignationMapping entity) {
      return types.contains(String.valueOf(entity.getType()));
    }
  }

  private class SpamFilter extends SearchFilter<DesignationMapping> {

    SpamFilter(String value) {
      spam = Boolean.valueOf(value);
    }

    @Override
    public boolean isAppliedToEntity(DesignationMapping entity) {
      return entity.isSpam() == spam;
    }
  }

  private class DesignationFilter extends SearchFilter<DesignationMapping> {

    DesignationFilter(String value) {
      designations = new ArrayList<>();
      designations.add(value);
    }

    @Override
    public boolean isAppliedToEntity(DesignationMapping entity) {
      for (String designation: entity.getDesignations()) {
        if (designations.contains(designation)) {
          return true;
        }
      }
      return false;
    }
  }

  private class EmptyFilter extends SearchFilter<DesignationMapping> {

    EmptyFilter() {
      identifier = null;
      updatedAfter = null;
      types = null;
      spam = null;
    }

    @Override
    public boolean isAppliedToEntity(DesignationMapping entity) {
      return true;
    }
  }

  /**
   * Init or update designation-mapping filter.
   * Filter is used in DesignationMappingService for receive list of designation-mappings.
   *
   * @param criteria filter field
   * @param value value of filter
   * @return filter entity for designation-mappings
   */
  public DesignationMappingFilter filterBy(String criteria, String value) {
    switch (criteria.trim().toLowerCase()) {
      case "identifier":
        this.setActiveFilter(this.new IdentifierFilter(value));
        break;
      case "type":
        this.setActiveFilter(this.new TypeFilter(value));
        break;
      case "designation":
        this.setActiveFilter(this.new DesignationFilter(value));
        break;
      case "spam":
        this.setActiveFilter(this.new SpamFilter(value));
        break;
      case "updatedafter":
        this.setActiveFilter(this.new UpdateAfterFilter(new Date(Long.valueOf(value))));
        break;
      case "empty":
        this.setActiveFilter(this.new EmptyFilter());
        break;
      default:
        throw new AssertionError("Unknown filter type: " + criteria);
    }
    return this;
  }
}

