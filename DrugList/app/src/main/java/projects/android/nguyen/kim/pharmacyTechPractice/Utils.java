package projects.android.nguyen.kim.pharmacyTechPractice;

import android.widget.EditText;

/**
 * Utils contains common use methods.
 * <p>
 * Created by Kimmy on 2/12/2017.
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
}
