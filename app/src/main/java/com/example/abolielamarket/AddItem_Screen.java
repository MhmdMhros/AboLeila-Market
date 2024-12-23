package com.example.abolielamarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class AddItem_Screen extends AppCompatActivity {
    EditText t1, t2, t3, t4, t5;
    Button b1, b2;
    ImageView backArrow, IVPreviewImage;
    String imageURL = "https://res.cloudinary.com/ddzepnwgb/image/upload/v1733960587/identity.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);

        IVPreviewImage = findViewById(R.id.previewImageView);
        backArrow = findViewById(R.id.backArrowAdd);

        backArrow.setOnClickListener(v -> finish());

        // Initial image load
        Glide.with(this).load(imageURL).into(IVPreviewImage);

        // Show Image URL button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredURL = t4.getText().toString().trim();
                if (!enteredURL.isEmpty()) {
                    imageURL = enteredURL; // Update the image URL
                    Glide.with(AddItem_Screen.this).load(imageURL).into(IVPreviewImage); // Display the image
                    Toast.makeText(AddItem_Screen.this, "Image loaded successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddItem_Screen.this, "Please enter a valid image URL.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Save Product button
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "cartDB10")
                        .allowMainThreadQueries().build();
                ProductDao productDao = db.productDao();
                Boolean check = productDao.isExist(Integer.parseInt(t1.getText().toString()));
                if (!check) {
                    int pid = Integer.parseInt(t1.getText().toString());
                    String pname = t2.getText().toString();
                    String desc = t3.getText().toString();
                    int price = Integer.parseInt(t5.getText().toString());

                    productDao.insert(new Product(pid, pname, imageURL, desc, price));
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    Toast.makeText(AddItem_Screen.this, "Product Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    Toast.makeText(AddItem_Screen.this, "Product Already in Cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
