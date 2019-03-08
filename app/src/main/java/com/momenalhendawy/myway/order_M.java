package com.momenalhendawy.myway;

public class order_M {
    private String captainname;
    private String ordername;
    private int id;

    public order_M(String captain_name, String order_name, int ID) {
        this.captainname = captain_name;
        this.ordername = order_name;
        this.id = ID;
    }

    public String getCaptainname() {
        return captainname;
    }


    public String getOrdername() {
        return ordername;
    }


    public int getId() {
        return id;
    }


}
