/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.ScreensClasses;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import theaterapp.DispenserClasses.DispenserHardware;
import theaterapp.DispenserClasses.DispenserManager;
import theaterapp.enums.ScreenMode;
import theaterapp.enums.ScreenResult;
import urjc.UrjcBankServer;

/**
 *
 * @author masan
 */
public class PaymentScreen extends Screen {

    //Properties
    private UrjcBankServer bank;
    private int price;
    private boolean operationDone;

    //Constructor
    /**
     * @param price of the purchase
     * @param dispenserManager
     * @return if the screen should continue being shown
     */
    PaymentScreen(int price, DispenserManager dispenserManager) {
        super("Inserte una tarjeta de credito", ScreenMode.messageMode, dispenserManager);
        this.bank = new UrjcBankServer();
        this.price = price;
        this.setDescription("El precio de su compra es de: " + price + '€');
        this.setOptions(new LinkedList<>());
    }
    //getters and setters

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return the operationDone
     */
    public boolean isOperationDone() {
        return operationDone;
    }

    //Other methods
    /**
     * It takes the operation done and show error screens if the operation could
     * not be done
     *
     * @param d
     * @return if the screen should continue being shown
     */
    @Override
    public ScreenResult creditCardDetected(DispenserHardware d) {
        try {
            long creditCardNumber = d.retainCreditCard(false);
            this.operationDone = this.bank.doOperation(creditCardNumber, this.getPrice());
            if (!this.operationDone) {
                String message = "No dispone de saldo suficiente por lo que no podemos realizar la transaccion; disculpe las molestias";
                this.getDispenserManager().showScreen(Screen.timeShowErrorScreen, new ErrorScreen(message, this.getDispenserManager()));
            }
        } catch (CommunicationException ex) {
            String message = "En estos momentos no podemos conectarnos con su banco; disculpe las molestias";
            this.getDispenserManager().showScreen(Screen.timeShowErrorScreen, new ErrorScreen(message, this.getDispenserManager()));
            Logger.getLogger(PaymentScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ScreenResult.exitScreen;
    }

    /**
     * It initializes the screen. If the communication with the bank is
     * available then it continues in the screen, else it shows an error screen
     * and exits
     *
     * @param d
     * @return if the screen should continue being shown
     */
    @Override
    public ScreenResult begin(DispenserHardware d) {
        if (this.bank.comunicationAvaiable()) {
            return ScreenResult.continueScreen;
        } else {
            String message = "En estos momentos no podemos conectarnos con su banco; disculpe las molestias";
            this.getDispenserManager().showScreen(Screen.timeShowErrorScreen, new ErrorScreen(message, this.getDispenserManager()));
            return ScreenResult.exitScreen;
        }
    }
}
