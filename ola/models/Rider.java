package ola.models;

import ola.enums.Rating;

public class Rider {
    private String name;
    private Rating rating;
    
    public Rider(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
    }
    
    public String getName() { return name; }
    public Rating getRating() { return rating; }
}