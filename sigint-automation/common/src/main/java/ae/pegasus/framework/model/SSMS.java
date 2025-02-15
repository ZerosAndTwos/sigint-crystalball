package ae.pegasus.framework.model;

import java.util.Date;


public class SSMS extends G4Record {

    private int cdr_id;
    private Date event_time;
    private int unit;
    private int matrix_ts;
    private int ni;
    private int opc;
    private int dpc;
    private int hit_count;
    private int maskmarked;
    private String srcip;
    private String dscip;
    private Date sms_time;
    private String smstimezone;
    private String txt;
    private int haveparts;
    private int totalparts;
    private String called_imsi;
    private String called_imsi_cc;
    private String called_imsi_ac;
    private String called_imsi_hit;
    private String caller_imsi;
    private String caller_imsi_cc;
    private String caller_imsi_ac;
    private String caller_imsi_hit;
    private String pattern;
    private String called;
    private int called_non;
    private String called_rule;
    private String called_hit;
    private String called_cc;
    private String called_ac;
    private String caller;
    private int caller_non;
    private String caller_rule;
    private String caller_hit;
    private String caller_cc;
    private String caller_ac;
    private String smsc;
    private int smsc_non;
    private String smsc_mod;
    private String smsc_rule;
    private String smsc_hit;
    private String smsc_cc;
    private String smsc_ac;

    public int getCdr_id() {
        return cdr_id;
    }

    public SSMS setCdr_id(int cdr_id) {
        this.cdr_id = cdr_id;
        return this;
    }

    public Date getEvent_time() {
        return event_time;
    }

    public SSMS setEvent_time(Date event_time) {
        this.event_time = event_time;
        return this;
    }

    public int getUnit() {
        return unit;
    }

    public SSMS setUnit(int unit) {
        this.unit = unit;
        return this;
    }

    public int getMatrix_ts() {
        return matrix_ts;
    }

    public SSMS setMatrix_ts(int matrix_ts) {
        this.matrix_ts = matrix_ts;
        return this;
    }

    public int getNi() {
        return ni;
    }

    public SSMS setNi(int ni) {
        this.ni = ni;
        return this;
    }

    public int getOpc() {
        return opc;
    }

    public SSMS setOpc(int opc) {
        this.opc = opc;
        return this;
    }

    public int getDpc() {
        return dpc;
    }

    public SSMS setDpc(int dpc) {
        this.dpc = dpc;
        return this;
    }

    public int getHit_count() {
        return hit_count;
    }

    public SSMS setHit_count(int hit_count) {
        this.hit_count = hit_count;
        return this;
    }

    public int getMaskmarked() {
        return maskmarked;
    }

    public SSMS setMaskmarked(int maskmarked) {
        this.maskmarked = maskmarked;
        return this;
    }

    public String getSrcip() {
        return srcip;
    }

    public SSMS setSrcip(String srcip) {
        this.srcip = srcip;
        return this;
    }

    public String getDscip() {
        return dscip;
    }

    public SSMS setDscip(String dscip) {
        this.dscip = dscip;
        return this;
    }

    public Date getSms_time() {
        return sms_time;
    }

    public SSMS setSms_time(Date sms_time) {
        this.sms_time = sms_time;
        return this;
    }

    public String getSmstimezone() {
        return smstimezone;
    }

    public void setSmstimezone(String smstimezone) {
        this.smstimezone = smstimezone;
    }

    public String getTxt() {
        return txt;
    }

    public SSMS setTxt(String txt) {
        this.txt = txt;
        return this;
    }

    public int getHaveparts() {
        return haveparts;
    }

    public SSMS setHaveparts(int haveparts) {
        this.haveparts = haveparts;
        return this;
    }

    public int getTotalparts() {
        return totalparts;
    }

    public void setTotalparts(int totalparts) {
        this.totalparts = totalparts;
    }

    public String getCalled_imsi() {
        return called_imsi;
    }

    public SSMS setCalled_imsi(String called_imsi) {
        this.called_imsi = called_imsi;
        return this;
    }

    public String getCalled_imsi_cc() {
        return called_imsi_cc;
    }

    public SSMS setCalled_imsi_cc(String called_imsi_cc) {
        this.called_imsi_cc = called_imsi_cc;
        return this;
    }

    public String getCalled_imsi_ac() {
        return called_imsi_ac;
    }

    public SSMS setCalled_imsi_ac(String called_imsi_ac) {
        this.called_imsi_ac = called_imsi_ac;
        return this;
    }

