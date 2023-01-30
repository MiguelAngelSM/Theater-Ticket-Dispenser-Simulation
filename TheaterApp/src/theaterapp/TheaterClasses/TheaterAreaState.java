/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.TheaterClasses;

import theaterapp.enums.SeatState;
import java.io.Serializable;

/**
 *
 * @author masan
 */
public class TheaterAreaState implements Serializable {
    //Properties

    private SeatState[][] seatsState;
    private String name;
    private int cols;
    private int rows;
    private int price;


    //Methods
    //Constructor
    /**
     * @param area with the required info to copy
     */
    public TheaterAreaState(TheaterArea area) {
        this.name = area.getName();
        this.cols = area.getCols();
        this.rows = area.getRows();
        this.price = area.getPrice();
        this.seatsState = new SeatState[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.seatsState[i][j] = area.getSeat(i, j);
            }
        }

    }

    //getters and setters
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the cols
     */
    public int getCols() {
        return cols;
    }

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    public int getPrice() {
        return price;
    }

    //Other methods
    /**
     * @param row the row from the array
     * @param col the col from the array
     * @return SeatState from the row and col of the array
     */
    public SeatState getSeat(int row, int col) {
        return this.seatsState[row][col];
    }

    /**
     * @param row the row from the array
     * @param col the col from the array
     * @param state for the new SeatState It will update the Seat to its new
     * SeatState
     */
    public void setSeat(int row, int col, SeatState state) {
        this.seatsState[row][col] = state;
    }
}
