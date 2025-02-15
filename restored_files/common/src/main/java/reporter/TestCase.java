package reporter;

import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCase {

    private String title;
    private String status;
    private String url;
    private HashMap<Integer, Step> steps = new HashMap<>();
    private Logger log = Logger.getLogger(TestCase.class);

    public String getTitle() {
        return title;
    }

    public TestCase setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TestCase setStatus(String status) {
        this.status = status;
        return this;
    }

    public Step get(Integer stepNumber) {
        return steps.get(stepNumber);
    }

    public Step add(Step step) {
        return steps.put(step.getNumber(), step);
    }

    public Step update(Step step) {
        return steps.put(step.getNumber(), step);
    }

    public Step remove(int stepNumber) {
        return steps.remove(stepNumber);
    }

    public List<Step> getSteps() {
        return new ArrayList<>(steps.values());
    }

    public TestCase setSteps(HashMap<Integer, Step> steps) {
        this.steps = steps;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TestCase setUrl(String url) {
        this.url = url;
        return this;
    }

    private List<Step> getStepsByStatus(String status) {
        log.debug("Getting '" + status + "' steps for test: " + title);
        List<Step> stepList = new ArrayList<>();
        for (Step step : getSteps()) {
            if (step.getStatus().equals(status)) {
                if (!stepList.add(step)) {
                    log.warn("Step: " + step.getName() + ", does not added into '" + status + "' steps list");
                }
            }
        }
        return stepList;
    }

    public List<Step> getFailedSteps() {
        List<Step> failedSteps = getStepsByStatus("failed");
        failedSteps.addAll(getBrokenSteps());
        return failedSteps;
    }

    public List<Step> getPassedSteps() {
        return getStepsByStatus("passed");
    }

    public List<Step> getBrokenSteps() {
        return getStepsByStatus("broken");
    }

    public List<Step> getSkippedSteps() {
        return getStepsByStatus("skipped");
    }

    private Function<Step, String> stepNames = new Function<Step, String>() {
        @Override
        public String apply(Step from) {
            return from.getName();
        }
    };

    public List<String> getStepsNames(List<Step> steps) {
        return Lists.transform(steps,stepNames);

    }

}
