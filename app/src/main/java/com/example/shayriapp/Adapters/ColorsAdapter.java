package com.example.shayriapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shayriapp.AllShayris;
import com.example.shayriapp.R;

public class ColorsAdapter extends BaseAdapter {

    Context context;
    boolean colors;

    TextView colors_txtv, grad_txtv;

    public ColorsAdapter(Context context, boolean colors) {
        this.context = context;
        this.colors = colors;
    }

    @Override
    public int getCount() {
        if (colors) {
            return AllShayris.COLORS.length;
        } else {
            return AllShayris.GRADIENT.length;
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
        if (colors) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_item, viewGroup, false);

            colors_txtv = view.findViewById(R.id.color_txtv);

            colors_txtv.setBackgroundColor(view.getResources().getColor(AllShayris.COLORS[i]));

            return view;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.grad_item, viewGroup, false);

            grad_txtv = view.findViewById(R.id.grad_txtv);

            grad_txtv.setBackground(view.getResources().getDrawable(AllShayris.GRADIENT[i]));

            return view;
        }
    }
}
