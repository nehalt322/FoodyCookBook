package com.example.foodycookbook.favourite;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.foodycookbook.Home.MenuAdapter;
import com.example.foodycookbook.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment {
    SharedPreferences sharedPreferences;
    String SHARED_PREF_NAME = "pref";
    String KEY_NAME = "name";
    String KEY_CATEGORY = "category";
    String KEY_URL = "url";
    Context context;
    RecyclerView recyclerView;
    ArrayList<FavouriteModal> flist;
    FavouriteAdapter favouriteAdapter;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        context = getContext();
        recyclerView=view.findViewById(R.id.fav_recycler);
        flist = new ArrayList<>();
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        favourite();
        return view;


    }

    private void favourite() {
        String name = sharedPreferences.getString(KEY_NAME,null);
        String category= sharedPreferences.getString(KEY_CATEGORY,null);
        String url = sharedPreferences.getString(KEY_URL,null);
        flist.add(new FavouriteModal(name,category,url));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        favouriteAdapter = new FavouriteAdapter(context,flist);
        recyclerView.setAdapter(favouriteAdapter);

    }
}