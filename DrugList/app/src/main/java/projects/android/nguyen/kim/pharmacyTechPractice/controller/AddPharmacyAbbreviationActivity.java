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
import projects.android.nguyen.kim.pharmacyTechPractice.logic.AddPharmacyAbbreviationLogic;
import projects.android.nguyen.kim.pharmacyTechPractice.model.PharmacyAbbreviationModel;

/**
 * AbbreviationDbOperations get data from users' input and store it into the Abbreviation Database.
 *
 * @author Kim Nguyen
 * @version 2/12/2017
 *          <p>
 *          Modified by Kim Nguyen 3/9/2017
 *          Modified by Kim Nguyen 3/16/2017
 */
public class AddPharmacyAbbreviationActivity extends AppCompatActivity
        implements IAddScreen {

    private EditText sigCodeEditText;
    private EditText meaningEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pharmacy_abbreviations);
        sigCodeEditText = (EditText) findViewById(R.id.sig_code);
        meaningEditText = (EditText) findViewById(R.id.meaning);
        Button saveDataButton = (Button) findViewById(R.id.saveSigDataButton);
        Button clearScreenButton = (Button) findViewById(R.id.clearAddSigScreanButton);

        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sigCode = sigCodeEditText.getText().toString().trim();
                String meaning = meaningEditText.getText().toString().trim();

                if (Utils.isEmpty(sigCode)) {
                    sigCodeEditText.setError("sig code is required!");
                } else if (Utils.isEmpty(meaning)) {
                    meaningEditText.setError("translation is required!");
                } else {
                    AddPharmacyAbbreviationLogic logic =
                            new AddPharmacyAbbreviationLogic(getApplicationContext());
                    PharmacyAbbreviationModel model = new PharmacyAbbreviationModel();

                    logic.saveData(sigCode, meaning, model);
                    clearScreen(view);
                    sigCodeEditText.requestFocus();
                }
            }
        });

        clearScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearScreen(view);
            }
        });
    }

    /**
     * Clears all the user input data
     *
     * @param view the current screen
     */
    public void clearScreen(View view) {
        Log.d("AddAbbActivity", "clearScreen start!");

        Utils.clearEditText(sigCodeEditText);
        Utils.clearEditText(meaningEditText);

        Log.d("AddAbbActivity", "clearScreen end!");
    }
}
