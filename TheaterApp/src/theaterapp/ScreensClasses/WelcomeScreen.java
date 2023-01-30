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
import theaterapp.enums.ScreenMode;
import theaterapp.enums.ScreenResult;

/**
 *
 * @author masan
 */
public class WelcomeScreen extends Screen {
    //Properties

    private Theater theater;
    private DateSelectionScreen dateScreen;
    private LanguageSelectionScreen languageScreen;

    //Methods
    //Constructor
    /**
     *
     * @param theater
     * @param dispenserManager
     */
    public WelcomeScreen(Theater theater, DispenserManager dispenserManager) {
        super("Bienvenido al teatro " + theater.getTheaterName(), ScreenMode.optionsMode, dispenserManager);
        this.dateScreen = new DateSelectionScreen(theater, dispenserManager);
        this.languageScreen = new LanguageSelectionScreen(dispenserManager);
        this.theater = theater;
        this.setDescription(this.theater.getPlay().getDescription());
        this.setImage("../ConfigFilesExample/" + this.theater.getPlay().getImage());
        List<String> opt = new ArrayList<>();
        opt.add(this.dateScreen.getTitle());
        opt.add(this.languageScreen.getTitle());
        this.setOptions(opt);
    }

    //Other methods
    /**
     * It decides what the next step will be depending on the button pressed
     *
     * @param dh not used here
     * @param c with the button pressed
     * @return if the screen should continue being shown
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dh, char c) {
        switch (c) {
            case 'A':
                this.getDispenserManager().showScreen(Screen.timeShowScreen, this.dateScreen);
                break;
            case 'B':
                this.getDispenserManager().showScreen(Screen.timeShowScreen, this.languageScreen);
                break;
        }
        return ScreenResult.continueScreen;
    }

    /**
     * This screen will never ends
     *
     * @return the screen continues shown
     */
    @Override
    public ScreenResult end(DispenserHardware d) {
        return ScreenResult.continueScreen;
    }
}
