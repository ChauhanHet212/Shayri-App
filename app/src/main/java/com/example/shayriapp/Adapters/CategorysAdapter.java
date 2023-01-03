package com.example.shayriapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shayriapp.R;

public class CategorysAdapter extends BaseAdapter {

    Context context;
    int[] categorys_img;
    String[] categorys_name;

    ImageView category_item_img;
    TextView category_item_name;

    public CategorysAdapter(Context context, int[] categorys_img, String[] categorys_name) {
        this.context = context;
        this.categorys_img = categorys_img;
        this.categorys_name = categorys_name;
    }

    @Override
    public int getCount() {
        return categorys_name.length;
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

        category_item_img = view.findViewById(R.id.category_item_img);
        category_item_name = view.findViewById(R.id.category_item_name);

        category_item_img.setImageResource(categorys_img[i]);
        category_item_name.setText(categorys_name[i]);

        return view;
    }
}
