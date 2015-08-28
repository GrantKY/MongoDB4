package com.example.gy185013.mongodb4;


import com.mongodb.BasicDBObject;

import junit.framework.TestCase;

/**
 * Created by gy185013 on 24/08/2015.
 */
//http://developer.android.com/tools/testing/testing_android.html
public class TreatmentObjectTest extends TestCase {

    public void testResult_Insulin() {
        TreatmentObject obj = new TreatmentObject();
        String value = "2.3";
        obj.SetInsulin(value);
        assertEquals(value, obj.GetInsulin());
    }

    public void testResult_CarbsGiven() {
        TreatmentObject obj = new TreatmentObject();
        String value = "2.3";
        obj.SetCarbsGiven(value);
        assertEquals(value, obj.GetCarbsGiven());
    }

    public void testResult_AdditionalNotes() {
        TreatmentObject obj = new TreatmentObject();
        String value = "2.3";
        obj.SetAdditionalNotes(value);
        assertEquals(value, obj.GetAdditionalNotes());
    }

    public void testResult_EatingIn() {
        TreatmentObject obj = new TreatmentObject();
        String value = "15 Minutes";
        obj.SetEatingIn(value);
        assertEquals(value, obj.GetEatingIn());
    }

    public void testResult_DateTime() {
        TreatmentObject obj = new TreatmentObject();
        String value = "Date Time is now";
        obj.SetDateTime(value);

        assertEquals(value, obj.GetDateTime());
    }

    public void testResult_EnteredBy() {
        TreatmentObject obj = new TreatmentObject();
        String value = "Dad";
        obj.SetEnteredBy(value);

        assertEquals(value, obj.GetEnteredBy());
    }

    public void testResult_EventType() {
        TreatmentObject obj = new TreatmentObject();
        String value = "Dad";
        obj.SetEventType(value);

        assertEquals(value, obj.GetEventType());
    }

    public void testResult_GlucoseReading() {
        TreatmentObject obj = new TreatmentObject();
        String value = "4.3";
        obj.SetGlucoseReading(value);

        assertEquals(value, obj.GetGlucoseReading());
    }

    public void testResult_MeasurementMethod() {
        TreatmentObject obj = new TreatmentObject();
        String value = "Finger";
        obj.SetMeasurementMethod(value);

        assertEquals(value, obj.GetMeasurementMethod());
    }

    public void testResult_GetMongoDBOject_Test1() {

        TreatmentObject obj = new TreatmentObject();
        obj.SetMeasurementMethod("Meter");
        obj.SetGlucoseReading("4.5");
        obj.SetAdditionalNotes("Additional Notes ");

        BasicDBObject baseobj = obj.GetMongoDBOject();

        assertEquals(3, baseobj.size());

    }

    public void testResult_GetMongoDBOject_Test2() {

        TreatmentObject obj = new TreatmentObject();
        obj.SetInsulin("4.3");
        obj.SetEventType("BG Reading");
        obj.SetCarbsGiven("6.5");

        BasicDBObject baseobj = obj.GetMongoDBOject();

        assertEquals(3, baseobj.size());

    }

    public void testResult_GetMongoDBOject_Test3() {

        TreatmentObject obj = new TreatmentObject();
        obj.SetMeasurementMethod("Meter");
        BasicDBObject baseobj = obj.GetMongoDBOject();

        assertEquals(0, baseobj.size());
    }

    public void testResult_GetMongoDBOject_Test4() {

        TreatmentObject obj = new TreatmentObject();
        obj.SetMeasurementMethod("Meter");
        obj.SetGlucoseReading("6.5");
        BasicDBObject baseobj = obj.GetMongoDBOject();

        assertEquals(2, baseobj.size());
    }

    public void testResult_GetMongoDBOject_Test5() {

        //    "eating at": "Time in minutes",
        TreatmentObject obj = new TreatmentObject();
        obj.SetEatingIn("Time in minutes");
        BasicDBObject baseobj = obj.GetMongoDBOject();

        assertEquals(0, baseobj.size());
    }

    public void testResult_GetMongoDBOject_Test6() {

        //    "eating at": "Time in minutes",
        TreatmentObject obj = new TreatmentObject();
        obj.SetEatingIn("15  minutes");
        BasicDBObject baseobj = obj.GetMongoDBOject();

        assertEquals(1, baseobj.size());
    }
}