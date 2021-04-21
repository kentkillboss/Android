package com.example.product1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqliteDatabase  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;
    private static final String    DATABASE_NAME = "all";
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTNAME = "productname";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_NAMECUS = "nameCus";
    private static final String COLUMN_ADDRESS= "address";
    private static final String COLUMN_PHONE = "phone";



    private static final String TABLE_HISTORY = "history";
    private static final String COLUMN_IDLS = "id";
    private static final String COLUMN_NAMECUSTOMER = "nameCus";
    private static final String COLUMN_NAMEPRODUCT = "nameProduct";
    private static final String COLUMN_DATE = "datetime";
    private static final String COLUMN_TONGTIEN = "tongtien";

    private List<History> histories;

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
            String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement," +
                    COLUMN_PRODUCTNAME + " TEXT," +
                    COLUMN_PRICE + " INTEGER," +
                    COLUMN_NAMECUS + " TEXT," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_PHONE + " INTEGER" +
                    ")";
            db.execSQL(CREATE_PRODUCTS_TABLE);
            String LICHSU = "CREATE TABLE " + TABLE_HISTORY +
                    "(" + COLUMN_IDLS + " INTEGER PRIMARY KEY autoincrement," +
                    COLUMN_NAMECUSTOMER + " TEXT," +
                    COLUMN_NAMEPRODUCT + " TEXT," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_TONGTIEN + " INTEGER" +
                    ")";
            db.execSQL(LICHSU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }
    public List<Product> listProducts(){
        String sql = "select * from " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product> storeProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
               // int quantity = Integer.parseInt(cursor.getString(2));
                int price = Integer.parseInt(cursor.getString(2));
                String nameCus = cursor.getString(3);
                String address = (cursor.getString(4));
                int phone = Integer.parseInt(cursor.getString(5));
                storeProducts.add(new Product(id, name, price, nameCus, address, phone));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }
    public void addProduct(Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getName());
        //values.put(COLUMN_QUANTITY, product.getQuantity());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_NAMECUS, product.getNameCus());
        values.put(COLUMN_ADDRESS, product.getAddress());
        values.put(COLUMN_PHONE, product.getPhone());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS,null,values);
        db.close();
    }
    public void updateProduct(Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getName());
       // values.put(COLUMN_QUANTITY, product.getQuantity());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_NAMECUS, product.getNameCus());
        values.put(COLUMN_ADDRESS, product.getAddress());
        values.put(COLUMN_PHONE, product.getPhone());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_PRODUCTS, values, COLUMN_ID    + "    = ?", new String[] { String.valueOf(product.getId())});
    }
    public void deleteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, COLUMN_ID    + "    = ?", new String[] { String.valueOf(id)});
    }

    public void addHistory(History history){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMECUSTOMER, history.getNameCus());
        values.put(COLUMN_NAMEPRODUCT, history.getNameSP());
        values.put(COLUMN_DATE, history.getDatetime());
        values.put(COLUMN_TONGTIEN, history.getTongtien());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_HISTORY,null,values);
        db.close();
    }
    public int deleteHistory1(History a){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_HISTORY, COLUMN_IDLS    + " = ?", new String[] { String.valueOf(a.getId())});
    }


    public List<History> getall(){
        String sql = "select * from " + TABLE_HISTORY;
        histories = new ArrayList<History>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = Integer.parseInt(cursor.getString(0));
            String tenkhach = cursor.getString(1);
            String tensp = cursor.getString(2);
            String ngaythang = cursor.getString(3);
            int tongtien = cursor.getInt(4);
            History history = new History(id,tenkhach, tensp, ngaythang, tongtien);
            histories.add(history);
            cursor.moveToNext();
        }
        return histories;
    }

}
