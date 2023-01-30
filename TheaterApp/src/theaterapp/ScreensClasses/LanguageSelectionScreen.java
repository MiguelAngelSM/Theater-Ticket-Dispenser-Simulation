/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.ScreensClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import theaterapp.DispenserClasses.DispenserHardware;
import theaterapp.DispenserClasses.DispenserManager;
import theaterapp.enums.ScreenMode;
import theaterapp.enums.ScreenResult;

/**
 *
 * @author masan
 */
public class LanguageSelectionScreen extends Screen {
    //Methods
    //Constructor
    /**
     * @param dispenserManager
     */
    LanguageSelectionScreen(DispenserManager dispenserManager) {
        super("Selección de idioma",ScreenMode.optionsMode,dispenserManager);
        this.read();
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
                this.getDispenserManager().getTranslator().setActiveLanguage(this.getOptions().get(0));
                break;
            case 'B':
                this.getDispenserManager().getTranslator().setActiveLanguage(this.getOptions().get(1));
                break;
            case 'C':
                this.getDispenserManager().getTranslator().setActiveLanguage(this.getOptions().get(2));
                break;
            case 'D':
                this.getDispenserManager().getTranslator().setActiveLanguage(this.getOptions().get(3));
                break;
            case 'E':
                this.getDispenserManager().getTranslator().setActiveLanguage(this.getOptions().get(4));
                break;
            case 'F':
                break;
        }
        return ScreenResult.continueScreen;
    }

    /**
     * It reads the available languages
     */
    private void read() {
        try {
            File file = new File("../Languages/" + "Available.txt");
            Scanner scanner = new Scanner(file);
            ArrayList<String> options = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String fileLine = scanner.nextLine();
                String[] ph = fileLine.split(":"); //ph is placeholder
                options.add(ph[0]);
            }
            this.setOptions(options);
            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LanguageSelectionScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
