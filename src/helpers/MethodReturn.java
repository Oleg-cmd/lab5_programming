package helpers;

import model.Movie;

import java.util.HashMap;
import java.util.function.Consumer;
/**
 This class represents a wrapper class used to return both the setters of a Movie object and the Movie object itself
 from a method at the same time. It contains two instance variables: a HashMap that stores the setters of the Movie object,
 and the Movie object itself. It has a constructor to initialize these instance variables, and getter methods to return
 them.
 */
public class MethodReturn {
    private final HashMap<String, Consumer<String>> setters;
    private final Movie movie;
    /**
     * Returns the setters of the Movie object.
     * @return a HashMap representing the setters of the Movie object.
     */
    public MethodReturn(HashMap<String, Consumer<String>> setters, Movie movie) {
        this.setters = setters;
        this.movie = movie;
    }

    public HashMap<String, Consumer<String>> getSetters() {
        return setters;
    }
    /**
     * Returns the Movie object.
     * @return a Movie object.
     */
    public Movie getMovie() {
        return movie;
    }
}