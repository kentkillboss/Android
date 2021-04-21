package com.example.product1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ImageView deleteProduct;
    public  ImageView editProduct;
    public  ImageView desProduct;
    public ImageView logoProduct;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.product_name);
        deleteProduct = (ImageView)itemView.findViewById(R.id.delete_product);
        editProduct = (ImageView)itemView.findViewById(R.id.edit_product);
        desProduct = (ImageView)itemView.findViewById(R.id.des_product);
        logoProduct = (ImageView)itemView.findViewById(R.id.imageview);
    }
}
