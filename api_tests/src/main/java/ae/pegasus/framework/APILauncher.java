package ae.pegasus.framework;

import ae.pegasus.framework.reporter.AllureReporter;

public class APILauncher extends G4Embeddable {

    private static AllureReporter reporter = new AllureReporter();

    public APILauncher() {
        super(reporter);
    }
}
