package com.airbnb.exception;

public class ReviewExists extends RuntimeException{

    public ReviewExists(String msg){
        super(msg);
    }
}
