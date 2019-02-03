package com.example.sviye.asahiproject;

public class DiaPrice {

    private String diameter;
    private Double price;

    public DiaPrice() {
        //public no-arg constructor needed
    }

    public DiaPrice(String diameter, Double price) {
        this.diameter = diameter;
        this.price = price;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
