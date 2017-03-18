package test.ktrips;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Ahmed Hmouz.
 */

public class DBHandler extends SQLiteOpenHelper{ //DB Handler extended from SQLiteOpenHelper

    private static final int DB_VERSION = 1; //Declare private static integer to store database version.
    private static final String DB_NAME = "TravelApp.db"; //Declare private static String to store database name.

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// TABLES

    private static final String USER_TABLE = "User"; //Declare private static String to store User table name.
    private static final String TRIP_TABLE = "Trips"; //Declare private static String to store shopping trip table name.
    private static final String SESSION_TABLE = "Session"; //Declare private static String to store user session table name.
    private static final String CODE_TABLE = "Code"; //Declare private static String to store code table name.

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// User Table Columns

    private static final String USER_ID = "UserID"; //Declare private static String to store User id column name.
    public static final String USER_NAME = "UserName"; //Declare private static String to store UserName column name.
    public static final String USER_EMAIL = "Email"; //Declare private static String to store User Email column name.
    public static final String USER_PASSWORD = "Password"; //Declare private static String to store User Password column name.
    private static final String USER_COUNTRY = "Country"; //Declare private static String to store user country column name.
    private static final String CREATION_DATE = "DateOfCreation"; //Declare private static String to store account creation date column name.
    private static final String BIRTH_DATE = "BirthDate"; //Declare private static String to store account Birth Date column name.

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// Card Table Columns

