package com.example.jimrat.models;

public class Gym {
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private int imageId;
    private String password;
    private double monthPrice;
    private double halfyearPrice;
    private double yearPrice;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(double monthPrice) {
        this.monthPrice = monthPrice;
    }

    public double getHalfyearPrice() {
        return halfyearPrice;
    }

    public void setHalfyearPrice(double halfyearPrice) {
        this.halfyearPrice = halfyearPrice;
    }

    public double getYearPrice() {
        return yearPrice;
    }

    public void setYearPrice(double yearPrice) {
        this.yearPrice = yearPrice;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double langitude) {
        this.latitude = langitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
