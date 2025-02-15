package ae.pegasus.framework.model;

import java.util.List;

public class Authorization {
    private List<Authorizations> authorizations;

    private String justification;

    public List<Authorizations> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<Authorizations> authorizations) {
        this.authorizations = authorizations;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

}

