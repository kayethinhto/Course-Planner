package model;

import org.json.JSONObject;
import persistance.Writable;

//Represents a day and time assigned to a given task
public class Date {
    private int year;
    private int month;
    private int day;


    //REQUIRES: (1 <= month <= 12), (1 <= date <= 31), (<= 0 hour <= 24), (0 <= minute <= 59)
    //EFFECTS: Assigns each date a year, month, day and time
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;

    }

    //getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }


    //EFFECTS: returns formatted date as a string
    public String returnDate() {
        String strDate = String.format("%02d / %02d / %02d", year, month, day);
        return strDate;
    }

}
