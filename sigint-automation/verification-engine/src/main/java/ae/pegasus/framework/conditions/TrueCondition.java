package ae.pegasus.framework.conditions;

public class TrueCondition implements ExpectedCondition {

    private Boolean element;

    public String toString() {return "True condition";}

    public TrueCondition element(Boolean element) {
        this.element = element;
        return this;
    }

    public Boolean check() {
        return element;
    }
}
