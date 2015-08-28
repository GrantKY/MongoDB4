package com.example.gy185013.mongodb4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by gy185013 on 23/08/2015.
 */
public  class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

        private int current_year, current_month,current_day;
        private boolean OK_Pressed = false;
        public int getYear(){ return current_year;}
        public int getMonth() { return current_month;}
        public int getDay() { return current_day;}
        boolean IsOK_Pressed(){ return OK_Pressed;}
        public void OK_Not_Pressed(){OK_Pressed = false;}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        OK_Pressed = false;
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        current_year = year;
        current_month = month + 1;// zero based month 0 = January
        current_day = day;
        OK_Pressed= true;
    }
}
