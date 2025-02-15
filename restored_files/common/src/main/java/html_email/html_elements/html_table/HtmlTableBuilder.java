package html_email.html_elements.html_table;

import html_email.HtmlElement;
import html_email.Style;

public class HtmlTableBuilder {

    private HtmlElement currentRow;
    private HtmlElement top;

    public HtmlTableBuilder() {
        table();
    }

    public HtmlElement getTable() {
        return top;
    }


    private void table() {
        HtmlElement table = new HtmlTable();
        top = table;
    }

    public HtmlTableBuilder addRow(String value, Style style) {
        HtmlElement row = new HtmlTableRow();
        row.setValue(value);
        if (!(style==null)) {
            row.setStyle(style);
        }
        top.addChild(row);
        currentRow = row;
        return this;

    }

    public HtmlTableBuilder addColumn(String value) {
        HtmlElement column = new HtmlTableColumn();
        column.setValue(value);
        currentRow.addChild(column);
        return this;
    }


}
