package com.example.calendar;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Comparator;
import java.util.Date;

public class Event implements Comparable<Event>, Parcelable {

    // Variables
    private String eventDetails;
    private String eventDate;
    private int year;
    private int month;
    private int day;
    private boolean recurringEvent;


    // Constructors
    public Event(){}
    public Event(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public Event(String eventDetails, String eventDate, boolean recurringEvent) {
        this.eventDetails = eventDetails;
        this.eventDate = eventDate;
        this.recurringEvent = recurringEvent;
    }

    public Event(String eventDetails, int year, int month, int day, boolean recurringEvent) {
        this.eventDetails = eventDetails;
        this.recurringEvent = recurringEvent;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Getters & Setters
    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isRecurringEvent() {
        return recurringEvent;
    }

    public void setRecurringEvent(boolean recurringEvent) {
        this.recurringEvent = recurringEvent;
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
        this.month = month + 1;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    // Methods
    public String selectDate(int year, int month, int day) {
        String dateReturn;
        switch (month) {
            case 0: dateReturn = "Jan"; break;
            case 1: dateReturn = "Feb"; break;
            case 2: dateReturn = "Mar"; break;
            case 3: dateReturn = "Apr"; break;
            case 4: dateReturn = "May"; break;
            case 5: dateReturn = "Jun"; break;
            case 6: dateReturn = "Jul"; break;
            case 7: dateReturn = "Aug"; break;
            case 8: dateReturn = "Sep"; break;
            case 9: dateReturn = "Oct"; break;
            case 10: dateReturn = "Nov"; break;
            case 11: dateReturn = "Dec"; break;
            default: dateReturn = "";
        }
        dateReturn += " " + day;

        switch(day) {
            case 1:
            case 21:
            case 31: dateReturn += "st";
            break;
            case 2:
            case 22: dateReturn += "nd";
            break;
            case 3:
            case 23: dateReturn += "rd";
            break;
            default: dateReturn += "th";
        }

        dateReturn += ", " + year;
        return dateReturn;
    }

    // Used this for debugging; not intended to keep
//    public String toString() {
//        return "Details: " + getEventDetails() + " |Date: " + getMonth()+ "/"+ getDay() + "/" + getYear() + "    ||";
//    }


    // Comparable Method
    @Override
    public int compareTo(Event event) {
        // Years the same
        if (getYear() == event.getYear()) {
            // Months are the same
            if (getMonth() == event.getMonth()) {
                return Integer.compare(getDay(), event.getDay());
            }
            // Months are different
            return Integer.compare(getMonth(), event.getMonth());
        }
        // Years are different
        return Integer.compare(getYear(), event.getYear());
    }



    // Parcelable Methods

    protected Event(Parcel in) {
        eventDetails = in.readString();
        year = in.readInt();
        month = in.readInt();
        day = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(eventDetails);
        parcel.writeInt(year);
        parcel.writeInt(month);
        parcel.writeInt(day);
    }
}
