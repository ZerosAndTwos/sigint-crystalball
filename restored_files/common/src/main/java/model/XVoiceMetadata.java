package model;

import abs.TeelaEntity;
import data_for_entity.annotations.DataProvider;
import data_for_entity.annotations.WithFieldDataType;
import data_for_entity.data_providers.PhonesProvider;
import data_for_entity.data_types.FieldDataType;

import java.util.Date;

/**
 * Target teams. X-VoiceMetadata source.
 *
 * Example:
 * EventTime	        Sender	        Receiver
 * 16.03.22 23:03:52	923312824935	787
 * 16.03.22 21:14:57	971509155084	38970281738
 */
public class XVoiceMetadata extends TeelaEntity {

    @WithFieldDataType(FieldDataType.DATE)
    private Date eventTime;
    @DataProvider(PhonesProvider.class)
    private String sender;
    @DataProvider(PhonesProvider.class)
    private String receiver;

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

}
