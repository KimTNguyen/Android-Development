package projects.android.nguyen.kim.pharmacyTechPractice.logic;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import projects.android.nguyen.kim.pharmacyTechPractice.CommonConstants;
import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugModel;

/**
 * Drug Lab is a singleton class responsible for generating a list of drugs.
 *
 * Created by Kimmy on 4/10/2017.
 * Modified by Kim Nguyen on 5/9/2017
 */

public class DrugLab {
    private final String TAG = "DrugLab";
    private static DrugLab drugLab;

    private List<DrugModel> drugList;

    private DrugLab(Context context) {
        drugList = new ArrayList<>();
        getDataFromDB(context);
    }

    public static DrugLab get(Context context) {
        if (drugLab == null) {
            return new DrugLab(context);
        }

        return drugLab;
    }

    public List<DrugModel> getDrugList() {
        return drugList;
    }

//    private void setDrugList(List<DrugModel> drugList) {
//        this.drugList = drugList;
//    }

    /**
     * Reads data from the DB
     */
    private void getDataFromDB(Context context) {
        DrugDbLogic logic = new DrugDbLogic(context);
        Cursor cursor = logic.getEntries();

        while (cursor.moveToNext()) {
            DrugModel drug = new DrugModel();
            drug.setBrand(cursor.getString(CommonConstants.KEY_COL_INDEX));
            drug.setGeneric(cursor.getString(CommonConstants.GENERIC_COL_INDEX));
            drug.setScheduled(cursor.getString(CommonConstants.SCHEDULED_COL_INDEX));
            drug.setDoseForm(cursor.getString(CommonConstants.DOSE_FORMS_COL_INDEX));
            drug.setCommonUses(cursor.getString(CommonConstants.FUNCTION_COL_INDEX));
            drug.setComments(cursor.getString(CommonConstants.COMMENTS_COL_INDEX));
            drug.setSideEffects(cursor.getString(CommonConstants.SIDE_EFFECTS_COL_INDEX));

            drugList.add(drug);
        }

        Log.d(TAG, "scheduled drug list: " + drugList.toString());
    }
}
