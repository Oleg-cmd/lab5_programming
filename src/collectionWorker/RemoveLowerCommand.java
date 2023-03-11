package collectionWorker;

import model.Movie;
import model.MpaaRating;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 RemoveLowerCommand is a class that represents the remove_lower command. It removes the last movie in the collection
 that has a rating lower than or equal to the one entered by the user.
 */
public class RemoveLowerCommand implements Command {
    public static String info = "remove_lower command:\n" +
            "   This command will remove last movie that rating will be lower or equal urs\n";

    public static void RemoveArg(String rate){
        try{
            Comparator<Movie> mpaaComparator = Comparator.comparingInt(movie -> movie.getMpaaRating().ordinal());
            List<Movie> sortedMovies = new ArrayList<>(collectionManager.getMovies());
            sortedMovies.sort(mpaaComparator);
            MpaaRating last = sortedMovies.get(sortedMovies.size()-1).getMpaaRating();

            try{
                MpaaRating movieRating = MpaaRating.valueOf(rate);
                if(last.ordinal() <= movieRating.ordinal()){
                    writer.write("Okey, u can do it");
                    collectionManager.removeMovie(sortedMovies.get(sortedMovies.size() - 1));
                    writer.write("The movie was removed");
                }else{
                    writer.write("Im sorry, but your rating is too small");
                }
            }catch (IllegalArgumentException err){
                System.out.println("Illegal args");
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * Executes the remove_lower command. Prompts the user to enter an MPAA rating and removes the last movie in the
     * collection that has a rating lower than or equal to the one entered by the user.
     */

    @Override
    public void execute(){
        try{
            Comparator<Movie> mpaaComparator = Comparator.comparingInt(movie -> movie.getMpaaRating().ordinal());
            List<Movie> sortedMovies = new ArrayList<>(collectionManager.getMovies());
            sortedMovies.sort(mpaaComparator);
            MpaaRating last = sortedMovies.get(sortedMovies.size()-1).getMpaaRating();

            writer.write("Enter movie mpaa rating if u want to remove last field with lower rating that u entered: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try{
                MpaaRating movieRating = MpaaRating.valueOf(reader.readLine().trim());
                if(last.ordinal() <= movieRating.ordinal()){
                    writer.write("Okey, u can do it");
                    collectionManager.removeMovie(sortedMovies.get(sortedMovies.size() - 1));
                    writer.write("The movie was removed");
                }else{
                    writer.write("Im's sorry, but your rating is too small");
                }
            }catch (IllegalArgumentException err){
                System.out.println("Illegal args");
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
