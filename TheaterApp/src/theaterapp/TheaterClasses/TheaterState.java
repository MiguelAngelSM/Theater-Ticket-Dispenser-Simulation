/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.TheaterClasses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author masan
 */
public class TheaterState implements Serializable {
    //Properties

    private String date;
    private ArrayList<TheaterAreaState> areaStateList;

    //Methods
    //Constructor
    /**
     * @param theater from where we get the areas
     * @param date is the actual date
     */
    public TheaterState(Theater theater, String date) {
        this.date = date;
        this.areaStateList = new ArrayList<>();
        for (TheaterArea i : theater.getTheaterAreas()) {
            this.areaStateList.add(new TheaterAreaState(i));
        }
        this.loadInfo();
    }

    //getters and setters
    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the areaStateList
     */
    public ArrayList<TheaterAreaState> getAreaStateList() {
        return areaStateList;
    }

    /**
     * @param areaStateList the areaStateList to set
     */
    public void setAreaStateList(ArrayList<TheaterAreaState> areaStateList) {
        this.areaStateList = areaStateList;
    }

    //Other methods
    /**
     * @return the size of the areaStateList
     */
    public int getNumAreas() {
        return this.getAreaStateList().size();
    }

    /**
     *
     * @param position in the ArrayList of the desired state
     * @return the required TheaterAreaState
     */
    public TheaterAreaState getArea(int position) {
        return this.getAreaStateList().get(position);
    }

    /**
     * It saves the info of the theater in the selected date
     */
    public void saveInfo() {
        try {
            String fic = "../TheaterEachDay/" + this.date + ".TheaerState";
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fic));
            output.writeObject(this);
            output.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * It loads the info of the theater in the selected date
     */
    private void loadInfo() {
        try {
            String fic = "../TheaterEachDay/" + this.date + ".TheaerState";
            ObjectInputStream output = new ObjectInputStream(new FileInputStream(fic));
            TheaterState theaterState = (TheaterState) output.readObject();
            this.areaStateList=theaterState.areaStateList;
            this.date=theaterState.date;
        } catch (Exception e) {
            System.out.println("Fichero no encontrado, carga de archivos por defecto");
        }
    }
}
