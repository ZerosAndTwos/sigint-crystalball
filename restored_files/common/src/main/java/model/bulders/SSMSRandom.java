package model.bulders;

class SSMSRandom extends SSMSBuilder {

    @Override
    public SSMSBuilder buildText() {
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
