package ae.pegasus.framework.blocks.context.tables;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public abstract class Row {


    protected SelenideElement row;
    Logger log = Logger.getRootLogger();

    public Row(SelenideElement row) {
        this.row = row;
    }

    private ElementsCollection getColumns() {
        return $$(By.xpath("//div[@class='pg-thead']" +
                "//div[contains(@class, 'pg-col')]"));
    }

    public SelenideElement getCellByColumnName(String columnName) {
        log.debug("Find cell by column name:" + columnName + " in the table");

        String[] colTexts = getColumns().getTexts();
        for (int i = 0; i < colTexts.length; i++) {
            if (colTexts[i].equals(columnName)) {
                return row
                        .$(By.xpath("./div[contains(@class, 'pg-col')][" + (i+1) + "]"));
            }
        }

        log.warn("Column with name:" + columnName + " was not found in the table");
        throw new AssertionError("Column with name:" + columnName + " was not found in the table");
    }

    public SelenideElement getCheckBox() {
        return row
                .$(By.xpath(".//input[@type='checkbox']"));
    }

    public Boolean isSelected() {
        return getCheckBox().isSelected();
    }

}
