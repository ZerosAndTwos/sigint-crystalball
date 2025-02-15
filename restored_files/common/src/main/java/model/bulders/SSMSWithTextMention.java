package model.bulders;

import org.apache.commons.lang.RandomStringUtils;

class SSMSWithTextMention extends SSMSBuilder {

    SSMSWithTextMention(String text) {
        setPattern(text);
    }

    @Override
    public SSMSBuilder buildText() {
        ssms.setTxt(
                RandomStringUtils.randomAlphabetic(6) + " " + pattern + " " + RandomStringUtils.randomAlphanumeric(8)
        );
        return this;
    }

    @Override
    public SSMSBuilder buildToNumber() {
        return this;
    }

    @Override
    public SSMSBuilder buildFromNumber() {
        return this;
    }
}
