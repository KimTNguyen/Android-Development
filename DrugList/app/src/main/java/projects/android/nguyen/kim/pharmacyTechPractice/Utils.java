package projects.android.nguyen.kim.pharmacyTechPractice;

import android.content.Context;
import android.widget.EditText;

import projects.android.nguyen.kim.pharmacyTechPractice.logic.AddDrugActivityLogic;
import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugModel;

/**
 * Utils contains common use methods.
 * <p>
 * Created by Kimmy on 2/12/2017.
 * Modified by Kim Nguyen on 5/9/2017
 */

public class Utils {
    /**
     * Compares the string with an empty string
     *
     * @param str the String to be compared
     * @return true if the String is empty, false otherwise
     */
    public static boolean isEmpty(String str) {
        return "".equals(str);
    }

    /**
     * Sets the EditText field to blank
     *
     * @param editText the EditText that will be clear
     */
    public static void clearEditText(EditText editText) {
        editText.setText("");
    }

    public static void addDrug(String brand, String generic, String scheduled, String route,
                        String function, String sideEffects, String comments, Context context,
                               DrugModel model) {

        AddDrugActivityLogic logic = new AddDrugActivityLogic(context);
        logic.saveData(brand, generic, scheduled, route, function, sideEffects, comments, model);
    }
}
