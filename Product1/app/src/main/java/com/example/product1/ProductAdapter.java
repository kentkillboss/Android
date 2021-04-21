package com.example.product1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>  {
    private Context context;
    private List<Product> listProducts;
    private SqliteDatabase mDatabase;

    public ProductAdapter(Context context, List<Product> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
        mDatabase = new SqliteDatabase(context);
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_layout, parent, false);
        context = view.getContext();
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        final Product singleProduct = listProducts.get(position);
        holder.name.setText(singleProduct.getName());
        holder.editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(singleProduct);
            }
        });
        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database
                mDatabase.deleteProduct(singleProduct.getId());
                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
        holder.desProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //desTaskDialog(singleProduct);
                Intent it = new Intent(context, Chitiet.class);

                Bundle bundle=new Bundle();
                bundle.putString("Name",singleProduct.getName());
                bundle.putString("NameCus",singleProduct.getNameCus());
                bundle.putString("Address",singleProduct.getAddress());
                int a = Integer.parseInt(singleProduct.getPrice() + "");
                int b = Integer.parseInt(singleProduct.getPhone() + "");
                bundle.putInt("Price",a);
                bundle.putInt("Phone",b);
                it.putExtra("MyPackage",bundle);
                context.startActivity(it);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }
    public void  setFilter(List<Product> newList){
        listProducts = new LinkedList<>();
        listProducts.addAll(newList);
        notifyDataSetChanged();
    }
    private void editTaskDialog(final Product product){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_product_layout, null);
        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
        //final EditText quantityField = (EditText)subView.findViewById(R.id.enter_quantity);
        final EditText price = (EditText)subView.findViewById(R.id.enter_Price);
        final EditText nameCus = (EditText)subView.findViewById(R.id.enter_nameCus);
        final EditText address = (EditText)subView.findViewById(R.id.enter_Address);
        final EditText phone = (EditText)subView.findViewById(R.id.enter_Phone);
        if(product != null){
            nameField.setText(product.getName());
            //quantityField.setText(String.valueOf(product.getQuantity()));
            price.setText(String.valueOf(product.getPrice()));
            nameCus.setText(product.getNameCus());
            address.setText((product.getAddress()));
            phone.setText(String.valueOf(product.getPhone()));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit product");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("EDIT PRODUCT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                //final int quantity = Integer.parseInt(quantityField.getText().toString());
                final int Pricee = Integer.parseInt(price.getText().toString());
                final String NameCus =(nameCus.getText().toString());
                final String addRess =(address.getText().toString());
                final int Phone = Integer.parseInt(phone.getText().toString());
                if(TextUtils.isEmpty(name) ){
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    mDatabase.updateProduct(new Product(product.getId(), name, Pricee, NameCus, addRess, Phone));
                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

}
