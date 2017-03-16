package projects.android.nguyen.kim.pharmacyTechPractice.model;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * PharmacyAbbreviationLogic holds the logic of pharmacy abbreviation operations.
 *
 * @author Kim Nguyen
 * @version 3/15/2017.
 */

public class PharmacyAbbreviationLogic extends AppCompatActivity {
    private AbbreviationDbOperations dbOperations;

    public PharmacyAbbreviationLogic(Context context) {
        dbOperations = new AbbreviationDbOperations(context);
    }

    /**
     * Inserts the entry into the abbreviation db
     *
     * @param model the instance of PharmacyAbbreviationModel
     */
    public void insertEntry(PharmacyAbbreviationModel model) {
        Log.d("AbbreviationLogic","insertEntry start!");

        dbOperations.insertEntry(dbOperations, model.getSigCode(), model.getMeaning());

        Log.d("AbbreviationLogic","insertEntry end!");
    }

    /**
     * Queries number of records in the abbreviation table
     *
     * @return number of entries in the abbreviation table
     */
    public long getNoEntries() {
        return dbOperations.getNoEntries(dbOperations);
    }

    /**
     * Selects entries from the abbreviation table
     *
     * @return set of selected entries
     */
    public Cursor getEntries() {
        return dbOperations.getEntries(dbOperations);
    }
}
