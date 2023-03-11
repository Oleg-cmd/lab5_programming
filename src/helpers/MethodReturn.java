package helpers;

import model.Movie;

import java.util.HashMap;
import java.util.function.Consumer;

public class MethodReturn {
    private final HashMap<String, Consumer<String>> setters;
    private final Movie movie;

    public MethodReturn(HashMap<String, Consumer<String>> setters, Movie movie) {
        this.setters = setters;
        this.movie = movie;
    }

    public HashMap<String, Consumer<String>> getSetters() {
        return setters;
    }

    public Movie getMovie() {
        return movie;
    }
}