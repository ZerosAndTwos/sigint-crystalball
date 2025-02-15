package model.bulders;

class SSMSToNumber extends SSMSBuilder {

    SSMSToNumber(String pattern) {
        setPattern(pattern);
    }

    @Override
    public SSMSBuilder buildText() {
        return this;
    }

    @Override
    public SSMSBuilder buildToNumber() {
        ssms.setToNumber(pattern);
        return this;
    }

    @Override
    public SSMSBuilder buildFromNumber() {
        return this;
    }
}
