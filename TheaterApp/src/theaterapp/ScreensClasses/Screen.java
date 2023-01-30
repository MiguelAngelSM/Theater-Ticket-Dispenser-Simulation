/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.ScreensClasses;

import theaterapp.enums.ScreenMode;
import theaterapp.enums.ScreenResult;
import java.util.List;
import theaterapp.DispenserClasses.DispenserHardware;
import theaterapp.DispenserClasses.DispenserManager;
import theaterapp.TheaterClasses.TheaterAreaState;

/**
 *
 * @author masan
 */
public class Screen {
    //Properties

    private DispenserManager dispenserManager;
    private String title;
    private String description;
    private String image;
    private List<String> options;
    private ScreenMode mode;

    public static final int timeShowScreen = 30;
    public static final int timeShowErrorScreen = 5;

    //Methods
    //Constructor
    /**
     * @param title
     * @param mode
     * @param dispenserManager
     */
    public Screen(String title, ScreenMode mode, DispenserManager dispenserManager) {
        this.title = title;
        this.mode = mode;
        this.dispenserManager = dispenserManager;
    }

    //getters and setters
    /**
     * @return the dispenserManager
     */
    public DispenserManager getDispenserManager() {
        return dispenserManager;
    }

    /**
     * @param dispenserManager the dispenserManager to set
     */
    public void setDispenserManager(DispenserManager dispenserManager) {
        this.dispenserManager = dispenserManager;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the options
     */
    public List<String> getOptions() {

        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * @return the mode
     */
    public ScreenMode getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(ScreenMode mode) {
        this.mode = mode;
    }
    //Other methods

    /**
     * @param dh
     * @param c
     * @return nothing (to be implemented)
     */
    public ScreenResult optionButtonPressed(DispenserHardware dh, char c) {
        return null;
    }

    /**
     * @param d
     * @param row
     * @param col
     * @return nothing (to be implemented)
     */
    public ScreenResult seatButtonPressed(DispenserHardware d, int row, int col) {
        return null;
    }

    /**
     * @param d
     * @return nothing (to be implemented)
     */
    public ScreenResult creditCardDetected(DispenserHardware d) {
        return null;
    }

    /**
     * @return nothing (to be implemented)
     */
    public TheaterAreaState getAreaState() {
        return null;
    }

    /**
     * It initializes the screen
     *
     * @param d
     * @return if the screen should continue being shown
     */
    public ScreenResult begin(DispenserHardware d) {

        return ScreenResult.continueScreen;
    }

    /**
     * It ends the screen
     *
     * @param d
     * @return if the screen should continue being shown
     */
    public ScreenResult end(DispenserHardware d) {
        return ScreenResult.exitScreen;
    }
}
