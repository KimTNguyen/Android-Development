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
 */

public class QuizLogic {
    private DrugDbLogic logic;
    private final int NO_GENERIC_ON_SCREEN = 5;
    private final int GENERIC_COL_INDEX = 1;
    private final int FUNCTION_COL_INDEX = 2;
    private final int DIRECTION_COL_INDEX = 3;

    private Map<String, String> functionAndUsage = new HashMap<>();
    private Map<String, String> generatedDrugs = new HashMap<>();
    private int randNum;

    public QuizLogic(Context context) {
        logic = new DrugDbLogic(context);
        generateListDrugs();
    }

    /**
     * Generates a list of random generic and a random brand name to appear on the screen
     */
    public void generateListDrugs() {
        Log.d("QuizActivity", "generateListDrugs start!");

        Cursor cursor = logic.getEntries();
        long records = logic.getNoRecords();

        if (records > 0) {
            /* Generate a list of random brand names and its generic */
            if (records <= NO_GENERIC_ON_SCREEN) {
                while (cursor.moveToNext()) {
                    generatedDrugs.put(cursor.getString(CommonConstants.KEY_COL_INDEX), cursor.getString(GENERIC_COL_INDEX));
                }
                Log.d("generatedDrugs", generatedDrugs.toString());
            } else {
                generatedDrugs.clear();
                while (generatedDrugs.size() != NO_GENERIC_ON_SCREEN) {
                    // unsafe casting
                    randNum = generateRandNumber((int) records);
                    Log.d("QuizActivity", "randNum: " + randNum);
                    cursor.moveToPosition(randNum);
                    generatedDrugs.put(cursor.getString(CommonConstants.KEY_COL_INDEX), cursor.getString(GENERIC_COL_INDEX));
                    functionAndUsage.put(cursor.getString(CommonConstants.KEY_COL_INDEX), cursor.getString(FUNCTION_COL_INDEX) +
                            "\n" + cursor.getString(DIRECTION_COL_INDEX));
                }
            }
        }

        Log.d("QuizActivity", "generateListDrugs end!");
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

    /**
     * Gets the map of generated brand, its function and usage
     *
     * @return the map of brand and its function and usage
     */
    public Map<String, String> getFunctionAndUsage() { return functionAndUsage; }


    /**
     * Gets the map of generated brand and its generic
     *
     * @return the map of generated brand and its generic
     */
    public Map<String, String> getGeneratedDrugs() { return generatedDrugs; }

    /**
     * Generates a random brand name
     *
     * @return the brand name
     */
    public String generateBrandName() {
        randNum = generateRandNumber(generatedDrugs.size());
        return (String) generatedDrugs.keySet().toArray()[randNum];
    }
}
