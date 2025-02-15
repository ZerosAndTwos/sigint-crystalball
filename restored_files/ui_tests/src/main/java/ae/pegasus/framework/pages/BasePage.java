package ae.pegasus.framework.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {

    public void WaitForAjax() {
        while (true) {
            boolean ajaxIsComplete = (boolean) ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return jQuery.active == 0");
            if (ajaxIsComplete) {
                break;
            }
        }
    }

    /**
     * <div class="page-loading text-center">
     * <i class="fa fa-gear fa-spin"></i> Loading...
     * </div>
     */
    public static SelenideElement getPageLoading() {
        return $(By.xpath(".//div[@class='page-loading text-center']"));
    }

}
