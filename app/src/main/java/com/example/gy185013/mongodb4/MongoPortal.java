package com.example.gy185013.mongodb4;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gy185013 on 21/08/2015.
 */
public class MongoPortal extends AsyncTask<Object, Void, String> {

    protected String myresult;

    @Override
    protected String doInBackground(Object... params) {
        TreatmentObject treatmentobj = (TreatmentObject)params[0];
        String dbURL = (String)params[1];
        Context  context = (Context)params[2];
        PopulateTreatmentTable(treatmentobj, dbURL, context);
        return null;
    }


    private void DisplayToastMessage(final Context context, final String message)
    {
        Handler handler =  new Handler(context.getMainLooper());
        handler.post( new Runnable(){
            public void run(){
                Toast.makeText(context, message,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void PopulateTreatmentTable(TreatmentObject treatmentobj, String dbURI, Context  context) {

        //String dbURI = "mongodb://<username>:<password>@<server>:<port>/<database>";

        String databasename = GetDatabaseName(dbURI);

        MongoClient mongoClient = null;
        try
        {
            DisplayToastMessage(context, "Care Portal Record Submitted");

          //  Toast.makeText(context, "Care Portal Record Submitted", Toast.LENGTH_LONG).show();

            MongoClientURI uri = new MongoClientURI(dbURI);
            mongoClient = new MongoClient(uri);

            DB db = mongoClient.getDB(databasename);
            //  DBCollection school = db.createCollection("test", new BasicDBObject() );

            DBCollection treatments = db.getCollection("treatments");
            treatments.insert(treatmentobj.GetMongoDBOject());
            if(mongoClient != null)
            {
                 mongoClient.close();
            }
        }
        catch (Exception e) {
            if(mongoClient != null)
            {
                mongoClient.close();
            }

            DisplayToastMessage(context, "ERROR: uploading to care portal.\nPlease check connection string");
            e.printStackTrace();
        }
    }

    public String GetDatabaseName(String connectionstring)
    {
        int last_slash_index = connectionstring.lastIndexOf('/');
        String result = "";
        if(last_slash_index != -1)
        {
            result = connectionstring.substring(last_slash_index + 1, connectionstring.length());
        }

        return result;
    }


    public static Boolean ValidMongoDBConnectionString(String Connectionstring)
    {
        Boolean res = true;
        // "mongodb://<username>:<password>@<server>:<port>/<database>";
        String pattern = "mongodb:\\/\\/[a-zA-Z0-9]+:[a-zA-Z0-9]+@[a-zA-Z0-9.]+:[0-9]+\\/[a-zA-Z0-9]+";

        if(Connectionstring.matches(pattern))
            res = true;

        Connectionstring = Connectionstring.toLowerCase();
       // String defaultconnectionstring = Resources.getSystem().getString(R.string.mongodbConnectionstringdefault);
      //  ~String default = getApplicationContext().
 //       String defaultconnectionstring = "@string/mongodbConnectionstringdefault";
        //if(defaultconnectionstring.contains(Connectionstring)) {
        //    res = false;

        //}


//"mongodb://<username>:<password?@<server>:<port>/<database>";

    //    Connectionstring = Connectionstring.toLowerCase();

    //    String pattern = "\bmongodb\b://.:.@.:./.";

   //     res = Connectionstring.matches(pattern);


        return res;

    }

    @Override
    protected void onPostExecute(String result) {
        //do stuff
        MainActivity.dataFromAsyncTask = myresult;
    }
}
