package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import projects.android.nguyen.kim.pharmacyTechPractice.R;
import projects.android.nguyen.kim.pharmacyTechPractice.Utils;
import projects.android.nguyen.kim.pharmacyTechPractice.logic.TestSigCodeLogic;

/**
 * TestSigCodeActivity generates a random translation text, and compares its sig code with the sig
 * entering by the user
 *
 * @author Kim Nguyen
 * @version 3/9/2017
 *          <p>
 *          Modified by Kim Nguyen 3/16/2017
 */
public class TestSigCodeActivity extends AppCompatActivity {

    private String translationText;
    private TestSigCodeLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TestSigCodeActivity", "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sig_code);

        Button checkButton = (Button) findViewById(R.id.checkSigCode);

        logic = new TestSigCodeLogic(getApplicationContext());

        translationText = logic.generateRandAbbreviation();

        if (translationText.length() > 0) {
            checkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText inputEditText = (EditText) findViewById(R.id.sig);
                    if (logic.checkSigCode(inputEditText.getText().toString(), translationText)) {
                        Toast.makeText(getApplicationContext(), "Well done!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "You suck!",
                                Toast.LENGTH_SHORT).show();
                    }
                    Utils.clearEditText(inputEditText);
                    translationText = logic.generateRandAbbreviation();
                    display(translationText);
                }
            });
        } else {
            finish();
            Toast.makeText(this, "data set is empty", Toast.LENGTH_SHORT).show();
        }

        Log.d("TestSigCodeActivity", "onCreate end!");
    }

    /**
     * Displays an abbreviation on the screen next to Meaning TextField
     *
     * @param text the text displayed on the screen
     */
    private void display(String text) {
        Log.d("TestSigCodeActivity", "display start!");

        TextView abbTextView = (TextView) findViewById(R.id.sig_meaning);
        abbTextView.setText(text);

        Log.d("TestSigCodeActivity", "display end!");
    }

    @Override
    protected void onResume() {
        Log.d("TestSigCodeActivity", "onResume start!");

        super.onResume();

        display(translationText);

        Log.d("TestSigCodeActivity", "onResume end!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("TestSigCodeActivity", "onSaveInstanceState start!");

        super.onSaveInstanceState(outState);

        outState.putString("abbreviationText", translationText);

        Log.d("TestSigCodeActivity", "onSaveInstanceState end!");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("TestSigCodeActivity", "onRestoreInstanceState start!");

        super.onRestoreInstanceState(savedInstanceState);

        translationText = savedInstanceState.getString("abbreviationText");

        Log.d("TestSigCodeActivity", "onRestoreInstanceState end!");
    }
}
