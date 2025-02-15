package model.rfi;

import model.AbstractEntity;
import model.SearchFilter;
import model.InformationRequest;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class RFISearchFilter extends SearchFilter {

    private String state;
    private Date minCreatedDate;
    private Date maxCreatedDate;
    private Integer minPriority;
    private String createdBy;
    private String requestSource;
    private Date minDueDate;
    private Date maxDueDate;
    private final List<RFIStates> states = new ArrayList<>(Arrays.asList(RFIStates.values()));


    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getMinDueDate() {
        return minDueDate;
    }

    public void setMinDueDate(Date minDueDate) {
        this.minDueDate = minDueDate;
    }

    public Date getMaxDueDate() {
        return maxDueDate;
    }

    public void setMaxDueDate(Date maxDueDate) {
        this.maxDueDate = maxDueDate;
    }

    public Date getMinCreatedDate() {
        return minCreatedDate;
    }

    public void setMinCreatedDate(Date minCreatedDate) {
        this.minCreatedDate = minCreatedDate;
    }

    public Date getMaxCreatedDate() {
        return maxCreatedDate;
    }

    public void setMaxCreatedDate(Date maxCreatedDate) {
        this.maxCreatedDate = maxCreatedDate;
    }

    public Integer getMinPriority() {
        return minPriority;
    }

    public void setMinPriority(Integer minPriority) {
        this.minPriority = minPriority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    @SuppressWarnings("unchecked")
    public boolean isAppliedToEntity(AbstractEntity entity) {
        return activeFilter.isAppliedToEntity(entity);
    }

    public List<RFIStates> getStates() {
        return states;
    }

    private class StatusFilter extends SearchFilter<InformationRequest> {

        StatusFilter(String value) {
            state = value;
        }

        public boolean isAppliedToEntity(InformationRequest entity) {
             return entity.getState().equals(state);
        }
    }

    private class PriorityFilter extends SearchFilter<InformationRequest> {

        PriorityFilter(Integer value) {
            minPriority = value;
        }

        public boolean isAppliedToEntity(InformationRequest entity) {
            return entity.getPriority() >= minPriority;
        }
    }

    private class CreatedDateFilter extends SearchFilter<InformationRequest> {

        CreatedDateFilter(Date date) {
            maxCreatedDate = date;
            minCreatedDate = date;
        }

        public boolean isAppliedToEntity(InformationRequest entity) {
            return (entity.getCreatedDate().equals(minCreatedDate) &&
                    entity.getCreatedDate().equals(maxCreatedDate));
        }
    }

    private class DueDateFilter extends SearchFilter<InformationRequest> {

        DueDateFilter(Date date) {
            minDueDate = date;
            maxDueDate = date;
        }
        public boolean isAppliedToEntity(InformationRequest entity) {
            return (entity.getDueDate().equals(minDueDate) &&
                    entity.getDueDate().equals(maxDueDate));
        }
    }

    private class CreatedByFilter extends SearchFilter<InformationRequest> {

        CreatedByFilter(String id) {
            createdBy = id;
        }

        public boolean isAppliedToEntity(InformationRequest entity) {
            return entity.getCreatedBy().equals(createdBy);
        }
    }

    private class OriginatorFilter extends SearchFilter<InformationRequest> {

        OriginatorFilter(String originator) {
            requestSource = originator;
        }

        public boolean isAppliedToEntity(InformationRequest entity) {
            return entity.getRequestSource().equals(requestSource);
        }
    }

    public RFISearchFilter filterBy(String criteria, String value) throws ParseException {
        if (criteria.toLowerCase().equals("state")) {
            this.setActiveFilter(this.new StatusFilter(value));
        } else if (criteria.toLowerCase().equals("min priority")) {
            this.setActiveFilter(this.new PriorityFilter(Integer.parseInt(value)));
        } else if (criteria.toLowerCase().equals("created date")) {
            this.setActiveFilter(this.new CreatedDateFilter(new Date(Long.valueOf(value))));
        } else if (criteria.toLowerCase().equals("due date")) {
            this.setActiveFilter(this.new DueDateFilter(new Date(Long.valueOf(value))));
        } else if (criteria.toLowerCase().equals("created by")) {
            this.setActiveFilter(this.new CreatedByFilter(value));
        } else if (criteria.toLowerCase().equals("originator")) {
            this.setActiveFilter(this.new OriginatorFilter(value));
        } else {
            throw new AssertionError("Unknown isAppliedToEntity type");
        }
        return this;
    }

}

