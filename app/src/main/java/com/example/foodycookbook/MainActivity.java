package com.example.foodycookbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodycookbook.Home.HomeFragment;
import com.example.foodycookbook.Home.MenuAdapter;
import com.example.foodycookbook.Home.MenuModal;
import com.example.foodycookbook.Search.SearchFragment;
import com.example.foodycookbook.favourite.FavouriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    String url = "https://www.themealdb.com/api/json/v1/1/random.php";
    String menu,category,downloadurl;
    MenuAdapter menuAdapter;
    private ArrayList<MenuModal> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FoodyCookBook");

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.favourite:
                        fragment = new FavouriteFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                return true;
            }
        });
     //   recyclerView=findViewById(R.id.items_recyclerview);
      //  list = new ArrayList<>();
     //   parseitems();
    }

  /*  private void parseitems() {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
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
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                menuAdapter = new MenuAdapter(MainActivity.this,list);
                recyclerView.setAdapter(menuAdapter);

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("FoodyCookBook", "Something went Wrong");
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }*/
}