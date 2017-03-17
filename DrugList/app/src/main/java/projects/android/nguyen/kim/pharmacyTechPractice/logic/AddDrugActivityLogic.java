package projects.android.nguyen.kim.pharmacyTechPractice.logic;

import android.content.Context;
import android.util.Log;

import projects.android.nguyen.kim.pharmacyTechPractice.Utils;
import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugModel;

/**
 * AddDrugActivityLogic holds the logic of the add drug screen.
 *
 * @author Kim Nguyen
 * @version 3/16/2017
 */

public class AddDrugActivityLogic {
    private static final String TAG = "AddDrugActivity";
    private DrugDbLogic logic;

    public AddDrugActivityLogic(Context context) {
        logic = new DrugDbLogic(context);
    }

    /**
     * Saves user input data into the database
     *
     * @param brand     the brand from user's input
     * @param generic   the generic from user's input
     * @param function  the function from user's input
     * @param direction the direction from user'd input
     * @param drugModel the DrugModel instance
     */
    public void saveData(String brand, String generic, String function,
                         String direction, DrugModel drugModel) {
        Log.d(TAG, "saveData start!");

        drugModel.setBrand(brand);
        drugModel.setGeneric(generic);
        drugModel.setFunction(Utils.isEmpty(function) ? "to be updated" : function);
        drugModel.setDirection(Utils.isEmpty(direction) ? "to be updated" : direction);

        logic.insertEntry(drugModel);
        Log.d(TAG, "No entries: " + logic.getNoRecords());

        Log.d(TAG, "saveData end!");
    }
}
