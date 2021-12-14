package com.example.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ENMListAdapter extends ArrayAdapter<ENMExamples> {

    private Context mContext;
    private int mResource;

    public ENMListAdapter(Context context, int resource, ArrayList<ENMExamples> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int year = getItem(position).getYear();
        int month = getItem(position).getMonth();
        int day = getItem(position).getDay();
        String date = "";
        String eventName = getItem(position).getEventName();
        String desc = getItem(position).getDescription();

        //call method to create string of date
        date = createDate(year, month, day);

        //create ENMExamples objects
        ENMExamples event = new ENMExamples(eventName,year, month, day, desc);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //associate each field with correct variable
        TextView tvEventName = (TextView) convertView.findViewById(R.id.eventName);
        TextView tvDate = (TextView) convertView.findViewById(R.id.eventDate);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.eventDescription);

        //set the texts in the correct areas
        tvEventName.setText(eventName);
        tvDate.setText(date);
        tvDesc.setText(desc);

        return convertView;
    }

    //create date for the list
    public String createDate(int year, int month, int day){
        String date = "";

        switch (month) {
            case 0: date += "Jan"; break;
            case 1: date += "Feb"; break;
            case 2: date += "Mar"; break;
            case 3: date += "Apr"; break;
            case 4: date += "May"; break;
            case 5: date += "Jun"; break;
            case 6: date += "Jul"; break;
            case 7: date += "Aug"; break;
            case 8: date += "Sep"; break;
            case 9: date += "Oct"; break;
            case 10: date += "Nov"; break;
            case 11: date += "Dec"; break;
            default: date += "";
        }
        date += " " + day;
        date += ", " + year;

        return date;
    }
}
