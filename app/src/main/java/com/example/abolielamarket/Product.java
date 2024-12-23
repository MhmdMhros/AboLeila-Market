package com.example.abolielamarket;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.net.URI;

@Entity(tableName = "Product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int pid;
    private String image;
    private String disc;
    private String pname;
    private int price;

    public Product(int pid, String pname, String image, String disc,int price) {
        this.pid = pid;
        this.pname = pname;
        this.image = image;
        this.disc = disc;
        this.price = price;
    }

    // Getters and Setters
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

}
