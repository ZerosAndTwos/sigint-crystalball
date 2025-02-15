package junit_custom;

import org.jbehave.core.junit.JUnitStories;
import org.junit.Test;

public abstract class CustomStories extends JUnitStories {

    @Test
    public void run() {
        try {
            super.run();
        } catch (Throwable throwable) {
            // Jira/Zephyr integration
            // if (new Statistic().hasFailuresWithoutBugs()) {
                throw new AssertionError(throwable);
            // }
        }
    }

}
