package com.momenalhendawy.myway;

import com.google.firebase.Timestamp;

import java.util.List;

public class Basket_Order {

    private String Order_Name;
    private List<String> Order_Item;
    private List<String> Item_ID;
    private List<Integer> Order_Values;


    public String getOrder_Name() {
        return Order_Name;
    }

    public List<String> getItem_ID() {
        return Item_ID;
    }

    public void setItem_ID(List<String> item_ID) {
        Item_ID = item_ID;
    }

    public void setOrder_Name(String order_Name) {
        Order_Name = order_Name;
    }

    public List<String> getOrder_Item() {
        return Order_Item;
    }

    public void setOrder_Item(List<String> order_Item) {
        Order_Item = order_Item;
    }

    public List<Integer> getOrder_Values() {
        return Order_Values;
    }

    public void setOrder_Values(List<Integer> order_Values) {
        Order_Values = order_Values;
    }

}
