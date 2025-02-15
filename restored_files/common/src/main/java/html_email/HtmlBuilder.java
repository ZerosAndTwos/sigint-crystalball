package html_email;

import com.google.common.base.Joiner;
import html_email.html_elements.Heading;
import html_email.html_elements.Paragraph;
import html_email.html_elements.Title;

import java.util.ArrayList;
import java.util.List;

 class HtmlBuilder {

    private List<HtmlElement> elements = new ArrayList<>();


    String build() {
        String html = "";
        for (HtmlElement element: elements) {
            html+=Joiner.on("").join(element.expose());
        }
        return html;
    }

    HtmlBuilder addTitle(String value, Style style) {
        HtmlElement title = new Title();
        if (!(style==null)) {
            title.setStyle(style);
        }
        title.setValue(value);
        elements.add(title);
        return this;
    }


    HtmlBuilder addHeading(String value, Style style) {
        HtmlElement heading = new Heading();
        heading.setValue(value);
        if (!(style==null)) {
            heading.setStyle(style);
        }
        elements.add(heading);
        return this;
    }

    HtmlBuilder addParagraph(String value, Style style) {
        HtmlElement parag = new Paragraph();
        parag.setValue(value);
        if (!(style==null)) {
            parag.setStyle(style);
        }
        elements.add(parag);
        return this;
    }

    HtmlBuilder addTable(HtmlElement table){
        elements.add(table);
        return this;
    }

}
