package collectionWorker;

import fileManager.CollectionManager;
import fileManager.Command;
import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;

/**
 * A command that adds a new movie to the collection.
 */
public class AddCommand implements Command {

    /**
     * A string containing information about how to use this command.
     */

    public static String info = "add command:\n" +
            "   This command can add elem\n" +
            "   Syntax:\n" +
            "       add\n" +
            "   then program will require for u to input to console every field of this class\n";

    private CollectionManager manager;
    private BufferedReader reader;
    private Integer id;
    private ZonedDateTime ZonedTimeNow;

    /**
     * Creates a new AddCommand with the given CollectionManager, BufferedReader, ID, and ZonedDateTime.
     *
     * @param manager      The CollectionManager to use.
     * @param reader       The BufferedReader to use for reading input.
     * @param id           The ID to assign to the new movie.
     * @param ZonedTimeNow The ZonedDateTime to use for setting the creation date of the new movie.
     */

    public AddCommand(CollectionManager manager, BufferedReader reader, Integer id, ZonedDateTime ZonedTimeNow) {
        this.manager = manager;
        this.reader = reader;
        this.id = id;
        this.ZonedTimeNow = ZonedTimeNow;
    }

    /**
     * Prompts the user to input information for a new movie, creates the movie, and adds it to the collection.
     *
     * @param movies The HashSet of movies to add the new movie to.
     * @throws IOException If an I/O error occurs.
     */
    public void Worker(HashSet<Movie> movies) throws IOException {
        Movie movie = new Movie();
        HashMap<String, Consumer<String>> setters = new HashMap<>();
        System.out.println("    name: String (e.g. \"The Shawshank Redemption\")\n" +
                "    coordinates: Float (e.g. \"45.123 67.890\")\n" +
                "    oscarsCount: Integer (e.g. \"5\")\n" +
                "    goldenPalmCount: Integer (e.g. \"3\")\n" +
                "    tagline: String (e.g. \"Fear can hold you prisoner. Hope can set you free.\")\n" +
                "    mpaaRating: String (e.g. \"PG_13\")\n" +
                "    directorName: String (e.g. \"Frank Darabont\")\n" +
                "    directorBirthday: LocalDate (e.g. \"1990-05-01\")\n" +
                "    directorHeight: Integer (e.g. \"183\")\n" +
                "    directorEyeColor: String (e.g. \"BLUE\")\n" +
                "    location: Double, String (e.g. \"45.123 67.890 MyPLace\")\n" +
                "\n");
        setters.put("name", movie::setName);
        setters.put("coordinates", s -> {
            String[] coordinates = s.split(" ");
            if (coordinates.length != 2) {
                throw new IllegalArgumentException("Invalid coordinates format");
            }
            try {
                Float x = Float.parseFloat(coordinates[0]);
                Float y = Float.parseFloat(coordinates[1]);
                Coordinates myCoordinates = new Coordinates();
                myCoordinates.setX(x);
                myCoordinates.setY(y);
                movie.setCoordinates(myCoordinates);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid coordinates format");
            }
        });
        Person director = new Person();
        setters.put("oscarsCount", s -> movie.setOscarsCount(Integer.parseInt(s)));
        setters.put("goldenPalmCount", s -> movie.setGoldenPalmCount(Integer.parseInt(s)));
        setters.put("tagline", movie::setTagline);
        setters.put("mpaaRating", s -> movie.setMpaaRating(MpaaRating.valueOf(s)));
        setters.put("directorName", s -> director.setName(s));
        setters.put("directorHeight", s -> director.setHeight(Integer.parseInt(s)));
        setters.put("directorEyeColor", s -> director.setEyeColor(Color.valueOf(s)));
        setters.put("directorBirthday", s -> {
            try {
                LocalDate birthday = LocalDate.parse(s);
                ZoneId zoneId = ZoneId.of("Europe/Moscow"); // Or use your desired time zone
                ZonedDateTime zonedDateTime = birthday.atStartOfDay(zoneId);
                director.setBirthday(zonedDateTime);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid birthday format");
            }
        });
        setters.put("location", s -> {
            String[] locationValues = s.split(" ");
            if (locationValues.length != 3) {
                throw new IllegalArgumentException("Invalid location format");
            }
            try {
                double x = Double.parseDouble(locationValues[0]);
                double y = Double.parseDouble(locationValues[1]);
                String name = locationValues[2];
                Location location = new Location();
                location.setLocation(x, y, name);
                director.setLocation(location);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid location format");
            }
        });

        movie.setCreationDate(ZonedTimeNow);

        for (String field : setters.keySet()) {
            System.out.print(field + ": ");
            String value = reader.readLine().trim();
            if (value.isEmpty()) {
                throw new IllegalArgumentException(field + " cannot be empty");
            }
            setters.get(field).accept(value);
        }

        movie.setDirector(director);

        if (movies.contains(movie)) {
            throw new IllegalArgumentException("Movie with same parameters already exists in the collection");
        }

        movie.setId(id);
        movie.setCreationDate(ZonedDateTime.now());
        movies.add(movie);

        System.out.println("Movie added to collection with ID " + movie.getId());
    }
    /**

     Adds a new movie to the collection managed by the {@link CollectionManager} instance
     provided to this command's constructor. This method prompts the user to enter values for
     every field of the new movie and creates a new {@link Movie} object with the specified values.
     The new movie is added to the collection with a unique ID, which is generated by the manager.
     If a movie with the same parameters already exists in the collection, an {@link IllegalArgumentException}
     is thrown.
     <p>
     This method calls {@link CollectionManager#getMovies()} to get a reference to the collection of movies.
     If an I/O error occurs while reading user input, this method throws a {@link RuntimeException}.
     </p>

     */

    @Override
    public void execute() {
        HashSet<Movie> movies = manager.getMovies();
        try {
            Worker(movies);
            System.out.println("Movie was successfully added");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
