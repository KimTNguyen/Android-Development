package projects.android.nguyen.kim.pharmacyTechPractice.logic;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugModel;

/**
 * DrugRelatedLogic holds the logic of anything related to medication operations.
 *
 * @author Kim Nguyen
 * @version 3/15/2017.
 */

public class DrugRelatedLogic extends AppCompatActivity {
    private DrugDbOperations dbOperations;

    public DrugRelatedLogic(Context context) {
        dbOperations = new DrugDbOperations(context);
    }

    /**
     * Inserts the entry into the drug table
     */
    public void insertEntry(DrugModel model) {
        Log.d("DrugRelatedLogic", "insertEntry start!");

        dbOperations.insertEntry(dbOperations, model.getBrand(), model.getGeneric(),
                model.getFunction(), model.getDirection());

        Log.d("DrugRelatedLogic", "insertEntry end!");
    }

    /**
     * Retrieves number of records in the database
     *
     * @return number of records
     */
    public long getNoRecords() {
        return dbOperations.getNoRecords(dbOperations);
    }

    /**
     * Selects entries from the drug table
     *
     * @return set of selected entries
     */
    public Cursor getEntries() {
        return dbOperations.getEntries(dbOperations);
    }
}