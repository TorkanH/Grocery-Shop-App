package com.kansavozer.gulcetorkanbedirhankansavproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class CartDB {
    public static String TABLE_NAME="cart";
    public static String FIELD_ID = "id";
    public static String FIELD_NAME = "name";
    public static String FIELD_TYPE = "type";
    public static String FIELD_PRICE = "price";

    public static String CREATE_TABLE_SQL="CREATE TABLE "+TABLE_NAME +"( "+FIELD_ID+" INTEGER, "+FIELD_NAME+" TEXT, "+FIELD_TYPE+" TEXT, "+FIELD_PRICE+" NUMERIC, PRIMARY KEY("+FIELD_ID+" AUTOINCREMENT))";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<Cart> getAllItems(DatabaseHelper dbHelper){
        Cart item;
        ArrayList<Cart> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name= cursor.getString(1);
            String  type= cursor.getString(2);
            int price= cursor.getInt(3);
            item = new Cart(id,name, type, price);
            data.add(item);

        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static boolean insert(DatabaseHelper dbHelper, String name, String type, int price) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_TYPE, type);
        contentValues.put(FIELD_PRICE, price);

        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static boolean update(DatabaseHelper dbHelper, String id, String name, String type, int price) {

        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_TYPE, type);
        contentValues.put(FIELD_PRICE, price);

        String where = FIELD_ID +" = "+id;
        boolean res = dbHelper.update(TABLE_NAME,contentValues,where );
        return res;
    }

    public static boolean delete(  DatabaseHelper dbHelper, String id){
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_ID + " = "+id;
        boolean res =  dbHelper.delete(TABLE_NAME, where);
        return  res;
    }

}
