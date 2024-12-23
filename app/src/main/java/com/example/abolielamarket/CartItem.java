package com.example.abolielamarket;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "CartItem")
public class CartItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String image;
    private String desc;
    private String name;
    private int price;
    private int quantity;

    public CartItem(int id, String name, String image, String desc,int price,int quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
