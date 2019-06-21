package com.example.projects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Recycler_Activity extends AppCompatActivity {
    ArrayList<ExampleItem> exampleList;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ExampleAdapter mAdapter;

    EditText insert,remove;
    Button insertbutton,removebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        createExampleList();
        buildRecyclerView();
        setButtons();
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    public void insertitem(int position){
        exampleList.add(position,new ExampleItem(R.drawable.round, "Line 7", "Line 8"));
        mAdapter.notifyDataSetChanged();
    }

    public void removeitem(int position){
        exampleList.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    public void changeitem(int position,String text){
        exampleList.get(position).changeText1(text);
        mAdapter.notifyDataSetChanged();
    }

    public void createExampleList(){
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.round, "Line 1", "Line 2"));
        exampleList.add(new ExampleItem(R.drawable.round, "Line 3", "Line 4"));
        exampleList.add(new ExampleItem(R.drawable.round, "Line 5", "Line 6"));
    }

    public void buildRecyclerView(){

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeitem(position,"Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                removeitem(position);
            }

        });
    }

    public void setButtons(){
        insert = (EditText) findViewById(R.id.insert);
        insertbutton = (Button) findViewById(R.id.insert_button);
        remove = (EditText) findViewById(R.id.remove);
        removebutton = (Button) findViewById(R.id.remove_button);

        insertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.valueOf(insert.getText().toString());
                insertitem(position);
            }
        });

        removebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.valueOf(remove.getText().toString());
                removeitem(position);
            }
        });
    }

}
