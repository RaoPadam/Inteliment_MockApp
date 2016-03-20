package com.padam.mockapp.navigationApp;

/**
 * Created by padam on 20-03-2016.
 */
public class Beans {


    private int id;
    private String name, mode_car, mode_train, location_longitude, location_latitude;

    public Beans() {
    }


    public Beans(int id, String name, String mode_car, String mode_train, String location_latitude, String location_longitude) {
        this.id = id;
        this.name = name;
        this.mode_car = mode_car;
        this.mode_train = mode_train;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;


    }


    public Beans(String name, String mode_car, String mode_train, String location_latitude, String location_longitude) {

        this.name = name;
        this.mode_car = mode_car;
        this.mode_train = mode_train;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;


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

    public String getLocation_latitude() {
        return location_latitude;
    }

    public String getLocation_longitude() {
        return location_longitude;
    }

    public String getMode_car() {
        return mode_car;
    }

    public String getMode_train() {
        return mode_train;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;

    }

    public void setLocation_longitude(String location_longitude) {
        this.location_longitude = location_longitude;
    }

    public void setMode_car(String mode_car) {
        this.mode_car = mode_car;
    }

    public void setMode_train(String mode_train) {
        this.mode_train = mode_train;
    }
}


