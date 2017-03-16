/**
 * AddDrugActivity gets data from user input then adds the data into the database.
 *
 * @author Kim Nguyen
 * @version 2/12/2017
 *
 * Modified by Kim Nguyen on 3/8/2017.
 */

package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugModel;
import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugRelatedLogic;

public class AddDrugActivity extends AppCompatActivity implements IAddScreen {

    private EditText brandEditText;
    private EditText genericEditText;
    private EditText functionEditText;
    private EditText directionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AddDrugActivity", "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        brandEditText = (EditText) findViewById(R.id.add_brand);
        genericEditText = (EditText) findViewById(R.id.add_generic);
        functionEditText = (EditText) findViewById(R.id.add_function);
        directionEditText = (EditText) findViewById(R.id.add_direction);

        Log.d("AddDrugActivity", "onCreate end!");
    }

    /**
     * Saves user input data into the database
     *
     * @param view the current screen
     */
    public void saveData(View view) {
        Log.d("AddDrugActivity", "saveData start!");

        String brand = brandEditText.getText().toString().trim();
        String generic = genericEditText.getText().toString().trim();
        String function = functionEditText.getText().toString();
        String direction = directionEditText.getText().toString();

        /* If brand and generic fields are not empty, insert all field's values into the db,
        otherwise display error messages on the screen */
        if (Utils.isEmpty(brand)) {
            brandEditText.setError("brand is required!");
        } else if (Utils.isEmpty(generic)) {
            genericEditText.setError("generic is required!");
        } else {
            DrugModel drugModel = new DrugModel();
            drugModel.setBrand(brand);
            drugModel.setGeneric(generic);
            drugModel.setFunction(Utils.isEmpty(function) ? "to be updated" : function);
            drugModel.setDirection(Utils.isEmpty(direction) ? "to be updated" : direction);

            DrugRelatedLogic logic = new DrugRelatedLogic(getApplicationContext());
            logic.insertEntry(drugModel);
            Log.d("AddDrugActivity", "No entries: " + logic.getNoRecords());

            // Clears all the fields
            clearScreen(view);

            // Moves the focus back to brand field
            brandEditText.requestFocus();
        }

        Log.d("AddDrugActivity", "saveData end!");
    }

    /**
     * Clears all the user input data
     *
     * @param view the current screen
     */
    public void clearScreen(View view) {
        Log.d("AddDrugActivity", "clearScreen start!");

        Utils.clearEditText(brandEditText);
        Utils.clearEditText(genericEditText);
        Utils.clearEditText(functionEditText);
        Utils.clearEditText(directionEditText);

        Log.d("AddDrugActivity", "clearScreen end!");
    }
}
