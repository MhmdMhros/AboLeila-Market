package com.example.abolielamarket;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM Product")
    void deleteAll();

    @Query("DELETE FROM Product WHERE pid = :id")
    void deleteById(int id);

    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();

    @Query("SELECT EXISTS (SELECT 1 FROM Product WHERE pid = :id LIMIT 1)")
    boolean isExist(int id);

    @Query("SELECT * FROM Product WHERE pid = :id")
    Product getProductById(int id);

}
