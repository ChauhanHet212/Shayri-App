package com.tuneonn.shayariapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuneonn.shayariapp.R;

public class CategorysAdapter extends BaseAdapter {

    Context context;
    int[] images;
    String[] names;

    ImageView category_list_img;
    TextView category_list_name;

    public CategorysAdapter(Context context, int[] images, String[] names) {
        this.context = context;
        this.images = images;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.category_list_items, viewGroup, false);

        category_list_img = view.findViewById(R.id.category_list_img);
        category_list_name = view.findViewById(R.id.category_list_name);

        category_list_img.setImageResource(images[i]);
        category_list_name.setText(names[i]);

        return view;
    }
}
