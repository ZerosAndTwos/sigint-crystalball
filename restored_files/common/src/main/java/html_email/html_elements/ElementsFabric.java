package html_email.html_elements;

import com.google.common.base.Joiner;
import org.apache.log4j.Logger;

public class ElementsFabric {


    private static Logger log = Logger.getLogger(ElementsFabric.class);

    public static HtmlElement heading(String value, Style style) {
        return createHeading(value, style);
        }

    public static HtmlElement heading(Iterable<String> values, Style style) {
        String value = Joiner.on("</br>").join(values);
        return createHeading(value, style);

    }

    public static HtmlTable table(Style style) {
        log.debug("Creating new table");
        HtmlTable table =  new HtmlTable();
        table.setStyle(style);
        return table;
    }


    public static HtmlElement link(String url, String text) {
        log.debug("Creating new link");
        HtmlLink link = new HtmlLink();
        link.setUrl(url);
        link.setValue(text);
        return link;
    }

    public  static HtmlElement image(String url) {
        log.debug("Creating new image");
        HtmlImage image = new HtmlImage();
        image.setUrl(url);
        return image;
    }

    public static HtmlElement tableRow() {
        log.debug("Creating new table row");
        return new HtmlTableRow();
    }

    public static HtmlElement tableColumn(String value, Style style) {
        return createColumn(value, style);
    }

    private static HtmlElement createColumn(String value, Style style) {
        log.debug("Creating new table column");
        HtmlElement tableColumn = new HtmlTableColumn();
        tableColumn.setValue(value);
        tableColumn.setStyle(style);
        return tableColumn;
    }

    private static HtmlElement createHeading(String value, Style style) {
        log.debug("Creating new html heading");
        HtmlElement heading = new Heading();
        heading.setValue(value);
        heading.setStyle(style);
        return heading;
    }

    public static HtmlElement tableColumn(Iterable<String> values, Style style) {
        String value = Joiner.on("</br>").join(values);
        return createColumn(value, style);

    }

    public static HtmlElement bold(String value, Style style) {
        log.debug("Creating new bold line");
        HtmlElement bold = new HtmlBold();
        bold.setValue(value);
        bold.setStyle(style);
        return bold;
    }

    }

