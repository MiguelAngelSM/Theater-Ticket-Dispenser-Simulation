/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.DispenserClasses;

import java.util.List;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author masan
 */
public class DispenserHardware {

    //Properties
    private TheaterTicketDispenser dispenser;
    private final int timeUntilRetainCreditCardDefinietly = 30;
    
    //Methods
    //Constructor
    /**
     * @param dispenser
     */
    DispenserHardware(TheaterTicketDispenser dispenser) {
        this.dispenser = dispenser;
    }

    //Other methods
    /**
     * @param ticket
     */
    public void printTicket(List<String> ticket) {
        this.dispenser.print(ticket);
    }

    /**
     * @param retain
     * @return creadit card number
     */
    public long retainCreditCard(boolean retain) {
        this.dispenser.retainCreditCard(retain);
        return this.dispenser.getCardNumber();
    }

    /**
     * 
     */
    public void expelCreditCard() {
        this.dispenser.expelCreditCard(this.timeUntilRetainCreditCardDefinietly);
    }

    /**
     * @return the card number
     */
    public long getCardNumber() {
        return this.dispenser.getCardNumber();
    }
}
