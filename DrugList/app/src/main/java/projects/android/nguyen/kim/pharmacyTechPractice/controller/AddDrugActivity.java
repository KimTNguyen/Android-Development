/*
 * AddDrugActivity gets data from user input then adds the data into the database.
 *
 * @author Kim Nguyen
 * @version 2/12/2017
 * <p>
 * Modified by Kim Nguyen on 3/8/2017
 * Modified by Kim Nguyen on 4/9/2017
 * Modified by Kim Nguyen on 5/9/2017
 */

package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import projects.android.nguyen.kim.pharmacyTechPractice.CommonConstants;
import projects.android.nguyen.kim.pharmacyTechPractice.R;
import projects.android.nguyen.kim.pharmacyTechPractice.Utils;
import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugModel;

public class AddDrugActivity extends AppCompatActivity {

    private static final String TAG = "AddDrugActivity";

    private EditText brandEditText;
    private EditText genericEditText;
    private RadioGroup scheduledRadioButton;
    private EditText functionEditText;
    private EditText sideEffectsEditText;
    private EditText commentsEditText;

    private CheckBox byMouthCheckBox;
    private CheckBox suppositoryCheckBox;
    private CheckBox inhalationCheckBox;
    private CheckBox injectionCheckBox;
    private CheckBox nasalSprayCheckBox;
    private CheckBox aerosolCheckBox;
    private CheckBox topicallyCheckBox;
    private CheckBox eyeDropCheckBox;
    private CheckBox implantCheckBox;
    private CheckBox vaginalCheckBox;

    private ArrayList<String> route;
    private String scheduled = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        brandEditText = (EditText) findViewById(R.id.add_brand);
        genericEditText = (EditText) findViewById(R.id.add_generic);
        functionEditText = (EditText) findViewById(R.id.add_function);
        sideEffectsEditText = (EditText) findViewById(R.id.side_effects);
        commentsEditText = (EditText) findViewById(R.id.comments);
        scheduledRadioButton = (RadioGroup) findViewById(R.id.scheduled);

        byMouthCheckBox = (CheckBox) findViewById(R.id.by_mouth);
        suppositoryCheckBox = (CheckBox) findViewById(R.id.suppositories);
        inhalationCheckBox = (CheckBox) findViewById(R.id.inhalation);
        injectionCheckBox = (CheckBox) findViewById(R.id.injection);
        nasalSprayCheckBox = (CheckBox) findViewById(R.id.nasal_spray);
        aerosolCheckBox = (CheckBox) findViewById(R.id.aerosol);
        topicallyCheckBox = (CheckBox) findViewById(R.id.topically);
        eyeDropCheckBox = (CheckBox) findViewById(R.id.eye_drop);
        implantCheckBox = (CheckBox) findViewById(R.id.implant);
        vaginalCheckBox = (CheckBox) findViewById(R.id.vaginal);

        Button saveDataButton = (Button) findViewById(R.id.savaDrugData);
        Button clearScreenButton = (Button) findViewById(R.id.clearAddDrugScreen);

        route = new ArrayList<>();

        byMouthCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actOnCheckBox(v, CommonConstants.BY_MOUTH);
            }
        });

        suppositoryCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actOnCheckBox(v, CommonConstants.SUPPOSITORY);
            }
        });

        inhalationCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actOnCheckBox(v, CommonConstants.INHALATION);
            }
        });

        injectionCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actOnCheckBox(v, CommonConstants.INJECTION);
            }
        });

        nasalSprayCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actOnCheckBox(v, CommonConstants.NASAL_SPRAY);
            }
        });

        aerosolCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actOnCheckBox(v, CommonConstants.AEROSOL);
            }
        });

        topicallyCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actOnCheckBox(v, CommonConstants.TOPICALLY);
            }
        });

        eyeDropCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actOnCheckBox(v, CommonConstants.EYE_DROP);
            }
        });

        implantCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actOnCheckBox(v, CommonConstants.IMPLANT);
            }
        });

        vaginalCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actOnCheckBox(v, CommonConstants.VAGINAL);
            }
        });

        scheduledRadioButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.one) {
                    scheduled = CommonConstants.SCHEDULED_I;
                } else if (checkedId == R.id.two) {
                    scheduled = CommonConstants.SCHEDULED_II;
                } else if (checkedId == R.id.three) {
                    scheduled = CommonConstants.SCHEDULED_III;
                } else if (checkedId == R.id.four) {
                    scheduled = CommonConstants.SCHEDULED_IV;
                } else if (checkedId == R.id.none) {
                    scheduled = CommonConstants.NONE_CONTROLLED_SUBSTANCES;
                }
            }
        });

        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = brandEditText.getText().toString().trim();
                String generic = genericEditText.getText().toString().trim();

                Log.d("schedule",scheduled);
                /* If brand and generic, route and schedule fields are not empty, insert all field's values into the db,
                otherwise display error messages on the screen */
                if (Utils.isEmpty(brand)) {
                    brandEditText.setError("brand is required!");
                } else if (Utils.isEmpty(generic)) {
                    genericEditText.setError("generic is required!");
                } else if (route.isEmpty()) {
                    Toast.makeText(getApplication(), "Dose Forms is required!", Toast.LENGTH_SHORT).show();
                } else if (scheduled == null) {
                    Toast.makeText(getApplication(), "Scheduled is required!", Toast.LENGTH_SHORT).show();
                } else {
                    DrugModel drugModel = new DrugModel();

                    Utils.addDrug(brand, generic, scheduled, route.toString(),
                            functionEditText.getText().toString(),
                            sideEffectsEditText.getText().toString(),
                            commentsEditText.getText().toString(), getApplicationContext(),
                            drugModel);

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
        Utils.clearEditText(commentsEditText);
        Utils.clearEditText(sideEffectsEditText);

        byMouthCheckBox.setChecked(false);
        suppositoryCheckBox.setChecked(false);
        inhalationCheckBox.setChecked(false);
        injectionCheckBox.setChecked(false);
        nasalSprayCheckBox.setChecked(false);
        aerosolCheckBox.setChecked(false);
        topicallyCheckBox.setChecked(false);
        eyeDropCheckBox.setChecked(false);
        implantCheckBox.setChecked(false);
        vaginalCheckBox.setChecked(false);

        scheduledRadioButton.clearCheck();

        Log.d(TAG, "clearScreen end!");
    }

    private void actOnCheckBox(View view, String obj) {
        boolean checked = ((CheckBox) view).isChecked();

        if (checked) {
            if (!route.contains(obj)) {
                route.add(obj);
            }
        } else {
            route.remove(obj);
        }

    }
}
