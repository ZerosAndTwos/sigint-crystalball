package ae.pegasus.framework.support.story_parser;

import ae.pegasus.framework.story_parsing.BasicStoryParser;
import org.jbehave.core.model.ExamplesTableFactory;
import ae.pegasus.framework.steps.UISteps;

import java.util.ArrayList;
import java.util.List;

public class SpecialStoryParser extends BasicStoryParser {

    private boolean skipAddingOfJsErrorStep = true;

    public SpecialStoryParser(ExamplesTableFactory examplesTableFactory) {
        super(examplesTableFactory);
    }

    @Override
    protected List<String> addConditionalSteps(String stepToAdd) {
        List<String> result = new ArrayList<>();
        result.addAll(addCheckJsErrorStep(stepToAdd));
        return result;
    }

    private List<String> addCheckJsErrorStep (String currentStep) {
        List<String> result = new ArrayList<>();
        if (skipAddingOfJsErrorStep) {
            if (currentStep != null) {
                skipAddingOfJsErrorStep = false;
            }
        } else {
            if (currentStep == null) {
                skipAddingOfJsErrorStep = true;
            }

            if (currentStep == null ||
                    !UISteps.CHECK_JS_ERRORS_STEP_FULL_DEFINITION.equals(currentStep)) {
                result.add(UISteps.CHECK_JS_ERRORS_STEP_FULL_DEFINITION);
            }
        }
        return result;
    }
}
