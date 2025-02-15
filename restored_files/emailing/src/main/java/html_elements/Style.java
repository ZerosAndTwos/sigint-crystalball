package html_elements;

public class Style {
    private String color;
    private String text_align;
    private String border;
    private String padding;
    private String fontWeight;

    public String getValign() {
        return valign;
    }

    public Style setValign(String valign) {
        this.valign = valign;
        return this;
    }

    private String valign;

    public String getAlign() {
        return align;
    }

    public Style setAlign(String align) {
        this.align = align;
        return this;
    }

    private String align;

    public String getFontFamily() {
        return fontFamily;
    }

    public Style setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public String getFontSize() {
        return fontSize;
    }

    public Style setFontSize(String fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    private String fontFamily;
    private String fontSize;

    @Override
    public String toString() {
        String main = "style = \"";
        if (color!=null) {
            main+=String.format(" color:%s;", color);
        }
        if (text_align!=null) {
            main+=String.format(" text-align:%s;", text_align);
        }

        if (border!=null) {
            main+=String.format(" border=%s;", border);
        }
        if (padding!=null) {
            main+=String.format(" padding:%s;", padding);
        }
        if (fontWeight!=null) {
            main+=String.format(" font-weight:%s;", fontWeight);
        }
        if (fontSize!=null) {
            main+=String.format(" font-size:%s;", fontSize);
        }
        if (fontFamily!=null){
            main+=String.format(" font-family:%s;", fontFamily);
        }
        if (align!=null) {
            return String.format(" align=%s", align);

        }
        if (valign!=null) {
            return String.format(" valign=%s", valign);
        }
        main+="\"";
        return main;

    }

    public String getColor() {
        return color;
    }

    public Style setColor(String color) {
        this.color = color;
        return this;
    }

    public String getText_align() {
        return text_align;
    }

    public void setTextAlign(String text_align) {
        this.text_align = text_align;
    }

    public String getBorder() {
        return border;
    }

    public Style setBorder(String border) {
        this.border = border;
        return this;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getFont_weight() {
        return fontWeight;
    }

    public Style setFontWeight(String font_weight) {
        this.fontWeight = font_weight;
        return this;
    }

}
