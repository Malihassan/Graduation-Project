package com.momenalhendawy.myway;

public class category_order {
    private String ordername;
    private int id;

    public category_order( String order_name, int ID) {
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
