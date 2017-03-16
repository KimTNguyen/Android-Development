package projects.android.nguyen.kim.pharmacyTechPractice.logic;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import projects.android.nguyen.kim.pharmacyTechPractice.model.PharmacyAbbreviationModel;

/**
 * AbbreviationDbLogic holds db related operations.
 *
 * @author Kim Nguyen
 * @version 3/16/2017
 *          <p>
 *          Modified by Kim Nguyen 3/16/17
 */

class AbbreviationDbLogic {

    private AbbreviationDbOperations dbOperations;

    AbbreviationDbLogic(Context context) {
        dbOperations = new AbbreviationDbOperations(context);
    }

    /**
     * Inserts the entry into the abbreviation db
     *
     * @param model the instance of PharmacyAbbreviationModel
     */
    void insertEntry(PharmacyAbbreviationModel model) {
        Log.d("AbbreviationLogic", "insertEntry start!");

        dbOperations.insertEntry(dbOperations, model.getSigCode(), model.getMeaning());

        Log.d("AbbreviationLogic", "insertEntry end!");
    }

    /**
     * Queries number of records in the abbreviation table
     *
     * @return number of entries in the abbreviation table
     */
    long getNoEntries() {
        return dbOperations.getNoEntries(dbOperations);
    }

    /**
     * Selects entries from the abbreviation table
     *
     * @return set of selected entries
     */
    Cursor getEntries() {
        return dbOperations.getEntries(dbOperations);
    }

    /**
     * Selects entries that match the condition
     *
     * @param condition condition of the select query
     * @return the selected entries
     */
    Cursor getEntry(String condition) {
        return dbOperations.getEntry(dbOperations, condition);
    }
}
