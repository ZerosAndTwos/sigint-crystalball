package html_email.html_elements;

import java.util.ArrayList;
import java.util.List;

class HtmlLink extends HtmlElement {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    HtmlLink() {
        super("a");
    }

    @Override
    public List<String> expose() {
        List<String> elements = new ArrayList<>();
        String startTag= "<" + tag;
        if (style!=null) {
            startTag += ' ' + style.toString();
        }
        elements.add(startTag + String.format(" href='%s'", url)+ ">"+ "\r\n ");

        if (value!=null) {
            elements.add(value+"\r\n ");
        }
        elements.add("<"+tag+"/>"+"\r\n ");
        return elements;
    }
}
