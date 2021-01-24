package com.example.foodycookbook.Search;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context context;
    private ArrayList<SearchModal> list;
    SharedPreferences sharedPreferences;
    String PREF_NAME = "pref";
    String KEY_NAME = "name";
    String KEY_CATEGORY = "category";
    String KEY_URL = "url";


    public SearchAdapter(Context context, ArrayList<SearchModal> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_items,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        SearchModal items = list.get(position);
        holder.menu_name.setText(items.getMenu());
        holder.menu_category.setText(items.getCategory());
        Glide.with(context).load(items.getDownloadURL()).into(holder.menu_image);
        holder.fav_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fav_image.setText("ADDED");
                SearchModal temp = list.get(position);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME,temp.getMenu());
                editor.putString(KEY_CATEGORY,temp.getCategory());
                editor.putString(KEY_URL,temp.getDownloadURL());
                editor.apply();


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView menu_name;
        private TextView menu_category;
        private ImageView menu_image;
        private MaterialButton fav_image;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            menu_name=itemView.findViewById(R.id.meal_name);
            menu_category=itemView.findViewById(R.id.category_name);
            menu_image=itemView.findViewById(R.id.m_image);
            fav_image=itemView.findViewById(R.id.fav_image);
            sharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        }
    }
}