    public String getCalled_imsi_hit() {
        return called_imsi_hit;
    }

    public SSMS setCalled_imsi_hit(String called_imsi_hit) {
        this.called_imsi_hit = called_imsi_hit;
        return this;
    }

    public String getCaller_imsi() {
        return caller_imsi;
    }

    public SSMS setCaller_imsi(String caller_imsi) {
        this.caller_imsi = caller_imsi;
        return this;
    }

    public String getCaller_imsi_cc() {
        return caller_imsi_cc;
    }

    public SSMS setCaller_imsi_cc(String caller_imsi_cc) {
        this.caller_imsi_cc = caller_imsi_cc;
        return this;
    }

    public String getCaller_imsi_ac() {
        return caller_imsi_ac;
    }

    public SSMS setCaller_imsi_ac(String caller_imsi_ac) {
        this.caller_imsi_ac = caller_imsi_ac;
        return this;
    }

    public String getCaller_imsi_hit() {
        return caller_imsi_hit;
    }

    public SSMS setCaller_imsi_hit(String caller_imsi_hit) {
        this.caller_imsi_hit = caller_imsi_hit;
        return this;
    }

    public String getPattern() {
        return pattern;
    }

    public SSMS setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public String getCalled() {
        return called;
    }

    public SSMS setCalled(String called) {
        this.called = called;
        return this;
    }

    public int getCalled_non() {
        return called_non;
    }

    public SSMS setCalled_non(int called_non) {
        this.called_non = called_non;
        return this;
    }

    public String getCalled_rule() {
        return called_rule;
    }

    public SSMS setCalled_rule(String called_rule) {
        this.called_rule = called_rule;
        return this;
    }

    public String getCalled_hit() {
        return called_hit;
    }

    public SSMS setCalled_hit(String called_hit) {
        this.called_hit = called_hit;
        return this;
    }

    public String getCalled_cc() {
        return called_cc;
    }

    public SSMS setCalled_cc(String called_cc) {
        this.called_cc = called_cc;
        return this;
    }

    public String getCalled_ac() {
        return called_ac;
    }

    public SSMS setCalled_ac(String called_ac) {
        this.called_ac = called_ac;
        return this;
    }

    public String getCaller() {
        return caller;
    }

    public SSMS setCaller(String caller) {
        this.caller = caller;
        return this;
    }

    public int getCaller_non() {
        return caller_non;
    }

    public SSMS setCaller_non(int caller_non) {
        this.caller_non = caller_non;
        return this;
    }

    public String getCaller_rule() {
        return caller_rule;
    }

    public SSMS setCaller_rule(String caller_rule) {
        this.caller_rule = caller_rule;
        return this;
    }

    public String getCaller_hit() {
        return caller_hit;
    }

    public SSMS setCaller_hit(String caller_hit) {
        this.caller_hit = caller_hit;
        return this;
    }

    public String getCaller_cc() {
        return caller_cc;
    }

    public SSMS setCaller_cc(String caller_cc) {
        this.caller_cc = caller_cc;
        return this;
    }

    public String getCaller_ac() {
        return caller_ac;
    }

    public SSMS setCaller_ac(String caller_ac) {
        this.caller_ac = caller_ac;
        return this;
    }

    public String getSmsc() {
        return smsc;
    }

    public SSMS setSmsc(String smsc) {
        this.smsc = smsc;
        return this;
    }

    public int getSmsc_non() {
        return smsc_non;
    }

    public SSMS setSmsc_non(int smsc_non) {
        this.smsc_non = smsc_non;
        return this;
    }

    public String getSmsc_mod() {
        return smsc_mod;
    }

    public SSMS setSmsc_mod(String smsc_mod) {
        this.smsc_mod = smsc_mod;
        return this;
    }

    public String getSmsc_rule() {
        return smsc_rule;
    }

    public SSMS setSmsc_rule(String smsc_rule) {
        this.smsc_rule = smsc_rule;
        return this;
    }

    public String getSmsc_hit() {
        return smsc_hit;
    }

    public SSMS setSmsc_hit(String smsc_hit) {
        this.smsc_hit = smsc_hit;
        return this;
    }

    public String getSmsc_cc() {
        return smsc_cc;
    }

    public SSMS setSmsc_cc(String smsc_cc) {
        this.smsc_cc = smsc_cc;
        return this;
    }

    public String getSmsc_ac() {
        return smsc_ac;
    }

    public SSMS setSmsc_ac(String smsc_ac) {
        this.smsc_ac = smsc_ac;
        return this;
    }

    
   
}
