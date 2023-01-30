/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.ScreensClasses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import theaterapp.DispenserClasses.DispenserHardware;
import theaterapp.DispenserClasses.DispenserManager;
import theaterapp.TheaterClasses.Theater;
import theaterapp.TheaterClasses.TheaterAreaState;
import theaterapp.enums.ScreenMode;
import theaterapp.enums.ScreenResult;
import theaterapp.enums.SeatState;

/**
 *
 * @author masan
 */
class SeatSelectionScreen extends Screen {

    //Properties
    private TheaterAreaState selectArea;
    private int amountSelected = 0;
    private PaymentScreen payScreen;
    private Theater theater;
    private List<String> selectedSeats = new LinkedList<>();
    private String dayOfPlay;

    private final int maxAmount = 4;

    //Methods
    //Constructor
    /**
     * @param theater
     * @param dispenserManager
     * @param selectArea
     * @param day
     */
    SeatSelectionScreen(Theater theater, DispenserManager dispenserManager, TheaterAreaState selectArea, String day) {
        super("Seleccione sus butacas",ScreenMode.theaterMode,dispenserManager);
        this.selectArea = selectArea;
        this.theater = theater;
        List<String> list = new ArrayList<>();
        list.add("Cancelar");
        list.add("Aceptar");
        this.setOptions(list);
        this.dayOfPlay = day;
    }

    //getters and setters
    /**
     * @return the select area
     */
    @Override
    public TheaterAreaState getAreaState() {
        return this.selectArea;
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
        ScreenResult result = ScreenResult.exitScreen;;
        switch (c) {
            case 'A':
                this.updateState(false);
                break;
            case 'B':
                if (this.amountSelected > 0) {
                    this.payScreen = new PaymentScreen(this.computePrice(), this.getDispenserManager());
                    this.getDispenserManager().showScreen(Screen.timeShowScreen, this.payScreen);
                } else {
                    result = ScreenResult.continueScreen;
                }
                break;
        }
        return result;
    }

    /**
     * It ends the screen; and print the tickets and update states and files if
     * it is needed
     *
     * @param d
     * @return if the screen should continue being shown
     */
    @Override
    public ScreenResult end(DispenserHardware d) {
        if (this.payScreen != null) {
            if (this.payScreen.isOperationDone()) {
                this.updateState(true);
                for (int i = 0; i < this.selectedSeats.size(); i++) {
                    List<String> ticket = new LinkedList<>();
                    ticket.add(this.getDispenserManager().getTranslator().translate("Entrada para") + " " + this.theater.getPlay().getTitle());
                    ticket.add("========================");
                    ticket.add(this.getDispenserManager().getTranslator().translate("Teatro " + theater.getTheaterName()));
                    ticket.add(this.getDispenserManager().getTranslator().translate(this.selectArea.getName()));
                    ticket.add(this.getDispenserManager().getTranslator().translate(this.dayOfPlay.substring(0, 3)) + this.dayOfPlay.substring(3));
                    String fileLine = this.selectedSeats.get(i);
                    String[] ph = fileLine.split(":");
                    ticket.add(this.getDispenserManager().getTranslator().translate("Fila") + ":" + ph[0]);
                    ticket.add(this.getDispenserManager().getTranslator().translate("Columna") + ":" + ph[1]);
                    ticket.add(this.getDispenserManager().getTranslator().translate("Precio") + ":" + this.selectArea.getPrice() + "€");
                    d.printTicket(ticket);
                }
            } else {
                this.updateState(false);
            }
            this.getDispenserManager().showScreen(SeatSelectionScreen.timeShowScreen, new RemoveAdviseScreen(this.getDispenserManager()));
        }else{
            this.updateState(false);
        }
        return ScreenResult.exitScreen;
    }

    /**
     * It update the selectArea seats display temporarily (they are just
     * selected until the screen ends). It also show a helper message for the
     * customer
     *
     * @param d
     * @param row
     * @param col
     * @return if the screen should continue being shown
     */
    @Override
    public ScreenResult seatButtonPressed(DispenserHardware d, int row, int col) {
        if ((this.selectArea.getSeat(row - 1, col - 1) == SeatState.free) && (this.amountSelected < this.maxAmount)) {
            this.selectArea.setSeat(row - 1, col - 1, SeatState.selected);
            this.getDispenserManager().getDispenser().markSeat(row, col, 1);
            this.amountSelected++;
            this.getDispenserManager().getDispenser().setTitle(this.getDispenserManager().getTranslator().translate("Asientos seleccionados") + ":" + this.amountSelected);
        } else if (this.selectArea.getSeat(row - 1, col - 1) == SeatState.selected) {
            this.selectArea.setSeat(row - 1, col - 1, SeatState.free);
            this.getDispenserManager().getDispenser().markSeat(row, col, 2);
            this.amountSelected--;
            this.getDispenserManager().getDispenser().setTitle(this.getDispenserManager().getTranslator().translate("Asientos seleccionados") + ":" + this.amountSelected);
        } else if (this.selectArea.getSeat(row - 1, col - 1) == SeatState.occupied) {
            this.getDispenserManager().getDispenser().setTitle(this.getDispenserManager().getTranslator().translate("Asiento ocupado"));
        } else if (this.amountSelected >= this.maxAmount) {
            this.getDispenserManager().getDispenser().setTitle(this.getDispenserManager().getTranslator().translate("El maximo es 4 asientos"));
        }
        return ScreenResult.continueScreen;
    }

    /**
     * It calculates the price of the purchase
     *
     * @return the purchase price
     */
    public int computePrice() {
        return this.amountSelected * this.selectArea.getPrice();
    }

    /**
     * It update the selectArea seats when the purchase process ends; if it was
     * finished, the seats will be updated to occupied, else, they will recover
     * their initial state (free)
     *
     * @param update if the purchase was finished
     */
    private void updateState(boolean update) {
        for (int i = 0; i < this.selectArea.getRows(); i++) {
            for (int j = 0; j < this.selectArea.getCols(); j++) {
                if (this.selectArea.getSeat(i, j) == SeatState.selected) {
                    if (update) {
                        this.selectArea.setSeat(i, j, SeatState.occupied);
                        int row = i + 1;
                        int col = j + 1;
                        this.selectedSeats.add(row + ":" + col);
                    } else {
                        this.selectArea.setSeat(i, j, SeatState.free);
                    }
                }
            }
        }

    }
}
