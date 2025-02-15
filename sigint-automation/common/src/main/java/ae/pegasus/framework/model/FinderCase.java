package ae.pegasus.framework.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class FinderCase extends FinderFile {

    public FinderCase() {
        setType(ProfilerJsonType.Case);
        setBaseType(ProfilerJsonType.Case);
    }
}
