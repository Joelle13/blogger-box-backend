package com.dauphine.blogger.exceptions;

public class CategoryAlreadyExistsException extends Exception{

    public CategoryAlreadyExistsException(){
        super("This category already exists !!");
    }
}
