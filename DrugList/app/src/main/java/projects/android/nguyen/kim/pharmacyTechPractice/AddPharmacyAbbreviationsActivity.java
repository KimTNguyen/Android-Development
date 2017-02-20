package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddPharmacyAbbreviationsActivity extends AppCompatActivity implements AddScreenInterface {

    private EditText sigCodeEditText;
    private EditText meaningEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pharmacy_abbreviations);
        sigCodeEditText = (EditText) findViewById(R.id.sig_code);
        meaningEditText = (EditText) findViewById(R.id.meaning);
    }

    public void saveData(View view) {
        Log.d("AddAbbreActivity","saveSigCode start!");
        try (PrintStream output = new PrintStream(openFileOutput(CommonConstants.SIG_CODE_FILE, MODE_APPEND))) {

            String sigCode = sigCodeEditText.getText().toString().trim();
            String meaning = meaningEditText.getText().toString().trim();

            if (Utils.isEmpty(sigCode)) {
                sigCodeEditText.setError("sig code is required!");
            } else if (Utils.isEmpty(meaning)) {
                meaningEditText.setError("translation is required!");
            } else {
                output.print(meaning + CommonConstants.REGEX_TAB);
                output.println(sigCode);
            }

            clearScreen(view);
        } catch (FileNotFoundException exception) {
            Log.e("FileNotFoundException", "Cannot open file", exception);
        }
        clearScreen(view);
        sigCodeEditText.requestFocus();

        Log.d("AddAbbreActivity","saveSigCode end!");
    }

    public void clearScreen(View view) {
        Log.d("AddAbbreActivity","clearScreen start!");

        Utils.clearData(sigCodeEditText);
        Utils.clearData(meaningEditText);

        Log.d("AddAbbreActivity","clearScreen end!");
    }
}
