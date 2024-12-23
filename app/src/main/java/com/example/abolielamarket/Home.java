    package com.example.abolielamarket;

    import android.content.Intent;
    import android.os.Bundle;
    import android.widget.Button;
    import android.widget.GridView;
    import android.widget.ImageButton;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.room.Room;

    import java.util.ArrayList;
    import java.util.List;

    public class Home extends AppCompatActivity {

        private GridView gridView;
        private List<Product> productList;
        private ImageButton refreshIcon, shoppingList;
        private AppDatabase appDatabase;
        private ProductDao productDao;
        private Button addItemButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.home);

            // Initialize the views
            gridView = findViewById(R.id.gridView);
            addItemButton = findViewById(R.id.addItemButton);
            refreshIcon = findViewById(R.id.refreshIcon);
            shoppingList = findViewById(R.id.shoppingList);

            // Initialize Room database
            appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "cartDB10")
                    .allowMainThreadQueries() // Allows querying on the main thread (not recommended for production)
                    .build();
            productDao = appDatabase.productDao();

            // Refresh button functionality
            refreshIcon.setOnClickListener(v -> refreshProductData());

            // Load product data initially
            refreshProductData();

            // Navigate to AddItem screen
            addItemButton.setOnClickListener(v -> {
                Intent intent = new Intent(Home.this, AddItem_Screen.class);
                startActivity(intent);
            });

            shoppingList.setOnClickListener(v -> {
                Intent intent = new Intent(Home.this, ShoppingList.class);
                startActivity(intent);
            });
        }

        @Override
        protected void onResume() {
            super.onResume();
            // Refresh the product data when returning to the Home screen
            refreshProductData();
        }

        private void refreshProductData() {
            // Fetch the product list from the Room database
            productList = productDao.getAllProducts();

            // Ensure the product list is not null
            if (productList == null) {
                productList = new ArrayList<>();
            }

            // Set the adapter with updated product list
            ProductAdapter productAdapter = new ProductAdapter(this, productList);
            gridView.setAdapter(productAdapter);
        }
    }
