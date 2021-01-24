package com.example.foodycookbook.Home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodycookbook.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    String url = "https://www.themealdb.com/api/json/v1/1/random.php";
    String menu,category,downloadurl;
    MenuAdapter menuAdapter;
    private ArrayList<MenuModal> list;
    private Context context;
    ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.items_recyclerview);
        progressBar = view.findViewById(R.id.progress_bar);
        list = new ArrayList<>();
        context = getContext();
        parseitems();

        return view;
    }


    private void parseitems() {
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("meals");
                    JSONObject meals = jsonArray.getJSONObject(0);
                    menu = meals.getString("strMeal");
                    category = meals.getString("strCategory");
                    downloadurl = meals.getString("strMealThumb");


                    Log.d("FoodyCookBook", "The name of the meal is: "+meals.getString("strMeal"));
                    list.add(new MenuModal(menu,category,downloadurl));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                progressBar.setVisibility(View.GONE);
                menuAdapter = new MenuAdapter(context,list);
                recyclerView.setAdapter(menuAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("FoodyCookBook", "Something went Wrong");
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }
}