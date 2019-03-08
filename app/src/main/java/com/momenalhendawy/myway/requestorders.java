package com.momenalhendawy.myway;

public class requestorders {
    private String ordername;
    private int imageorder;

    public requestorders( String order_name, int imageID)
    {
        this.ordername = order_name;
        this.imageorder = imageID;
    }

    public String getOrdername() {
        return ordername;
    }

    public int getImageorder() {
        return imageorder;
    }
}
