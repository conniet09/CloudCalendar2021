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
import java.util.Date;

public class EventListAdapter extends ArrayAdapter<Event> {
    private static final String TAG = "EventListAdapter";

    private Context eventContext;
    private int eventResource;


    public EventListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Event> objects) {
        super(context, resource, objects);
        eventContext = context;
        eventResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String eventDetails = getItem(position).getEventDetails();
        String eventDate = getItem(position).getEventDate();
        boolean recurringEvent = getItem(position).isRecurringEvent() ;

        LayoutInflater inflater = LayoutInflater.from(eventContext);

        convertView = inflater.inflate(eventResource, parent, false);

        TextView eDate = convertView.findViewById(R.id.dateOfEvent);
        TextView eDetails = convertView.findViewById(R.id.eventDetails);

        eDate.setText(eventDate.toString());
        eDetails.setText(eventDetails);

        return convertView;
    }
}
