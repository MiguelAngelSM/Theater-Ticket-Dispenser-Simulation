/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.TheaterClasses;

import theaterapp.DispenserClasses.DispenserManager;
import theaterapp.ScreensClasses.WelcomeScreen;

/**
 *
 * @author masan
 */
public class TheaterManager {

    //Methods
    //Other methods
    public void run() {
        DispenserManager dm = new DispenserManager();
        Theater t = new Theater("theater.txt");
        WelcomeScreen ws = new WelcomeScreen(t, dm);
        dm.showScreen(30, ws);
    }
}
