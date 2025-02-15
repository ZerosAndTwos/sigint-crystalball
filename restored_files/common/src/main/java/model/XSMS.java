package model;

import abs.TeelaEntity;
import data_for_entity.annotations.DataProvider;
import data_for_entity.annotations.WithFieldDataType;
import data_for_entity.data_providers.PhonesProvider;
import data_for_entity.data_providers.data_target.SMSTextProvider;
import data_for_entity.data_types.FieldDataType;

import java.util.Date;

/**
 * Target teams. X-SMS source.
 *
 * Example:
 * EventTime	        CallerMod	    CalledMod	Txt
 * 16.03.22 23:03:52	923312824935	787	        Turkey
 * 16.03.22 21:14:57	971509155084	38970281738	istanbul
 */
public class XSMS extends TeelaEntity {

    @WithFieldDataType(FieldDataType.DATE)
    private Date eventTime;
    @DataProvider(PhonesProvider.class)
    private String callerMod;
    @DataProvider(PhonesProvider.class)
    private String calledMod;
    @DataProvider(SMSTextProvider.class)
    private String txt;

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getCallerMod() {
        return callerMod;
    }

    public void setCallerMod(String callerMod) {
        this.callerMod = callerMod;
    }

    public String getCalledMod() {
        return calledMod;
    }

    public void setCalledMod(String calledMod) {
        this.calledMod = calledMod;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

}
