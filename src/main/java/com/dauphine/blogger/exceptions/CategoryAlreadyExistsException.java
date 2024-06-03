package com.dauphine.blogger.exceptions;

public class CategoryAlreadyExistsException extends Exception{

    public CategoryAlreadyExistsException(String name){
        super("The " + name + " category already exists !!");
    }
}
