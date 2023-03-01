package collectionWorker;

import fileManager.CollectionManager;
import fileManager.Command;
import model.Movie;
import model.MpaaRating;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 RemoveLowerCommand is a class that represents the remove_lower command. It removes the last movie in the collection
 that has a rating lower than or equal to the one entered by the user.
 */
public class RemoveLowerCommand implements Command {
    public static String info = "remove_lower command:\n" +
            "   This command will remove last movie that rating will be lower or equal urs\n";
    private final HashSet<Movie> myCollection;
    private CollectionManager manager;

    /**
     * Constructs a new RemoveLowerCommand with the given collection and manager.
     * @param myCollection the collection to remove from
     * @param manager the collection manager to use for removing the movie
     */
    public RemoveLowerCommand(HashSet<Movie> myCollection, CollectionManager manager) {
        this.myCollection = myCollection;
        this.manager = manager;
    }

    /**
     * Executes the remove_lower command. Prompts the user to enter an MPAA rating and removes the last movie in the
     * collection that has a rating lower than or equal to the one entered by the user.
     */

    @Override
    public void execute(){
        try{
            Comparator<Movie> mpaaComparator = new Comparator<Movie>() {
                @Override
                public int compare(Movie movie1, Movie movie2) {
                    return Integer.compare(movie1.getMpaaRating().ordinal(), movie2.getMpaaRating().ordinal());
                }
            };

            List<Movie> sortedMovies = new ArrayList<>(myCollection);
            sortedMovies.sort(mpaaComparator);

            MpaaRating last = sortedMovies.get(sortedMovies.size()-1).getMpaaRating();

            System.out.print("Enter movie mpaa rating if u want to remove last field with lower rating that u entered: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            MpaaRating movieRating = MpaaRating.valueOf(reader.readLine().trim());

            if(Integer.compare(last.ordinal(), movieRating.ordinal()) <= 0){
                System.out.println("Okey, u can do it");
                manager.removeMovie(sortedMovies.get(sortedMovies.size() - 1));
                System.out.println("The movie was removed");
            }else{
                System.out.println("Im's sorry, but your rating is too small");
            }

        }catch (IOException e){
            System.out.println(e);
        }
    }
}
