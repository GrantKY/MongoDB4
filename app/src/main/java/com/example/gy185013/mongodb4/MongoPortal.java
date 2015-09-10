package com.example.gy185013.mongodb4;
import android.os.AsyncTask;
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

/**
 * Created by gy185013 on 21/08/2015.
 */
public class MongoPortal extends AsyncTask<Object, Void, String> {

    protected String myresult;

    @Override
    protected String doInBackground(Object... params) {
        TreatmentObject treatmentobj = (TreatmentObject)params[0];
        PopulateTreatmentTable(treatmentobj);
        return null;
    }

    public void PopulateTreatmentTable(TreatmentObject treatmentobj) {

        String dbURI = "mongodb://<username>:<password?@<server>:<port>/<database>";

        String databasename = GetDatabaseName(dbURI);

        MongoClient mongoClient = null;
        try
        {
            MongoClientURI uri = new MongoClientURI(dbURI);
            mongoClient = new MongoClient(uri);

            DB db = mongoClient.getDB(databasename);
            //  DBCollection school = db.createCollection("test", new BasicDBObject() );

            DBCollection treatments = db.getCollection("treatments");
            treatments.insert(treatmentobj.GetMongoDBOject());
            if(mongoClient != null)
            {
               // Toast.makeText(getApplicationContext(), "Date or Time have not been set", Toast.LENGTH_LONG).show();
                mongoClient.close();
            }
        }
        catch (Exception e) {
            if(mongoClient != null)
            {
                mongoClient.close();
            }

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


    @Override
    protected void onPostExecute(String result) {
        //do stuff
        MainActivity.dataFromAsyncTask = myresult;
    }
}
