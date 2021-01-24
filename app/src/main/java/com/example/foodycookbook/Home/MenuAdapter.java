package com.example.foodycookbook.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodycookbook.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    private ArrayList<MenuModal> list;

    public MenuAdapter(Context context, ArrayList<MenuModal> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuModal items = list.get(position);
        holder.menu_name.setText(items.getMenu());
        holder.menu_category.setText(items.getCategory());
        Glide.with(context).load(items.getDownloadURL()).into(holder.menu_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private TextView menu_name;
        private TextView menu_category;
        private ImageView menu_image;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            menu_name=itemView.findViewById(R.id.meal_name);
            menu_category=itemView.findViewById(R.id.category_name);
            menu_image=itemView.findViewById(R.id.m_image);
        }
    }
}
