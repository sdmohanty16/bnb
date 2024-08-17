package com.airbnb.exception;

public class PropertyExists extends RuntimeException{

    public PropertyExists(String msg){
        super(msg);
    }
}
