package com.example.gy185013.mongodb4;

/**
 * Created by gy185013 on 9/16/2015.
 */
import android.content.res.Resources;

import junit.framework.TestCase;
public class MongoPortalTest extends TestCase {

    public void testMongoDBConnectionString() {

        String defaultconnectionstring = "mongodb://<username>:<password>@<server>:<port>/<database>";
        Boolean result = MongoPortal.ValidMongoDBConnectionString(defaultconnectionstring);

        assertTrue(result);
    }
    public void testMongoDBConnectionString_InvalidString() {

        String defaultconnectionstring = "mongodb://<password>@<server>:<port>/<database>";
        Boolean result = MongoPortal.ValidMongoDBConnectionString(defaultconnectionstring);

        assertTrue(result);
    }
}
