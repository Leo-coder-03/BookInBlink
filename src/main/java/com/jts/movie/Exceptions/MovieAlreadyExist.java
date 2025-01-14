package com.jts.movie.Exceptions;

public class MovieAlreadyExist extends RuntimeException{
    private static final long serialVersionUID = 8299839004990203987L;
    public MovieAlreadyExist(){
        super("Movie already exists with same name and language");
    }
}
