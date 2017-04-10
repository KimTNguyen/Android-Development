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
 *
 * Modified by Kim Nguyen on 04/09/2017
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
     * @param scheduled the scheduled from user's input
     * @param doseForm the doseForm from the user's input
     * @param function  the function from user's input
     * @param sideEffects the side effects from user'd input
     * @param comments the comments from the user's input
     * @param drugModel the DrugModel instance
     */
    public void saveData(String brand, String generic, String scheduled, String doseForm,
                         String function, String sideEffects, String comments, DrugModel drugModel) {
        Log.d(TAG, "saveData start!");

        drugModel.setBrand(brand);
        drugModel.setGeneric(generic);
        drugModel.setScheduled(scheduled);
        drugModel.setDoseForm(doseForm);
        drugModel.setCommonUses(Utils.isEmpty(function) ? "to be updated" : function);
        drugModel.setSideEffects(Utils.isEmpty(sideEffects) ? "to be updated" : sideEffects);
        drugModel.setComments(Utils.isEmpty(comments) ? "to be updated" : comments);

        logic.insertEntry(drugModel);
        Log.d(TAG, "No entries: " + logic.getNoRecords());

        Log.d(TAG, "saveData end!");
    }
}
