package model.bulders;

class SSMSFromNumber extends SSMSBuilder {

    SSMSFromNumber(String targetPhoneNumber) {
        setPattern(targetPhoneNumber);
    }

    @Override
    public SSMSBuilder buildText() {
        return this;
    }

    @Override
    public SSMSBuilder buildToNumber() {
        return this;
    }

    public SSMSBuilder buildFromNumber() {
        ssms.setFromNumber(pattern);
        return this;
    }
}
