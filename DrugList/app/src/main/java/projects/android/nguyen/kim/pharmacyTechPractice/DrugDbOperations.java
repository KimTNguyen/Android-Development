package projects.android.nguyen.kim.pharmacyTechPractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * DrugDbOperations performs the operations of creating and updating the Drug Database.
 *
 * Created by Kimmy on 3/8/2017.
 */

public class DrugDbOperations extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Drug.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " +
            TableData.DrugInfo.TABLE_NAME + " (" + TableData.DrugInfo._ID +
            " INTEGER PRIMARY KEY," + TableData.DrugInfo.COLUMN_NAME_BRAND + " TEXT," +
            TableData.DrugInfo.COLUMN_NAME_GENERIC + " TEXT," +
            TableData.DrugInfo.COLUMN_NAME_FUNCTION + " TEXT," +
            TableData.DrugInfo.COLUMN_NAME_DOSE + " TEXT)";

    public DrugDbOperations(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DrugDbOperations","onCreate start!");
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        Log.d("DrugDbOperations","onCreate end!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Inserts drug data into the table
     *
     * @param dbOperations the DrugDbOperations instant
     * @param brand brand name
     * @param generic generic name
     * @param function the use of the medication
     * @param dose dose form of the medication
     */
    public void insertEntry(DrugDbOperations dbOperations, String brand, String generic, String function, String dose) {
        Log.d("DrugDbOperations","insertEntry start!");

        // Gets the data repository in write mode
        SQLiteDatabase db = dbOperations.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TableData.DrugInfo.COLUMN_NAME_BRAND, brand);
        values.put(TableData.DrugInfo.COLUMN_NAME_GENERIC, generic);
        values.put(TableData.DrugInfo.COLUMN_NAME_FUNCTION, function);
        values.put(TableData.DrugInfo.COLUMN_NAME_DOSE, dose);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TableData.DrugInfo.TABLE_NAME,null,values);

        Log.d("DrugDbOperations","insertEntry end!");
    }

    /**
     * Retrieves values from database
     *
     * @param dbOperations the DrugDbOperations instant
     * @return the result set returned by a database query
     */
    public Cursor getEntries(DrugDbOperations dbOperations) {
        Log.d("DrugDbOperations","getEntry start!");

        // Gets the data repository in read mode
        SQLiteDatabase db = dbOperations.getReadableDatabase();

        String[] columns = {TableData.DrugInfo.COLUMN_NAME_BRAND,
                TableData.DrugInfo.COLUMN_NAME_GENERIC, TableData.DrugInfo.COLUMN_NAME_FUNCTION,
                TableData.DrugInfo.COLUMN_NAME_DOSE};

        Cursor cursor = db.query(TableData.DrugInfo.TABLE_NAME, columns, null, null, null, null, null);

        Log.d("DrugDbOperations","getEntry end!");

        return cursor;
    }

    /**
     * Retrieves number of records in the database
     *
     * @param dbOperations the DrugDbOperations instant
     *
     * @return number of records
     */
    public long getNoRecords(DrugDbOperations dbOperations) {
        // Gets the data repository in read mode
        SQLiteDatabase db = dbOperations.getReadableDatabase();

        return DatabaseUtils.queryNumEntries(db, TableData.DrugInfo.TABLE_NAME);
    }
}
