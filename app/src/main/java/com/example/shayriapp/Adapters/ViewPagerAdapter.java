package com.example.shayriapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shayriapp.AllShayris;
import com.example.shayriapp.R;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    Context context;
    int pos;
    TextView shayriTxtv;

    public ViewPagerAdapter(Context context, int pos) {
        this.context = context;
        this.pos = pos;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.pager_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {
        shayriTxtv = holder.show_shayri;
        holder.show_shayri.setText(AllShayris.ST_EMOJIS[position] + AllShayris.ALL_SHAYRIS[pos][position] + AllShayris.EN_EMOJIS[position]);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView show_shayri;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            show_shayri = itemView.findViewById(R.id.show_shayri);
        }
    }

    public void changeBg(int i){
        shayriTxtv.setBackground(context.getDrawable(AllShayris.GRADIENT[i]));
    }
}
