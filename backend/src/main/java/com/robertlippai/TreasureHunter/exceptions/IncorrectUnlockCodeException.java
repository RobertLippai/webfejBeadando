package com.robertlippai.TreasureHunter.exceptions;

public class IncorrectUnlockCodeException extends RuntimeException{
    public static final long serialVersionUID = 1;

    public IncorrectUnlockCodeException(String message){
        super(message);
    }
}
