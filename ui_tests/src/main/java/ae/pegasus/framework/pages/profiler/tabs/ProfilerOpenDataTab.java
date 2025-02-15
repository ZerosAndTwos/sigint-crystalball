package ae.pegasus.framework.pages.profiler.tabs;

import ae.pegasus.framework.utils.TimeUtils;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import ae.pegasus.framework.constants.profiler.TargetSummaryParameter;
import ae.pegasus.framework.pages.profiler.ProfilerBasePage;
import ae.pegasus.framework.utils.PageUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.constants.CommonXPaths.LOADING_BASE_XPATH;
import static ae.pegasus.framework.constants.profiler.ProfilerTab.SUMMARY;
import static ae.pegasus.framework.constants.profiler.ProfilerWidget.*;
import static ae.pegasus.framework.constants.profiler.TargetSummaryParameter.PHOTO;
import static ae.pegasus.framework.constants.profiler.TargetSummaryParameter.TARGET_STATUS;

public class ProfilerOpenDataTab extends ProfilerDetailsTab {



}
