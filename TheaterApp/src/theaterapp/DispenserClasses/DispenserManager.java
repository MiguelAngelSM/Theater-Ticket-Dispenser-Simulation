/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.DispenserClasses;

import theaterapp.Translator.TranslatorManager;
import sienens.TheaterTicketDispenser;
import theaterapp.ScreensClasses.Screen;
import theaterapp.TheaterClasses.TheaterAreaState;
import theaterapp.enums.ScreenResult;
import theaterapp.enums.SeatState;

/**
 *
 * @author masan
 */
public class DispenserManager {

    //Properties
    private TranslatorManager translator;
    private TheaterTicketDispenser dispenser;
    private DispenserHardware dispenserHardware;
    //Methods

    //Constructor
    /**
     * It creates the translator,dispenser and dispenserHardware used in all the
     * program
     */
    public DispenserManager() {
        this.translator = new TranslatorManager();
        this.dispenser = new TheaterTicketDispenser();
        this.dispenserHardware = new DispenserHardware(this.dispenser);
    }

    //getters and setters
    /**
     * @return the translator
     */
    public TranslatorManager getTranslator() {
        return translator;
    }

    /**
     * @return the dispenser
     */
    public TheaterTicketDispenser getDispenser() {
        return dispenser;
    }

    /**
     * @return the dispenserHardware
     */
    public DispenserHardware getDispenserHardware() {
        return dispenserHardware;
    }

    //Other methods
    /**
     * @param time screen is shown
     * @param screen to be shown
     */
    public void showScreen(int time, Screen screen) {
        ScreenResult continueInScreen = screen.begin(this.getDispenserHardware());
        if (continueInScreen == ScreenResult.continueScreen) {
            String message = this.translator.translate(screen.getTitle());
            this.dispenser.setTitle(message);
            this.printOptions(screen);
            switch (screen.getMode()) {
                case optionsMode:
                    this.optionsMode(time, screen, continueInScreen);
                    break;
                case theaterMode:
                    this.theaterMode(time, screen, continueInScreen);
                    break;
                case messageMode:
                    this.messageMode(time, screen);
                    break;
            }
        }
    }

    /**
     * Helper Method for showScreen. It prints the options. First of all it
     * prints all the options from the options list of the screen; The it print
     * a Cancel button at the end adn finally it fill the other buttons with nul
     * in order to delete them.
     *
     * @param screen for printing the options
     */
    private void printOptions(Screen screen) {
        int i = 0;
        if (screen.getOptions() != null) {
            while (i < screen.getOptions().size()) {
                if (!screen.getTitle().equals("Selección fecha de representación")) {
                    this.dispenser.setOption(i, this.translator.translate(screen.getOptions().get(i)));
                } else {
                    this.dispenser.setOption(i, this.translator.translate(screen.getOptions().get(i).substring(0, 3)) + screen.getOptions().get(i).substring(3));
                }
                i++;
            }
            if (!screen.getTitle().equals("Bienvenido al teatro Alfil")) {
                this.dispenser.setOption(i, this.translator.translate("Cancelar"));
                i++;
            }
        }
        while (i < 6) {
            this.dispenser.setOption(i, null);
            i++;
        }
    }

    /**
     * @param area to be shown
     */
    private void drawArea(TheaterAreaState area) {
        for (int i = 1; i <= area.getRows(); i++) {
            for (int j = 1; j <= area.getCols(); j++) {
                SeatState seat = area.getSeat(i - 1, j - 1);
                switch (seat) {
                    case corridor:
                        this.dispenser.markSeat(i, j, 0);
                        break;
                    case occupied:
                        this.dispenser.markSeat(i, j, 1);
                        break;
                    case free:
                        this.dispenser.markSeat(i, j, 2);
                        break;

                }
            }
        }
    }

    /**
     * Helper Method for showScreen; it does what optionsMode does
     *
     * @param screen to be shown
     * @param time screen is shown
     * @param continueInScreen for the do-while loop
     */
    private void optionsMode(int time, Screen screen, ScreenResult continueInScreen) {
        do {
            this.dispenser.setMenuMode();
            String message = this.translator.translate(screen.getTitle());
            this.dispenser.setTitle(message);
            if (screen.getDescription() != null) {
                message = this.translator.translate(screen.getDescription());
                this.dispenser.setDescription(message);
            } else {
                this.dispenser.setDescription("");
            }
            this.dispenser.setImage(screen.getImage());
            char opt = this.dispenser.waitEvent(time);
            if (opt != 0) {
                screen.optionButtonPressed(this.dispenserHardware, opt);
            }
            if (opt != '1') {
                continueInScreen = screen.end(this.getDispenserHardware());
            }
            this.printOptions(screen);
        } while (continueInScreen != ScreenResult.exitScreen);
    }

    /**
     * Helper Method for showScreen; it does what theaterMode does
     *
     * @param screen to be shown
     * @param time screen is shown
     * @param continueInScreen for the do-while loop
     */
    private void theaterMode(int time, Screen screen, ScreenResult continueInScreen) {
        this.getDispenser().setTheaterMode(screen.getAreaState().getRows(), screen.getAreaState().getCols());
        TheaterAreaState area = screen.getAreaState();
        this.drawArea(area);
        do {
            char opt = this.dispenser.waitEvent(time);
            if ((opt == 'A') || (opt == 'B')) {
                continueInScreen = screen.optionButtonPressed(this.dispenserHardware, opt);
                if (continueInScreen == ScreenResult.exitScreen) {
                    continueInScreen = screen.end(this.getDispenserHardware());
                }
            } else if ((opt != '1') && (opt != 0)) {
                byte col = (byte) (opt & 0xFF);
                byte row = (byte) ((opt & 0xFF00) >> 8);
                continueInScreen = screen.seatButtonPressed(this.dispenserHardware, row, col);
            }
            if (opt == 0) {
                continueInScreen = screen.end(this.getDispenserHardware());
            }
        } while (continueInScreen != ScreenResult.exitScreen);
    }

    /**
     * Helper Method for showScreen; it does what messageMode does
     *
     * @param screen to be shown
     * @param time screen is shown
     */
    private void messageMode(int time, Screen screen) {
        this.dispenser.setMessageMode();
        if (screen.getTitle().equals("Inserte una tarjeta de credito")) {
            this.dispenser.setDescription(this.translator.translate(screen.getDescription().substring(0, 28)) + screen.getDescription().substring(28));
            char opt = this.dispenser.waitEvent(time);
            if (opt == '1') {
                screen.creditCardDetected(this.dispenserHardware);
            }
        } else {
            this.dispenser.setDescription(this.translator.translate(screen.getDescription()));
            if (!screen.getTitle().equals("Retire su tarjeta de credito")) {
                this.dispenser.waitEvent(time);
            }
        }
        screen.end(this.dispenserHardware);
    }
}
