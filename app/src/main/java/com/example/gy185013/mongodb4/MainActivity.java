package com.example.gy185013.mongodb4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    public static String dataFromAsyncTask;
    DatePickerFragment pickerDialogs=new DatePickerFragment();
    TimePickerFragment timepicker=new TimePickerFragment();
    int TimeZoneOffSet = -1;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_event_types);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.event_types, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner_eatingin = (Spinner) findViewById(R.id.spinner_eating_in);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_eatingin = ArrayAdapter.createFromResource(this,
                R.array.eating_in, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter_eatingin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_eatingin.setAdapter(adapter_eatingin);

        // Initial Button Status
       // RadioButton button = (RadioButton)findViewById(R.id.rbOther);
      //  button.setAlpha(.5f);
       // button.setClickable(false);

       // button = (RadioButton)findViewById(R.id.rbNow);
       // button.setAlpha(.5f);
       // button.setClickable(false);

        SetButtonStatus(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OnSubmit(View v) {

        if(Valid()) {
            MongoPortal portal = new MongoPortal();
            portal.execute(CreateTreatmentObject());
            Toast.makeText(getApplicationContext(), "Care Portal Record Submitted", Toast.LENGTH_LONG).show();
        }

    }
    private String GetCurrentTextInEditText(int Id) {
        EditText edittxt = (EditText)findViewById(Id);
        return edittxt.getText().toString();
    }

    private TreatmentObject CreateTreatmentObject()
    {

        TreatmentObject treatmentObj = new TreatmentObject();

        // Entered By
        Spinner spinner = (Spinner)findViewById(R.id.spinner_event_types);
        String event_type = spinner.getSelectedItem().toString();
        treatmentObj.SetEventType(event_type);

        // Glucose Reading
        treatmentObj.SetGlucoseReading(GetCurrentTextInEditText(R.id.edtxtGlucoseReading));

        // Carbs Given
        treatmentObj.SetCarbsGiven(GetCurrentTextInEditText(R.id.edittxtCarbsGiven));

        // Insulin
        treatmentObj.SetInsulin(GetCurrentTextInEditText(R.id.edittxtInsulinGiven));

        // Eating In
        spinner = (Spinner)findViewById(R.id.spinner_eating_in);
        String eating_in = spinner.getSelectedItem().toString();
        treatmentObj.SetEatingIn(eating_in);

        // Additional notes
        treatmentObj.SetAdditionalNotes(GetCurrentTextInEditText(R.id.editTextAdditionalNotes));

        // Entered By
        treatmentObj.SetEnteredBy(GetCurrentTextInEditText(R.id.edtxtEnteredBy));

        // Date Time
        RadioButton other_Button = (RadioButton)findViewById(R.id.rbOther);

        String date_time = GetDateTime(other_Button.isChecked(), pickerDialogs.getYear(), pickerDialogs.getMonth(),pickerDialogs.getDay(),
                timepicker.getHour(),timepicker.getMinute(), timepicker.getSeconds());



        treatmentObj.SetDateTime(date_time);

        // Measurement Method
        RadioButton rdbutton = (RadioButton)findViewById(R.id.rbMeter);
        String result = rdbutton.isChecked() == true ? "Finger" : "Sensor";
        treatmentObj.SetMeasurementMethod(result);

        return treatmentObj;
    }

    public String GetDateTime(boolean other_checked, int Year, int Month, int Day, int Hour, int Minute, int Seconds)
    {
        Date date = null;

     //   RadioButton other_Button = (RadioButton)findViewById(R.id.rbOther);
        if(other_checked) {

            date = GetOtherDate( Year,  Month,  Day,  Hour,  Minute,  Seconds);
        }
        else // Now
        {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR,cal.get(Calendar.HOUR) + TimeZoneOffSet );

            date = cal.getTime();
        }

        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ms'Z'");
        String date_time = dateformat.format(date);

        return date_time;
    }

    public Date GetOtherDate(int Year, int Month, int Day, int Hour, int Minute, int Seconds)
    {
        Calendar cal = new GregorianCalendar(Year,Month -1, Day); // Month zero based 0 = January
        cal.add(Calendar.HOUR,Hour + TimeZoneOffSet);
        cal.add(Calendar.MINUTE, Minute);
        cal.add(Calendar.SECOND, Seconds);
        return cal.getTime();
    }

    public boolean Valid()
    {
        boolean valid = true;
        RadioButton button = (RadioButton)findViewById(R.id.rbOther);

        if(button.isChecked())
        {
            if(!pickerDialogs.IsOK_Pressed() || !timepicker.IsOK_Pressed())
            {
                Toast.makeText(getApplicationContext(), "Date or Time have not been set", Toast.LENGTH_LONG).show();
                valid = false;
            }
        }
        return valid;
    }

    public void OnNowClicked(View v){
        SetButtonStatus(false);
        pickerDialogs.OK_Not_Pressed();
        timepicker.OK_Not_Pressed();
    }
    public void OnOtherClicked(View v){
       SetButtonStatus(true);
    }
    private void SetButtonStatus(boolean status){
        Button btn = (Button) findViewById(R.id.butSetDate);
        btn.setEnabled(status);
        btn = (Button) findViewById(R.id.butSetTime);
        btn.setEnabled(status);
     }

    public void setDate(View view){
        pickerDialogs.show(this.getFragmentManager(), "datePicker");
     }

    public void setTime(View view){
        timepicker.show(getSupportFragmentManager(), "timePicker");
    }
}
