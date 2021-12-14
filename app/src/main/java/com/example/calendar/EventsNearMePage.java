package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EventsNearMePage extends AppCompatActivity{

    private String eName;
    private int eYear;
    private int eMonth;
    private int eDay;
    EventListParcel eventListParcel;

    //empty constructor
    public EventsNearMePage(){
    }

    //constructor with name and date of event
    public EventsNearMePage(String eName, int year, int month, int day){
        this.eName = eName;
        this.eYear = year;
        this.eMonth = month;
        this.eDay = day;
    }

    //getters and setters
    public String getEName(){
        return eName;
    }

    public void setEName(String eName){
        this.eName = eName;
    }

    public int getYear() { return eYear; }

    public void setYear(int year) { this.eYear = year; }

    public int getEMonth(){
        return eMonth;
    }

    public void setEMonth(int month){
        this.eMonth = month;
    }

    public int getEDay(){
        return eDay;
    }

    public void setEDay(int day){
        this.eDay = day;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_near_me_page);

        Intent intent = getIntent();
        eventListParcel = intent.getParcelableExtra("GlobalEvents");


        //set buttons
        ImageView calendarButtonENM = findViewById(R.id.calendarIcon);
        ImageView backButtonENM = findViewById(R.id.backIcon);

        //go back to calendar
        calendarButtonENM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("GlobalEvents", eventListParcel.getEventArrayList());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //back button
        backButtonENM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("GlobalEvents", eventListParcel.getEventArrayList());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //list of events near you
        ListView myListView = (ListView) findViewById(R.id.listView);

        //mock events near you
        ENMExamples e1 = new ENMExamples("Downtown Sunset Bike Tours", 2021,  11, 13,
                "Enjoy city and sunset views along downtown streets and the Virginia Capital Trail on a classic bike or electric bike. ");

        ENMExamples e2 = new ENMExamples("Alive in the Superunknown", 2021,  11, 13,
                "Reynolds Gallery is pleased to announce the opening of Alive in the Superunknown, an exhibition of Ron Johnson’s latest works.");

        ENMExamples e3 = new ENMExamples("Hot Wheels Race to Win Exhibit", 2021,  11, 14,
                "Speed, power, performance ... buckle up and race with the most famous toy vehicles on the planet—Hot Wheels!");

        ENMExamples e4 = new ENMExamples("The Nutcracker", 2021,  11, 18,
                "The Nutcracker makes its triumphant return this holiday season with the Richmond Ballet!");

        ENMExamples e5 = new ENMExamples("Richmond Ballet's the Nutcracker", 2021,  11, 19,
                "The Nutcracker makes its triumphant return this holiday season! Accompanied by the Richmond Symphony, Clara and her adoring Nutcracker, the glittering butterfly, and dancing Russian bear will once again charm audience members of all ages.");

        ENMExamples e6 = new ENMExamples("Richmond Scavenger Hunts", 2021, 11, 28,
                "In this era of social distancing, Discover Richmond Tours is now offering neighborhood scavenger hunts!");

        ENMExamples e7 = new ENMExamples("Saved by the 90s", 2022, 0, 7,
                "At The National at 8 PM.");

        ENMExamples e8 = new ENMExamples("Dancing with the Stars Live", 2022,  0, 7,
                "America’s favorite dance show is coming to your town with “Dancing with the Stars – Live Tour 2022!”");

        ENMExamples e9 = new ENMExamples("Richmond Brewery Tour", 2022,  0, 10,
                "Discover the best in Richmond beer!");

        ENMExamples e10 = new ENMExamples("Scott's Addition Food Tour", 2022,  0, 14,
                "Ready to explore Richmond’s hottest neighborhood? Rapidly-evolving Scott’s Addition is quickly becoming Richmond’s go-to for food, drink, and fun.");

        ENMExamples e11 = new ENMExamples("The Groove Lounge at King Crab", 2022,  0, 22,
                "The Groove Lounge is a place where you can just be free. Eat and Socialize and dance like there is no tomorrow. ");

        ENMExamples e12 = new ENMExamples("VCU Rams Basketball", 2022,  0, 22,
                "Event will be hosted at VCU at 2:30 PM.");

        ENMExamples e13 = new ENMExamples("Pet Expo - Henrico Humane Society", 2022,  0, 22,
                "The 21st Annual Pet Expo will be a unique indoor event where people can bring their pet to enjoy a day of fun and excitement with the whole family.");

        ENMExamples e14 = new ENMExamples("Trivia Tuesday", 2022,  1, 1,
                "Every Tuesday join us at #OurHouse to show us what you know!");

        ENMExamples e15 = new ENMExamples("Game Night At Bryant's Cider", 2022,  1, 17,
                "Come hang with us every Thursday for game night! Who doesn't love Catan and Uno?");

        ENMExamples e16 = new ENMExamples("Richmond Ballet's Romeo and Juliet", 2022,  1, 18,
                "Wide-eyed youth, overwhelming emotions, and tragic heartbreak take center stage as audiences are transported by the greatest love story ever told, Romeo & Juliet.");

        ENMExamples e17 = new ENMExamples("The Lion King", 2022,  2, 7,
                "More than 100 million people around the world have experienced the phenomenon of Disney’s THE LION KING, and now you can, too, when Richmond’s best-loved musical returns to the Altria Theater.");

        ENMExamples e18 = new ENMExamples("Official St. Patrick's Bar Crawl", 2022,  2, 12,
                "Grab Your Favorite Drinking Buddies, Throw On Some Green And Join The SHENANIGANS At The Official St. Patrick's Bar Crawl.");

        ENMExamples e19 = new ENMExamples("Richmond NASCAR Package", 2022,  3, 2,
                "Richmond NASCAR Package includes hotel, bus transport and race tickets.");

        ENMExamples e20 = new ENMExamples("Lea Salonga", 2022,  3, 23,
                "Lea Salonga is a musical theater singer and actress who hails from the Philippines.");

        //building event list
        ArrayList<ENMExamples> eventList = new ArrayList<>();
        eventList.add(e1);
        eventList.add(e2);
        eventList.add(e3);
        eventList.add(e4);
        eventList.add(e5);
        eventList.add(e6);
        eventList.add(e7);
        eventList.add(e8);
        eventList.add(e9);
        eventList.add(e10);
        eventList.add(e11);
        eventList.add(e12);
        eventList.add(e13);
        eventList.add(e14);
        eventList.add(e15);
        eventList.add(e16);
        eventList.add(e17);
        eventList.add(e18);
        eventList.add(e19);
        eventList.add(e20);

        //creating adapter
        ENMListAdapter adapter = new ENMListAdapter(this, R.layout.adapter_view_layout, eventList);
        myListView.setAdapter(adapter);

        //interaction with event list
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                eName = eventList.get(i).getEventName();
                eYear = eventList.get(i).getYear();
                eMonth = eventList.get(i).getMonth();
                eDay = eventList.get(i).getDay();

                Intent popUp = new Intent(getApplicationContext(), ENMPopUp.class);
                popUp.putExtra("pickedEvent", eName);
                popUp.putExtra("pickedYear", eYear);
                popUp.putExtra("pickedMonth", eMonth);
                popUp.putExtra("pickedDay", eDay);
                popUp.putExtra("GlobalEvents", eventListParcel);
                startActivityForResult(popUp, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            eventListParcel.setEventArrayList(data.getParcelableArrayListExtra("GlobalEvents"));
        }
    }

}