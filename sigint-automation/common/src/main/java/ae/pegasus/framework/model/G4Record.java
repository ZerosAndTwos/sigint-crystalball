package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataProvider;
import ae.pegasus.framework.data_for_entity.data_providers.PhonesProvider;

/**
 *
 */
public class G4Record extends G4Entity {

    @DataProvider(PhonesProvider.class)
    private String fromNumber;
    @DataProvider(PhonesProvider.class)
    private String toNumber;

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }
}
