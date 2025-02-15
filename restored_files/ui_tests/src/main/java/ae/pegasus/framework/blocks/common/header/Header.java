package ae.pegasus.framework.blocks.common.header;

import ae.pegasus.framework.blocks.common.header.breadcrumb.Breadcrumb;
import ae.pegasus.framework.pages.login.LoginPage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Header {

    private Breadcrumb breadcrumb = page(Breadcrumb.class);

    public SelenideElement get() {
        return $(By.xpath(".//div[@class='pg-common']/div[@class='pg-header']"));
    }

    public SelenideElement getUserProfile() {
        return $(By.xpath("//*[@id='userprofile']"));
    }

    public Header clickUserProfile() {
        getUserProfile().click();
        return this;
    }

    public SelenideElement getUserProfileDropdownMenu() {
        return $(By.xpath("//*[@aria-labelledby='userprofile']"));
    }

    public SelenideElement getSignOut() {
        return $(By.xpath("//a[@click.trigger='logout()']"));
    }

    public LoginPage clickSignOut() {
        getSignOut().click();
        return page(LoginPage.class);
    }

    public Breadcrumb getBreadcrumb() {
        return breadcrumb;
    }

}
