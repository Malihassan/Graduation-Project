package com.momenalhendawy.myway;

import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User_singleton  implements Serializable {
    private static User_singleton ourInstance ;

    private String number;
    private int Value_of_products;
    private String page ;
    private String acceptvalue ;

    private ArrayList<String> OilOrderDescribe;
    private ArrayList<String> OilOrderID;

    private ArrayList<String> DrinkOrderDescribe;
    private ArrayList<String> DrinkOrderID;

    private ArrayList<String> RiceOrderDescribe;
    private ArrayList<String> RiceOrderID;

    private ArrayList<String> CleaningOrderDescribe;
    private ArrayList<String> CleaningOrderID;

    private ArrayList<String> VegetableOrderDescribe;
    private ArrayList<String> VegetableOrderID;

    private ArrayList<String> SpiceOrderDescribe;
    private ArrayList<String> SpiceOrderID;


    private SparseArray<String> DrinkItem;
    private SparseArray<String> DrinkItemID;
    private SparseArray<Integer> DrinkItemValues;

    private SparseArray<String> OilItem;
    private SparseArray<String> OilItemID;
    private SparseArray<Integer> oilItemValues;

    private SparseArray<String> RiceItem;
    private SparseArray<String> RiceItemID;
    private SparseArray<Integer> RiceItemValues;

    private SparseArray<String> CleaningItem;
    private SparseArray<String> CleaningItemID;
    private SparseArray<Integer> CleaningtemValues;

    private SparseArray<String> VegetableItem;
    private SparseArray<String> VegetableItemID;
    private SparseArray<Integer> VegetabletemValues;


    private SparseArray<String> SpiceItem;
    private SparseArray<String> SpiceItemID;
    private SparseArray<Integer> SpicetemValues;


    private ArrayList<String> OrderItems;
    private ArrayList<String> OrderItemIDs;
    private ArrayList<Integer> OrderItemValues;

    private ArrayList<String> BasketOrderDescribe;
    private ArrayList<String> BasketOrderID;
    private ArrayList<Integer> BasketOrderValue;

    private ArrayList<Integer> pos;
    List<String> Basket = new ArrayList<>();
    private List<String> document_id = new ArrayList<>();
    private String position_waiting_order ;

    private int sizeofcategoryvegetable ;
    private int sizeofcategoryspice;
    private int sizeofcategorydrink ;
    private int sizeofcategoryoil;
    private int sizeofcategoryrice ;
    private int sizeofcategoryclean ;


    private ArrayList<String> arrcaptainpicture;
    private ArrayList<String> arrcaptainrate;

    public ArrayList<String> arrcaptainphone;
    public ArrayList<String> arrcaptainame;

    public ArrayList<String> getArrcaptainphone() {
        return arrcaptainphone;
    }

    public void setArrcaptainphone(ArrayList<String> arrcaptainphone) {
        this.arrcaptainphone = arrcaptainphone;
    }

    public ArrayList<Integer> getPos() {
        return pos;
    }

    public void setPos(ArrayList<Integer> pos) {
        this.pos = pos;
    }

    public ArrayList<String> getBasketOrderDescribe() {
        return BasketOrderDescribe;
    }

    public void setBasketOrderDescribe(ArrayList<String> basketOrderDescribe) {
        BasketOrderDescribe = basketOrderDescribe;
    }

    public ArrayList<String> getArrcaptainrate() {
        return arrcaptainrate;
    }

    public void setArrcaptainrate(ArrayList<String> arrcaptainrate) {
        this.arrcaptainrate = arrcaptainrate;
    }

    public String getPosition_waiting_order() {
        return position_waiting_order;
    }

    public void setPosition_waiting_order(String position_waiting_order) {
        this.position_waiting_order = position_waiting_order;
    }

    public List<String> getDocument_id() {
        return document_id;
    }

    public void setDocument_id(List<String> document_id) {
        this.document_id = document_id;
    }

    public String getAcceptvalue() {
        return acceptvalue;
    }

    public void setAcceptvalue(String acceptvalue) {
        this.acceptvalue = acceptvalue;
    }

    public ArrayList<String> getArrcaptainame() {
        return arrcaptainame;
    }

    public void setArrcaptainame(ArrayList<String> arrcaptainame) {
        this.arrcaptainame = arrcaptainame;
    }
    public List<String> getBasket() {
        return Basket;
    }

    public void setBasket(List<String> basket) {
        Basket = basket;
    }
    public ArrayList<String> getBasketOrderID() {
        return BasketOrderID;
    }

    public void setBasketOrderID(ArrayList<String> basketOrderID) {
        BasketOrderID = basketOrderID;
    }

    public ArrayList<Integer> getBasketOrderValue() {
        return BasketOrderValue;
    }

    public void setBasketOrderValue(ArrayList<Integer> basketOrderValue) {
        BasketOrderValue = basketOrderValue;
    }

    public ArrayList<String> getArrcaptainpicture() {
        return arrcaptainpicture;
    }

    public void setArrcaptainpicture(ArrayList<String> arrcaptainpicture) {
        this.arrcaptainpicture = arrcaptainpicture;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSizeofcategoryvegetable() {
        return sizeofcategoryvegetable;
    }

    public void setSizeofcategoryvegetable(int sizeofcategoryvegetable) {
        this.sizeofcategoryvegetable = sizeofcategoryvegetable;
    }

    public int getSizeofcategoryspice() {
        return sizeofcategoryspice;
    }

    public void setSizeofcategoryspice(int sizeofcategoryspice) {
        this.sizeofcategoryspice = sizeofcategoryspice;
    }

    public int getSizeofcategorydrink() {
        return sizeofcategorydrink;
    }

    public void setSizeofcategorydrink(int sizeofcategorydrink) {
        this.sizeofcategorydrink = sizeofcategorydrink;
    }

    public int getSizeofcategoryoil() {
        return sizeofcategoryoil;
    }

    public void setSizeofcategoryoil(int sizeofcategoryoil) {
        this.sizeofcategoryoil = sizeofcategoryoil;
    }

    public int getSizeofcategoryrice() {
        return sizeofcategoryrice;
    }

    public void setSizeofcategoryrice(int sizeofcategoryrice) {
        this.sizeofcategoryrice = sizeofcategoryrice;
    }

    public int getSizeofcategoryclean() {
        return sizeofcategoryclean;
    }

    public void setSizeofcategoryclean(int sizeofcategoryclean) {
        this.sizeofcategoryclean = sizeofcategoryclean;
    }

    public int getValue_of_products() {
        return Value_of_products;
    }

    public void setValue_of_products(int value_of_products) {
        Value_of_products = value_of_products;
    }

    public ArrayList<String> getSpiceOrderDescribe() {
        return SpiceOrderDescribe;
    }

    public void setSpiceOrderDescribe(ArrayList<String> spiceOrderDescribe) {
        SpiceOrderDescribe = spiceOrderDescribe;
    }

    public ArrayList<String> getSpiceOrderID() {
        return SpiceOrderID;
    }

    public void setSpiceOrderID(ArrayList<String> spiceOrderID) {
        SpiceOrderID = spiceOrderID;
    }

    public ArrayList<String> getOilOrderDescribe() {
        return OilOrderDescribe;
    }

    public void setOilOrderDescribe(ArrayList<String> oilOrderDescribe) {
        OilOrderDescribe = oilOrderDescribe;
    }

    public ArrayList<String> getOilOrderID() {
        return OilOrderID;
    }

    public void setOilOrderID(ArrayList<String> oilOrderID) {
        OilOrderID = oilOrderID;
    }

    public ArrayList<String> getDrinkOrderDescribe() {
        return DrinkOrderDescribe;
    }

    public void setDrinkOrderDescribe(ArrayList<String> drinkOrderDescribe) {
        DrinkOrderDescribe = drinkOrderDescribe;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<String> getRiceOrderDescribe() {
        return RiceOrderDescribe;
    }

    public void setRiceOrderDescribe(ArrayList<String> riceOrderDescribe) {
        RiceOrderDescribe = riceOrderDescribe;
    }

    public ArrayList<String> getRiceOrderID() {
        return RiceOrderID;
    }

    public void setRiceOrderID(ArrayList<String> riceOrderID) {
        RiceOrderID = riceOrderID;
    }

    public ArrayList<String> getCleaningOrderDescribe() {
        return CleaningOrderDescribe;
    }

    public void setCleaningOrderDescribe(ArrayList<String> cleaningOrderDescribe) {
        CleaningOrderDescribe = cleaningOrderDescribe;
    }

    public ArrayList<String> getCleaningOrderID() {
        return CleaningOrderID;
    }

    public void setCleaningOrderID(ArrayList<String> cleaningOrderID) {
        CleaningOrderID = cleaningOrderID;
    }

    public ArrayList<String> getOrderItems() {
        return OrderItems;
    }

    public SparseArray<String> getOilItem() {
        return OilItem;
    }

    public void setOilItem(SparseArray<String> oilItem) {
        OilItem = oilItem;
    }

    public SparseArray<String> getOilItemID() {
        return OilItemID;
    }

    public void setOilItemID(SparseArray<String> oilItemID) {
        OilItemID = oilItemID;
    }

    public SparseArray<Integer> getOilItemValues() {
        return oilItemValues;
    }

    public void setOilItemValues(SparseArray<Integer> oilItemValues) {
        this.oilItemValues = oilItemValues;
    }

    public SparseArray<String> getRiceItem() {
        return RiceItem;
    }

    public void setRiceItem(SparseArray<String> riceItem) {
        RiceItem = riceItem;
    }

    public SparseArray<String> getRiceItemID() {
        return RiceItemID;
    }

    public void setRiceItemID(SparseArray<String> riceItemID) {
        RiceItemID = riceItemID;
    }

    public SparseArray<Integer> getRiceItemValues() {
        return RiceItemValues;
    }

    public void setRiceItemValues(SparseArray<Integer> riceItemValues) {
        RiceItemValues = riceItemValues;
    }

    public SparseArray<String> getCleaningItem() {
        return CleaningItem;
    }

    public void setCleaningItem(SparseArray<String> cleaningItem) {
        CleaningItem = cleaningItem;
    }

    public SparseArray<String> getCleaningItemID() {
        return CleaningItemID;
    }

    public void setCleaningItemID(SparseArray<String> cleaningItemID) {
        CleaningItemID = cleaningItemID;
    }

    public SparseArray<Integer> getCleaningtemValues() {
        return CleaningtemValues;
    }

    public void setCleaningtemValues(SparseArray<Integer> cleaningtemValues) {
        CleaningtemValues = cleaningtemValues;
    }

    public ArrayList<String> getVegetableOrderDescribe() {
        return VegetableOrderDescribe;
    }

    public void setVegetableOrderDescribe(ArrayList<String> vegetableOrderDescribe) {
        VegetableOrderDescribe = vegetableOrderDescribe;
    }

    public ArrayList<String> getVegetableOrderID() {
        return VegetableOrderID;
    }

    public void setVegetableOrderID(ArrayList<String> vegetableOrderID) {
        VegetableOrderID = vegetableOrderID;
    }

    public void setOrderItems(ArrayList<String> orderItems) {
        OrderItems = orderItems;
    }

    public ArrayList<String> getOrderItemIDs() {
        return OrderItemIDs;
    }

    public void setOrderItemIDs(ArrayList<String> orderItemIDs) {
        OrderItemIDs = orderItemIDs;
    }

    public ArrayList<Integer> getOrderItemValues() {
        return OrderItemValues;
    }

    public void setOrderItemValues(ArrayList<Integer> orderItemValues) {
        OrderItemValues = orderItemValues;
    }

    public SparseArray<String> getDrinkItem() {
        return DrinkItem;
    }

    public void setDrinkItem(SparseArray<String> drinkItem) {
        DrinkItem = drinkItem;
    }

    public SparseArray<String> getDrinkItemID() {
        return DrinkItemID;
    }

    public void setDrinkItemID(SparseArray<String> drinkItemID) {
        DrinkItemID = drinkItemID;
    }

    public SparseArray<Integer> getDrinkItemValues() {
        return DrinkItemValues;
    }

    public void setDrinkItemValues(SparseArray<Integer> drinkItemValues) {
        DrinkItemValues = drinkItemValues;
    }

    public SparseArray<String> getVegetableItem() {
        return VegetableItem;
    }

    public void setVegetableItem(SparseArray<String> vegetableItem) {
        VegetableItem = vegetableItem;
    }

    public SparseArray<String> getVegetableItemID() {
        return VegetableItemID;
    }

    public void setVegetableItemID(SparseArray<String> vegetableItemID) {
        VegetableItemID = vegetableItemID;
    }

    public SparseArray<Integer> getVegetabletemValues() {
        return VegetabletemValues;
    }

    public void setVegetabletemValues(SparseArray<Integer> vegetabletemValues) {
        VegetabletemValues = vegetabletemValues;
    }


    public SparseArray<String> getSpiceItem() {
        return SpiceItem;
    }

    public void setSpiceItem(SparseArray<String> spiceItem) {
        SpiceItem = spiceItem;
    }

    public SparseArray<String> getSpiceItemID() {
        return SpiceItemID;
    }

    public void setSpiceItemID(SparseArray<String> spiceItemID) {
        SpiceItemID = spiceItemID;
    }

    public SparseArray<Integer> getSpicetemValues() {
        return SpicetemValues;
    }

    public void setSpicetemValues(SparseArray<Integer> spicetemValues) {
        SpicetemValues = spicetemValues;
    }

    public ArrayList<String> getDrinkOrderID() {
        return DrinkOrderID;
    }

    public void setDrinkOrderID(ArrayList<String> drinkOrderID) {
        DrinkOrderID = drinkOrderID;
    }

    private User_singleton() {

        //Prevent form the reflection api.
        if (ourInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public  static User_singleton getInstance()
    {

        if (ourInstance == null) { //Check for the first time

            synchronized (User_singleton.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (ourInstance == null) ourInstance = new User_singleton();
            }
        }
        return ourInstance;
    }
    //Make singleton from serialize and deserialize operation.
    protected User_singleton readResolve() {
        return getInstance();
    }


}
