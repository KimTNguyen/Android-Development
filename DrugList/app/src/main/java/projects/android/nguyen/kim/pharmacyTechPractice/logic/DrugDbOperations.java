package projects.android.nguyen.kim.pharmacyTechPractice.logic;

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
 * @author Kim Nguyen
 * @version 3/8/2017
 *          <p>
 *          Modified by Kim Nguyen on 03/16/17
 *          Modified by Kim Nguyen on 04/09/2017
 */

class DrugDbOperations extends SQLiteOpenHelper {
    private static final String TAG = "DrugDbOperations";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Drug.db";
    private static final String SQL_CREATE_DRUG_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TableData.DrugInfo.TABLE_NAME + " (" + TableData.DrugInfo._ID +
            " INTEGER PRIMARY KEY," + TableData.DrugInfo.COLUMN_NAME_BRAND + " TEXT NOT NULL," +
            TableData.DrugInfo.COLUMN_NAME_GENERIC + " TEXT NOT NULL," +
            TableData.DrugInfo.COLUMN_NAME_SCHEDULED + " TEXT NOT NULL," +
            TableData.DrugInfo.COLUMN_NAME_DOSE_FORMS + " TEXT NOT NULL," +
            TableData.DrugInfo.COLUMN_NAME_FUNCTION + " TEXT," +
            TableData.DrugInfo.COLUMN_NAME_SIDE_EFFECTS + " TEXT," +
            TableData.DrugInfo.COLUMN_NAME_COMMENTS + " TEXT)";

    DrugDbOperations(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate start!");
        sqLiteDatabase.execSQL(SQL_CREATE_DRUG_TABLE);
        Log.d(TAG, "onCreate end!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Inserts the entry into the drug table
     *
     * @param dbOperations the DrugDbOperations instance
     * @param brand        brand name
     * @param generic      generic name
     * @param doseForms    route to take the medication
     * @param function     the use of the medication
     * @param comments     comments on the medication
     */
    void insertEntry(DrugDbOperations dbOperations, String brand, String generic, String scheduled,
                     String doseForms, String function, String sideEffects, String comments) {
        Log.d(TAG, "insertEntry start!");

        // Gets the data repository in write mode
        SQLiteDatabase db = dbOperations.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TableData.DrugInfo.COLUMN_NAME_BRAND, brand);
        values.put(TableData.DrugInfo.COLUMN_NAME_GENERIC, generic);
        values.put(TableData.DrugInfo.COLUMN_NAME_SCHEDULED, scheduled);
        values.put(TableData.DrugInfo.COLUMN_NAME_DOSE_FORMS, doseForms);
        values.put(TableData.DrugInfo.COLUMN_NAME_FUNCTION, function);
        values.put(TableData.DrugInfo.COLUMN_NAME_SIDE_EFFECTS, sideEffects);
        values.put(TableData.DrugInfo.COLUMN_NAME_COMMENTS, comments);

        // Insert the new row, returning the primary key value of the new row
        db.insert(TableData.DrugInfo.TABLE_NAME, null, values);

        Log.d(TAG, "insertEntry end!");
    }

    /**
     * Selects entries from the drug table
     *
     * @param dbOperations the DrugDbOperations instance
     * @return set of selected entries
     */
    Cursor getEntries(DrugDbOperations dbOperations) {
        Log.d(TAG, "getEntry start!");

        // Gets the data repository in read mode
        SQLiteDatabase db = dbOperations.getReadableDatabase();

        String[] columns = {TableData.DrugInfo.COLUMN_NAME_BRAND,
                TableData.DrugInfo.COLUMN_NAME_GENERIC, TableData.DrugInfo.COLUMN_NAME_SCHEDULED,
                TableData.DrugInfo.COLUMN_NAME_DOSE_FORMS, TableData.DrugInfo.COLUMN_NAME_FUNCTION,
                TableData.DrugInfo.COLUMN_NAME_SIDE_EFFECTS, TableData.DrugInfo.COLUMN_NAME_COMMENTS};

        Cursor cursor = db.query(TableData.DrugInfo.TABLE_NAME, columns,
                null, null, null, null, null);

        Log.d(TAG, "getEntry end!");

        return cursor;
    }

    /**
     * Retrieves number of records in the database
     *
     * @param dbOperations the DrugDbOperations instant
     * @return number of records
     */
    long getNoRecords(DrugDbOperations dbOperations) {
        // Gets the data repository in read mode
        SQLiteDatabase db = dbOperations.getReadableDatabase();

        return DatabaseUtils.queryNumEntries(db, TableData.DrugInfo.TABLE_NAME);
    }
}
