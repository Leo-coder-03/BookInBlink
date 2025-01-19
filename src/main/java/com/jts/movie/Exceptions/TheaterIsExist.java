package com.jts.movie.Exceptions;

public class TheaterIsExist extends RuntimeException{
    private static final long serialVersionUID = 6386810783666583528L;

    public TheaterIsExist() {
        super("Theater is already present on this Address");
    }
}