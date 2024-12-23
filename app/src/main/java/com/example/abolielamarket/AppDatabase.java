package com.example.abolielamarket;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class,CartItem.class}, version = 10, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract CartItemDao cartItemDao();
}
