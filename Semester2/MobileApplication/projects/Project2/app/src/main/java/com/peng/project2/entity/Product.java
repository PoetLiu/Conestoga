package com.peng.project2.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.File;

@Entity
public class Product {
    @PrimaryKey
    private Long id;
    private String title;
    private String subTitle;
    private String colorInfo;
    private String details;
    private String brand;
    private Double price;
    private Integer stock;
    private String imageUrl;

    @Ignore
    private File tmpImageFile;

    public Product() {
    }

    public Product(String title, String subTitle, String colorInfo, String details,
                   String brand, Double price, Integer stock, String imageUrl) {
        this.title = title;
        this.subTitle = subTitle;
        this.colorInfo = colorInfo;
        this.details = details;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    public File getTmpImageFile() {
        return tmpImageFile;
    }

    public void setTmpImageFile(File tmpImageFile) {
        this.tmpImageFile = tmpImageFile;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getColorInfo() {
        return colorInfo;
    }

    public void setColorInfo(String colorInfo) {
        this.colorInfo = colorInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", colorInfo='" + colorInfo + '\'' +
                ", details='" + details + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
