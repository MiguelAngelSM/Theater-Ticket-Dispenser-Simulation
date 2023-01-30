/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.TheaterClasses;

import theaterapp.enums.SeatState;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author masan
 */
public class TheaterArea {

    //Properties
    private int rows;
    private int cols;
    private String name;
    private SeatState[][] seats;
    private int price;

    //Methods
    //Constructor
    /**
     * @param areaInfo from where we extract the info
     */
    TheaterArea(String areaInfo) {
        String[] ph = areaInfo.split(";"); //ph is placeholder
        this.name = ph[0];
        ph[1] = ph[1].substring(0, ph.length - 1); //In order to remove the symbol €
        this.price = Integer.valueOf(ph[1]);
        this.read(ph[2]);
    }
    //getters and setters

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return the cols
     */
    public int getCols() {
        return cols;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the seats
     */
    public SeatState[][] getSeats() {
        return seats;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param row of the desired Seat
     * @param col of the desired Seat
     * @return SeatState required
     */
    public SeatState getSeat(int row, int col) {
        return this.seats[row][col];
    }

    //Other Methods
    /**
     * It reads the area file. First of all it reads the file and store the rows
     * in a list of Strings and get the rows and cols amounts. Then it creates
     * the matrix (array of array) of areas and the fill it in the following
     * way: 
     * The first for statement will fill with the info of the lines got
     * from the document and the other for statement with corridors cause the
     * documents have strings (lines) with differents sizes, so when the string
     * is ended and there are columns without SeatState they will be filled with
     * corridors (explanation of how a line is read)
     *
     * @param fileName from where we read
     */
    private void read(String fileName) {
        try {
            File file = new File("../ConfigFilesExample/" + fileName);
            Scanner scanner = new Scanner(file);
            LinkedList<String> rowsList = new LinkedList<>();
            this.cols = 0;
            while (scanner.hasNextLine()) {
                rowsList.add(scanner.nextLine());
                String lastReadString = rowsList.get(rowsList.size() - 1);
                if (this.cols < lastReadString.length()) {
                    this.cols = lastReadString.length();
                }
                this.rows++;
            }
            this.seats = new SeatState[this.rows][this.cols];
            for (int i = 0; i < this.rows; i++) {
                String ph = rowsList.get(i);
                /**
                 * Explanation of how a line is read
                 */
                for (int j = 0; j < ph.length(); j++) {
                    if (ph.charAt(j) == '*') {
                        this.seats[i][j] = SeatState.free;
                    } else {
                        this.seats[i][j] = SeatState.corridor;
                    }
                }
                for (int j = ph.length(); j < this.cols; j++) {
                    this.seats[i][j] = SeatState.corridor;
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TheaterArea.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fichero " + fileName + " no encontrado");
        }
    }

}
