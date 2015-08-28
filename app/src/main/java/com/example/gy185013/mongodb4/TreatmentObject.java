package com.example.gy185013.mongodb4;

import com.mongodb.BasicDBObject;

import java.util.UUID;

/**
 * Created by gy185013 on 22/08/2015.
 */
public class TreatmentObject implements ITreatmentObject{

    private String enteredBy;
    private String insulin;
    private String eventType;
    private String created_at;
    private String datetime;
    private String eatingIn;
    private String additionalNotes;
    private String glucoseReading;
    private String measurementMethod;
    private String carbsGiven;


    public void SetEnteredBy(String value){
        enteredBy = value;
    }

    public String GetEnteredBy(){
        return enteredBy;
    }
    public void SetEventType(String value){
        eventType = value;
    }

    public String GetEventType(){
        return eventType;
    }

    public void SetInsulin(String value){
        insulin = value;
    }
    public String GetInsulin(){
        return insulin;
    }

     public void SetEatingIn (String value){
        eatingIn = value;
    }
    public String GetEatingIn(){
        return eatingIn;
    }

    public void SetDateTime (String value){
        datetime = value;
    }
    public String GetDateTime(){
        return datetime;
    }

    public void SetAdditionalNotes (String value){
        additionalNotes = value;
    }
    public String GetAdditionalNotes(){
        return additionalNotes;
    }

    public void SetGlucoseReading (String value){
        glucoseReading = value;
    }
    public String GetGlucoseReading(){
        return glucoseReading;
    }

    public void SetMeasurementMethod (String value){
        measurementMethod = value;
    }
    public String GetMeasurementMethod(){
        return measurementMethod;
    }

    public void SetCarbsGiven (String value){
        carbsGiven = value;
    }
    public String GetCarbsGiven(){
        return carbsGiven;
    }


    public BasicDBObject  GetMongoDBOject()
    {
        BasicDBObject document = new BasicDBObject();

        if(eventType != null)
            document.put("eventType",eventType );

        if(glucoseReading != null && glucoseReading.length() > 0) {
            document.put("glucose", glucoseReading);
            document.put("glucoseType", measurementMethod);
        }


        if(carbsGiven != null && carbsGiven.length() > 0)
            document.put("carbs", carbsGiven);

        if(insulin != null && insulin.length() > 0)
            document.put("insulin",insulin );

        if(eatingIn != null && !eatingIn.toLowerCase().contains("time in minutes"))
            document.put("eating at", eatingIn);

        if(additionalNotes != null && additionalNotes.length() > 0)
            document.put("notes",additionalNotes );

        if(enteredBy != null  && enteredBy.length() > 0)
            document.put("enteredBy", enteredBy);

        if(datetime != null  && datetime.length() > 0)
            document.put("created_at",datetime );

        return document;
    }
}
