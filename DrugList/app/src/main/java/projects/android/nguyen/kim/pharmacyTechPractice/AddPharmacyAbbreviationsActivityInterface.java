package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddPharmacyAbbreviationsActivityInterface extends AppCompatActivity implements AddScreenInterface {

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
        Log.d("saveSigCode","saveSigCode start!");
        try (PrintStream output = new PrintStream(openFileOutput(CommonConstants.SIG_CODE_FILE, MODE_APPEND))) {

            String sigCode = sigCodeEditText.getText().toString();
            String meaning = meaningEditText.getText().toString();

            if (Utils.isEmpty(sigCode) || Utils.isEmpty(meaning)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                output.print(sigCode + CommonConstants.REGEX);
                output.println(meaning);
            }

            clearScreen(view);
        } catch (FileNotFoundException exception) {
            Log.e("FileNotFoundException", "Cannot open file", exception);
        }
        clearScreen(view);
        Log.d("saveSigCode","saveSigCode end!");
    }

    public void clearScreen(View view) {
        Utils.clearData(sigCodeEditText);
        Utils.clearData(meaningEditText);
    }
}
