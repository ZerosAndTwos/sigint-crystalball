package ae.pegasus.framework.pages.records;


import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.pages.SigintPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.errors.NullReturnException;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class RecordDetailsDialog extends SigintPage {

    private SelenideElement getRecordDetailsDialog() {
        return $(By.xpath("//div[@id='recordDetailsDialog']"));
    }

    private SelenideElement getAttachButton() {
        return getRecordDetailsDialog()
                .$(By.xpath(".//attach-to-report/div/button"));
    }

    public RecordDetailsDialog clickAttachButton() {
        getAttachButton().should(Condition.visible).click();
        return this;
    }

    private List<Attachment> getAttachListElements() {
        List<Attachment> attachments = new ArrayList<>();
        ElementsCollection collection= getRecordDetailsDialog().should(Condition.visible)
                .$("attach-to-report ul.dropdown-menu").$$("li").shouldHave(CollectionCondition.sizeGreaterThan(0));

        for (SelenideElement element: collection) {
            attachments.add(new Attachment(element));
        }
        return attachments;
    }

    public void selectReportInAttatchmentsList(String reportSubject) throws NullReturnException {
        List<Attachment> attachments = getAttachListElements();
        for (Attachment attachment:attachments) {
            if(attachment.getName().equalsIgnoreCase(reportSubject)) {
                attachment.getLink().click();
                return;
            }
        }
        throw new NullReturnException("No reports are found with subject "+ reportSubject);
    }

    @Override
    public Context context() {
        return null;
    }


    private class Attachment {

        private SelenideElement element;

        private Attachment(SelenideElement element) {
            this.element = element;
        }

        public String getName() {
            return element.$(By.xpath(".//div[@textcontent.bind='r.subject']")).getText();
        }

        public SelenideElement getLink() {
            return element.$("a");
        }

    }
}
