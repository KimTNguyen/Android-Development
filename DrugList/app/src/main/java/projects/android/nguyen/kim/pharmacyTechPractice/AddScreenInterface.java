package projects.android.nguyen.kim.pharmacyTechPractice;

import android.view.View;

import java.io.FileNotFoundException;

/**
 * Created by Kimmy on 2/12/2017.
 */

public interface AddScreenInterface {
    /**
     * Clears all the input on the screen
     *
     * @param view the current screen
     */
    public void clearScreen(View view);

    /**
     * Saves input data into file
     *
     * @param view the current screen
     * @throws FileNotFoundException
     */
    public void saveData(View view) throws FileNotFoundException;
}
