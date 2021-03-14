package com.momenalhendawy.myway;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.StateSet;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class requestorder_adapter extends ArrayAdapter<requestorders> {
    public requestorder_adapter(Context context, List<requestorders> objects) {
        super(context, 0, objects);
    }
    public TextView result ;
    private  Button increase;
    private  Button decrease;
    String x ;
    String page ;
    boolean drink = false , rice = false;


    //   this for basket Drinks value
    private SparseArray<String> basketItemDrink = new SparseArray<>();
    private SparseArray<String> basketIdItemDrink = new SparseArray<>();
    private SparseArray<Integer> basketItemValueDrink = new SparseArray<>();

    //   this for basket Oil value
    private SparseArray<String> basketItemOil = new SparseArray<>();
    private SparseArray<String> basketIdItemOil =new SparseArray<>();
    private SparseArray<Integer> basketItemValueOil = new SparseArray<>();

    //   this for basket Rice value
    private SparseArray<String> basketItemRice = new SparseArray<>();
    private SparseArray<String> basketIdItemRice =new SparseArray<>();
    private SparseArray<Integer> basketItemValueRice = new SparseArray<>();

    //   this for basket Cleaning value
    private SparseArray<String> basketItemCleaning = new SparseArray<>();
    private SparseArray<String> basketIdItemCleaning =new SparseArray<>();
    private SparseArray<Integer> basketItemValueCleaning = new SparseArray<>();

    //   this for basket Vegetable value
    private SparseArray<String> basketItemVegetable = new SparseArray<>();
    private SparseArray<String> basketIdItemVegetable =new SparseArray<>();
    private SparseArray<Integer> basketItemValueVegetable= new SparseArray<>();

    //   this for basket Vegetable value
    private SparseArray<String> basketItemSpice = new SparseArray<>();
    private SparseArray<String> basketIdItemSpice =new SparseArray<>();
    private SparseArray<Integer> basketItemValueSpice= new SparseArray<>();

    // this for record value that return from singleton for requestm4robat
    ArrayList<String> DrinkOrderDescribe = new ArrayList<>();
    ArrayList<String> DrinkOrderID = new ArrayList<>();
    // this for record value that return from singleton for requestzeet
    public ArrayList<String> OilOrderDescribe = new ArrayList<>();
    public ArrayList<String> OilOrderID = new ArrayList<>();
    // this for record value that return from singleton for requestmkrona
    public ArrayList<String> RiceOrderDescribe = new ArrayList<>();
    public ArrayList<String> RiceOrderID = new ArrayList<>();
    // this for record value that return from singleton for requestmonzfat
    public ArrayList<String> CleaningOrderDescribe = new ArrayList<>();
    public ArrayList<String> CleaningOrderID = new ArrayList<>();
    // this for record value that return from singleton for request5dar
    public ArrayList<String> VegetableOrderDescribe = new ArrayList<>();
    public ArrayList<String> VegetableOrderID = new ArrayList<>();
    // this for record value that return from singleton for requestatara
    public ArrayList<String> SpiceOrderDescribe = new ArrayList<>();
    public ArrayList<String> SpiceOrderID = new ArrayList<>();


    // this for set total basket to singletone
    public ArrayList<String> BasketOrderDescribe = new ArrayList<String>();
    public ArrayList<String> BasketOrderID = new ArrayList<String>();
    public ArrayList<Integer> BasketOrderValue = new ArrayList<Integer>();




    int y;
    boolean del =false;
    int i=0;

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemview = convertView;
        if (listitemview == null) {
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.activity_menuorder_design, parent, false);
        }
        final View finalListitemview = listitemview;


        increase = listitemview.findViewById(R.id.increase);
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = (TextView) finalListitemview.findViewById(R.id.showvalue);
                String x = (String) result.getText();
                int re = Integer.parseInt(x);
                if (re >= 0)
                {
                    y = re+1;

                    result.setText(String.valueOf(y));
                }
            }
        });

        decrease =listitemview.findViewById(R.id.decrease);

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = (TextView) finalListitemview.findViewById(R.id.showvalue);
                String x = (String) result.getText();
                int re = Integer.parseInt(x);
                if (re > 0)
                {
                    y = re-1;
                    result.setText(String.valueOf(y));

                }
                else
                    result.setText("0");
            }
        });

        //  Log.d(TAG, "getView: position clicked "+User_singleton.getInstance().getPos());



        Button addorder = listitemview.findViewById(R.id.addinbox);
        addorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int re ;

                result = (TextView) finalListitemview.findViewById(R.id.showvalue);
                x =(String) result.getText();
                re =Integer.parseInt(x);

                DrinkOrderDescribe=User_singleton.getInstance().getDrinkOrderDescribe();
                DrinkOrderID=User_singleton.getInstance().getDrinkOrderID();


                OilOrderDescribe=User_singleton.getInstance().getOilOrderDescribe();
                OilOrderID=User_singleton.getInstance().getOilOrderID();

                RiceOrderDescribe=User_singleton.getInstance().getRiceOrderDescribe();
                RiceOrderID=User_singleton.getInstance().getRiceOrderID();

                CleaningOrderDescribe=User_singleton.getInstance().getCleaningOrderDescribe();
                CleaningOrderID=User_singleton.getInstance().getCleaningOrderID();

                VegetableOrderDescribe=User_singleton.getInstance().getVegetableOrderDescribe();
                VegetableOrderID=User_singleton.getInstance().getVegetableOrderID();

                SpiceOrderDescribe = User_singleton.getInstance().getSpiceOrderDescribe();
                SpiceOrderID = User_singleton.getInstance().getSpiceOrderID();




                if (re == 0 ) {
                    Toast.makeText(getContext(), " لم يتم اضافة عنصر ", Toast.LENGTH_SHORT).show();
                    if (DrinkOrderID != null && !DrinkOrderID.isEmpty()) {
                        if (Boolean.valueOf(del).equals(false)) {
                            basketItemDrink.remove(position);
                            basketIdItemDrink.remove(position);
                            basketItemValueDrink.remove(position);
                            del = true;
                            User_singleton.getInstance().setDrinkItem(basketItemDrink);
                            User_singleton.getInstance().setDrinkItemID(basketIdItemDrink);
                            User_singleton.getInstance().setDrinkItemValues(basketItemValueDrink);
                        }
                        else if (RiceOrderID != null && !RiceOrderID.isEmpty()) {
                            if (Boolean.valueOf(del).equals(false)) {
                                basketItemRice.remove(position);
                                basketIdItemRice.remove(position);
                                basketItemValueRice.remove(position);
                                del = true;

                                User_singleton.getInstance().setRiceItem(basketItemRice);
                                User_singleton.getInstance().setRiceItemID(basketIdItemRice);
                                User_singleton.getInstance().setRiceItemValues(basketItemValueRice);
                            }
                        }
                    } else if (OilOrderID != null && !OilOrderID.isEmpty()) {
                        if (Boolean.valueOf(del).equals(false)) {
                            basketItemOil.remove(position);
                            basketIdItemOil.remove(position);
                            basketItemValueOil.remove(position);
                            del = true;
                            User_singleton.getInstance().setOilItem(basketItemOil);
                            User_singleton.getInstance().setOilItemID(basketIdItemOil);
                            User_singleton.getInstance().setOilItemValues(basketItemValueOil);
                        }
                    } else if (CleaningOrderID != null && !CleaningOrderID.isEmpty()) {
                        if (Boolean.valueOf(del).equals(false)) {
                            basketItemCleaning.remove(position);
                            basketIdItemCleaning.remove(position);
                            basketItemValueCleaning.remove(position);
                            del = true;

                            User_singleton.getInstance().setCleaningItem(basketItemCleaning);
                            User_singleton.getInstance().setCleaningItemID(basketIdItemCleaning);
                            User_singleton.getInstance().setCleaningtemValues(basketItemValueCleaning);
                        }
                    }else if (VegetableOrderID != null && !VegetableOrderID.isEmpty()) {
                        if (Boolean.valueOf(del).equals(false)) {
                            basketItemVegetable.remove(position);
                            basketIdItemVegetable.remove(position);
                            basketItemValueVegetable.remove(position);
                            del = true;

                            User_singleton.getInstance().setVegetableItem(basketItemVegetable);
                            User_singleton.getInstance().setVegetableItemID(basketIdItemVegetable);
                            User_singleton.getInstance().setVegetabletemValues(basketItemValueVegetable);
                        }
                    }else if (SpiceOrderID != null && !SpiceOrderID.isEmpty()) {
                        if (Boolean.valueOf(del).equals(false)) {
                            basketItemSpice.remove(position);
                            basketIdItemSpice.remove(position);
                            basketItemValueSpice.remove(position);
                            del = true;

                            User_singleton.getInstance().setSpiceItem(basketItemSpice);
                            User_singleton.getInstance().setSpiceItemID(basketIdItemSpice);
                            User_singleton.getInstance().setSpicetemValues(basketItemValueSpice);
                        }
                    }
                }
                else if (re != 0) {
                    x = (String) result.getText();
                    Toast.makeText(getContext(), " تم اضافة عدد  " + x, Toast.LENGTH_SHORT).show();
                   // Log.d(TAG, "onClick:  total drink that returned"+DrinkOrderDescribe);

                    page = User_singleton.getInstance().getPage();

                    if(DrinkOrderID != null && DrinkOrderID.size()!=0 && page.equals("Drinks")) {
                        Log.d(TAG, "onComplete: iam heeeeeeeeeeer "+position);
                        Log.d(TAG, "onClick:  Drink adapter first "+page);


                        basketItemDrink.put(position, DrinkOrderDescribe.get(position));
                        basketIdItemDrink.put(position, DrinkOrderID.get(position));
                        basketItemValueDrink.put(position, Integer.parseInt(x));

                        User_singleton.getInstance().setDrinkItem(basketItemDrink);
                        User_singleton.getInstance().setDrinkItemID(basketIdItemDrink);
                        User_singleton.getInstance().setDrinkItemValues(basketItemValueDrink);


                        Log.d(TAG, "onClick:  basket drink "+ User_singleton.getInstance().getDrinkItem());
                        Log.d(TAG, "onClick:  drink adapter last");
                    //    Log.d(TAG, "onClick:  total Rice that returned"+RiceOrderDescribe);


                    }
                    if (RiceOrderID != null && !RiceOrderID.isEmpty() && page.equals("Rice")) {

                        Log.d(TAG, "onClick:  Rice adapter first");
                        Log.d(TAG, "onComplete: iam heeeeeeeeeeer"+position);
                        Log.d(TAG, "onClick:  Drink adapter first"+page);


                        basketItemRice.put(position, RiceOrderDescribe.get(position));
                        basketIdItemRice.put(position, RiceOrderID.get(position));
                        basketItemValueRice.put(position, Integer.parseInt(x));


                        User_singleton.getInstance().setRiceItem(basketItemRice);
                        User_singleton.getInstance().setRiceItemID(basketIdItemRice);
                        User_singleton.getInstance().setRiceItemValues(basketItemValueRice);


                        Log.d(TAG, "onClick:  basket Rice "+  User_singleton.getInstance().getRiceItem());
                        Log.d(TAG, "onClick:  Rice adapter last");


                    }
                    if (OilOrderID != null && !OilOrderID.isEmpty() && page.equals("Oil")) {

                        Log.d(TAG, "onClick:  Rice adapter first");
                        Log.d(TAG, "onComplete: iam heeeeeeeeeeer"+position);
                        Log.d(TAG, "onClick:  Drink adapter first"+page);

                        basketItemOil.put(position, OilOrderDescribe.get(position));
                        basketIdItemOil.put(position, OilOrderID.get(position));
                        basketItemValueOil.put(position, Integer.parseInt(x));

                        User_singleton.getInstance().setOilItem(basketItemOil);
                        User_singleton.getInstance().setOilItemID(basketIdItemOil);
                        User_singleton.getInstance().setOilItemValues(basketItemValueOil);

                        Log.d(TAG, "onClick:  basket Oil "+basketItemOil);
                        Log.d(TAG, "onClick:  oil adapter last");

                    }
                    if (CleaningOrderID != null && !CleaningOrderID.isEmpty() && page.equals("Cleaning")) {
                        Log.d(TAG, "onClick:  Rice adapter first");
                        Log.d(TAG, "onComplete: iam heeeeeeeeeeer"+position);
                        Log.d(TAG, "onClick:  Drink adapter first"+page);

                        basketItemCleaning.put(position, CleaningOrderDescribe.get(position));
                        basketIdItemCleaning.put(position, CleaningOrderID.get(position));
                        basketItemValueCleaning.put(position, Integer.parseInt(x));

                        User_singleton.getInstance().setCleaningItem(basketItemCleaning);
                        User_singleton.getInstance().setCleaningItemID(basketIdItemCleaning);
                        User_singleton.getInstance().setCleaningtemValues(basketItemValueCleaning);

                        Log.d(TAG, "onClick:  basket Cleaning "+basketItemCleaning);
                        Log.d(TAG, "onClick:  Cleaning adapter last");

                    }
                    if (VegetableOrderID != null && !VegetableOrderID.isEmpty() && page.equals("Vegetable")) {
                        Log.d(TAG, "onClick:  Rice adapter first");
                        Log.d(TAG, "onComplete: iam heeeeeeeeeeer"+position);
                        Log.d(TAG, "onClick:  Drink adapter first"+page);

                        basketItemVegetable.put(position, VegetableOrderDescribe.get(position));
                        basketIdItemVegetable.put(position, VegetableOrderID.get(position));
                        basketItemValueVegetable.put(position, Integer.parseInt(x));

                        User_singleton.getInstance().setVegetableItem(basketItemVegetable);
                        User_singleton.getInstance().setVegetableItemID(basketIdItemVegetable);
                        User_singleton.getInstance().setVegetabletemValues(basketItemValueVegetable);

                        Log.d(TAG, "onClick:  basket Vegetable "+User_singleton.getInstance().getVegetableItem());
                        Log.d(TAG, "onClick:  Cleaning adapter last");

                    }
                    if (SpiceOrderID != null && !SpiceOrderID.isEmpty() && page.equals("Spice")) {
                        Log.d(TAG, "onClick:  Rice adapter first");
                        Log.d(TAG, "onComplete: iam heeeeeeeeeeer"+position);
                        Log.d(TAG, "onClick:  Drink adapter first"+page);

                        basketItemSpice.put(position, SpiceOrderDescribe.get(position));
                        basketIdItemSpice.put(position, SpiceOrderID.get(position));
                        basketItemValueSpice.put(position, Integer.parseInt(x));

                        User_singleton.getInstance().setSpiceItem(basketItemSpice);
                        User_singleton.getInstance().setSpiceItemID(basketIdItemSpice);
                        User_singleton.getInstance().setSpicetemValues(basketItemValueSpice);

                        Log.d(TAG, "onClick:  Spice basket Vegetable "+basketItemSpice);
                        Log.d(TAG, "onClick:  Spice adapter last");
                    }
                    else {
                        Log.d(TAG, "======>>>: failed  ");
                    }
                }

            }
        });

        TextView orderDescribe = listitemview.findViewById(R.id.menuorder_name);
        ImageView orderImage = listitemview.findViewById(R.id.menuorder_image);

        requestorders oder = getItem(position);

        orderDescribe.setText((oder.getOrderDescribe()));

        Glide.with(listitemview)
                .load(oder.getOrderImage())
                .thumbnail(Glide.with(listitemview)
                        .load(oder.getOrderImage()))
                .into(orderImage);

        return listitemview;
    }

}
