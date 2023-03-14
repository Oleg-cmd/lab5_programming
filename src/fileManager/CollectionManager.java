package fileManager;

import model.Movie;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 The CollectionManager class manages a collection of movies.
 */

public class CollectionManager {
    private final HashSet<Movie> movies;

    /**
     * Constructs a CollectionManager object with an empty collection of movies.
     */

    public CollectionManager() {
        movies = new HashSet<>();
    }

    /**
     * Returns the collection of movies managed by this CollectionManager object.
     * @return the collection of movies
     */

    public HashSet<Movie> getMovies() {
        return movies;
    }

    /**
     * Adds a movie to the collection managed by this CollectionManager object.
     * @param movie the movie to add
     */

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    /**
     * Adds a collection of movies to the collection managed by this CollectionManager object.
     * @param moviesToAdd the collection of movies to add
     */

    public void addAllMovies(Collection<Movie> moviesToAdd) {
        for (Movie movie : moviesToAdd) {
            if (!movies.contains(movie)) { // Check if movie is already in the set
                movies.add(movie);
            }
        }
    }

    public Integer getRandomID(){
        Random rand = new Random();
        int upperbound = 9999999;
        int int_random = rand.nextInt(upperbound);
        return int_random;
    }

    public ZonedDateTime getNow(){
        return ZonedDateTime.now();
    }

    /**
     * Removes a movie from the collection managed by this CollectionManager object.
     * @param movie the movie to remove
     * @return true if the movie was removed, false otherwise
     */
    public boolean removeMovie(Movie movie) {
        return movies.remove(movie);
    }
    public boolean removeId(Integer id){
        return movies.remove(getById(id));
    }

    /**
     * Returns the number of movies in the collection managed by this CollectionManager object.
     * @return the number of movies
     */

    public int getMoviesCount() {
        return movies.size();
    }

    /**
     * Removes all movies from the collection managed by this CollectionManager object.
     */

    public void clearMovies() {
        movies.clear();
    }

    /**
     * Returns the movie with the specified ID from the collection managed by this CollectionManager object.
     * @param id the ID of the movie to find
     * @return the movie with the specified ID, or null if no such movie exists
     */

    public Movie getById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Returns a collection of movies from the collection managed by this CollectionManager object that have the specified
     * number of Oscars.
     * @param oscarsCount the number of Oscars to search for
     * @return the collection of movies with the specified number of Oscars
     */

    public HashSet<Movie> getMoviesWithOscarsCount(int oscarsCount) {
        HashSet<Movie> result = new HashSet<>();
        for (Movie movie : movies) {
            if (movie.getOscarsCount() == oscarsCount) {
                result.add(movie);
            }
        }
        return result;
    }
}