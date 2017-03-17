package projects.android.nguyen.kim.pharmacyTechPractice.logic;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.Random;

import projects.android.nguyen.kim.pharmacyTechPractice.CommonConstants;

/**
 * TestSigCodeLogic holds the logic of the test sig code screen.
 *
 * @author Kim Nguyen
 * @version 3/15/2017
 *          <p>
 *          Modified by Kim Nguyen 3/16/2017
 */
public class TestSigCodeLogic {
    private AbbreviationDbLogic dbLogic;

    public TestSigCodeLogic(Context context) {
        dbLogic = new AbbreviationDbLogic(context);
    }

    /**
     * Picks a random abbreviation text from the database
     */
    public String generateRandAbbreviation() {
        Log.d("AbbreviationLogic", "generateRandAbbreviation start!");

        long entries = dbLogic.getNoEntries();
        String translationText = "";

        Log.d("AbbreviationLogic", "number of sig rows: " + entries);

        if (entries > 0) {
            final int TRANSLATION_COL_INDEX = 1;

            Random rand = new Random();
            Cursor cursor = dbLogic.getEntries();
            cursor.moveToPosition(rand.nextInt((int) entries));
            translationText = " " + cursor.getString(TRANSLATION_COL_INDEX);
        }

        Log.d("AbbreviationLogic", "generateRandAbbreviation end!");

        return translationText;
    }

    /**
     * Checks whether the sig code input match the translation displayed on the screen
     *
     * @param answer               input from the user
     * @param translationDisplayed translation displayed on the screen
     */
    public boolean checkSigCode(String answer, String translationDisplayed) {
        Cursor cursor = dbLogic.getEntry(translationDisplayed);
        String correctAnswer = cursor.getString(CommonConstants.KEY_COL_INDEX);

        return answer.equalsIgnoreCase(correctAnswer);
    }
}
