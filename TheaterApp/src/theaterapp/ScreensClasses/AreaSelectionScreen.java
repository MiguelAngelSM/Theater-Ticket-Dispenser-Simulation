/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.ScreensClasses;

import java.util.ArrayList;
import java.util.List;
import theaterapp.DispenserClasses.DispenserHardware;
import theaterapp.DispenserClasses.DispenserManager;
import theaterapp.TheaterClasses.Theater;
import theaterapp.TheaterClasses.TheaterAreaState;
import theaterapp.TheaterClasses.TheaterState;
import theaterapp.enums.ScreenMode;
import theaterapp.enums.ScreenResult;

/**
 *
 * @author masan
 */
class AreaSelectionScreen extends Screen {

    //Properties
    private Theater theater;
    private TheaterAreaState selectArea;
    private SeatSelectionScreen seatsScreen;
    private String day;
    private TheaterState theaterState;

    //Methods
    //Constructor
    /**
     * @param theater
     * @param dispenserManager
     * @param theaterState of the selected day
     */
    AreaSelectionScreen(Theater theater, DispenserManager dispenserManager, String day, TheaterState theaterState) {
        super("Seleccione la zona del teatro", ScreenMode.optionsMode, dispenserManager);
        this.theater = theater;
        this.theaterState = theaterState;
        this.setImage("../ConfigFilesExample/" + this.theater.getTheaterImage());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < this.theater.getAmountOfAreas(); i++) {
            list.add(this.theater.getArea(i).getName());
        }
        this.setOptions(list);
        this.day = day;
    }

    //Other methods
    /**
     * It decides what the next step will be depending on the button pressed.
     * The switch will decide what index from the areas list is the selected or
     * if the user cancels. Then if the user select an area the next screen is
     * shown with the selected area, else it returns exitScreen in order to
     * return to the main page
     *
     * @param dh not used here
     * @param c with the button pressed
     * @return if the screen should continue being shown
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dh, char c) {
        ScreenResult result = ScreenResult.continueScreen;
        int index = 0;
        switch (c) {
            case 'A':
                index = 0;
                break;
            case 'B':
                index = 1;
                break;
            case 'C':
                index = 2;
                break;
            case 'D':
                index = 3;
                break;
            case 'E':
                index = 4;
                break;
            case 'F':
                result = ScreenResult.exitScreen;
                break;
        }
        if (result == ScreenResult.continueScreen) {
            this.selectArea = this.theaterState.getArea(index);
            this.seatsScreen = new SeatSelectionScreen(this.theater, this.getDispenserManager(), this.selectArea, this.day);
            this.getDispenserManager().showScreen(Screen.timeShowScreen, this.seatsScreen);
        }
        return result;
    }
}
