package com.kansavozer.gulcetorkanbedirhankansavproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Groceries>>  {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CustomRecyclerAdapter adapter;
    ImageButton btnCart;
    private GestureDetectorCompat gestureDetector;
    private CustomGestureListener customGestureListener;

    // JSON related
    private String jsonStr;
    private JSONArray Groceries;
    private JSONObject courseJSONObject;
    private JSONObject allJSON;



    private ArrayList<Groceries> GroceriesList =new ArrayList();

    public static final String TAG_GROCERIES = "groceries";
    public static final String TAG_TYPE = "type";
    public static final String TAG_ITEM1 = "item1";
    public static final String TAG_IMG1 = "imgId1";
    public static final String TAG_PRICE1 = "price1";
    public static final String TAG_ITEM2 = "item2";
    public static final String TAG_IMG2 = "imgId2";
    public static final String TAG_PRICE2 = "price2";

    LoaderManager loaderManager=null;
    private static int TASK_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        mRecyclerView = (RecyclerView) findViewById(R.id.rcycCart);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btnCart = findViewById(R.id.btnCart);

        //simplecallbackerr
        //ItemTouchHelper.SimpleCallback simpleCallback = null;
        //ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        //itemTouchHelper.attachToRecyclerView(mRecyclerView);
        customGestureListener = new CustomGestureListener();
        gestureDetector = new GestureDetectorCompat(this, customGestureListener);

        if (loaderManager == null) {
            loaderManager = LoaderManager.getInstance(MainActivity.this);
            loaderManager.initLoader(TASK_ID, null, MainActivity.this).forceLoad();
        } else
            loaderManager.restartLoader(TASK_ID, null, MainActivity.this).forceLoad();

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        btnCart.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    Toast.makeText(getApplicationContext(), "long press", Toast.LENGTH_SHORT).show();
                    super.onLongPress(e);
                }

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    Toast.makeText(getApplicationContext(), "Double tap", Toast.LENGTH_SHORT).show();

                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });

//        ItemTouchHelper.SimpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//            }
//        };

    }

    @NonNull
    @Override
    public Loader<ArrayList<Groceries>> onCreateLoader(int id, @Nullable Bundle args) {
        return new FetchJSON(this);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Groceries>> loader) {
        adapter = new CustomRecyclerAdapter(this, GroceriesList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Groceries>> loader, ArrayList<Groceries> data) {
        if (data != null) {
            GroceriesList = data;
            adapter = new CustomRecyclerAdapter(this, GroceriesList);
            mRecyclerView.setAdapter(adapter);
        } else
            Toast.makeText(MainActivity.this, "Not Found", Toast.LENGTH_LONG).show();
    }

    private static class FetchJSON extends AsyncTaskLoader<ArrayList<Groceries>> {

        String myDdata = "";
        String line = "";
        Context context;
        String key;
        public FetchJSON(Context context) {
            super(context);
            this.context = context;
            this.key = key;
        }
        @Override
        public  ArrayList<Groceries> loadInBackground () {
            ArrayList<Groceries> tempList = new ArrayList<Groceries>();
            String jsonStr = loadFileFromAssets("groceries.json");
            if (jsonStr != null) {
                try {
                    JSONObject allJSON = new JSONObject(jsonStr);
                    JSONArray GroceriesJSONArray = allJSON.getJSONArray(TAG_GROCERIES);


                    for (int i = 0; i < GroceriesJSONArray.length(); i++) {
                        JSONObject s = GroceriesJSONArray.getJSONObject(i);
                        Thread.sleep(900);
                        String type = s.getString(TAG_TYPE);
                        String item1 = s.getString(TAG_ITEM1);
                        int price1 = s.getInt(TAG_PRICE1);
                        String imgId1 = s.getString(TAG_IMG1);
                        String item2 = s.getString(TAG_ITEM2);
                        int price2 = s.getInt(TAG_PRICE2);
                        String imgId2 = s.getString(TAG_IMG2);
                        Groceries stu = new Groceries(type, item1, price1, imgId1, item2, price2, imgId2);
                        tempList.add(stu);
                    }
                } catch (JSONException ee) {
                    ee.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return tempList;
        }
        private String loadFileFromAssets(String fileName) {
            String fileContent = null;
            try {
                InputStream is = context.getAssets().open(fileName);

                int size = is.available();
                byte[] buffer = new byte[size];

                is.read(buffer);
                is.close();

                fileContent = new String(buffer, "UTF-8");

            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return fileContent;
        }
    }

    class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            Toast.makeText(getBaseContext(), "Press to view the available products", Toast.LENGTH_SHORT).show();
        }
    }

}