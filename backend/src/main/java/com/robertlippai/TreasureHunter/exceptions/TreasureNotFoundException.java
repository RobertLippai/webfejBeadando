package com.robertlippai.TreasureHunter.exceptions;

public class TreasureNotFoundException extends RuntimeException{
    public static final long serialVersionUID = 1;

    public TreasureNotFoundException(String message){
        super(message);
    }
}
