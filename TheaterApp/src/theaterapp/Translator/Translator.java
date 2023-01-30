/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.Translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;

/**
 *
 * @author masan
 */
public class Translator {

    //Properties
    private Map<String, String> messages;

    //Methods
    //Constructor
    /**
     * @param translatorFile
     */
    public Translator(String translatorFile) {
        this.messages = new HashMap();
        read(translatorFile);
    }

    //Other methods
    /**
     * It translate the message
     *
     * @param msg
     * @return the message translated to the active language
     */
    public String translate(String msg) {
        return this.messages.get(msg);
    }

    /**
     * It reads the file with the translator info
     *
     * @param fileName
     */
    private void read(String fileName) {
        try {
            File file = new File("../Languages/" + fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String fileLine = scanner.nextLine();
                String[] ph = fileLine.split(":"); //ph is placeholder
                this.messages.put(ph[0], ph[1]);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fichero de " + fileName + " no encontrado");
        }
    }
}
