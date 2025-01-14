package com.jts.movie.Exceptions;

public class MovieDoesNotExists extends RuntimeException{
    private static final long serialVersionUID = -5385129013790060351L;

    public MovieDoesNotExists() {
        super("Movie dose not Exists");
    }
}
