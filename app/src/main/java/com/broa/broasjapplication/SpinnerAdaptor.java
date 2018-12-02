package com.broa.broasjapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SpinnerAdaptor extends ArrayAdapter {

    Context context;
    List <String> list;

    public SpinnerAdaptor(Context context, int resource
            , int textViewResourceId, List <String> list) {
        super(context, resource, textViewResourceId, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return creatView(position, convertView, parent);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return creatView(position, convertView, parent);
    }

    public View creatView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rootview = inflater.inflate(R.layout.spinner_customer, parent, false);


        TextView name = rootview.findViewById(R.id.tv_cantact_customerspinner);
        TextView age = rootview.findViewById(R.id.tv_age_customer);
        ImageView imageView = rootview.findViewById(R.id.image_spinner);

        name.setText(this.list.get(position));
        age.setText(position + 20 + "");
        Drawable drawable = null;
        for (int i = 0; i < 10; i++) {
            if (position == 0)
                drawable = this.context.getResources().getDrawable(R.drawable.p1);
            else if (position == 1)
                drawable = this.context.getResources().getDrawable(R.drawable.p2);
            else if (position == 2)
                drawable = this.context.getResources().getDrawable(R.drawable.p3);
            else if (position == 3)
                drawable = this.context.getResources().getDrawable(R.drawable.p4);
            else if (position == 4)
                drawable = this.context.getResources().getDrawable(R.drawable.p5);
            else if (position == 5)
                drawable = this.context.getResources().getDrawable(R.drawable.p6);
            else if (position == 6)
                drawable = this.context.getResources().getDrawable(R.drawable.p7);
            else if (position == 7)
                drawable = this.context.getResources().getDrawable(R.drawable.p8);

        }
        imageView.setImageDrawable(drawable);
        return rootview;

    }
}
