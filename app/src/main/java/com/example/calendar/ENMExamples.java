package com.example.calendar;

public class ENMExamples {

    private String eventName;
    private int year;
    private int month;
    private int day;
    private String description;

    public ENMExamples(){
        this.eventName = "";
        this.year = 0;
        this.month = 0;
        this.day = 0;
        this.description = "";
    }

    public ENMExamples(String eventName,int year, int month, int day, String description){
        this.eventName = eventName;
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
    }

    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    public void setYear(int year) { this.year = year; }

    public void setMonth(int month){
        this.month = month;
    }

    public void setDay(int day){
        this.day = day;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getEventName(){
        return eventName;
    }

    public int getYear() { return year; }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public String getDescription(){
        return description;
    }
}
