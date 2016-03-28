package com.example.nancy.poznyakova_anna;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.app_name);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null)

        {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        initRecyclerView();
    }

    public void onClick(View v) {
        int id = v.getId();
        String text;
        Resources i = v.getResources();
        text = i.getResourceEntryName(id);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, R.string.button, Toast.LENGTH_SHORT).show();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initRecyclerView() {
        RecyclerView imageRecyclerView = (RecyclerView) findViewById(R.id.image_recycler_view);
        imageRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        imageRecyclerView.setLayoutManager(layoutManager);

        com.example.nancy.poznyakova_anna.RecyclerViewAdapter adapter = new com.example.nancy.poznyakova_anna.RecyclerViewAdapter(this,
                getResources().getStringArray(com.example.nancy.poznyakova_anna.R.array.urls_array));
        imageRecyclerView.setAdapter(adapter);
    }

}

