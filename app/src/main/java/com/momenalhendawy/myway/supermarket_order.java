package com.momenalhendawy.myway;

public class supermarket_order {
    private String ordername;
    private int id;

    public supermarket_order( String order_name, int ID) {
        this.ordername = order_name;
        this.id = ID;
    }

    public String getOrdername() {
        return ordername;
    }

    public int getId() {
        return id;
    }
}
