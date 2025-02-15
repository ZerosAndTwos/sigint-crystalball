package html_email.html_elements;

import com.google.common.base.Joiner;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class HtmlBuilder {

    private Logger log = Logger.getLogger(HtmlBuilder.class);

    private List<HtmlElement> elements = new ArrayList<>();

    public String build() {
        log.debug("Building html string...");
        String html = "";
        for (HtmlElement element: elements) {
            html+=Joiner.on("").join(element.expose());
        }
        log.debug("Resulting html is:"+ html);
        return html;
    }

    public void addHtmlElement(HtmlElement element) {
        this.elements.add(element);
    }


}
