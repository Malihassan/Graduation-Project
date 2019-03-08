package com.momenalhendawy.myway;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.BufferUnderflowException;
import java.util.List;

public class requestorder_adapter extends ArrayAdapter<requestorders> {
    public TextView result ;

    public requestorder_adapter(Context context, List<requestorders> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemview = convertView;
        if (listitemview == null) {
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.activity_menuorder_design, parent, false);
        }
        TextView ordername = listitemview.findViewById(R.id.menuorder_name);
        ImageView captainimage = listitemview.findViewById(R.id.menuorder_image);
        Button increse = listitemview.findViewById(R.id.increase);
        final View finalListitemview = listitemview;

        increse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = (TextView) finalListitemview.findViewById(R.id.showvalue);
                String x = (String) result.getText();
                int re = Integer.parseInt(x);
                if (re >= 0)
                {
                    int y = re+1;

                    result.setText(String.valueOf(y));
                }
            }
        });
        Button decrease =listitemview.findViewById(R.id.decrease);
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = (TextView) finalListitemview.findViewById(R.id.showvalue);
                String x = (String) result.getText();
                 int re = Integer.parseInt(x);
                if (re > 0)
                {
                    int y = re-1;
                    result.setText(String.valueOf(y));

                }
                else
                    result.setText("0");
            }
        });

        Button addorder = listitemview.findViewById(R.id.addinbox);
        addorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int re ;

                result = (TextView) finalListitemview.findViewById(R.id.showvalue);
                String x =(String) result.getText();
                re =Integer.parseInt(x);
                if (re == 0){

                    Toast.makeText(getContext(),  " لم يتم اضافة عنصر ", Toast.LENGTH_SHORT).show();
                }
                else if (re != 0) {
                    x = (String) result.getText();
                    Toast.makeText(getContext(), " تم اضافة عدد  " + x, Toast.LENGTH_SHORT).show();
                }
            }
        });
        requestorders oder = getItem(position);
        ordername.setText((oder.getOrdername()));
        captainimage.setImageResource(oder.getImageorder());
        return listitemview;
    }
}
