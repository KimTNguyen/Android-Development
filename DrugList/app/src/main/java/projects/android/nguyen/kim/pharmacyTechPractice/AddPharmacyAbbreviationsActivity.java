package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddPharmacyAbbreviationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pharmacy_abbreviations);
    }

    public void saveSigCode(View view) throws FileNotFoundException {
        Log.d("saveSigCode","saveSigCode start!");
        try (PrintStream output = new PrintStream(openFileOutput(CommonConstants.SIG_CODE_FILE, MODE_APPEND))) {
            String sigCode = ((EditText) findViewById(R.id.sig_code)).getText().toString();
            String meaning = ((EditText) findViewById(R.id.meaning)).getText().toString();

            if (isEmpty(sigCode) || isEmpty(meaning)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                output.print(sigCode + CommonConstants.REGEX);
                output.println(meaning);
            }
        }

        Log.d("saveSigCode","saveSigCode end!");
    }

    /**
     * Compares the string with an empty string
     * @param str the String to be compared
     * @return true if the String is empty, false otherwise
     */
    private boolean isEmpty(String str) {
        if ("".equals(str)) {
            return true;
        }

        return false;
    }

    /**
     * Sets the EditText field to blank
     * @param editText the EditText that will be clear
     */
    public void clearData(EditText editText) {
        Log.d("clearData", "clearData start!");

        editText.setText("");

        Log.d("clearData", "clearData end!");
    }
}
