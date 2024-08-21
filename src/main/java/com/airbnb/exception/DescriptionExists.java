package com.airbnb.exception;

public class DescriptionExists extends RuntimeException{

    public DescriptionExists(String msg){
        super(msg);
    }
}
