package com.example.app;

public class Airport {
    private final String name;
    private final String code;
    private final String city;
    private final String country;
    private final float GMT;
    private final float latitude;
    private final float longitude;

    Airport(String name, String code, String city, String country, float GMT, float latitude, float longitude) {
        this.name = name;
        this.code = code;
        this.city = city;
        this.country = country;
        this.GMT = GMT;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public float getGMT() {
        return GMT;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Airport{ " + name + "\nCode: " + code + "\nCity: " + city + "\nCountry: " + country + "\nGMT: " + GMT + "\nLatitude: "
                + latitude + "\nLongitude: " + longitude + "}";
    }
}
