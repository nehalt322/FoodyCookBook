package com.example.foodycookbook.Search;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodycookbook.Home.MenuAdapter;
import com.example.foodycookbook.R;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    RecyclerView srecyclerView;
    EditText editText;
    MaterialButton materialButton;
    String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    String menu,category,downloadurl;
    Context context;
    ArrayList<SearchModal> list;
    SearchAdapter searchAdapter;
    ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
       View view =  inflater.inflate(R.layout.fragment_search, container, false);
       srecyclerView = view.findViewById(R.id.search_recyclerview);
       editText = view.findViewById(R.id.search_edit);
       materialButton = view.findViewById(R.id.search_btn);
       progressBar=view.findViewById(R.id.search_progress);

       context = getContext();
       list = new ArrayList<>();
       materialButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               searchItems();
           }
       });
       return view;
    }

    private void searchItems() {

        progressBar.setVisibility(View.VISIBLE);


        final String query =editText.getText().toString();
        final String URL = url+query;
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("meals");
                    int length = jsonArray.length();
                    for(int i=0;i<length;i++){
                        JSONObject meals = jsonArray.getJSONObject(i);
                        menu = meals.getString("strMeal");
                        category = meals.getString("strCategory");
                        downloadurl = meals.getString("strMealThumb");
                        list.add(new SearchModal(menu,category,downloadurl));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                srecyclerView.setHasFixedSize(true);
                srecyclerView.setLayoutManager(new LinearLayoutManager(context));
                progressBar.setVisibility(View.GONE);
                searchAdapter = new SearchAdapter(context,list);
                srecyclerView.setAdapter(searchAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}