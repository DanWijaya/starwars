package starwars.coding.com.ParkLah.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;

public class CarparkSqlManager extends SQLiteOpenHelper implements CarparkDB {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CarparkManager.db";
    private static final String TABLE_CARPARK = "carpark";
    private static final String COLUMN_CID = "id";
    private static final String COLUMN_UID = "uid";
    private static final String COLUMN_X_COORDS = "x_coords";
    private static final String COLUMN_Y_COORDS = "y_coords";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_TOTAL_LOTS = "total";
    private static final String COLUMN_AVAILABLE_LOTS = "available";

    private static final String DELETE_ALL_RECORDS = "DELETE FROM " + TABLE_CARPARK;
    // create table sql query
    private String CREATE_CARPARK_TABLE = "CREATE TABLE " + TABLE_CARPARK + "("
            + COLUMN_CID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_UID + " TEXT,"
            + COLUMN_X_COORDS + " REAL," + COLUMN_Y_COORDS + " REAL," + COLUMN_ADDRESS + " REAL," + COLUMN_TOTAL_LOTS + " INTEGER," + COLUMN_AVAILABLE_LOTS + " INTEGER" + ")";

    // drop table sql query
    private String DROP_CARPARK_TABLE = "DROP TABLE IF EXISTS " + TABLE_CARPARK;

    /**
     * Constructor
     *
     * @param context
     */
    public CarparkSqlManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CARPARK_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_CARPARK_TABLE);
        onCreate(db);
    }

    @Override
    public void deleteAllEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(DELETE_ALL_RECORDS);
    }

    @Override
    public void addCarparkInfo(CarparkInfoRecord record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_X_COORDS, record.getXCoord());
        values.put(COLUMN_Y_COORDS, record.getYCoord());
        values.put(COLUMN_UID, record.getCarParkNo());
        values.put(COLUMN_ADDRESS, record.getAddress());
        db.insert(TABLE_CARPARK, null, values);
        db.close();
    }
}
