package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import projects.android.nguyen.kim.pharmacyTechPractice.model.PharmacyAbbreviationModel;
import projects.android.nguyen.kim.pharmacyTechPractice.model.PharmacyAbbreviationLogic;

/**
 * AbbreviationDbOperations get data from users' input and store it into the Abbreviation Database.
 *
 * @author Kim Nguyen
 * @version 2/12/2017.
 *
 * Modified by Kim Nguyen 3/9/2017.
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
    }

    /**
     * Saves user input data into the database
     *
     * @param view the current screen
     */
    public void saveData(View view) {
        Log.d("AddAbbActivity","saveSigCode start!");

        String sigCode = sigCodeEditText.getText().toString().trim();
        String meaning = meaningEditText.getText().toString().trim();

        if (Utils.isEmpty(sigCode)) {
            sigCodeEditText.setError("sig code is required!");
        } else if (Utils.isEmpty(meaning)) {
            meaningEditText.setError("translation is required!");
        } else {
            PharmacyAbbreviationModel sigCodeModel = new PharmacyAbbreviationModel();
            sigCodeModel.setSigCode(sigCode);
            sigCodeModel.setMeaning(meaning);

            PharmacyAbbreviationLogic logic = new PharmacyAbbreviationLogic(getApplicationContext());
            logic.insertEntry(sigCodeModel);
            Log.d("AddAbbActivity", "No entries: " + logic.getNoEntries());

            clearScreen(view);
            sigCodeEditText.requestFocus();
        }

        Log.d("AddAbbActivity","saveSigCode end!");
    }

    /**
     * Clears all the user input data
     *
     * @param view the current screen
     */
    public void clearScreen(View view) {
        Log.d("AddAbbActivity","clearScreen start!");

        Utils.clearEditText(sigCodeEditText);
        Utils.clearEditText(meaningEditText);

        Log.d("AddAbbActivity","clearScreen end!");
    }
}
