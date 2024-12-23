package com.example.abolielamarket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class ShoppingList extends AppCompatActivity {

    private GridView gridView;
    private List<CartItem> cartItemList;
    private AppDatabase appDatabase;
    private CartItemDao cartItemDao;
    private ImageView arrowBack;
    private TextView totalAmountTextView;
    private ImageButton refreshIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoping_list);

        // Initialize the views
        gridView = findViewById(R.id.gridViewShopping);
        arrowBack = findViewById(R.id.backArrowShop);
        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        refreshIcon = findViewById(R.id.refreshTotalIcon);


        // Initialize Room database
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "cartDB10")
                .allowMainThreadQueries() // Allows querying on the main thread (not recommended for production)
                .build();
        cartItemDao = appDatabase.cartItemDao();

        loadCartItemData();

        // Set the adapter with product data and update total amount
        CartItemAdapter cartItemAdapter = new CartItemAdapter(this, cartItemList);
        gridView.setAdapter(cartItemAdapter);

        arrowBack.setOnClickListener(v -> finish());

        // Update the total amount
        updateTotalAmount();

        // Add a click listener to update the total amount
        refreshIcon.setOnClickListener(v -> {
            updateTotalAmount();
            Toast.makeText(this, "Total Amount Updated", Toast.LENGTH_SHORT).show();
        });

    }

    private void loadCartItemData() {
        // Fetch the cart item list from the Room database
        cartItemList = cartItemDao.getAllCartItems();
    }

    private void updateTotalAmount() {
        int totalAmount = 0;
        for (CartItem cartItem : cartItemList) {
            totalAmount += cartItem.getPrice() * cartItem.getQuantity();
        }
        totalAmountTextView.setText("Total Amount: ₹" + totalAmount);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
