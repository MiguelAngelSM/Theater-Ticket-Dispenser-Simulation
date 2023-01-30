/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.Play;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author masan
 */
public class Play {
    //Properties

    private String title;
    private String image;
    private String description;

    //Methods
    //Constructor
    /**
     * @param fileName of the Play
     */
    public Play(String fileName) {
        read(fileName);
    }

    //getters and setters
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    //Other methods
    /**
     * It reads the play from the file
     *
     * @param fileName of the file to read
     */
    private void read(String fileName) {
        try {
            File file = new File("../ConfigFilesExample/" + fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String fileLine = scanner.nextLine();
                String[] ph = fileLine.split(":"); //ph is placeholder
                this.setProperty(ph[0], ph[1]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fichero de obra no encontrado");
        }
    }

    /**
     * @param selector is for choosing what attribute will be rewrite
     * @param text is the info written This is a helper method for read
     */
    private void setProperty(String selector, String text) {
        switch (selector) {
            case "play_name":
                this.title = text;
                break;
            case "play_poster":
                this.image = text;
                break;
            case "description":
                this.description = text;
                break;
            default:
                System.out.println("Error while reading from file");
                break;
        }
    }

}