    private static final String TRIP_ID = "TripID"; //Declare private static String to store Trip id column name.
    public static final String TRIP_NAME = "TripName"; //Declare private static String to store TripName column name.
    public static final String TRIP_LOCATION = "Location"; //Declare private static String to store Location column name.
    public static final String START_DATE = "Start"; //Declare private static String to store Start date column name.
    public static final String END_DATE = "End"; //Declare private static String to store End date column name.
    public static final String RATING = "Rating"; //Declare private static String to store trip rating column name.

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// Session Table Columns
    private static final String USER_STATUS = "Status"; //Declare private static String to store user status column name.
    private static final String LOGIN_TIME = "LoginTime"; //Declare private static String to store the login time column name.

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// Code Table Columns
    private static final String CODE_ID = "CodeID"; //Declare private static String to store CodeID column name.
    private static final String CODE_NUMBER = "CodeNumber"; //Declare private static String to store the login time column name.




    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) { //Constructor setup Database and version.
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //onCreate method


        String SQLquery1 = "CREATE TABLE " + USER_TABLE + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + USER_NAME + " TEXT, " + USER_EMAIL + " TEXT, " + USER_PASSWORD + " TEXT, " + USER_COUNTRY + " TEXT, " + CREATION_DATE + " TEXT, " + BIRTH_DATE + " TEXT)"; //First SQL query that creates the user table and its columns.
        String SQLquery2 = "CREATE TABLE " + TRIP_TABLE + " (" + TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + TRIP_NAME + " TEXT, " + TRIP_LOCATION + " TEXT, " + START_DATE + " TEXT, " + END_DATE + " TEXT, " + RATING + " INTEGER)"; //Second SQL query that creates the trip table and its columns.
        String SQLquery3 = "CREATE TABLE " + SESSION_TABLE + " (" + USER_ID + " INTEGER, " + USER_STATUS + " INTEGER, " + LOGIN_TIME + " TEXT)"; //Third SQL query that creates the session table and its columns.
        String SQLquery4 = "CREATE TABLE " + CODE_TABLE + " (" + CODE_ID + " INTEGER, " + CODE_NUMBER + " TEXT)"; //Third SQL query that creates the code table and its columns.

        db.execSQL(SQLquery1); //Execute first SQL query.
        db.execSQL(SQLquery2); //Execute second SQL query.
        db.execSQL(SQLquery3); //Execute third SQL query.
        db.execSQL(SQLquery4); //Execute fourth SQL query.

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //onUpgrade method

        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE); //Delete user table is it exists
            db.execSQL("DROP TABLE IF EXISTS " + TRIP_TABLE); //Delete trip table is it exists
            db.execSQL("DROP TABLE IF EXISTS " + SESSION_TABLE); //Delete session table is it exists
            db.execSQL("DROP TABLE IF EXISTS " + CODE_TABLE); //Delete code table is it exists
            onCreate(db); //Run the onCreate mehtod
        }
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //User Methods



    public int CreateUser(User newUser) { //Method that adds new user to user table in database.

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd"); //Create SimpleDate
        String date = simpleDate.format(new Date());  //Format Simple Date as a string


        if(userExist()==false){

            ContentValues UserValues = new ContentValues(); //Create new ContentValue to store set of user related values.

            UserValues.put(USER_NAME, newUser.getUsername()); //Put User Name in ContentValue.
            UserValues.put(USER_EMAIL, newUser.getEmail()); //Put User email in ContentValue.
            UserValues.put(USER_PASSWORD, newUser.getPassword()); //Put User password in ContentValue.
            UserValues.put(USER_COUNTRY, newUser.getCountry()); //Put User country in ContentValue.
            UserValues.put(CREATION_DATE , date); //Put Date in ContentValue.
            UserValues.put(BIRTH_DATE, newUser.getDateOfBirth()); //Put User birth date in ContentValue.*/

            SQLiteDatabase db = getWritableDatabase(); //Declare SQLiteDatabase

            long CID = db.insert(USER_TABLE, null, UserValues); //Insert User values into course table;

            if (CID == -1) { //Check if insertion was unsuccessful.

                return 0; //Return 0 to signal that the query returned false.

            } else {


                int status = 1;


                ContentValues SessionValues = new ContentValues(); //Create new ContentValue to store set of session related values.


                SessionValues.put(USER_ID, CID); //Put User id in ContentValue.
                SessionValues.put(USER_STATUS, status); //Put status in ContentValue.
                SessionValues.put(LOGIN_TIME, date); //Put login date in ContentValue.

                long CID2 = db.insert(SESSION_TABLE, null, SessionValues); //Insert User values into session table;


                if(CID2 == -1){

                    return 0; //Return 0 to signal that the query returned false.


                }else{

                    int codeID = 101;
                    String none = "NA";

                    ContentValues CodeValues = new ContentValues(); //Create new ContentValue to store set of Code related values.


                    CodeValues.put(CODE_ID, codeID); //Put User id in ContentValue.
                    CodeValues.put(CODE_NUMBER, none); //Put status in ContentValue.

                    long CID3 = db.insert(CODE_TABLE, null, CodeValues); //Insert User values into session table;

                    if(CID3 == -1) {

                        return 0; //Return 0 to signal that the query returned false.

                    }else{
                        return 2; //Return 2 to signal success.
                    }


                }


            }

        }else{

            return 1; //Return 1 to signal that user already exists

        }




    }


    public boolean validateUser(String username, String password){ //Method that validates user credentials.

        // String Cquery = "SELECT  * FROM " + USER_TABLE + " WHERE " + USER_NAME + "=\"%" + username + "%\" AND " + USER_PASSWORD +  "=\"%" + password + "%\";"; //SQL query that selects all elements in user table where is email.

       /* String Cquery = "SELECT  * FROM " + USER_TABLE + ";";

        SQLiteDatabase db = getReadableDatabase(); //Declare SQLiteDatabase
        Cursor cursor = db.rawQuery(Cquery, null); //Execute rawQuery Cquery and set result in cursor
        if(cursor.moveToFirst()) {
            if((cursor.getCount())==1){
                if(username.equals(cursor.getString(cursor.getColumnIndex(USER_NAME))){
                    if(password.equals(cursor.getString(cursor.getColumnIndex(USER_PASSWORD))){
                        cursor.close();
                        return true; //Return true is validation was a success.
                    }
                }

            }else{
                cursor.close();
                return false;  //Return false is validation was a failure.
            }

        }
        cursor.close();
        return false;  //Return false is validation was a failure.
*/

        User usr = getUser();

        if(username.equals(usr.getUsername())){
            if(password.equals(usr.getPassword())){
                return true; //Return true is validation was a success.
            }else{
                return false;
            }
        }else{
            return false;
        }


    }


    public boolean userExist() { //Method that checks if user already exists.
        String Cquery = "SELECT  * FROM " + USER_TABLE + ";"; //SQL query that selects all elements in user table where is email.
        SQLiteDatabase db = getReadableDatabase(); //Declare SQLiteDatabase
        Cursor cursor = db.rawQuery(Cquery, null); //Execute rawQuery Cquery and set result in cursor
        if(cursor.moveToFirst()) {
            if ((cursor.getCount()) <= 0) {
                cursor.close();
                return false; //Return false if user does not exist.
            } else {
                cursor.close();
                return true; //Return true if user does exist.
            }
        }
        return false;
    }


