package ae.pegasus.framework.pages.search;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class GeoSearchPage extends BasePage {
    private final String DRAW_CIRCLE_BUTTON_TOOLTIP = "Draw Circle";
    private final String DRAW_POLYGON_BUTTON_TOOLTIP = "Draw Polygon";
    private final String RESET_DRAW_BUTTON_TOOLTIP = "Reset Draw";

    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Geo Search")
                && getMapToolbarButtonByTooltip(DRAW_CIRCLE_BUTTON_TOOLTIP).isDisplayed()
                && getMapToolbarButtonByTooltip(DRAW_POLYGON_BUTTON_TOOLTIP).isDisplayed()
                && getMapToolbarButtonByTooltip(RESET_DRAW_BUTTON_TOOLTIP).isDisplayed();
    }

    private SelenideElement getTopLeftMapToolbarContainer() {
        return $x("//pg-map-toolbars//div[contains(@class, 'pg-map__toolbar--top-left')]");
    }

    private SelenideElement getMapToolbarButtonByTooltip(String tooltip) {
        return getTopLeftMapToolbarContainer().$x(".//pg-btn[@pg-tooltip='" + tooltip + "']");
    }
}
