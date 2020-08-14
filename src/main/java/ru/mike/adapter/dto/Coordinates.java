package ru.mike.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Coordinates {
    private String latitude;
    private String longitude;

    public Coordinates(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinates() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
