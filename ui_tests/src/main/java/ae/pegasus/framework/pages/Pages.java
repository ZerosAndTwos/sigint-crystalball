package ae.pegasus.framework.pages;

import ae.pegasus.framework.pages.basic_pages.AppMainPage;
import ae.pegasus.framework.pages.basic_pages.AppNavigation;
import ae.pegasus.framework.pages.basic_pages.ModalDialogPage;
import ae.pegasus.framework.pages.cbfinder.CBFinderBasePage;
import ae.pegasus.framework.pages.cbfinder.CBFinderExistEntityPage;
import ae.pegasus.framework.pages.cbfinder.CBFinderTargetPage;
import ae.pegasus.framework.pages.cbfinder.new_entity.CBFinderNewCasePage;
import ae.pegasus.framework.pages.cbfinder.new_entity.CBFinderNewFilePage;
import ae.pegasus.framework.pages.profiler.CreateTargetPage;
import ae.pegasus.framework.pages.profiler.ProfilerBasePage;
import ae.pegasus.framework.pages.profiler.tabs.*;
import ae.pegasus.framework.pages.record_assessment.MyRecordsPage;
import ae.pegasus.framework.pages.record_assessment.OrgUnitRecords;
import ae.pegasus.framework.pages.search.*;
import ae.pegasus.framework.pages.signin.SignInPage;
import ae.pegasus.framework.pages.special.ActionsWithSelected;
import ae.pegasus.framework.pages.special.SearchFilterPage;
import ae.pegasus.framework.pages.special.alert_history.ToDoListTab;
import ae.pegasus.framework.pages.special.attach_to.AssignRecordsPage;
import ae.pegasus.framework.pages.special.attach_to.AttachToOperatorReportPage;
import ae.pegasus.framework.pages.special.attach_to.AttachToTargetPage;
import ae.pegasus.framework.pages.special.create_event_record.ManualRecordPage;
import ae.pegasus.framework.pages.special.queues.QueuesBasePage;
import ae.pegasus.framework.pages.special.queues.SearchQueuesTab;
import ae.pegasus.framework.pages.special.IM_requests.master_report.UpdateMasterReportPage;
import ae.pegasus.framework.pages.special.IM_requests.master_report.VerifyMasterReportPage;
import ae.pegasus.framework.pages.special.IM_requests.operator_report.UpdateOperatorReportPage;
import ae.pegasus.framework.pages.special.IM_requests.operator_report.VerifyOperatorReportPage;
import ae.pegasus.framework.pages.special.IM_requests.request_for_information.RequestForInformationSendingDialog;
import ae.pegasus.framework.pages.special.IM_requests.request_for_information.UpdateRequestForInformationPage;
import ae.pegasus.framework.pages.special.IM_requests.request_for_information.VerifyRequestForInformationPage;
import ae.pegasus.framework.pages.special.saved_search.CreateSavedSearchPage;
import ae.pegasus.framework.pages.special.saved_search.EditSavedSearchPage;
import ae.pegasus.framework.pages.special.search_results.SearchResultDetailsPage;
import ae.pegasus.framework.pages.special.search_results.SearchResultsAsCardsPage;
import ae.pegasus.framework.pages.special.search_results.SearchResultsForScrollCheckPage;
import ae.pegasus.framework.pages.special.search_results.SearchResultsPage;

import static com.codeborne.selenide.Selenide.page;

public class Pages {

    public static SignInPage signInPage() {
        return page(SignInPage.class);
    }

    public static AppMainPage mainPage() {
        return page(AppMainPage.class);
    }

    public static AppNavigation navigation() {return AppNavigation.getAppNavigation();}

    public static ActionsWithSelected actionsWithSelected() {
        return page(ActionsWithSelected.class);
    }

    public static AttachToOperatorReportPage attachToReportPage() {
        return page(AttachToOperatorReportPage.class);
    }

    public static UpdateOperatorReportPage updateOperatorReportPage() {
        return page(UpdateOperatorReportPage.class);
    }

    public static VerifyOperatorReportPage verifyOperatorReportPage() {
        return page(VerifyOperatorReportPage.class);
    }

    public static UpdateMasterReportPage updateMasterrReportPage() {
        return page(UpdateMasterReportPage.class);
    }

    public static VerifyMasterReportPage verifyMasterReportPage() {
        return page(VerifyMasterReportPage.class);
    }


    public static UpdateRequestForInformationPage updateRequestForInformationPage() {
        return page(UpdateRequestForInformationPage.class);
    }

