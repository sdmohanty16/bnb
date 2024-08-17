package com.airbnb.exception;

public class CountryExists extends RuntimeException{

    public CountryExists(String msg){
        super(msg);
    }
}
