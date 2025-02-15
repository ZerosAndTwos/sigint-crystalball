package ae.pegasus.framework.jenkins;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
class JobInfo {
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    private String id;
    private JobStatus result;
    private Long duration;
    @JsonProperty("fullDisplayName")
    private String name;
    
    @Override
    public String toString() {
        return String.format("result:%s, duration:%s, name:%s", result, duration, name);
    }
    
    public JobStatus getResult() {
        return result;
    }
    
    public void setResult(JobStatus result) {
        this.result = result;
    }
    
    public Long getDuration() {
        return duration;
    }
    
    public void setDuration(Long duration) {
        this.duration = duration;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}
