package com.example.abolielamarket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abolielamarket.Home;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if this is the first time the app is launched
        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
        boolean isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true);

        if (!isFirstLaunch) {
            // If not the first launch, navigate directly to Home screen
            navigateToHome();
            return;
        }

        // Otherwise, show this screen
        setContentView(R.layout.activity_welcome);

        // Find the button and set the click listener
        Button loginWithAdminButton = findViewById(R.id.loginWithAdminButton);
        loginWithAdminButton.setOnClickListener(v -> {
            // Save that the welcome screen has been shown
            sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply();

            // Navigate to the Home screen
            navigateToHome();
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(WelcomeActivity.this, Home.class);
        startActivity(intent);
        finish(); // Close the Welcome screen so it won't appear again
    }
}
