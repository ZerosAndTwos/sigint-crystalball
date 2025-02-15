package ae.pegasus.framework.steps;

import ae.pegasus.framework.constants.navigation.MainMenuChildItem;
import ae.pegasus.framework.constants.navigation.MainMenuRootItem;
import org.jbehave.core.annotations.Given;
import ae.pegasus.framework.pages.Pages;

public class UINavigationSteps {
    private void navigateTo(MainMenuRootItem item) {
        Pages.navigation().navigateTo(item);
    }
    private void navigateTo(MainMenuChildItem item) {
        Pages.navigation().navigateTo(item);
    }

    @Given("I navigate to Search")
    public void navigateToSearch() {
        navigateTo(MainMenuChildItem.SEARCH);
    }

    @Given("I navigate to Geo Search")
    public void navigateToGeoSearch() {
        navigateTo(MainMenuChildItem.GEO_SEARCH);
    }

    @Given("I navigate to Advanced Search")
    public void navigateToAdvancedSearch() {
        navigateTo(MainMenuChildItem.ADVANCED_SEARCH);
    }

    @Given("I navigate to Saved Searches")
    public void navigateToSavedSearches() {
        navigateTo(MainMenuChildItem.SAVED_SEARCHES);
        Pages.savedSearchesPage().waitForPageLoading();
    }

    @Given("I navigate to CB Finder")
    public void navigateToCBFinder() {
        navigateTo(MainMenuRootItem.CB_FINDER);
        Pages.cbFinderBasePage().waitSidePanelLoading();
    }

    @Given("I navigate to Create Target")
    public void navigateToCreateTarget() {
        navigateTo(MainMenuChildItem.CREATE_TARGET);
    }

    @Given("I navigate to Funnel")
    public void navigateToFunnel() {
        navigateTo(MainMenuRootItem.FUNNEL);
    }

    @Given("I navigate to Sandbox")
    public void navigateToSandbox() {
        navigateTo(MainMenuRootItem.SANDBOX);
    }

    @Given("I navigate to My Records")
    public void navigateToMyRecords() {
        navigateTo(MainMenuChildItem.MY_RECORDS);
        Pages.myRecordsPage().waitForPageLoading();
    }

    @Given("I navigate to OrgUnit Records")
    public void navigateToTeamRecords() {
        navigateTo(MainMenuChildItem.OrgUnit_RECORDS);
        Pages.orgUnitRecords().waitForPageLoading();
    }

    @Given("I navigate to Workloads")
    public void navigateToWorkloads() {
        navigateTo(MainMenuChildItem.WORKLOADS);
    }

    @Given("I navigate to Profile Dashboard")
    public void navigateToTargetDashboard() {
        navigateTo(MainMenuChildItem.PROFILE_DASHBOARD);
    }

    @Given("I navigate to User Performance Dashboard")
    public void navigateToUserPerformanceDashboard() {
        navigateTo(MainMenuChildItem.USER_PERFORMANCE_DASHBOARD);
    }

    @Given("I navigate to IM Dashboard")
    public void navigateToIMDashboard() {
        navigateTo(MainMenuChildItem.IM_DASHBOARD);
    }

    @Given("I navigate to Custom Dashboards")
    public void navigateToCustomDashboards() {
        navigateTo(MainMenuChildItem.CUSTOM_DASHBOARDS);
    }

    @Given("I navigate to Sentiment Analysis")
    public void navigateToSentimentAnalysis() {
        navigateTo(MainMenuChildItem.SENTIMENT_ANALYSIS);
    }

    @Given("I navigate to Tweet Life")
    public void navigateToTweetLife() {
        navigateTo(MainMenuChildItem.TWEET_LIFE);
    }

    @Given("I navigate to First Instance Identification")
    public void navigateToFirstInstanceIdentification() {
        navigateTo(MainMenuChildItem.FIRST_INSTANCE_IDENTIFICATION);
    }

    @Given("I navigate to Twitter Discovery")
    public void navigateToTwitterDiscovery() {
        navigateTo(MainMenuChildItem.TWITTER_DISCOVERY);
    }

    @Given("I navigate to Monitoring Deck")
    public void navigateToMonitoringDeck() {
        navigateTo(MainMenuChildItem.MONITORING_DECK);
    }

    @Given("I navigate to Org Units And Users")
    public void navigateToTeamsAndUsers() {
        navigateTo(MainMenuChildItem.ORG_UNIT_AND_USER);
    }

    @Given("I navigate to Title Management")
    public void navigateToTitleManagement() {
        navigateTo(MainMenuChildItem.TITLE_MANAGEMENT);
    }

    @Given("I navigate to Responsibilities")
    public void navigateToResponsibilities() {
        navigateTo(MainMenuChildItem.RESPONSIBILITIES);
    }

    @Given("I navigate to User Privilege Auditing")
    public void navigateToUserPrivilegeAuditing() {
        navigateTo(MainMenuChildItem.USER_PRIVILEGE_AUDITING);
    }

    @Given("I navigate to Upload Data")
    public void navigateToUploadData() {
        navigateTo(MainMenuChildItem.UPLOAD_DATA);
    }

    @Given("I navigate to Search Data Files")
    public void navigateToSearchDataFiles() {
        navigateTo(MainMenuChildItem.SEARCH_DATA_FILES);
    }

    @Given("I navigate to Data Sub-Source Management")
    public void navigateToDataSubSourceManagement() {
        navigateTo(MainMenuChildItem.DATA_SUB_SOURCE_MANAGEMENT);
    }

    @Given("I navigate to Designations List")
    public void navigateToDesignationsList() {
        navigateTo(MainMenuChildItem.DESIGNATIONS_LIST);
    }

    @Given("I navigate to Designations Mapping")
    public void navigateToDesignationsMapping() {
        navigateTo(MainMenuChildItem.DESIGNATIONS_MAPPING);
    }

    @Given("I navigate to Whitelist")
    public void navigateToWhitelist() {
        navigateTo(MainMenuChildItem.WHITELIST);
    }

    @Given("I navigate to Tag Management")
    public void navigateToTagManagement() {
        navigateTo(MainMenuChildItem.TAG_MANAGEMENT);
    }
}