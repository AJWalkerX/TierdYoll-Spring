package com.ajwalker.exception;

import lombok.Getter;

@Getter
public class TierdYolException extends RuntimeException{
    private ErrorType errorType;
    public TierdYolException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}