package com.example.gy185013.mongodb4;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by gy185013 on 25/08/2015.
 */
public class MainActivityTest extends TestCase{

    public void test_GetOtherDate() {

        int Year = 1999;
        int Month = 10;
        int Day = 23;
        int Hour = 7;
        int Minute = 45;
        int Seconds = 9;

        MainActivity activity = new MainActivity();
        Date date= activity.GetOtherDate(Year, Month, Day, Hour, Minute,Seconds);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        assertEquals(Year, cal.get(Calendar.YEAR));
        assertEquals(Month, cal.get(Calendar.MONTH) + 1);// Month zero based 0 = January
        assertEquals(Day, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(Hour - 1, cal.get(Calendar.HOUR));
        assertEquals(Minute, cal.get(Calendar.MINUTE));
        assertEquals(Seconds, cal.get(Calendar.SECOND));
    }
    public void test_GetDateTime()
    {
        MainActivity activity = new MainActivity();

        int Year = 2015;
        int Month = 8;
        int Day = 25;
        int Hour = 19;
        int Minute = 25;
        int Seconds = 9;

        String date_time = activity.GetDateTime(true, Year, Month, Day, Hour, Minute,Seconds);
        assertEquals("2015-08-25T18:25:09.259Z", date_time);
    }
}
