package com.airbnb.exception;

public class CityExists extends RuntimeException{

    public CityExists(String msg){
        super(msg);
    }
}
