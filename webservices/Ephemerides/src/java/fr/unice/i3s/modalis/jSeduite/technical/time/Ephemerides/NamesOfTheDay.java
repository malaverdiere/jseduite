/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.technical.time.Ephemerides;

/**
 *
 * @author mosser
 */
public class NamesOfTheDay {
    private String[] names;
    private int month;
    private int day;

    public NamesOfTheDay() { this.names = new String[0]; }
    public NamesOfTheDay(String[] names, int month, int day) {
        this.names = names;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }


}
