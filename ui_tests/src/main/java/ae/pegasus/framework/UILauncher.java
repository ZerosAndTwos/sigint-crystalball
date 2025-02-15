package ae.pegasus.framework;

import ae.pegasus.framework.reporter.AllureReporter;
import ae.pegasus.framework.support.reporter.UIReporter;
import ae.pegasus.framework.support.story_parser.SpecialStoryParser;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

public class UILauncher extends G4Embeddable {

    private static AllureReporter reporter = new UIReporter();

    public UILauncher() {
        super(reporter);
    }

    @Override
    public Configuration configuration() {
        Configuration basicConfiguration = super.configuration();
        Configuration resultConfiguration = basicConfiguration
                .useStoryParser(new SpecialStoryParser(new ExamplesTableFactory(new LocalizedKeywords(),
                        new LoadFromClasspath(this.getClass()), new ParameterConverters(), new ParameterControls(), new TableTransformers())))
                .useStoryReporterBuilder(basicConfiguration.storyReporterBuilder().withFailureTrace(true));
        return resultConfiguration;
    }

    @Override
    protected List<String> storyPaths() {
        List<String> paths = new StoryFinder()
                .findPaths(codeLocationFromClass(this.getClass()),
                        "**/UI*.story",
                        "**/excluded*.story"
                );
        return paths;
    }
}
