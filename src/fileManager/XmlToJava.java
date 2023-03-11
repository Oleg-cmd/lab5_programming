package fileManager;

import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.time.ZonedDateTime;
import java.util.HashSet;

/**
 The XmlToJava class provides a method to parse an XML file and convert its contents to a HashSet of Movie objects.
 */
public class XmlToJava {
    /**
     * A HashSet of Movie objects to store parsed XML data.
     */
    public static HashSet<Movie> movies = new HashSet<>();

    /**
     * Parses an XML file and returns a HashSet of Movie objects.
     *
     * @param filename the name of the XML file to be parsed.
     * @return a HashSet of Movie objects containing data from the specified XML file.
     */
    public static HashSet<Movie> parseXml(String filename) {
        try {
            // Open the XML file and create a reader
            File xmlFile = new File(filename);
            Reader fileReader = new FileReader(xmlFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read the contents of the file into a StringBuilder
            StringBuilder xmlString = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                xmlString.append(line);
            }
            bufferedReader.close();

            // Parse the XML file using a DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Normalize the document
            doc.getDocumentElement().normalize();

            // Get all nodes in the document
            NodeList nodeList = doc.getElementsByTagName("*");

            // Loop through all nodes and extract data for Movie objects
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (!node.getNodeName().equals("root") && node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    NodeList childNodes = element.getChildNodes();
                    if(childNodes.getLength() != 1){
                        Element elem = (Element) childNodes;
//                        System.out.println(elem.getNodeName());
                        if(elem.getNodeName().equals("Movie")){
                            Movie movie = new Movie();
                            Coordinates coordinates = new Coordinates();
                            Person director = new Person();
                            for (int i = 0; i < childNodes.getLength(); i++) {
                                Node childNode = childNodes.item(i);
                                if (childNode != null && childNode.getNodeType() == Node.ELEMENT_NODE) {
                                    String content = childNode.getTextContent();
                                    if (content != null) {
                                        switch(childNode.getNodeName()) {
                                            case "id":
                                                movie.setId(Integer.parseInt(content));
                                                break;
                                            case "name":
                                                movie.setName(content);
                                                break;
                                            case "coordinates":
                                                Element coordinatesElement = (Element) childNode;
                                                NodeList xList = coordinatesElement.getElementsByTagName("x");
                                                NodeList yList = coordinatesElement.getElementsByTagName("y");
                                                if (xList.getLength() > 0 && yList.getLength() > 0) {
                                                    coordinates.setX(Float.parseFloat(xList.item(0).getTextContent()));
                                                    coordinates.setY(Float.parseFloat(yList.item(0).getTextContent()));
                                                    movie.setCoordinates(coordinates);
                                                }
                                                break;
                                            case "creationDate":
                                                movie.setCreationDate(ZonedDateTime.parse(content));
                                                break;
                                            case "oscarsCount":
                                                movie.setOscarsCount(Integer.parseInt(content));
                                                break;
                                            case "goldenPalmCount":
                                                movie.setGoldenPalmCount(Integer.parseInt(content));
                                                break;
                                            case "tagline":
                                                movie.setTagline(content);
                                                break;
                                            case "mpaaRating":
                                                movie.setMpaaRating(MpaaRating.valueOf(content));
                                                break;
                                            case "director":
                                                Element directorElement = (Element) childNode;
                                                NodeList nameElements = directorElement.getElementsByTagName("name");
                                                if (nameElements.getLength() > 0) {
                                                    director.setName(nameElements.item(0).getTextContent());
                                                }
                                                NodeList heightElements = directorElement.getElementsByTagName("height");
                                                if (heightElements.getLength() > 0) {
                                                    director.setHeight(Double.parseDouble(heightElements.item(0).getTextContent()));
                                                }
                                                NodeList birth = directorElement.getElementsByTagName("birthday");
                                                director.setBirthday(ZonedDateTime.parse(birth.item(0).getTextContent()));

                                                NodeList color = directorElement.getElementsByTagName("eyeColor");
                                                director.setEyeColor(Color.valueOf(color.item(0).getTextContent()));

                                                NodeList location = directorElement.getElementsByTagName("location");
                                                if (location.getLength() > 0) {
                                                    Element locationElement = (Element) location.item(0);
                                                    NodeList xElements = locationElement.getElementsByTagName("x");
                                                    NodeList yElements = locationElement.getElementsByTagName("y");
                                                    NodeList name = locationElement.getElementsByTagName("name");
                                                    Double directorX = 0.0;
                                                    Double directorY = 0.0;
                                                    String directorLocationName = "";
                                                    if (xElements.getLength() > 0) {
                                                        directorX = Double.parseDouble(xElements.item(0).getTextContent());
                                                    }
                                                    if (yElements.getLength() > 0) {
                                                        directorY = Double.parseDouble(yElements.item(0).getTextContent());
                                                    }
                                                    if (name.getLength() > 0) {
                                                        directorLocationName = name.item(0).getTextContent();
                                                    }
                                                    Location directorLocation = new Location();
                                                    directorLocation.setLocation(directorX, directorY, directorLocationName);
                                                    director.setLocation(directorLocation);
                                                }

                                                movie.setDirector(director);
                                                break;

                                        }
                                    }else{
//                                    System.out.println("");
                                    }

                                }
                            }
                            movies.add(movie);
                        }
                    }
                }

            }
//            System.out.println(movies);
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
}