package com.robertlippai.TreasureHunter.exceptions;

public class LocationNotFoundException extends RuntimeException{
    public static final long serialVersionUID = 1;

    public LocationNotFoundException(String message){
        super(message);
    }
}
