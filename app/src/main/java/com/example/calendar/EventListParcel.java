package com.example.calendar;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class EventListParcel implements Parcelable {
    private ArrayList<Event> eventArrayList;
    private int arrayIndex;

    public int getArrayIndex() {
        return arrayIndex;
    }

    public void setArrayIndex(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public ArrayList<Event> getEventArrayList() {
        return eventArrayList;
    }

    public void setEventArrayList(ArrayList<Event> eventArrayList) {
        this.eventArrayList = eventArrayList;
    }


    public EventListParcel(ArrayList<Event> eventArrayList) {
        this.eventArrayList = eventArrayList;
    }


    // Parcelable Methods

    protected EventListParcel(Parcel in) {
        eventArrayList = in.createTypedArrayList(Event.CREATOR);
        arrayIndex = in.readInt();
    }

    public static final Creator<EventListParcel> CREATOR = new Creator<EventListParcel>() {
        @Override
        public EventListParcel createFromParcel(Parcel in) {
            return new EventListParcel(in);
        }

        @Override
        public EventListParcel[] newArray(int size) {
            return new EventListParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(getEventArrayList());
        parcel.writeInt(arrayIndex);
    }
}
