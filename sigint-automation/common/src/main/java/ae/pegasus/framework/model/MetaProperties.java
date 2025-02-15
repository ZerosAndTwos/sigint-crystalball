package ae.pegasus.framework.model;

public class MetaProperties {
    
    private String subSourceType;
    private String dataSourceType;
    private String service;
    private String error;
    private String processId;

    public String getSubSourceType() {
        return subSourceType;
    }

    public void setSubSourceType(String subSourceType) {
        this.subSourceType = subSourceType;
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
