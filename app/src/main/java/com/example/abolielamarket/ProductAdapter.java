package com.example.abolielamarket;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;


import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productList.get(position).getPid();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        }

        // Initialize the views in the list item layout
        ImageView productImage = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productDesc = convertView.findViewById(R.id.productDesc);
        TextView productPrice = convertView.findViewById(R.id.productPrice);

        // Set data for the current product
        Product product = productList.get(position);
        Glide.with(context).load(product.getImage()).into(productImage);
        productName.setText(product.getPname());
        productDesc.setText(product.getDisc());
        productPrice.setText("Price: " + product.getPrice());
        // Handle item click to navigate to product details screen
        convertView.setOnClickListener(v -> {
            int productId = product.getPid();
            Intent intent = new Intent(context, ProductDetailsActivity.class);
            intent.putExtra("product_id", productId); // Pass product ID
            context.startActivity(intent);
        });

        return convertView;
    }
}
