package com.robertlippai.TreasureHunter.exceptions;
public class LocationAlreadyExists extends RuntimeException {
    public static final long serialVersionUID = 1;

    public LocationAlreadyExists(String message){
        super(message);
    }
}
