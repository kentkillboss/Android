package com.example.product1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SqliteDatabase mDatabase;
    //private ArrayList<Product> listProduct;
    private List<Product> allProducts;
    private ProductAdapter mAdapter;
    private ViewFlipper viewFlipper;
    Animation in, out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Sản Phẩm");
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        in = AnimationUtils.loadAnimation(this,R.anim.face_in);
        out = AnimationUtils.loadAnimation(this,R.anim.face_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_to_do);
        RecyclerView productView = (RecyclerView)findViewById(R.id.product_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(linearLayoutManager);
        productView.setHasFixedSize(true);
        mDatabase = new SqliteDatabase(this);
        //List<Product>
                allProducts = mDatabase.listProducts();
        if(allProducts.size() > 0){
            productView.setVisibility(View.VISIBLE);
             mAdapter = new ProductAdapter(this, allProducts);
            productView.setAdapter(mAdapter);
        }else {
            productView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no product in the database. Start adding now", Toast.LENGTH_LONG).show();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add new quick task
                addTaskDialog();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);



        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.xemLS:
                Intent it = new Intent(this, LichSu.class);
                startActivity(it);
                break;
            case R.id.Thoat:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText){
        newText = newText.toLowerCase();
        List<Product> newList = new LinkedList<>();
        for(Product product : allProducts ){
            String name = product.getName().toLowerCase();
            if (name.contains(newText)){
                newList.add(product);
            }
        }
        mAdapter.setFilter(newList);
        return true;
    }

    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_product_layout, null);
        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
       // final EditText quantityField = (EditText)subView.findViewById(R.id.enter_quantity);
        final EditText price = (EditText)subView.findViewById(R.id.enter_Price);
        final EditText nameCus = (EditText)subView.findViewById(R.id.enter_nameCus);
        final EditText address = (EditText)subView.findViewById(R.id.enter_Address);
        final EditText phone = (EditText)subView.findViewById(R.id.enter_Phone);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new product");
        builder.setView(subView);
        builder.create();
        final String namefild =nameField.getText().toString();

        if(TextUtils.isEmpty(namefild)){
            nameField.setError("Please Enter Your Name");
        }

        builder.setPositiveButton("ADD PRODUCT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                //final int quantity = Integer.parseInt(quantityField.getText().toString());
                final int Pricee = Integer.parseInt(price.getText().toString());
                final String NameCus =(nameCus.getText().toString());
                final String addRess =(address.getText().toString());
                final int Phone = Integer.parseInt(phone.getText().toString());

                if(TextUtils.isEmpty(name) ){
                    Toast.makeText(MainActivity.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }

                else{
                    Product newProduct = new Product(name, Pricee, NameCus, addRess, Phone);
                    mDatabase.addProduct(newProduct);
                    //refresh the activity
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }
}