package projects.android.nguyen.kim.pharmacyTechPractice;

import android.view.View;

import java.io.FileNotFoundException;

/**
 * Created by Kimmy on 2/12/2017.
 */

public interface AddScreenInterface {
    public void clearScreen(View view);
    public void saveData(View view) throws FileNotFoundException;
}
