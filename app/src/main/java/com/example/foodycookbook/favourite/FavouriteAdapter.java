package com.example.foodycookbook.favourite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodycookbook.Home.MenuModal;
import com.example.foodycookbook.R;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    private Context context;
    private ArrayList<FavouriteModal> flist;

    public FavouriteAdapter(Context context, ArrayList<FavouriteModal> flist) {
        this.context = context;
        this.flist = flist;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        FavouriteModal items = flist.get(position);
        holder.menu_name.setText(items.getMenu());
        holder.menu_category.setText(items.getCategory());
        Glide.with(context).load(items.getDownloadURL()).into(holder.menu_image);

    }

    @Override
    public int getItemCount() {
        return flist.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        private TextView menu_name;
        private TextView menu_category;
        private ImageView menu_image;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            menu_name=itemView.findViewById(R.id.meal_name);
            menu_category=itemView.findViewById(R.id.category_name);
            menu_image=itemView.findViewById(R.id.m_image);
        }
    }
}
