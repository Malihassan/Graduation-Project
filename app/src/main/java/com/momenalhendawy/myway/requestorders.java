package com.momenalhendawy.myway;

public class requestorders {

    private String orderDescribe;
    private String orderImage;


    public requestorders( String order_Describe,String order_Image)
    {
        this.orderDescribe = order_Describe;
        this.orderImage = order_Image;
    }

    public String getOrderDescribe() {
        return orderDescribe;
    }

    public String getOrderImage() {
        return orderImage;
    }
}
