package projects.android.nguyen.kim.pharmacyTechPractice.logic;

import android.content.Context;
import android.util.Log;

import projects.android.nguyen.kim.pharmacyTechPractice.model.PharmacyAbbreviationModel;

/**
 * AddPharmacyAbbreviationLogic holds the logic of the add pharmacy screen.
 *
 * @author Kim Nguyen
 * @version 3/16/2017
 */

public class AddPharmacyAbbreviationLogic {
    private static final String TAG = "AddAbbreviationLogic";
    private AbbreviationDbLogic dbLogic;

    public AddPharmacyAbbreviationLogic(Context context) {
        dbLogic = new AbbreviationDbLogic(context);
    }

    /**
     * Saves user's input into the database
     *
     * @param sigCode the abbreviation
     * @param meaning the translation
     * @param model   the PharmacyAbbreviationModel instance
     */
    public void saveData(String sigCode, String meaning, PharmacyAbbreviationModel model) {
        Log.d(TAG, "saveSigCode start!");

        model.setSigCode(sigCode);
        model.setMeaning(meaning);

        dbLogic.insertEntry(model);
        Log.d(TAG, "No entries: " + dbLogic.getNoEntries());

        Log.d(TAG, "saveSigCode end!");
    }
}
