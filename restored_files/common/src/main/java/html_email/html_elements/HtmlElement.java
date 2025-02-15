package html_email.html_elements;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class HtmlElement {

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    protected String tag;
    private List<HtmlElement> childs = new ArrayList<>();
    protected String value;
    private Logger log = Logger.getLogger(HtmlElement.class);

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    protected List<String> expose() {
        log.debug("Exposing html elements to list of Strings");
        List<String> elements = new ArrayList<>();
        String startTag= "<" + tag;
        if (style!=null) {
            startTag += ' ' + style.toString();
        }
        elements.add(startTag + ">"+ "\r\n ");
        for (HtmlElement element:childs) {
            elements.addAll(element.expose());
        }
        if (!(value==null)) {
            elements.add(value+"\r\n ");
        }
        elements.add("</"+tag+">"+"\r\n ");
        log.debug("Result elements list:" + elements);
        return elements;
    }


    public HtmlElement(String tag) {
        this.tag = tag;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

     Style style;

    public HtmlElement addChild(HtmlElement child) {
        log.debug(String.format("Adding %s as child to current element %s", child.getTag(), this.getTag()));
        this.childs.add(child);
        return this;
    }

    public void removeChild(HtmlElement child) {
        this.childs.remove(child);
    }
}
