package com.peng.project2.dao;

import java.util.List;

public class ShoeStyle {
    private String name;
    private List<ShoeSize> sizes;
    private List<String> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoeSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<ShoeSize> sizes) {
        this.sizes = sizes;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
