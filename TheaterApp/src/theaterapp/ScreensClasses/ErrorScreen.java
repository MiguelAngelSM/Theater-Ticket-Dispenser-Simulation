/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.ScreensClasses;

import theaterapp.DispenserClasses.DispenserManager;
import theaterapp.enums.ScreenMode;

/**
 *
 * @author masan
 */
public class ErrorScreen extends Screen {

    //Constructor
    /**
     * 
     * @param message
     * @param dispenserManger 
     */
    public ErrorScreen(String message,DispenserManager dispenserManger) {
        super("Error",ScreenMode.messageMode,dispenserManger);
        this.setDescription(message);
    }
}
