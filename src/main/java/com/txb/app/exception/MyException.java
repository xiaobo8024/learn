package com.txb.app.exception;

import lombok.ToString;

@ToString
public class MyException extends Exception{
    private int id;
    private String message;

    public MyException(int id,String message){
        super(message);
        this.id = id;
    }
}
