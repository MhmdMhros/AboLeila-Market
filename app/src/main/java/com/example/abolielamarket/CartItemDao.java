package com.example.abolielamarket;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartItemDao {

    @Insert
    void insertCartItem(CartItem cartItem);

    @Update
    void updateCartItem(CartItem cartItem);

    @Delete
    void deleteCartItem(CartItem cartItem);

    @Query("DELETE FROM CartItem")
    void deleteAllCartItems();

    @Query("DELETE FROM CartItem WHERE id = :id")
    void deleteCartItemById(int id);

    @Query("SELECT * FROM CartItem")
    List<CartItem> getAllCartItems();

    @Query("SELECT EXISTS (SELECT 1 FROM CartItem WHERE id = :id LIMIT 1)")
    boolean isExist(int id);

    @Query("SELECT * FROM CartItem WHERE id = :id")
    CartItem getCartItemById(int id);

}
