package ae.pegasus.framework.reporter;
import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.events.MakeAttachmentEvent;
import java.io.File;
import java.io.IOException;


public class UIReporter extends AllureReporter {

    @Attachment(type = "image/png")
    private byte[] screenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }

    @Override
    protected void takeScreenshot() {
        try {
            allure.fire(new MakeAttachmentEvent(screenshot(), "Page screenshot", "image/png"));
        } catch (Exception e) {
            log.error("Was unable to take screenshot", e);

        }

    }
}
