package ae.pegasus.framework.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Voice extends AbstractEntity {

    private ArrayList<VoiceRecord> records = new ArrayList<>();
    private Integer version;
    private Double speechLength;
    private String data;

    public ArrayList<VoiceRecord> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<VoiceRecord> records) {
        this.records = records;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Double getSpeechLength() {
        return speechLength;
    }

    public void setSpeechLength(Double speechLength) {
        this.speechLength = speechLength;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
