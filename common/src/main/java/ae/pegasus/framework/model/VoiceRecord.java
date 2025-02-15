package ae.pegasus.framework.model;

public class VoiceRecord extends AbstractEntity {
        private Integer channel;
        private Double length;
        private Double speechLength;

        public Integer getChannel() {
            return channel;
        }

        public void setChannel(Integer channel) {
            this.channel = channel;
        }

        public Double getLength() {
            return length;
        }

        public void setLength(Double length) {
            this.length = length;
        }

        public Double getSpeechLength() {
            return speechLength;
        }

        public void setSpeechLength(Double speechLength) {
            this.speechLength = speechLength;
        }
}
