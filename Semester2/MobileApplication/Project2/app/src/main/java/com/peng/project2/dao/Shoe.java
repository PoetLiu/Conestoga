package com.peng.project2.dao;

import java.util.List;

public class Shoe {
    private Long id;
    private String brand;
    private String name;
    private String details;
    private List<ShoeStyle> styles;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<ShoeStyle> getStyles() {
        return styles;
    }

    public void setStyles(List<ShoeStyle> styles) {
        this.styles = styles;
    }
}
