/**
 * AddDrugActivity gets data from user input then adds the data into the database.
 *
 * @author Kim Nguyen
 * @version 2/12/2017
 * <p>
 * Modified by Kim Nguyen on 3/8/2017
 */

package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import projects.android.nguyen.kim.pharmacyTechPractice.IAddScreen;
import projects.android.nguyen.kim.pharmacyTechPractice.R;
import projects.android.nguyen.kim.pharmacyTechPractice.Utils;
import projects.android.nguyen.kim.pharmacyTechPractice.logic.AddDrugActivityLogic;
import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugModel;

public class AddDrugActivity extends AppCompatActivity implements IAddScreen {

    private static final String TAG = "AddDrugActivity";
    private EditText brandEditText;
    private EditText genericEditText;
    private EditText functionEditText;
    private EditText directionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        brandEditText = (EditText) findViewById(R.id.add_brand);
        genericEditText = (EditText) findViewById(R.id.add_generic);
        functionEditText = (EditText) findViewById(R.id.add_function);
        directionEditText = (EditText) findViewById(R.id.add_direction);

        Button saveDataButton = (Button) findViewById(R.id.savaDrugDataButton);
        Button clearScreenButton = (Button) findViewById(R.id.clearAddDrugScreenButton);

        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = brandEditText.getText().toString().trim();
                String generic = genericEditText.getText().toString().trim();

                /* If brand and generic fields are not empty, insert all field's values into the db,
                otherwise display error messages on the screen */
                if (Utils.isEmpty(brand)) {
                    brandEditText.setError("brand is required!");
                } else if (Utils.isEmpty(generic)) {
                    genericEditText.setError("generic is required!");
                } else {
                    AddDrugActivityLogic logic = new AddDrugActivityLogic(getApplicationContext());
                    DrugModel drugModel = new DrugModel();

                    logic.saveData(brand, generic, functionEditText.getText().toString(),
                            directionEditText.getText().toString(), drugModel);

                    // Clears all the fields
                    clearScreen(view);

                    // Moves the focus back to brand field
                    brandEditText.requestFocus();
                }
            }
        });

        clearScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearScreen(view);
            }
        });

        Log.d(TAG, "onCreate end!");
    }

    /**
     * Clears all the user input data
     *
     * @param view the current screen
     */
    public void clearScreen(View view) {
        Log.d(TAG, "clearScreen start!");

        Utils.clearEditText(brandEditText);
        Utils.clearEditText(genericEditText);
        Utils.clearEditText(functionEditText);
        Utils.clearEditText(directionEditText);

        Log.d(TAG, "clearScreen end!");
    }
}
