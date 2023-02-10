package com.example.wtank3.model;

import java.util.List;

public class responseModel {


    String id, sensor,location,value1,value2,value3,reading_time;

    public responseModel() {
    }

    public responseModel(String id, String sensor, String location, String value1, String value2, String value3, String reading_time) {
        this.id = id;
        this.sensor = sensor;
        this.location = location;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.reading_time = reading_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getReading_time() {
        return reading_time;
    }

    public void setReading_time(String reading_time) {
        this.reading_time = reading_time;
    }
}
