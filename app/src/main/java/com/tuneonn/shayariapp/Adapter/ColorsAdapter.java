package com.tuneonn.shayariapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tuneonn.shayariapp.AllShayris;
import com.tuneonn.shayariapp.R;

public class ColorsAdapter extends BaseAdapter {

    Context context;
    boolean h;

    TextView color_txtv, grad_txtv;

    public ColorsAdapter(Context context, boolean i) {
        this.context = context;
        this.h = i;
    }

    @Override
    public int getCount() {
        if (h) {
            return AllShayris.colorsarr.length;
        } else {
            return AllShayris.gradarr.length;
        }
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
        if (h) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_item, viewGroup, false);
            color_txtv = view.findViewById(R.id.color_TXTV);

            color_txtv.setBackgroundColor(view.getResources().getColor(AllShayris.colorsarr[i]));

            return view;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.grad_item, viewGroup, false);
            grad_txtv = view.findViewById(R.id.grad_txtv);

            grad_txtv.setBackground(view.getResources().getDrawable(AllShayris.gradarr[i]));

            return view;
        }
    }
}
