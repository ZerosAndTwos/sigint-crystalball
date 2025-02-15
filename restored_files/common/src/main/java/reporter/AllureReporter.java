package reporter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import model.ScenarioContext;
import org.apache.log4j.Logger;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.model.GivenStories;
import org.jbehave.core.model.Lifecycle;
import org.jbehave.core.model.Meta;
import org.jbehave.core.model.Narrative;
import org.jbehave.core.model.OutcomesTable;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.model.StoryDuration;
import org.jbehave.core.reporters.StoryReporter;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.config.AllureModelUtils;
import ru.yandex.qatools.allure.events.ClearStepStorageEvent;
import ru.yandex.qatools.allure.events.MakeAttachmentEvent;
import ru.yandex.qatools.allure.events.StepCanceledEvent;
import ru.yandex.qatools.allure.events.StepFailureEvent;
import ru.yandex.qatools.allure.events.StepFinishedEvent;
import ru.yandex.qatools.allure.events.StepStartedEvent;
import ru.yandex.qatools.allure.events.TestCaseFailureEvent;
import ru.yandex.qatools.allure.events.TestCaseFinishedEvent;
import ru.yandex.qatools.allure.events.TestCasePendingEvent;
import ru.yandex.qatools.allure.events.TestCaseStartedEvent;
import ru.yandex.qatools.allure.events.TestSuiteFinishedEvent;
import ru.yandex.qatools.allure.events.TestSuiteStartedEvent;


public class AllureReporter implements StoryReporter {

    Allure allure = Allure.LIFECYCLE;
    private final Map<String, String> suites = new HashMap<>();
    private String uid;
    static Logger log = Logger.getLogger(AllureReporter.class);
    private ScenarioContext context = ScenarioContext.get();

    @Override
    public void beforeStory(Story story, boolean givenStory) {
        uid = generateSuiteUid(story);
        TestSuiteStartedEvent event = new TestSuiteStartedEvent(uid, story.getName());
        event.withLabels(AllureModelUtils.createTestFrameworkLabel("JBehave"));
        event.withTitle(story.getName());
        allure.fire(event);
    }

    @Override
    public void afterStory(boolean givenStory) {
        allure.fire(new TestSuiteFinishedEvent(uid));
    }

    @Override
    public void beforeScenario(String scenarioTitle) {
        allure.fire(new TestCaseStartedEvent(uid, scenarioTitle));
        allure.fire(new ClearStepStorageEvent());
        context.setScenarioTitle(scenarioTitle);
    }

    @Override
    public void beforeStep(String step) {
        allure.fire(new StepStartedEvent(step).withTitle(step));
    }

    @Override
    public void successful(String step) {
        allure.fire(new StepFinishedEvent());
    }

    @Override
    public void ignorable(String step) {
        allure.fire(new StepCanceledEvent());
    }

    @Override
    public void comment(String s) {

    }

    @Override
    public void notPerformed(String step) {
        allure.fire(new StepCanceledEvent());
    }

    @Override
    public void failed(String step, Throwable cause) {
        if (true) {
//        if (!new Statistic().hasOpenedBug(context.getScenarioTitle())) {
            allure.fire(new StepFailureEvent().withThrowable(cause.getCause()));
            makeStepFailedAttachment();
            allure.fire(new TestCaseFailureEvent().withThrowable(cause.getCause()));
            allure.fire(new StepFinishedEvent());
            allure.fire(new TestCaseFinishedEvent());
        } else {
            allure.fire(new StepCanceledEvent());
            //allure.fire(new TestCaseCanceledEvent());
        }
    }


    @Override
    public void pending(String step) {
        allure.fire(new StepCanceledEvent());
        allure.fire(new TestCasePendingEvent().withMessage("PENDING"));
    }


    @Override
    public void afterScenario() {
        allure.fire(new TestCaseFinishedEvent());
    }


    @Override
    public void storyNotAllowed(Story story, String filter) {
        //TODO
        //Allure doesn't support this
    }


    @Override
    public void storyCancelled(Story story, StoryDuration storyDuration) {

    }

    @Override
    public void narrative(Narrative narrative) {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void lifecyle(Lifecycle lifecycle) {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void scenarioNotAllowed(Scenario scenario, String filter) {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void scenarioMeta(Meta meta) {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void givenStories(GivenStories givenStories) {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void givenStories(List<String> storyPaths) {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void beforeExamples(List<String> steps, ExamplesTable table) {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void example(Map<String, String> tableRow) {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void afterExamples() {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void failedOutcomes(String step, OutcomesTable table) {

    }

    @Override
    public void restarted(String step, Throwable cause) {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void restartedStory(Story story, Throwable throwable) {

    }

    @Override
    public void dryRun() {
        //TODO
        //Allure doesn't support this
    }

    @Override
    public void pendingMethods(List<String> methods) {
        //TODO
        //Allure doesn't support this
    }

    /**
     * Generate suite uid.
     *
     * @param story the story
     * @return the string
     */
    public String generateSuiteUid(Story story) {
        String uId = UUID.randomUUID().toString();
        synchronized (getSuites()) {
            getSuites().put(story.getPath(), uId);
        }
        return uId;
    }

    public Map<String, String> getSuites() {
        return suites;
    }

    protected void takeScreenshot() {
    }

    protected void attachLogs() {
        ByteArrayOutputStream zipArchive;
        ServicesLogs logs = new ServicesLogs();
        try {
            zipArchive = new Zipper().zip(logs.getOutputLog());
            byte[] zip = zipArchive.toByteArray();
            allure.fire(new MakeAttachmentEvent(zip, "logs", "application/zip"));
        } catch (IOException e) {
            log.error("Error occurred when getting logs of G4 services");
            log.error(e.getMessage());
        }
    }

    public void makeStepFailedAttachment() {
        takeScreenshot();
        attachLogs();
    }


}
