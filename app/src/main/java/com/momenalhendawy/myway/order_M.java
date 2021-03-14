package com.momenalhendawy.myway;

public class order_M {
    private String captainname;
    private String ordername;
    private String idpicture;

    public order_M(String captain_name, String order_name, String ID) {
        this.captainname = captain_name;
        this.ordername = order_name;
        this.idpicture = ID;
    }

    public String getCaptainname() {
        return captainname;
    }


    public String getOrdername() {
        return ordername;
    }

    public String getIdpicture() {
        return idpicture;
    }
}