    public static VerifyRequestForInformationPage verifyRequestForInformationPage() {
        return page(VerifyRequestForInformationPage.class);
    }

    public static RequestForInformationSendingDialog requestForInformationSendingDialog() {
        return page(RequestForInformationSendingDialog.class);
    }

    public static SearchPage searchPage() {
        return page(SearchPage.class);
    }

    public static SearchFilterPage searchFilterPage() {
        return page(SearchFilterPage.class);
    }

    public static CreateSavedSearchPage createSavedSearchPage() {
        return page(CreateSavedSearchPage.class);
    }

    public static EditSavedSearchPage editSavedSearchPage() {
        return page(EditSavedSearchPage.class);
    }

    public static SearchResultsPage searchResultsPage() {
        return page(SearchResultsPage.class);
    }

    public static SearchResultsAsCardsPage searchResultsAsCardsPage() {
        return page(SearchResultsAsCardsPage.class);
    }

    public static SearchResultsForScrollCheckPage searchResultsForScrollCheckPage() {
        return page(SearchResultsForScrollCheckPage.class);
    }

    public static SearchResultDetailsPage searchResultDetailsPage() {
        return page(SearchResultDetailsPage.class);
    }

    public static SearchAuthorizationPage searchAuthorizationPage() {
        return page(SearchAuthorizationPage.class);
    }

    public static GeoSearchPage geoSearchPage() {
        return page(GeoSearchPage.class);
    }

    public static AdvancedSearchPage advancedSearchPage() {
        return page(AdvancedSearchPage.class);
    }

    public static SavedSearchesPage savedSearchesPage() {
        return page(SavedSearchesPage.class);
    }

    public static CBFinderBasePage cbFinderBasePage() {
        return page(CBFinderBasePage.class);
    }

    public static CBFinderNewFilePage cbFinderNewFilePage() {
        return page(CBFinderNewFilePage.class);
    }

    public static CBFinderNewCasePage cbFinderNewCasePage() { return page(CBFinderNewCasePage.class); }

    public static CBFinderExistEntityPage cbFinderExistEntityPage() {return page(CBFinderExistEntityPage.class); }

    public static CBFinderTargetPage cbFinderTargetPage() {return page(CBFinderTargetPage.class); }

    public static AttachToTargetPage attachToTargetPage() {
        return page(AttachToTargetPage.class);
    }

    public static CreateTargetPage createTargetPage() {
        return page(CreateTargetPage.class);
    }

    public static ProfilerBasePage profilerBasePage() {
        return page(ProfilerBasePage.class);
    }

    public static ProfilerSummaryTab profilerSummaryTab() {
        return page(ProfilerSummaryTab.class);
    }
    public static ProfilerDetailsTab profilerDetailsTab() {
        return page(ProfilerDetailsTab.class);
    }


    public static ProfilerCommunicationTab profilerCommunicationTab() {
        return page(ProfilerCommunicationTab.class);
    }

    public static ProfilerOpenDataTab profilerOpenDataTab() {
        return page(ProfilerOpenDataTab.class);
    }

    public static ProfilerTravelMovementTab profilerTravelMovementTab() {
        return page(ProfilerTravelMovementTab.class);
    }

    public static ProfilerMentionsTab profilerMentionsTab() {
        return page(ProfilerMentionsTab.class);
    }
    public static ProfilerNetworkTab profilerNetworkTab() {
        return page(ProfilerNetworkTab.class);
    }
        public static ProfilerTargetActivityTab profilerTargetActivityTab() {
            return page(ProfilerTargetActivityTab.class);
        }
            public static ProfilerInsightsBetaTab profilerInsightsBetaTab() {
                return page(ProfilerInsightsBetaTab.class);
            }


    public static AssignRecordsPage assignRecordsPage() {
        return page(AssignRecordsPage.class);
    }

    public static MyRecordsPage myRecordsPage() {
        return page(MyRecordsPage.class);
    }

    public static ManualRecordPage manualRecordPage() {
        return page(ManualRecordPage.class);
    }

    public static OrgUnitRecords orgUnitRecords() {
        return page(OrgUnitRecords.class);
    }

    public static ModalDialogPage modalDialogPage() {
        return page(ModalDialogPage.class);
    }

    public static QueuesBasePage queuesBasePage() {
        return page(QueuesBasePage.class);
    }

    public static SearchQueuesTab searchQueuesTab() {
        return page(SearchQueuesTab.class);
    }

    public static ToDoListTab toDoListTab() {
        return page(ToDoListTab.class);
    }
}
