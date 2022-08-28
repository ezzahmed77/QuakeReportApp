package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeContentAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeContentAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the current list item
        Earthquake earthquake = getItem(position);

        //Setting the Magnitude of Earthquake
        TextView mag_text =listItemView.findViewById(R.id.mag_text);
        //Creating decimal format object
        String magnitude = formatMagnitude(earthquake.getMag());
        //Setting the formatted text to magnitude
        mag_text.setText(magnitude);

        //Coloring the magnitude circle based on the mag vale
        //Getting the background of the magnitude text
        GradientDrawable magnitudeCircle = (GradientDrawable) mag_text.getBackground();
        //Choosing the magnitude colors based on value
        int magnitudeColor = getMagnitudeColor(earthquake.getMag());
        //Setting the color
        magnitudeCircle.setColor(magnitudeColor);

        //Storing the location String
        String full_location = earthquake.getLocation();

        //Getting the TextView of Distance
        TextView distance_from =listItemView.findViewById(R.id.distance_from_text);

        //Getting the TextView of City
        TextView city_text = listItemView.findViewById(R.id.city_text);

        //Getting the separation index first
        int separation_index = full_location.indexOf("of"); // This returns index of o character

        //Getting the text of distance from text
        String distance = full_location.substring(0, separation_index + 2);

        //Getting the city string
        String city = full_location.substring(separation_index + 3, full_location.length());

        //Setting the text views
        distance_from.setText(distance);
        city_text.setText(city);


        //Making date object to store the time in milliseconds
        Date dateObject = new Date(earthquake.getTimeInMilliseconds());


        //Setting the Date of Earthquake
        TextView date_text =listItemView.findViewById(R.id.date_text);

        //Getting the formatted Date
        String formattedDate = formatDate(dateObject);
        date_text.setText(formattedDate);

        // Getting the time view
        TextView timeView = listItemView.findViewById(R.id.time_text);

        // Getting the formatted Time
        String formattedTime = formatTime(dateObject);

        //Setting the Time View text
        timeView.setText(formattedTime);

        //Setting onclicklistener for each list item to open the USGS website
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = earthquake.getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                getContext().startActivity(intent);
            }
        });

        return listItemView;

    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(magnitude);

        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
