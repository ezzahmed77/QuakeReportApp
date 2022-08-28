package com.example.android.quakereport;

public class Earthquake {
    private double mag;
    private String location;
    private long timeInMilliseconds;
    String url;

    //Constructor
    public Earthquake(double mag, String location, long timeInMilliseconds, String url) {
        this.mag = mag;
        this.location = location;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    //Get the magnitude of earthquake
    public double getMag(){
        return mag;
    }

    // Get the location
    public String getLocation(){
        return location;
    }

    // Get the date
    public long getTimeInMilliseconds(){
        return timeInMilliseconds;
    }
    //Get the URL
    public String getUrl(){
        return url;
    }
}
