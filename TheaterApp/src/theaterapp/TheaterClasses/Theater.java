/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.TheaterClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import theaterapp.Play.Play;

/**
 *
 * @author masan
 */
public class Theater {

    //Properties
    private ArrayList<TheaterArea> theaterAreas;
    private String theaterImage;
    private String theaterName;
    private Play play;

    //Methods
    //Constructor
    /**
     * @param theaterFile is the directory of the file direction file
     * ConfigFilesExample/theater.txt
     */
    public Theater(String theaterFile) {
        theaterAreas = new ArrayList<>();
        read(theaterFile);
        this.play = new Play("play.txt");
    }

    //getters and setters
    /**
     * @return the theaterAreas
     */
    public ArrayList<TheaterArea> getTheaterAreas() {
        return theaterAreas;
    }

    /**
     * @return the theaterImage
     */
    public String getTheaterImage() {
        return theaterImage;
    }

    /**
     * @return the theaterName
     */
    public String getTheaterName() {
        return theaterName;
    }

    /**
     * @return the play
     */
    public Play getPlay() {
        return play;
    }

    //Other methods
    /**
     * @return the size of the list of Areas
     */
    public int getAmountOfAreas() {
        return this.theaterAreas.size();
    }

    /**
     * @param position of the desired TheaterArea
     * @return the desired TheaterArea
     */
    public TheaterArea getArea(int position) {
        return this.getTheaterAreas().get(position);
    }

    /**
     * It read the theater file
     *
     * @param theaterDir of the file where the theater info is stored
     */
    private void read(String theaterDir) {
        File theaterFile = new File("../ConfigFilesExample/" + theaterDir);
        try {
            Scanner scanner = new Scanner(theaterFile);
            while (scanner.hasNextLine()) {
                String fileLine = scanner.nextLine();
                String[] ph = fileLine.split(":"); //ph is placeholder
                this.setProperty(ph[0], ph[1]);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Theater.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fichero de teatro no encontrado");
        }
    }

    /**
     * @param selector is for choosing what attribute will be rewrite
     * @param text is the info written This is a helper method for read
     */
    private void setProperty(String selector, String text) {
        switch (selector) {
            case "TheaterName":
                this.theaterName = text;
                break;
            case "TheaterPlaneImageFile":
                this.theaterImage = text;
                break;
            case "Area":
                TheaterArea area = new TheaterArea(text);
                this.theaterAreas.add(area);
                break;
            default:
                System.out.println("Error while reading from file");
                break;
        }
    }
}
