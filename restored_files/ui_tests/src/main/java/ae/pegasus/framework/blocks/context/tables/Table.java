package ae.pegasus.framework.blocks.context.tables;
import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.pages.BaseSection;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.sleep;

public class Table extends BaseSection {

    Logger log = Logger.getLogger(Table.class);

    Table() {
        super(Context.baseSelector);
    }


    public SelenideElement getTableHead() {
        return base()
                .$("./div[@class='pg-thead']");
    }

    public SelenideElement getRowByIndex(int indexRow) {
        return base()
                .$(By.xpath(".//div[contains(@class, 'pg-row')][" + indexRow + "]"));
    }

    public ElementsCollection getPgRows() {
        waitLoad();
        return base().$$("div.pg-row").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    public void waitLoad() {
        getLoading().shouldBe(disappear);
        sleep(500);
    }

    public ElementsCollection getRows() {
        log.debug("Rows size: " + getPgRows().size());
        return getPgRows();
    }

    public ElementsCollection getColumns() {
        return getTableHead()
                .$$(By.xpath(".//div[contains(@class, 'pg-col')]"));
    }

    public int getColumnIndexByName(String columnName) {
        log.info("Find column by name:" + columnName + " in the Records table");

        String[] colTexts = getColumns().getTexts();
        for (int i = 0; i < colTexts.length; i++) {
            if (colTexts[i].equals(columnName)) {
                return i+1;
            }
        }

        log.warn("Column with name:" + columnName + " was not found in the table");
        throw new AssertionError("Column with name:" + columnName + " was not found in the table");
    }

    public boolean isEmpty(){
        return base().$(new Selectors.ByText("Nothing found.")).isDisplayed();
    }



}


