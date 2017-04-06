package projects.android.nguyen.kim.pharmacyTechPractice.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * AddPharmacyAbbreviationActivity performs the operations of creating, selecting, inserting,
 * and updating the Abbreviation Database.
 *
 * @author Kim Nguyen
 * @version 3/9/2017
 *          <p>
 *          Modified by Kim Nguyen 3/16/17
 */

class AbbreviationDbOperations extends SQLiteOpenHelper {
    private static final String TAG = "AbbreviationDbOps";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Abbreviation.db";
    private static final String SQL_CREATE_ABBREVIATION_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TableData.AbbreviationInfo.TABLE_NAME + "(" +
            TableData.AbbreviationInfo.COLUMN_SIG_CODE +
            " TEXT," + TableData.AbbreviationInfo.COLUMN_NAME_TRANSLATION +
            " TEXT, " + "PRIMARY KEY (" + TableData.AbbreviationInfo.COLUMN_SIG_CODE + ", " +
            TableData.AbbreviationInfo.COLUMN_NAME_TRANSLATION + "))";

    AbbreviationDbOperations(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate start!");

        sqLiteDatabase.execSQL(SQL_CREATE_ABBREVIATION_TABLE);

        Log.d(TAG, "onCreate end!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Inserts the entry into the abbreviation table
     *
     * @param dbOperations the AbbreviationDbOperations instance
     * @param sigCode      the sig
     * @param meaning      the translation
     */
    void insertEntry(AbbreviationDbOperations dbOperations, String sigCode, String meaning) {
        Log.d(TAG, "insertEntry start!");

        // Gets the data repository in write mode
        SQLiteDatabase db = dbOperations.getWritableDatabase();

        // Creates the map of values
        ContentValues values = new ContentValues();
        values.put(TableData.AbbreviationInfo.COLUMN_SIG_CODE, sigCode);
        values.put(TableData.AbbreviationInfo.COLUMN_NAME_TRANSLATION, meaning);

        db.insert(TableData.AbbreviationInfo.TABLE_NAME, null, values);

        Log.d(TAG, "insertEntry end!");
    }

    /**
     * Selects entries from the abbreviation table
     *
     * @param dbOperations the AbbreviationDbOperations instance
     * @return set of selected entries
     */
    Cursor getEntries(AbbreviationDbOperations dbOperations) {
        Log.d(TAG, "getEntries start!");

        // Gets the data repository in reading mode
        SQLiteDatabase db = dbOperations.getReadableDatabase();

        String[] colums = {TableData.AbbreviationInfo.COLUMN_SIG_CODE,
                TableData.AbbreviationInfo.COLUMN_NAME_TRANSLATION};

        Cursor cursor = db.query(TableData.AbbreviationInfo.TABLE_NAME, colums,
                null, null, null, null, null);

        Log.d(TAG, "getEntries end!");

        return cursor;
    }

    long getNoEntries(AbbreviationDbOperations dbOperations) {
        // Gets the data repository in reading mode
        SQLiteDatabase db = dbOperations.getReadableDatabase();

        return DatabaseUtils.queryNumEntries(db, TableData.AbbreviationInfo.TABLE_NAME);
    }

    /**
     * Selects entries that match the condition
     *
     * @param dbOperations the AbbreviationDbOperations instance
     * @param condition    condition of the select query
     * @return the selected entries
     */
    Cursor getEntry(AbbreviationDbOperations dbOperations, String condition) {
        Log.d(TAG, "getEntry start!");

        // Gets the data repository in reading mode
        SQLiteDatabase db = dbOperations.getReadableDatabase();

        String[] colums = {TableData.AbbreviationInfo.COLUMN_SIG_CODE,
                TableData.AbbreviationInfo.COLUMN_NAME_TRANSLATION};

        Cursor cursor = db.query(TableData.AbbreviationInfo.TABLE_NAME, colums,
                TableData.AbbreviationInfo.COLUMN_NAME_TRANSLATION + " = '" + condition.trim() + "'",
                null, null, null, null);

        cursor.moveToFirst();

        Log.d(TAG, "getEntry end!");

        return cursor;
    }
}
