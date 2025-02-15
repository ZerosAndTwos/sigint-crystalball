package ae.pegasus.framework.pages.special.IM_requests.master_report;

import ae.pegasus.framework.pages.special.IM_requests.operator_report.VerifyOperatorReportPage;
import ae.pegasus.framework.utils.PageUtils;

import java.util.List;

public class VerifyMasterReportPage extends VerifyOperatorReportPage {

    public String getMasterReportOriginatingReportsBlockHeading() {
        return getMasterReportOriginatingReportsBlock().$x(".//pg-col").getText();
    }

    public List<String> getMasterReportOriginatingReportsBlockTableCol() {
        return PageUtils.convertElementsCollectionToStringList(getMasterReportOriginatingReportsBlock().$$x(".//pg-field//div[@class ='pg-head-row-small']//div[contains(@class,'pg-col')]"), true,true);

    }

    public List<String> getMasterReportOriginatingReportsValues() {
        return PageUtils.convertElementsCollectionToStringList(getMasterReportOriginatingReportsBlock().$$x(".//div[@class ='pg-tbody']//div[contains(@class,'pg-col')]"), true,true);

    }
}
