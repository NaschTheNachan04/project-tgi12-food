package de.ghse.tgi.rezepteapp;

import java.util.ArrayList;

public class Event {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private String title;
    private ArrayList<Integer> recipe;

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Integer> getRecipe() {
        return recipe;
    }



    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRecipe(ArrayList<Integer> recipe) {
        this.recipe = recipe;
    }



}
