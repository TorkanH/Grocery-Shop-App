package com.kansavozer.gulcetorkanbedirhankansavproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SecondActivity extends AppCompatActivity {
    TextView tvName1, tvName2, price1, price2;
    ImageView img1,img2;
    Button btn1, btn2, btnBack;
    Intent intent;
    Groceries gItem;
    String name1,name2, id1,id2,type;
    int p1,p2;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        tvName1 = findViewById(R.id.name1);
        tvName2 = findViewById(R.id.name2);
        price1 = findViewById(R.id.price1);
        price2 = (TextInputEditText)findViewById(R.id.price2);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        btn1 = findViewById(R.id.addBtn1);
        btn2 = findViewById(R.id.addBtn2);
        btnBack = findViewById(R.id.btnBack);

        intent = getIntent();
        Bundle b = intent.getExtras();

        gItem = (Groceries) b.getParcelable("Object");
        name1 = gItem.getGroceries1();
        name2 = gItem.getGroceries2();

        p1 = gItem.getPrice1();
        p2 = gItem.getPrice2();

        id1 = gItem.getImdId1();
        id2 = gItem.getImdId1();

        type = gItem.getType();

        if(type.equalsIgnoreCase("Fruits")){
            img1.setImageResource(R.drawable.banana);
            img2.setImageResource(R.drawable.blueberries);
        }
        else if(type.equalsIgnoreCase("Vegetables")){
            img1.setImageResource(R.drawable.potatoes);
            img2.setImageResource(R.drawable.spinach);
        }
        else if(type.equalsIgnoreCase("Dairy")){
            img1.setImageResource(R.drawable.lfmilk);
            img2.setImageResource(R.drawable.almondmilk);
        }



        tvName1.setText(name1);
        tvName2.setText(name2);
        price1.setText(p1+"");
        price2.setText(p2+"");


        btn1 = findViewById(R.id.addBtn1);
        btn2 = findViewById(R.id.addBtn2);

        dbHelper = new DatabaseHelper(this);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer sound = MediaPlayer.create(SecondActivity.this,R.raw.soundclick);
                boolean res;
                String t = gItem.getType();
                String n = "";
                int p = 0;

                n = tvName1.getText().toString();
                p = Integer.parseInt(price1.getText().toString());
                res = CartDB.insert(dbHelper, n,t,p);
                if(res) {
                    Toast.makeText(SecondActivity.this,"Item is added to cart", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SecondActivity.this, "Error occured!", Toast.LENGTH_SHORT).show();
                }
                sound.start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer sound = MediaPlayer.create(SecondActivity.this,R.raw.soundclick);
                boolean res;
                String t = gItem.getType();
                String n = "";
                int p = 0;

                n = tvName2.getText().toString();
                p = Integer.parseInt(price2.getText().toString());
                res = CartDB.insert(dbHelper, n,t,p);
                if(res) {
                    Toast.makeText(SecondActivity.this,"Item is added to cart", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SecondActivity.this, "Error occured!", Toast.LENGTH_SHORT).show();
                }
                sound.start();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}