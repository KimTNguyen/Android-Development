package projects.android.nguyen.kim.pharmacyTechPractice.logic;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import projects.android.nguyen.kim.pharmacyTechPractice.CommonConstants;

/**
 * QuizLogic holds the logic of the quiz screen.
 *
 * @author Kim Nguyen
 * @version 3/16/2017
 *
 * Modified by Kim Nguyen on 04/09/2017
 * Modified by Kim Nguyen on 05/09/2017
 */

public class QuizLogic {
    private static final String TAG = "QuizLogic";
    private static final int NO_GENERIC_ON_SCREEN = 5;

    private DrugDbLogic logic;

    private Map<String, String> doseForms = new HashMap<>();
    private Map<String, String> scheduled = new HashMap<>();
    private Map<String, String> comments = new HashMap<>();
    private Map<String, String> function = new HashMap<>();
    private Map<String, String> sideEffects = new HashMap<>();
    private Map<String, String> generatedDrugs = new HashMap<>();
    private int randNum;

    public QuizLogic(Context context) {
        logic = new DrugDbLogic(context);
    }

    /**
     * Generates a list of random generic and a random brand name to appear on the screen
     */
    public void generateListDrugs() {
        Log.d(TAG, "generateListDrugs start!");

        Cursor cursor = logic.getEntries();
        long records = logic.getNoRecords();

        if (records > 0) {
            /* Generate a list of random brand names and its generic */
            if (records <= NO_GENERIC_ON_SCREEN) {
                while (cursor.moveToNext()) {
                    setInfo(cursor);
                }
                Log.d("generatedDrugs", generatedDrugs.toString());
            } else {
                generatedDrugs.clear();
                while (generatedDrugs.size() != NO_GENERIC_ON_SCREEN) {
                    // unsafe casting
                    randNum = generateRandNumber((int) records);
                    Log.d(TAG, "randNum: " + randNum);
                    cursor.moveToPosition(randNum);
                    setInfo(cursor);
                }
            }
        }

        Log.d(TAG, "generateListDrugs end!");
    }

    /**
     * Generates a random number within the number of records
     *
     * @return the random number
     */
    private int generateRandNumber(int range) {
        Random randNo = new Random();
        return randNo.nextInt(range);
    }

    private void setInfo(Cursor cursor) {
        generatedDrugs.put(cursor.getString(CommonConstants.KEY_COL_INDEX),
                cursor.getString(CommonConstants.GENERIC_COL_INDEX));
        doseForms.put(cursor.getString(CommonConstants.KEY_COL_INDEX),
                cursor.getString(CommonConstants.DOSE_FORMS_COL_INDEX));
        scheduled.put(cursor.getString(CommonConstants.KEY_COL_INDEX),
                cursor.getString(CommonConstants.SCHEDULED_COL_INDEX));
        comments.put(cursor.getString(CommonConstants.KEY_COL_INDEX),
                cursor.getString(CommonConstants.COMMENTS_COL_INDEX));
        function.put(cursor.getString(CommonConstants.KEY_COL_INDEX),
                cursor.getString(CommonConstants.FUNCTION_COL_INDEX));
        sideEffects.put(cursor.getString(CommonConstants.KEY_COL_INDEX),
                cursor.getString(CommonConstants.SIDE_EFFECTS_COL_INDEX));
    }

    /**
     * Gets the map of generated brand and its generic
     *
     * @return the pair generated brand and its generic
     */
    public Map<String, String> getGeneratedDrugs() {
        return generatedDrugs;
    }

    /**
     * Generates a random brand name
     *
     * @return the brand name
     */
    public String generateBrandName() {
        randNum = generateRandNumber(generatedDrugs.size());
        return (String) generatedDrugs.keySet().toArray()[randNum];
    }

    /**
     * Gets the map of generated brand and its dose forms
     *
     * @return the pair generated brand and its dose forms
     */
    public Map<String, String> getDoseForms() {
        return doseForms;
    }

    /**
     * Gets the map of generated brand and its scheduled
     *
     * @return the pair generated brand and its scheduled
     */
    public Map<String, String> getScheduled() {
        return scheduled;
    }

    /**
     * Gets the map of generated brand and its comments
     *
     * @return the pair generated brand and its comments
     */
    public Map<String, String> getComments() {
        return comments;
    }

    /**
     * Gets the map of generated brand and its function
     *
     * @return the pair generated brand and its function
     */
    public Map<String, String> getFunction() {
        return function;
    }

    /**
     * Gets the map of generated brand and its side effects
     *
     * @return the pair generated brand and its side effects
     */
    public Map<String, String> getSideEffects() {
        return sideEffects;
    }
}
