package collectionWorker;

import fileManager.CollectionManager;
import fileManager.Command;
import model.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Consumer;

/**
 Represents an UpdateCommand which updates an element in a collection by its ID
 */
public class UpdateCommand implements Command {
    public static String info = "update command:\n" +
            "   This command will update an element by it's id\n" +
            "   Syntax:\n" +
            "       update\n";
    private final HashSet<Movie> myCollection;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    private final CollectionManager manager;

    /**
     * Constructs an UpdateCommand object with the given parameters.
     * @param myCollection the HashSet to be updated.
     * @param reader the BufferedReader object to read input.
     * @param writer the BufferedWriter object to write output.
     * @param manager the CollectionManager object to manage the collection.
     */

    public UpdateCommand(HashSet<Movie> myCollection, BufferedReader reader, BufferedWriter writer, CollectionManager manager) {
        this.myCollection = myCollection;
        this.reader = reader;
        this.writer = writer;
        this.manager = manager;
    }

    /**
     * Executes the update command by prompting the user to enter the ID of the movie to be updated and the field to update,
     * and then updating the specified field of the movie.
     */

    @Override
    public void execute() {
        try{
            writer.write("Please enter the ID of the movie you want to update:");
            writer.newLine();
            writer.flush();
            Integer id = Integer.parseInt(reader.readLine());
            Movie movie = manager.getById(id);

            Map<String, Consumer<String>> setters = new HashMap<>();

            System.out.println("    name: String (e.g. \"The Shawshank Redemption\")\n" +
                    "    coordinates: Float (e.g. \"3.34 4.56\")\n" +
                    "        - The coordinates must consist of two floating-point values separated by a single space.\n" +
                    "    oscarsCount: Integer (e.g. \"5\")\n" +
                    "    goldenPalmCount: Integer (e.g. \"3\")\n" +
                    "    tagline: String (e.g. \"Fear can hold you prisoner. Hope can set you free.\")\n" +
                    "    mpaaRating: String (e.g. \"PG_13\")\n" +
                    "    directorName: String (e.g. \"Frank Darabont\")\n" +
                    "    directorBirthday: LocalDate (e.g. \"1990-05-01\")\n" +
                    "        - The date must be in the format yyyy-MM-dd.\n" +
                    "    directorHeight: Integer (e.g. \"183\")\n" +
                    "    directorEyeColor: String (e.g. \"BLUE\")\n" +
                    "        - The eye color must be one of the following: BLACK, BLUE, GREEN, ORANGE, PURPLE, WHITE.\n" +
                    "    location: Double, String (e.g. \"45.123 67.890 MyPLace\")\n" +
                    "        - The location must consist of two double values separated by a single space, followed by a location name.");

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
            setters.put("oscarsCount", s -> movie.setOscarsCount(Integer.parseInt(s)));
            setters.put("goldenPalmCount", s -> movie.setGoldenPalmCount(Integer.parseInt(s)));
            setters.put("tagline", movie::setTagline);
            setters.put("mpaaRating", s -> movie.setMpaaRating(MpaaRating.valueOf(s)));
            setters.put("directorName", s -> movie.getDirector().setName(s));
            setters.put("directorHeight", s -> movie.getDirector().setHeight(Integer.parseInt(s)));
            setters.put("directorEyeColor", s -> movie.getDirector().setEyeColor(Color.valueOf(s)));
            setters.put("directorBirthday", s -> {
                try {
                    LocalDate birthday = LocalDate.parse(s);
                    ZoneId zoneId = ZoneId.of("Europe/Moscow"); // Or use your desired time zone
                    ZonedDateTime zonedDateTime = birthday.atStartOfDay(zoneId);
                    movie.getDirector().setBirthday(zonedDateTime);
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
                    movie.getDirector().setLocation(location);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid location format");
                }
            });

            writer.write("Enter the field you want to update:");
            writer.newLine();
            writer.flush();

            String field = reader.readLine().trim();

            if (!setters.containsKey(field)) {
                writer.write("Field " + field + " is not updatable");
                writer.newLine();
                writer.flush();
            }else{
                System.out.print(field + ": ");
                String value = reader.readLine().trim();
                if (value.isEmpty()) {
                    throw new IllegalArgumentException(field + " cannot be empty");
                }
                setters.get(field).accept(value);
                System.out.println("Ur field was updated successfully");
            }
        }catch (IOException e){
            System.out.println(e);
        }


    }

}