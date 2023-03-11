package helpers;

import collectionWorker.Command;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Consumer;

public class Worker implements Command {
    /**
     * Prompts the user to input information for a new movie, creates the movie, and adds it to the collection.
     *
     * @return
     * @throws IOException If an I/O error occurs.
     */


    public static MethodReturn Code(Movie currentMovie) {
        Movie movie = Objects.requireNonNullElseGet(currentMovie, Movie::new);

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
                "    location: Double, String (e.g. \"45.123 67.890 MyPLace\")\n");


        setters.put("name", movie::setName);

        setters.put("coordinates", s -> {
            while (true){
                System.out.print("coordinates: Float (e.g. \"45.123 67.890\")\n");
                String[] coordinates = s.split(" ");
                try{
                    if (coordinates.length != 2) {
                        System.out.println("Invalid coordinates format");
                        System.out.print("write data again: ");
                        s = reader.readLine().trim();
                    } else {
                        try {
                            Float x = Float.parseFloat(coordinates[0]);
                            Float y = Float.parseFloat(coordinates[1]);
                            Coordinates myCoordinates = new Coordinates();
                            myCoordinates.setX(x);
                            myCoordinates.setY(y);
                            movie.setCoordinates(myCoordinates);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid coordinates format");
                            System.out.print("write data again: ");
                            s = reader.readLine().trim();
                        }
                    }
                }catch (IOException err){
                    System.out.println("stupid user");
                }
            }
        });

        Person director = new Person();

        // right version
        setters.put("oscarsCount", s -> {
            while (true){
                try {
                    System.out.println( "oscarsCount: Integer (e.g. \"5\")");
                    try {
                        movie.setOscarsCount(Integer.parseInt(s));
                        break;
                    }catch (NumberFormatException e){
                        System.out.println("Invalid format");
                        System.out.print("write data again: ");
                        s = reader.readLine().trim();
                    }
                }catch (IOException err){
                    System.out.println("another error, user i hate u");
                }
            }
        });

        setters.put("goldenPalmCount", s -> {
            while (true){
                try {
                    System.out.println("goldenPalmCount: Integer (e.g. \"3\")");
                    try {
                        movie.setGoldenPalmCount(Integer.parseInt(s));
                        break;
                    }catch (NumberFormatException e){
                        System.out.println("Invalid format");
                        System.out.print("write data again: ");
                        s = reader.readLine().trim();
                    }
                }catch (IOException err){
                    System.out.println("another error, user i hate u");
                }
            }
        });


        setters.put("tagline", movie::setTagline);

        setters.put("mpaaRating", s -> {
            while (true){
                try {
                    System.out.println("mpaaRating: String (e.g. \"PG_13\")");
                    try {
                        movie.setMpaaRating(MpaaRating.valueOf(s));
                        break;
                    }catch (IllegalArgumentException e){
                        System.out.println("Invalid format");
                        System.out.print("write data again: ");
                        s = reader.readLine().trim();
                    }
                }catch (IOException err){
                    System.out.println("another error, user i hate u");
                }
            }
        });


        setters.put("directorName", director::setName);

        setters.put("directorHeight", s -> {
            while (true){
                try {
                    System.out.println("directorHeight: Integer (e.g. \"183\")");
                    try {
                        director.setHeight(Integer.parseInt(s));
                        break;
                    }catch (NumberFormatException e){
                        System.out.println("Invalid format");
                        System.out.print("write data again: ");
                        s = reader.readLine().trim();
                    }
                }catch (IOException err){
                    System.out.println("another error, user i hate u");
                }
            }
        });

        setters.put("directorEyeColor", s -> {
            while (true){
                try {
                    System.out.println("directorEyeColor: String (e.g. \"BLUE\")");
                    try {
                        director.setEyeColor(Color.valueOf(s));
                        break;
                    }catch (IllegalArgumentException e){
                        System.out.println("Invalid format");
                        System.out.print("write data again: ");
                        s = reader.readLine().trim();
                    }
                }catch (IOException err){
                    System.out.println("another error, user i hate u");
                }
            }
        });

        setters.put("directorBirthday", s -> {
            while (true){
                try {
                    System.out.println("directorBirthday: LocalDate (e.g. \"1990-05-01\")");
                    try {
                        LocalDate birthday = LocalDate.parse(s);
                        ZoneId zoneId = ZoneId.of("Europe/Moscow"); // Or use your desired time zone
                        ZonedDateTime zonedDateTime = birthday.atStartOfDay(zoneId);
                        director.setBirthday(zonedDateTime);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid birthday format");
                        System.out.print("write data again: ");
                        s = reader.readLine().trim();
                    }
                }catch (IOException err){
                    System.out.println("another error, user i hate u");
                }
            }
        });

        setters.put("location", s -> {
            while (true){
                String[] locationValues = s.split(" ");
                System.out.println("location: Double, String (e.g. \"45.123 67.890 MyPLace\")");
                try{
                    if (locationValues.length != 3) {
                        System.out.println("Invalid location format");
                        System.out.print("write data again: ");
                        s = reader.readLine().trim();
                    }else {
                        try {
                            double x = Double.parseDouble(locationValues[0]);
                            double y = Double.parseDouble(locationValues[1]);
                            String name = locationValues[2];
                            Location location = new Location();
                            location.setLocation(x, y, name);
                            director.setLocation(location);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid format");
                            System.out.print("write data again: ");
                            s = reader.readLine().trim();
                        }
                    }
                }catch (IOException err){
                    System.out.println("stupid user");
                }
            }
        });


        movie.setCreationDate(collectionManager.getNow());
        movie.setDirector(director);


        return new MethodReturn(setters, movie);
    }

    public void execute(){}
}
