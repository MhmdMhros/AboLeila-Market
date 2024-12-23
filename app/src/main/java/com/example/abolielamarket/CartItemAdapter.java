package com.example.abolielamarket;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;


import androidx.room.Room;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartItemAdapter extends BaseAdapter {

    private Context context;
    private List<CartItem> cartItemList;

    public CartItemAdapter(Context context, List<CartItem> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
    }

    @Override
    public int getCount() {
        return cartItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cartItemList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item_in_list, parent, false);
        }

        // Initialize the views in the list item layout
        ImageView cartItemImage = convertView.findViewById(R.id.cartItemImage);
        TextView cartItemName = convertView.findViewById(R.id.cartItemName);
        TextView cartItemDesc = convertView.findViewById(R.id.cartItemDesc);
        TextView cartItemPrice = convertView.findViewById(R.id.cartItemPrice);
        TextView cartItemQuantity = convertView.findViewById(R.id.quantityText);
        ImageButton deleteButton = convertView.findViewById(R.id.deleteItemButton);
        ImageButton incrementButton = convertView.findViewById(R.id.increaseQuantityButton);
        ImageButton decrementButton = convertView.findViewById(R.id.decreaseQuantityButton);

        // Set data for the current product
        CartItem cartItem = cartItemList.get(position);
        Glide.with(context).load(cartItem.getImage()).into(cartItemImage);
        cartItemName.setText(cartItem.getName());
        cartItemDesc.setText(cartItem.getDesc());
        cartItemPrice.setText(String.valueOf(cartItem.getPrice()));
        cartItemQuantity.setText(String.valueOf(cartItem.getQuantity()));



        deleteButton.setOnClickListener(v -> {
            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "cartDB10").allowMainThreadQueries().build();
            CartItemDao cartItemDao = db.cartItemDao();
            cartItemDao.deleteCartItemById(cartItem.getId());
            cartItemList.remove(position);
            notifyDataSetChanged();

            Intent intent = new Intent(context, ShoppingList.class);
            context.startActivity(intent);
            ((ShoppingList) context).finish(); // Finish current activity to prevent backstack issues
        });

        incrementButton.setOnClickListener(v -> {
            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "cartDB10").allowMainThreadQueries().build();
            CartItemDao cartItemDao = db.cartItemDao();
            int quantity = cartItem.getQuantity();
            cartItem.setQuantity(quantity + 1);
            cartItemDao.updateCartItem(cartItem);
            notifyDataSetChanged();

            Intent intent = new Intent(context, ShoppingList.class);
            context.startActivity(intent);
            ((ShoppingList) context).finish();
        });

        decrementButton.setOnClickListener(v -> {
            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "cartDB10").allowMainThreadQueries().build();
            CartItemDao cartItemDao = db.cartItemDao();
            int quantity = cartItem.getQuantity();
            if (quantity > 0) {
                cartItem.setQuantity(quantity - 1);
                cartItemDao.updateCartItem(cartItem);
                notifyDataSetChanged();

                Intent intent = new Intent(context, ShoppingList.class);
                context.startActivity(intent);
                ((ShoppingList) context).finish();
            }
        });



        return convertView;
    }
}
