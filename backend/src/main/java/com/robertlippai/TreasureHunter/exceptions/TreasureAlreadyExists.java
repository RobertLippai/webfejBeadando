package com.robertlippai.TreasureHunter.exceptions;

public class TreasureAlreadyExists extends RuntimeException {
    public static final long serialVersionUID = 1;

    public TreasureAlreadyExists(String message){
        super(message);
    }
}
