package starwars.coding.com.ParkLah.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import starwars.coding.com.ParkLah.Control.CoordManager.SVY21Coordinate;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;
import starwars.coding.com.ParkLah.Entity.Review;
import starwars.coding.com.ParkLah.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager extends SQLiteOpenHelper implements DataBase {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ParkLah.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_CARPARK = "carpark";
    private static final String TABLE_REVIEW = "review";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    // Carpark Table Columns names
    private static final String COLUMN_CID = "id";
    private static final String COLUMN_UID = "carpark_number";
    private static final String COLUMN_X_COORDS = "x_coords";
    private static final String COLUMN_Y_COORDS = "y_coords";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CARPARK_TYPE = "carpar_type";
    private static final String COLUMN_FREE_PARKING = "free_parking";
    private static final String COLUMN_NIGHT_PARKING = "night_parking";
    private static final String COLUMN_PARKING_SYSTEM_TYPE = "parking_system_type";
    private static final String COLUMN_GANTRY_HEIGHT = "gantry_heigh_metres";


    //Review Table Columns names
    private static final String COLUMN_REVIEW_ID = "id";
    private static final String COLUMN_REVIW_USER_NAME_FK = "review_user_name";
    private static final String COLUMN_REVIW_CARPARK_ID_FK = "review_carpark_id";
    private static final String COLUMN_REVIEW_TEXT = "text";
    private static final String COLUMN_REVIEW_RATING = "rating";


    //Singleton Pattern
    private static DataBaseManager aInstance;

    public static synchronized DataBaseManager getInstance(Context context){
        if(aInstance == null){
            aInstance = new DataBaseManager(context.getApplicationContext());
        }
        return aInstance;
    }

    private DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL query: create user table
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

        //SQL query: create carpark table
        String CREATE_CARPARK_TABLE = "CREATE TABLE " + TABLE_CARPARK + "("
                + COLUMN_CID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_UID + " TEXT,"
                + COLUMN_X_COORDS + " REAL,"
                + COLUMN_Y_COORDS + " REAL,"
                + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_CARPARK_TYPE + " TEXT,"
                + COLUMN_FREE_PARKING + " TEXT,"
                + COLUMN_NIGHT_PARKING + " TEXT,"
                + COLUMN_PARKING_SYSTEM_TYPE + " TEXT,"
                + COLUMN_GANTRY_HEIGHT + " TEXT "
//            + COLUMN_TOTAL_LOTS + " INTEGER,"
//            + COLUMN_AVAILABLE_LOTS + " INTEGER"
                + ")";
        db.execSQL(CREATE_CARPARK_TABLE);

        String CREATE_REVIEW_TABLE = "CREATE TABLE " + TABLE_REVIEW + "("
                + COLUMN_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_REVIW_USER_NAME_FK + " INTEGER REFERENCES " + TABLE_USER + ","
                + COLUMN_REVIW_CARPARK_ID_FK + " INTEGER REFERENCES " + TABLE_CARPARK + ","
                + COLUMN_REVIEW_TEXT + " TEXT,"
                + COLUMN_REVIEW_RATING + " REAL" + ")";
        db.execSQL(CREATE_REVIEW_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        // drop table sql query
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
        db.execSQL(DROP_USER_TABLE);
        // drop table sql query
        String DROP_CARPARK_TABLE = "DROP TABLE IF EXISTS " + TABLE_CARPARK;
        db.execSQL(DROP_CARPARK_TABLE);
        // drop table sql query
        String DROP_REVIEW_TABLE = "DROP TABLE IF EXISTS " + TABLE_REVIEW;
        db.execSQL(DROP_REVIEW_TABLE);

        // Create tables again
        onCreate(db);
    }

    @Override
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    @Override
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    @Override
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    @Override
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    public void addCarparkInfo(CarparkInfoRecord record) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(COLUMN_X_COORDS, record.getXCoord());
        values.put(COLUMN_Y_COORDS, record.getYCoord());
        values.put(COLUMN_UID, record.getCarParkNo());
        values.put(COLUMN_ADDRESS, record.getAddress());
        values.put(COLUMN_CARPARK_TYPE, record.getCarParkType());
        values.put(COLUMN_FREE_PARKING, record.getFreeParking());
        values.put(COLUMN_NIGHT_PARKING, record.getNightParking());
        values.put(COLUMN_PARKING_SYSTEM_TYPE, record.getTypeOfParkingSystem());
        values.put(COLUMN_GANTRY_HEIGHT, record.getGantryHeight());
//        values.put(COLUMN_TOTAL_LOTS, record.getTotalLots());
//        values.put(COLUMN_AVAILABLE_LOTS, record.getLotsAvailable());

        // Inserting Row
        db.insert(TABLE_CARPARK, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<CarparkInfoRecord> getCarparkByCoord(SVY21Coordinate bottomLeft, SVY21Coordinate upperRight){

        List<CarparkInfoRecord> results = new ArrayList<>();

        /*
         * SELECT *
         * FROM carpark AS C
         * WHERE C.COLUMN_X_COORDS > bottemLeft.X
         * AND C.COLUMN_X_COORDS < upperRight.X
         * AND C.COLUMN_Y_ COORDS > bottemLeft.Y
         * AND C.COLUMN_Y_COORDS < upperRight.Y
         */
        String QUERY = String.format("" +
                "SELECT * " +
                "FROM %s " +
                "WHERE %s.%s > %s " +
                "AND %s.%s < %s " +
                "AND %s.%s > %s " +
                "AND %s.%s < %s ",
                TABLE_CARPARK,
                TABLE_CARPARK, COLUMN_X_COORDS, Double.toString(bottomLeft.getEasting()),
                TABLE_CARPARK, COLUMN_X_COORDS, Double.toString(upperRight.getEasting()),
                TABLE_CARPARK, COLUMN_Y_COORDS, Double.toString(bottomLeft.getNorthing()),
                TABLE_CARPARK, COLUMN_Y_COORDS, Double.toString(upperRight.getNorthing()));

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        int count = 0;
        try{
            if(cursor.moveToFirst()){
                do{
                    Log.e("debug", "found record " + count);
                    count++;
                    CarparkInfoRecord record = new CarparkInfoRecord();
                    record.setCarParkNo(cursor.getString(cursor.getColumnIndex(COLUMN_UID)));
                    record.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                    record.setXCoord(cursor.getString(cursor.getColumnIndex(COLUMN_X_COORDS)));
                    record.setYCoord(cursor.getString(cursor.getColumnIndex(COLUMN_Y_COORDS)));
                    record.setCarParkType(cursor.getString(cursor.getColumnIndex(COLUMN_CARPARK_TYPE)));
                    record.setFreeParking(cursor.getString(cursor.getColumnIndex(COLUMN_FREE_PARKING)));
                    record.setNightParking(cursor.getString(cursor.getColumnIndex(COLUMN_NIGHT_PARKING)));
                    record.setTypeOfParkingSystem(cursor.getString(cursor.getColumnIndex(COLUMN_PARKING_SYSTEM_TYPE)));
                    record.setGantryHeight(cursor.getString(cursor.getColumnIndex(COLUMN_GANTRY_HEIGHT)));
                    results.add(record);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!= null && !cursor.isClosed()){
                cursor.close();
            }
        }
        Log.e("debug", "The results we fetched are: " + results.size());

        return results;
    }

    public void addReview(Review review){
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();

        ContentValues values = new ContentValues();
        values.put(COLUMN_REVIW_USER_NAME_FK, review.getUserName());
        values.put(COLUMN_REVIW_CARPARK_ID_FK, review.getCarparkID());
        values.put(COLUMN_REVIEW_TEXT, review.getText());
        values.put(COLUMN_REVIEW_RATING, review.getRating());

        // Inserting Row
        db.insert(TABLE_REVIEW, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }


    public List<Review> getReviewsOfCarpark(String carpark_number){

        List<Review> results = new ArrayList<>();

        String QUERY = String.format("" +
                "SELECT * " +
                "FROM %s" +
                "WHERE %s.%s LIKE '%s'",
                TABLE_REVIEW,
                TABLE_REVIEW, COLUMN_REVIW_CARPARK_ID_FK, carpark_number);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(QUERY, null);

        try{
            if(cursor.moveToFirst()){
                do{
                    Review review = new Review();
                    review.setCarparkID(cursor.getString(cursor.getColumnIndex(COLUMN_REVIW_CARPARK_ID_FK)));
                    review.setUserName(cursor.getString(cursor.getColumnIndex(COLUMN_REVIW_USER_NAME_FK)));
                    review.setRating(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_REVIEW_RATING))));
                    review.setText(cursor.getString(cursor.getColumnIndex(COLUMN_REVIEW_TEXT)));
                    results.add(review);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!= null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return results;
    }

        public void deleteAllEntries(){
            SQLiteDatabase db = this.getReadableDatabase();
            String DELETE_ALL_RECORDS = "DELETE FROM " + TABLE_CARPARK;
            db.execSQL(DELETE_ALL_RECORDS);
        }
    }
