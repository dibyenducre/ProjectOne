package com.app.projectone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FruitListAdapter extends BaseAdapter {
    Context context;
    ArrayList<FruitData> fruitDataArrayList;
    LayoutInflater inflter;

    public FruitListAdapter(Context applicationContext, ArrayList<FruitData> fruitDataArrayList) {
        this.context = applicationContext;
        this.fruitDataArrayList = fruitDataArrayList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return fruitDataArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_fruit_list, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        FruitData data = fruitDataArrayList.get(i);

        viewHolder.tv_fname.setText(data.getF_name());
        viewHolder.tv_fdetails.setText(data.getF_details());
        viewHolder.img_fimage.setImageResource(data.getF_image());


        return view;
    }


    private class ViewHolder {
        TextView tv_fname, tv_fdetails;
        ImageView img_fimage;

        public ViewHolder(View view) {
            tv_fname = (TextView)view.findViewById(R.id.tv_fname);
            tv_fdetails = (TextView) view.findViewById(R.id.tv_fdetails);
            img_fimage = (ImageView)view.findViewById(R.id.img_fimage);
        }
    }
}