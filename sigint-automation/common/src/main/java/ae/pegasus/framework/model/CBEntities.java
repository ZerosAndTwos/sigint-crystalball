package ae.pegasus.framework.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CBEntities {

    private List<SearchRecord> from = new ArrayList<>();
    private List<SearchRecord> root = new ArrayList<>();
    private List<SearchRecord> to = new ArrayList<>();
    private List<SearchRecord> cc = new ArrayList<>();
    private List<SearchRecord> bcc = new ArrayList<>();
    private List<SearchRecord> room = new ArrayList<>();
    private List<SearchRecord> forum = new ArrayList<>();
    @JsonProperty("Ticket")
    private List<SearchRecord> sitaTicket = new ArrayList<>();
    private List<SearchRecord> ticket = new ArrayList<>();
    @JsonProperty("Identity")
    private List<SearchRecord> sitaIdentity = new ArrayList<>();
    private List<SearchRecord> identity = new ArrayList<>();
    @JsonProperty("Payment")
    private List<SearchRecord> sitaPayment = new ArrayList<>();
    private List<SearchRecord> payment = new ArrayList<>();
    @JsonProperty("Reservation")
    private List<SearchRecord> sitaReservation = new ArrayList<>();
    private List<SearchRecord> reservation = new ArrayList<>();
    @JsonProperty("Email")
    private List<SearchRecord> sitaEmail = new ArrayList<>();
    private List<SearchRecord> email = new ArrayList<>();
    @JsonProperty("Visa")
    private List<SearchRecord> sitaVisa = new ArrayList<>();
    private List<SearchRecord> visa = new ArrayList<>();
    @JsonProperty("PhoneNumber")
    private List<SearchRecord> sitaPhone = new ArrayList<>();
    private List<SearchRecord> phonenumber = new ArrayList<>();
    @JsonProperty("Membership")
    private List<SearchRecord> sitaMembership = new ArrayList<>();
    private List<SearchRecord> membership = new ArrayList<>();
    @JsonProperty("Passport")
    private List<SearchRecord> sitaPassport = new ArrayList<>();
    private List<SearchRecord> passport = new ArrayList<>();
    private List<SearchRecord> unknown = new ArrayList<>();

    public List<SearchRecord> getFrom() {
        return from;
    }

    public void setFrom(List<SearchRecord> from) {
        this.from = from;
    }

    public List<SearchRecord> getTo() {
        return to;
    }

    public void setTo(List<SearchRecord> to) {
        this.to = to;
    }

    public List<SearchRecord> getCc() {
        return cc;
    }

    public void setCc(List<SearchRecord> cc) {
        this.cc = cc;
    }

    public List<SearchRecord> getBcc() {
        return bcc;
    }

    public void setBcc(List<SearchRecord> bcc) {
        this.bcc = bcc;
    }

    public List<SearchRecord> getTicket() {
        return ticket;
    }

    public void setTicket(List<SearchRecord> ticket) {
        this.ticket = ticket;
    }

    public List<SearchRecord> getIdentity() {
        return identity;
    }

    public void setIdentity(List<SearchRecord> identity) {
        this.identity = identity;
    }

    public List<SearchRecord> getPayment() {
        return payment;
    }

    public void setPayment(List<SearchRecord> payment) {
        this.payment = payment;
    }

    public List<SearchRecord> getReservation() {
        return reservation;
    }

    public void setReservation(List<SearchRecord> reservation) {
        this.reservation = reservation;
    }

    public List<SearchRecord> getUnknown() {
        return unknown;
    }

    public void setUnknown(List<SearchRecord> unknown) {
        this.unknown = unknown;
    }

    public List<SearchRecord> getRoom() {
        return room;
    }

    public void setRoom(List<SearchRecord> room) {
        this.room = room;
    }

    public List<SearchRecord> getForum() {
        return forum;
    }

    public void setForum(List<SearchRecord> forum) {
        this.forum = forum;
    }

    public List<SearchRecord> getEmail() {
        return email;
    }

    public void setEmail(List<SearchRecord> email) {
        this.email = email;
    }

    public List<SearchRecord> getVisa() {
        return visa;
    }

    public void setVisa(List<SearchRecord> visa) {
        this.visa = visa;
    }

    public List<SearchRecord> getMembership() {
        return membership;
    }

    public void setMembership(List<SearchRecord> membership) {
        this.membership = membership;
    }

    public List<SearchRecord> getPassport() {
        return passport;
    }

    public void setPassport(List<SearchRecord> passport) {
        this.passport = passport;
    }

    public List<SearchRecord> getRoot() {
        return root;
    }

    public void setRoot(List<SearchRecord> root) {
        this.root = root;
    }

    public List<SearchRecord> getSitaTicket() {
        return sitaTicket;
    }

    public void setSitaTicket(List<SearchRecord> sitaTicket) {
        this.sitaTicket = sitaTicket;
    }

    public List<SearchRecord> getSitaIdentity() {
        return sitaIdentity;
    }

    public void setSitaIdentity(List<SearchRecord> sitaIdentity) {
        this.sitaIdentity = sitaIdentity;
    }

    public List<SearchRecord> getSitaVisa() {
        return sitaVisa;
    }

    public void setSitaVisa(List<SearchRecord> sitaVisa) {
        this.sitaVisa = sitaVisa;
    }

    public List<SearchRecord> getSitaPhone() {
        return sitaPhone;
    }

    public void setSitaPhone(List<SearchRecord> sitaPhone) {
        this.sitaPhone = sitaPhone;
    }

    public List<SearchRecord> getSitaReservation() {
        return sitaReservation;
    }

    public void setSitaReservation(List<SearchRecord> sitaReservation) {
        this.sitaReservation = sitaReservation;
    }

    public List<SearchRecord> getSitaPayment() {
        return sitaPayment;
    }

    public void setSitaPayment(List<SearchRecord> sitaPayment) {
        this.sitaPayment = sitaPayment;
    }

    public List<SearchRecord> getSitaEmail() {
        return sitaEmail;
    }

    public void setSitaEmail(List<SearchRecord> sitaEmail) {
        this.sitaEmail = sitaEmail;
    }

    public List<SearchRecord> getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(List<SearchRecord> phonenumber) {
        this.phonenumber = phonenumber;
    }

    public List<SearchRecord> getSitaMembership() {
        return sitaMembership;
    }

    public void setSitaMembership(List<SearchRecord> sitaMembership) {
        this.sitaMembership = sitaMembership;
    }

    public List<SearchRecord> getSitaPassport() {
        return sitaPassport;
    }

    public void setSitaPassport(List<SearchRecord> sitaPassport) {
        this.sitaPassport = sitaPassport;
    }
}
