/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.ScreensClasses;

import theaterapp.DispenserClasses.DispenserHardware;
import theaterapp.DispenserClasses.DispenserManager;
import theaterapp.enums.ScreenMode;
import theaterapp.enums.ScreenResult;

/**
 *
 * @author masan
 */
public class RemoveAdviseScreen extends Screen {
    //Properties
    private int timeToRetainCreditCard=30;
    
    //Methods
    //Constructor
    /**
     * @param dm
     */
    RemoveAdviseScreen(DispenserManager dm) {
        super("Retire su tarjeta de credito",ScreenMode.messageMode,dm);
        this.setDescription("Si no la retira el dispensador la guardara y podra reclamarla en recepcion");
    }

    //Other methods
    /**
     * It ends the screen and reatin the credit card if the customer do not
     * remove it in 30 seconds
     *
     * @param d
     * @return if the screen should continue being shown
     */
    @Override
    public ScreenResult end(DispenserHardware d) {
        if (!this.getDispenserManager().getDispenser().expelCreditCard(this.timeToRetainCreditCard)) {
            this.getDispenserManager().getDispenser().retainCreditCard(true);
        }
        return ScreenResult.exitScreen;
    }
}
