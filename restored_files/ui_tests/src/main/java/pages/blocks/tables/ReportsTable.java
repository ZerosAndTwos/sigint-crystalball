package pages.blocks.tables;

import com.codeborne.selenide.SelenideElement;
import pages.blocks.tables.toolbar.ReportsTableToolbar;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.page;

public class ReportsTable extends Table {

    private ArrayList<ReportRow> reportRows = new ArrayList<>();

    public ReportsTableToolbar getTableToolbar() {
        return page(ReportsTableToolbar.class);
    }

    public ArrayList<ReportRow> getReports() {
        ArrayList<ReportRow> reportRows = new ArrayList<>();
        for (SelenideElement row : getRows()) {
            reportRows.add(new ReportRow(row));
        }
        return reportRows;
    }

    public ReportRow getReportByColumnNameAndValue(String columnName, String columnValue) {
        log.debug("Finding report by column name:" + columnName + " and value:" + columnValue + " in the Reports table");

        for (ReportRow reportRow : getReports()) {
            if (reportRow.getCellByColumnName(columnName).getText().contentEquals(columnValue)) {
                return reportRow;
            }
        }
        log.warn("Report with column name:" + columnName + " and value:" + columnValue + " was not found in the Reports table!");
        return null;
    }

    public ReportRow firstReport() {
        return getReports().get(0);
    }

    public ArrayList<ReportRow> getReportRows() {
        return reportRows;
    }

    public ReportsTable findReports() {
        reportRows = getReports();
        return this;
    }

    public ReportsTable filterByColumnWithValue(String column, String value) {
        ArrayList<ReportRow> list = new ArrayList<>();
        for (ReportRow reportRow : reportRows) {
            if (reportRow.getCellByColumnName(column).text().toLowerCase().contentEquals(value.toLowerCase())) {
                list.add(reportRow);
            }
        }
        reportRows = list;
        return this;
    }

}
