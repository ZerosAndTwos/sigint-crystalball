package ae.pegasus.framework.model;

import java.util.List;

public class UploadDetails {

    private ae.pegasus.framework.model.Process process;
    private MatchingContext matching;
    private List<MatchingResult> searchResults;

    public ae.pegasus.framework.model.Process getProcess() {
        return process;
    }

    public void setProcess(ae.pegasus.framework.model.Process process) {
        this.process = process;
    }

    public MatchingContext getMatching() {
        return matching;
    }

    public void setMatching(MatchingContext matching) {
        this.matching = matching;
    }

    public List<MatchingResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<MatchingResult> searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * Get matching result by Target name and TargetResultType
     * @param name target name
     * @param targetResultType TargetResultType
     * @return MatchingResult or null if none found
     */
    public MatchingResult findMatchingResultByTargetNameAndTargetResultType(String name, TargetResultType targetResultType) {
        List<MatchingResult> matchingResults = searchResults;
        for (MatchingResult result : matchingResults) {
            if (result.getTarget().getName().equals(name) && result.getTargetResultType().equals(targetResultType)) {
                return result;
            }
        }
        return null;
    }
}
