package com.peng.lab4;

public class Cellphone {
    private String image;
    private String model;
    private Double price;

    public Cellphone(String image, String model, Double price) {
        this.image = image;
        this.model = model;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
