package ae.pegasus.framework.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public abstract class BasicElement implements SelenideElement {
    private final SelenideElement wrappedElement;

    public BasicElement (SelenideElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    @Override
    @Deprecated
    public void followLink() {
        wrappedElement.followLink();
    }

    @Override
    public SelenideElement setValue(String text) {
        return wrappedElement.setValue(text);
    }

    @Override
    public SelenideElement val(String text) {
        return wrappedElement.val(text);
    }

    @Override
    public SelenideElement append(String text) {
        return wrappedElement.append(text);
    }

    @Override
    public SelenideElement pressEnter() {
        return wrappedElement.pressEnter();
    }

    @Override
    public SelenideElement pressTab() {
        return wrappedElement.pressTab();
    }

    @Override
    public SelenideElement pressEscape() {
        return wrappedElement.pressEscape();
    }

    @Override
    public String getText() {
        return wrappedElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return wrappedElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return wrappedElement.findElement(by);
    }

    @Override
    public String text() {
        return wrappedElement.text();
    }

    @Override
    public String innerText() {
        return wrappedElement.innerText();
    }

    @Override
    public String innerHtml() {
        return wrappedElement.innerHtml();
    }

    @Override
    public String attr(String attributeName) {
        return wrappedElement.attr(attributeName);
    }

    @Override
    public String name() {
        return wrappedElement.name();
    }

    @Override
    public String val() {
        return wrappedElement.val();
    }

    @Override
    public String getValue() {
        return wrappedElement.getValue();
    }

    @Override
    public SelenideElement selectRadio(String value) {
        return wrappedElement.selectRadio(value);
    }

    @Override
    public String data(String dataAttributeName) {
        return wrappedElement.data(dataAttributeName);
    }

    @Override
    public boolean exists() {
        return wrappedElement.exists();
    }

    @Override
    public boolean isDisplayed() {
        return wrappedElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return wrappedElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return wrappedElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return wrappedElement.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return wrappedElement.getCssValue(propertyName);
    }

    @Override
    public boolean is(Condition condition) {
        return wrappedElement.is(condition);
    }

    @Override
    public boolean has(Condition condition) {
        return wrappedElement.has(condition);
    }

    @Override
    public SelenideElement setSelected(boolean selected) {
        return wrappedElement.setSelected(selected);
    }

    @Override
    public SelenideElement should(Condition... condition) {
        return wrappedElement.should(condition);
    }

    @Override
    public SelenideElement shouldHave(Condition... condition) {
        return wrappedElement.shouldHave(condition);
    }

    @Override
    public SelenideElement shouldBe(Condition... condition) {
        return wrappedElement.shouldBe(condition);
    }

    @Override
    public SelenideElement shouldNot(Condition... condition) {
        return wrappedElement.shouldNot(condition);
    }

    @Override
    public SelenideElement shouldNotHave(Condition... condition) {
        return wrappedElement.shouldNotHave(condition);
    }

    @Override
    public SelenideElement shouldNotBe(Condition... condition) {
        return wrappedElement.shouldNotBe(condition);
    }

    @Override
    public SelenideElement waitUntil(Condition condition, long timeoutMilliseconds) {
        return wrappedElement.waitUntil(condition, timeoutMilliseconds);
    }

    @Override
    public SelenideElement waitUntil(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        return wrappedElement.waitUntil(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
    }

    @Override
    public SelenideElement waitWhile(Condition condition, long timeoutMilliseconds) {
        return wrappedElement.waitWhile(condition, timeoutMilliseconds);
    }

    @Override
    public SelenideElement waitWhile(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        return wrappedElement.waitWhile(condition, timeoutMilliseconds,pollingIntervalMilliseconds);
    }

    @Override
    public SelenideElement parent() {
        return wrappedElement.parent();
    }

    @Override
    public SelenideElement closest(String tagOrClass) {
        return wrappedElement.closest(tagOrClass);
    }

    @Override
    public SelenideElement find(String cssSelector) {
        return wrappedElement.find(cssSelector);
    }

    @Override
    public SelenideElement find(String cssSelector, int index) {
        return wrappedElement.find(cssSelector, index);
    }

    @Override
    public SelenideElement find(By selector) {
        return wrappedElement.find(selector);
    }

    @Override
    public SelenideElement find(By selector, int index) {
        return wrappedElement.find(selector, index);
    }

    @Override
    public SelenideElement $(String cssSelector) {
        return wrappedElement.$(cssSelector);
    }

    @Override
    public SelenideElement $(String cssSelector, int index) {
        return wrappedElement.$(cssSelector, index);
    }

    @Override
    public SelenideElement $(By selector) {
        return wrappedElement.$(selector);
    }

    @Override
    public SelenideElement $(By selector, int index) {
        return wrappedElement.$(selector, index);
    }

    @Override
    public SelenideElement $x(String xpath) {
        return wrappedElement.$x(xpath);
    }

    @Override
    public SelenideElement $x(String xpath, int index) {
        return wrappedElement.$x(xpath, index);
    }

    @Override
    public ElementsCollection findAll(String cssSelector) {
        return wrappedElement.findAll(cssSelector);
    }

    @Override
    public ElementsCollection findAll(By selector) {
        return wrappedElement.findAll(selector);
    }

    @Override
    public ElementsCollection $$(String cssSelector) {
        return wrappedElement.$$(cssSelector);
    }

    @Override
    public ElementsCollection $$(By selector) {
        return wrappedElement.$$(selector);
    }

    @Override
    public ElementsCollection $$x(String xpath) {
        return wrappedElement.$$x(xpath);
    }

    @Override
    public File uploadFromClasspath(String... fileName) {
        return wrappedElement.uploadFromClasspath(fileName);
    }

    @Override
    public File uploadFile(File... file) {
        return wrappedElement.uploadFile(file);
    }

    @Override
    public void selectOption(int... index) {
        wrappedElement.selectOption(index);
    }

    @Override
    public void selectOption(String... text) {
        wrappedElement.selectOption(text);
    }

    @Override
    public void selectOptionContainingText(String text) {
        wrappedElement.selectOptionContainingText(text);
    }

    @Override
    public void selectOptionByValue(String... value) {
        wrappedElement.selectOptionByValue(value);
    }

    @Override
    public SelenideElement getSelectedOption() throws NoSuchElementException {
        return wrappedElement.getSelectedOption();
    }

    @Override
    public ElementsCollection getSelectedOptions() {
        return wrappedElement.getSelectedOptions();
    }

    @Override
    public String getSelectedValue() {
        return wrappedElement.getSelectedValue();
    }

    @Override
    public String getSelectedText() {
        return wrappedElement.getSelectedText();
    }

    @Override
    public SelenideElement scrollTo() {
        return wrappedElement.scrollTo();
    }

    @Override
    public SelenideElement scrollIntoView(boolean alignToTop) {
        return wrappedElement.scrollIntoView(alignToTop);
    }

    @Override
    public SelenideElement scrollIntoView(String scrollIntoViewOptions) {
        return wrappedElement.scrollIntoView(scrollIntoViewOptions);
    }

    @Override
    public File download() throws FileNotFoundException {
        return wrappedElement.download();
    }

    @Override
    public String getSearchCriteria() {
        return wrappedElement.getSearchCriteria();
    }

    @Override
    public WebElement toWebElement() {
        return wrappedElement.toWebElement();
    }

    @Override
    public WebElement getWrappedElement() {
        return wrappedElement.getWrappedElement();
    }

    @Override
    public void click() {
        wrappedElement.click();
    }

    @Override
    public void submit() {
        wrappedElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        wrappedElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        wrappedElement.clear();
    }

    @Override
    public String getTagName() {
        return wrappedElement.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return wrappedElement.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return wrappedElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return wrappedElement.isEnabled();
    }

    @Override
    public void click(int offsetX, int offsetY) {
        wrappedElement.click(offsetX, offsetY);
    }

    @Override
    public SelenideElement contextClick() {
        return wrappedElement.contextClick();
    }

    @Override
    public SelenideElement doubleClick() {
        return wrappedElement.doubleClick();
    }

    @Override
    public SelenideElement hover() {
        return wrappedElement.hover();
    }

    @Override
    public SelenideElement dragAndDropTo(String targetCssSelector) {
        return wrappedElement.dragAndDropTo(targetCssSelector);
    }

    @Override
    public SelenideElement dragAndDropTo(WebElement target) {
        return wrappedElement.dragAndDropTo(target);
    }

    @Override
    public boolean isImage() {
        return wrappedElement.isImage();
    }

    @Override
    public File screenshot() {
        return wrappedElement.screenshot();
    }

    @Override
    public BufferedImage screenshotAsImage() {
        return wrappedElement.screenshotAsImage();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return wrappedElement.getScreenshotAs(target);
    }

    @Override
    public Coordinates getCoordinates() {
        return wrappedElement.getCoordinates();
    }

    @Override
    public WebElement findElementByClassName(String using) {
        return wrappedElement.findElementByClassName(using);
    }

    @Override
    public List<WebElement> findElementsByClassName(String using) {
        return wrappedElement.findElementsByClassName(using);
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        return wrappedElement.findElementByCssSelector(using);
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        return wrappedElement.findElementsByCssSelector(using);
    }

    @Override
    public WebElement findElementById(String using) {
        return wrappedElement.findElementById(using);
    }

    @Override
    public List<WebElement> findElementsById(String using) {
        return wrappedElement.findElementsById(using);
    }

    @Override
    public WebElement findElementByLinkText(String using) {
        return wrappedElement.findElementByLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByLinkText(String using) {
        return wrappedElement.findElementsByLinkText(using);
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        return wrappedElement.findElementByPartialLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        return wrappedElement.findElementsByPartialLinkText(using);
    }

    @Override
    public WebElement findElementByName(String using) {
        return wrappedElement.findElementByName(using);
    }

    @Override
    public List<WebElement> findElementsByName(String using) {
        return wrappedElement.findElementsByName(using);
    }

    @Override
    public WebElement findElementByTagName(String using) {
        return wrappedElement.findElementByTagName(using);
    }

    @Override
    public List<WebElement> findElementsByTagName(String using) {
        return wrappedElement.findElementsByTagName(using);
    }

    @Override
    public WebElement findElementByXPath(String using) {
        return wrappedElement.findElementByXPath(using);
    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        return wrappedElement.findElementsByXPath(using);
    }

    @Override
    public WebDriver getWrappedDriver() {
        return wrappedElement.getWrappedDriver();
    }
}
