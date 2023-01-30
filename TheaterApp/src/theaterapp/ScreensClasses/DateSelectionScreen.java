/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp.ScreensClasses;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import theaterapp.DispenserClasses.DispenserHardware;
import theaterapp.DispenserClasses.DispenserManager;
import theaterapp.TheaterClasses.Theater;
import theaterapp.TheaterClasses.TheaterState;
import theaterapp.enums.ScreenMode;
import theaterapp.enums.ScreenResult;

/**
 *
 * @author masan
 */
class DateSelectionScreen extends Screen {

    //Properties
    private Theater theater;
    private Map<String, TheaterState> schedule;
    private AreaSelectionScreen areasScreen;

    //Methods
    //Constructor
    /**
     * @param theater
     * @param dispenserManager
     */
    DateSelectionScreen(Theater theater, DispenserManager dispenserManager) {
        super("Selección fecha de representación", ScreenMode.optionsMode, dispenserManager);
        this.setDescription("Recuerden que los lunes no hay teatro");
        this.theater = theater;
        this.schedule = new HashMap();
    }

    //getters and setters
    /**
     * @return a list with the options
     */
    @Override
    public List<String> getOptions() {
        List<Date> dates = this.getDaysFromToday();
        List<String> list = new LinkedList<>();
        for (int i = 0; i < dates.size(); i++) {
            LocalDate date = dates.get(i).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            list.add(dates.get(i).toString().substring(0, 3) + ' ' + date.toString());
        }
        return list;
    }

    //Other methods
    /**
     * It initializes the screen
     *
     * @param d
     * @return if the screen should continue being shown
     */
    @Override
    public ScreenResult begin(DispenserHardware d) {
        this.loadStateFiles();
        return ScreenResult.continueScreen;
    }

    /**
     * It decides what the next step will be depending on the button pressed.
     * The switch will decide what index from the day list is the selected or
     * if the user cancels. Then if the user select a day the next screen is
     * shown with the selected area, else it returns exitScreen in order to
     * return to the main page
     *
     * @param dh not used here
     * @param c with the button pressed
     * @return if the screen should continue being shown
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dh, char c) {
        ScreenResult result = ScreenResult.continueScreen;
        AreaSelectionScreen selectScreen;
        ArrayList<String> list = new ArrayList<>(this.getOptions());
        int index = 0;
        switch (c) {
            case 'A':
                index = 0;
                break;
            case 'B':
                index = 1;
                break;
            case 'C':
                index = 2;
                break;
            case 'D':
                index = 3;
                break;
            case 'E':
                index = 4;
                break;
            case 'F':
                result = ScreenResult.exitScreen;
        }
        if (result == ScreenResult.continueScreen) {
            selectScreen = new AreaSelectionScreen(this.theater, this.getDispenserManager(), list.get(index), this.schedule.get(list.get(index)));
            this.getDispenserManager().showScreen(Screen.timeShowScreen, selectScreen);
            this.schedule.get(list.get(index)).saveInfo();
        }
        return result;
    }

    /**
     * It load the TheaterState files
     */
    private void loadStateFiles() {
        List<String> list = this.getOptions();
        for (String i : list) {
            this.schedule.put(i, new TheaterState(this.theater, i));
        }
    }

    /**
     * @return a List of Dates wich are the actual Date and the following 4 but
     * skipping Mondays
     */
    private List<Date> getDaysFromToday() {
        LocalDate today = LocalDate.now();
        List<Date> dayList = new ArrayList<>();
        int days = 0;
        do {
            Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (!date.toString().contains("Mon")) {
                days++;
                dayList.add(date);
            }
            today = today.plusDays(1);
        } while (days < 5);
        return dayList;
    }
}
