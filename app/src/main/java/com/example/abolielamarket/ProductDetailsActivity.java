package com.example.abolielamarket;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView productImage, backArrow;
    private TextView productName, productDescription,productPrice;
    private Button addToCartButton, removeFromCartButton, deleteItem;

    private Product product;
    private AppDatabase appDatabase;
    private ProductDao productDao;
    private CartItemDao cartItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Initialize the database and DAO
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "cartDB10")
                .allowMainThreadQueries() // For simplicity (not recommended for production)
                .build();
        productDao = appDatabase.productDao();
        cartItemDao = appDatabase.cartItemDao();

        // Retrieve the product passed via Intent
        int productId = getIntent().getIntExtra("product_id",-1);

        if (productId != -1) {
            product = productDao.getProductById(productId);
        } else {
            Toast.makeText(this, "Product not found!", Toast.LENGTH_SHORT).show();
            finish();
        }
//        product = productDao.getProductById(61);
        // Initialize the views
        productImage = findViewById(R.id.productImageView);
        backArrow = findViewById(R.id.backArrowP);
        productName = findViewById(R.id.productNameTextView);
        productDescription = findViewById(R.id.productDescriptionTextView);
        productPrice = findViewById(R.id.productPriceTextView);
        addToCartButton = findViewById(R.id.addToCartButton);
        removeFromCartButton = findViewById(R.id.removeFromCartButton);
        deleteItem = findViewById(R.id.deleteItem);

        // Set product details
        Glide.with(this).load(product.getImage()).into(productImage);
        productName.setText(product.getPname());
        productDescription.setText(product.getDisc());
        productPrice.setText("Price: ₹" + product.getPrice());

        // Check if the product is already in the cart
        boolean isInCart = cartItemDao.isExist(product.getPid());
        toggleCartButtons(isInCart);

        // Set click listeners
        backArrow.setOnClickListener(v -> finish());

        addToCartButton.setOnClickListener(v -> {
            cartItemDao.insertCartItem(new CartItem(product.getPid(), product.getPname(), product.getImage(), product.getDisc(), product.getPrice(),1));
            String name = cartItemDao.getCartItemById(product.getPid()).getName();
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            toggleCartButtons(true);
            Toast.makeText(this, "Added to cart!", Toast.LENGTH_SHORT).show();
        });

        removeFromCartButton.setOnClickListener(v -> {
            cartItemDao.deleteCartItemById(product.getPid());
            toggleCartButtons(false);
            Toast.makeText(this, "Removed from cart!", Toast.LENGTH_SHORT).show();
        });
        deleteItem.setOnClickListener(v -> {
            productDao.deleteById(product.getPid());
            cartItemDao.deleteCartItemById(product.getPid());
            Toast.makeText(this, "Item deleted from database", Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private void toggleCartButtons(boolean isInCart) {
        if (isInCart) {
            addToCartButton.setVisibility(View.GONE);
            removeFromCartButton.setVisibility(View.VISIBLE);
        } else {
            addToCartButton.setVisibility(View.VISIBLE);
            removeFromCartButton.setVisibility(View.GONE);
        }
    }
}
