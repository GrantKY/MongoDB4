package com.example.gy185013.mongodb4;

/**
 * Created by gy185013 on 23/08/2015.
 */

import java.util.Calendar;
import java.util.Date;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements
        TimePickerDialog.OnTimeSetListener {

    private int current_hour;
    private int current_minute;
    private int current_seconds;
    private int current_milliseconds;
    private boolean OK_Pressed = false;

    public int getHour() {return current_hour;}
    public int getMinute() { return current_minute;}
    public int getSeconds() { return current_seconds;}
    public boolean IsOK_Pressed(){ return OK_Pressed;}
    public void OK_Not_Pressed(){OK_Pressed = false;}
  //  public int getMilliSeconds(){return current_milliseconds;}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        OK_Pressed= false;
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        current_hour = hourOfDay;
        current_minute = minute;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        OK_Pressed= true;
      //  current_milliseconds = cal.get(Calendar.MILLISECOND);
    }

}