/*    public String getUser(int col){ //Method that returns specific user info in a string

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor theUser = db.rawQuery("SELECT * FROM " + USER_TABLE, null);

        if(theUser.moveToFirst()){
            if((col<=6)&&(col>0)){
                do{
                    String data = theUser.getString(col);
                    return data;
                }while(theUser.moveToNext());

            }else{
                return "N/A";
            }


        }

        *//*
        //
        // (1) --> UserName
        // (2) --> Email
        // (3) --> Password
        // (4) --> Country
        // (5) --> Account creation date
        // (6) --> Birth date
        *//*

        return "N/A";

    }*/


    public Cursor getAllUser(){ //Method that returns all user info in a cursor

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor theUser = db.rawQuery("SELECT * FROM " + USER_TABLE, null);
        return theUser;
    }


    public User getUser(){ //Method that returns all user info in a cursor

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor theUser = db.rawQuery("SELECT * FROM " + USER_TABLE, null);

        User usr = new User();

        if(theUser.moveToFirst()){
            String username = theUser.getString(theUser.getColumnIndex(USER_NAME));
            String email = theUser.getString(theUser.getColumnIndex(USER_EMAIL));
            String password = theUser.getString(theUser.getColumnIndex(USER_PASSWORD));
            String country = theUser.getString(theUser.getColumnIndex(USER_COUNTRY));
            String date = theUser.getString(theUser.getColumnIndex(BIRTH_DATE));

            usr.setUsername(username);
            usr.setPassword(password);
            usr.setEmail(email);
            usr.setCountry(country);
            usr.setDateOfBirth(date);


        }

        return usr;
    }


    public Boolean changePasswordTo(String newPassword){ //Public method that changes current old password to specified string.

        ContentValues password = new ContentValues();  //create content values to store session values
        password.put(USER_PASSWORD,newPassword);

        SQLiteDatabase db = getWritableDatabase(); //Declare SQLiteDatabase

        long CID = db.update(USER_TABLE, password, null, null); //UPDATE suser table;

        if (CID == -1) { //Check if insertion was unsuccessful.

            return false; //Return false to signal that the query returned false.

        } else {

            return true; //Return true to signal success.

        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Session Methods


    public boolean openSession() { //Method that creates a session.

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd"); //Create SimpleDate
        String date = simpleDate.format(new Date());  //Format Simple Date as a string
        int status = 1;

        ContentValues session = new ContentValues();  //create content values to store session values
        session.put(USER_STATUS,status);
        session.put(LOGIN_TIME,date);

        SQLiteDatabase db = getWritableDatabase(); //Declare SQLiteDatabase

        long CID = db.update(SESSION_TABLE, session, null, null); //UPDATE session table;

        if (CID == -1) { //Check if insertion was unsuccessful.

            return false; //Return false to signal that the query returned false.

        } else {

            return true; //Return true to signal success.

        }

    }

    public boolean destroySession() { //Method that destroys all sessions.


        int status = 0;

        ContentValues session = new ContentValues();  //create content values to store session values
        session.put(USER_STATUS,status);

        SQLiteDatabase db = getWritableDatabase(); //Declare SQLiteDatabase

        long CID = db.update(SESSION_TABLE, session, null, null); //UPDATE session table;

        if (CID == -1) { //Check if insertion was unsuccessful.

            return false; //Return false to signal that the query returned false.

        } else {

            return true; //Return true to signal success.

        }


    }

    public int getSessionStatus(){ //Method that returns session status

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor theUser = db.rawQuery("SELECT * FROM " + SESSION_TABLE, null);
        if(theUser.moveToFirst()){
            int status = theUser.getInt(1); //Declare and set cursor to id value as integer

            return status;
        }
        return 0;
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //trip Methods


    public long addTrip(Trip newTrip) { //Method that adds new trip to trip table in database and returns the trip id.


        if(getSessionStatus()==1){

            Cursor user = getAllUser();


            if(user.moveToFirst()){

                int id = user.getInt(0); //Declare and set cursor to id value as integer

                ContentValues TripValues = new ContentValues(); //Create new ContentValue to store set of trip related values.

                /*TripValues.put(TRIP_NAME, newTrip.getTripName()); //Put trip Name in ContentValue.
                TripValues.put(TRIP_LOCATION, newTrip.getTripLocation()); //Put trip location in ContentValue.
                TripValues.put(START_DATE, newTrip.getTripStart()); //Put trip start date in ContentValue.
                TripValues.put(END_DATE, newTrip.getTripEnd()); //Put end date in ContentValue.
                TripValues.put(RATING, newTrip.getTripRating()); //Put trip rating in ContentValue.*/


                SQLiteDatabase db = getWritableDatabase(); //Declare SQLiteDatabase

                long CID = db.insert(TRIP_TABLE, null, TripValues); //Insert trip values into course table;

                if(CID == -1){

                    return 0;


                }else{

                    return CID; //Return trip id


                }

            }

        }else{

            return 0; //Return 0 to signal that user must login

        }

        return 0;


    }

    public Cursor getAllTrips(){ //Method that returns all trips in a cursor

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor theTrips = db.rawQuery("SELECT * FROM " + TRIP_TABLE, null);
        return theTrips;
    }

    public Cursor getTrip(int id){ //Method that specific trip in a cursor

        String query = "SELECT  * FROM " + TRIP_TABLE + " WHERE " + TRIP_ID + "=\"" + id + "\";"; //SQL query that selects specific element from trip table where is id.

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor theTrip = db.rawQuery(query, null);
        return theTrip;
    }

    public void removeTrip(int id){ //Method that deletes a specific trip form trip table.

        SQLiteDatabase db = getWritableDatabase(); //Declare SQLiteDatabase

        String query = "DELETE FROM " + TRIP_TABLE + " WHERE " + TRIP_ID + "=\"" + id + "\";"; //SQL query that selects specific element from trip table where is id.

        db.execSQL(query, null); //Execute SQL query and set result in cursor

    }


    //////////////////////////////////////////////////////////////////////////////////////////////////

    //Forgot Password Code methods


    public Boolean setForgotCode() { //Method that sets forgot password code.

        Random r = new Random();
        int intCode = r.nextInt(300 - 50) + 50; //Generate random integer between 50 and 299
        String theCode = String.valueOf(intCode); //Convert integer code to string.

        ContentValues code = new ContentValues();  //create content values to store session values
        code.put(CODE_NUMBER,theCode);

        SQLiteDatabase db = getWritableDatabase(); //Declare SQLiteDatabase

        long CID = db.update(CODE_TABLE, code, null, null); //UPDATE Code table;

        if (CID == -1) { //Check if insertion was unsuccessful.

            return false; //Return false to signal that the query returned false.

        } else {

            return true; //Return true to signal success.

        }


    }

    public String getForgotCode() { //Method that returns forgot password code as a String.

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor theCode = db.rawQuery("SELECT * FROM " + CODE_TABLE, null);
        if(theCode.moveToFirst()){
            String c = theCode.getString(1); //Declare and set cursor to id value as integer

            return c;
        }
        return null;

    }



    //////////////////////////////////////////////////////////////////////////////////////////////////

    //General Delete methods


    public void deleteUser() { //Method that deletes User table

        SQLiteDatabase db = getWritableDatabase(); //Declare SQLiteDatabase


        db.delete(USER_TABLE, null , null);

    }

    public void deleteAllTrips() { //Method that deletes Trip table

        SQLiteDatabase db = getWritableDatabase(); //Declare SQLiteDatabase


        db.delete(TRIP_TABLE, null , null);

    }

    public void deleteAllSessions() { //Method that deletes Session table

        SQLiteDatabase db = getWritableDatabase(); //Declare SQLiteDatabase


        db.delete(SESSION_TABLE, null , null);

    }



}
