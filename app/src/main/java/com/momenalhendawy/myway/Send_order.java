package com.momenalhendawy.myway;


import com.google.firebase.Timestamp;

public class Send_order {

    private Boolean accept;
    private String order_ID ;
    private Timestamp Srart_Time ;
    private String Reason_for_canceling_the_request;
    private Boolean canceling ;
    private Boolean finished ;
    private String TokenID ;
    private String CaptainPhone ;

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public String getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(String order_ID) {
        this.order_ID = order_ID;
    }

    public Timestamp getSrart_Time() {
        return Srart_Time;
    }

    public void setSrart_Time(Timestamp srart_Time) {
        Srart_Time = srart_Time;
    }

    public String getReason_for_canceling_the_request() {
        return Reason_for_canceling_the_request;
    }

    public void setReason_for_canceling_the_request(String reason_for_canceling_the_request) {
        Reason_for_canceling_the_request = reason_for_canceling_the_request;
    }

    public Boolean getCanceling() {
        return canceling;
    }

    public void setCanceling(Boolean canceling) {
        this.canceling = canceling;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getTokenID() {
        return TokenID;
    }

    public void setTokenID(String tokenID) {
        TokenID = tokenID;
    }

    public String getCaptainPhone() {
        return CaptainPhone;
    }

    public void setCaptainPhone(String captainPhone) {
        CaptainPhone = captainPhone;
    }
}
