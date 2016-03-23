package com.example.nancy.p003_poznyakova;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

//[Comment] Wrong status bar color
//[Comment] Something wrong with layout paddings. Toolbar shouldn't have paddings
//[Comment] Please add space between images in recycler view
public class MainActivity extends AppCompatActivity {
    private String lang; //[Comment] Unused object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("CE-1257218");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final ArrayList<View> views = getAllChildren(findViewById(R.id.main_layout)); //[Comment] Please use abstraction instead of realization
        for (int i = 0; i < views.size(); i++) {
            views.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text;
                    int id = v.getId();
                    if (id > 0) { //[Comment] I'm not sure that id can be < 0
                        text = getResources().getResourceName(v.getId());
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        android.support.v7.app.ActionBar actionBar = getSupportActionBar(); //[Comment] Please optimize import
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Back button", Toast.LENGTH_SHORT).show(); //[Comment] Hardcode
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

    public void initRecyclerView() {
        RecyclerView imageRecyclerView = (RecyclerView) findViewById(R.id.image_recycler_view);
        imageRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        imageRecyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,
                getResources().getStringArray(R.array.urls_array));
        imageRecyclerView.setAdapter(adapter);
    }

}

