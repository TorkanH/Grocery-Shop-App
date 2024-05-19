package com.kansavozer.gulcetorkanbedirhankansavproject;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity{


    RecyclerView recyclerCart;
    DatabaseHelper dbHelper;
    TextView tvResult;
    LinearLayoutManager layoutManager;
    LoaderManager loaderManager=null;

    private static int TASK_ID = 200;
    private ArrayList<Cart> CartList =new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dbHelper = new DatabaseHelper(this);

        CartList = CartDB.getAllItems(dbHelper);

        recyclerCart = (RecyclerView) findViewById(R.id.rcycCart);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerCart.setLayoutManager(layoutManager);


        CustomCartRecyclerAdapter adapter = new CustomCartRecyclerAdapter(this, CartList);
        recyclerCart.setAdapter(adapter);

    }



}