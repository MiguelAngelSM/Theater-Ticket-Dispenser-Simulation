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
import java.util.logging.Logger;

/**
 *
 * @author masan
 */
public class TranslatorManager {

    //Properties
    private String activeLanguage;
    private Map<String, Translator> translatorMap;

    //Methods
    //Constructor
    /**
     */
    public TranslatorManager() {
        this.activeLanguage = "Español";
        this.translatorMap = new HashMap();
        readLanguages("../Languages/", "Available.txt");
    }

    //getters and setters
    /**
     * @param activeLanguage the activeLanguage to set
     */
    public void setActiveLanguage(String activeLanguage) {
        this.activeLanguage = activeLanguage;
    }

    //Other methods
    /**
     * It translate the message
     *
     * @param message
     * @return the message translated to the active language
     */
    public String translate(String message) {
        Translator translator = this.translatorMap.get(this.activeLanguage);
        return translator.translate(message);
    }

    /**
     * It reads the languages from the folder given and the available ones are
     * in the fileOfFiles
     *
     * @param folder where the languages are
     * @param fileOfFiles where the available languages are written with their
     * file name
     */
    private void readLanguages(String folder, String fileOfFiles) {
        try {
            File file = new File(folder + fileOfFiles);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String fileLine = scanner.nextLine();
                String[] ph = fileLine.split(":"); //ph is placeholder
                Translator translator = new Translator(ph[1]);
                this.translatorMap.put(ph[0], translator);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TranslatorManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fichero de idiomas no encontrado");
        }
    }

}
