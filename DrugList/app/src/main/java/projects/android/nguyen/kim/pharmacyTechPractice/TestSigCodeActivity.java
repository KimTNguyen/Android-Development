package projects.android.nguyen.kim.pharmacyTechPractice;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

/**
 * TestSigCodeActivity generates a random translation text, and compares its sig code with the sig
 * entering by the user
 *
 * @author Kim Nguyen
 * @version 3/9/2017.
 */
public class TestSigCodeActivity extends AppCompatActivity {

    private String translationText;
    private AbbreviationDbOperations operations;
    private long entries;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TestSigCodeActivity", "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sig_code);

        operations = new AbbreviationDbOperations(getApplicationContext());
        entries = operations.getNoEntries(operations);

        if (entries > 0 ) {
            generateRandAbbreviation();
        } else {
            finish();
            Log.d("TestSigCodeActivity", "number of sig rows: " + entries);
            Toast.makeText(this,"data set is empty",Toast.LENGTH_SHORT).show();
        }

        Log.d("TestSigCodeActivity", "onCreate end!");
    }

    /**
     * Picks a random abbreviation text from the database
     */
    private void generateRandAbbreviation() {
        Log.d("TestSigCodeActivity", "generateRandAbbreviation start!");

        final int TRANSLATION_COLUMN = 1;

        Random rand = new Random();
        cursor = operations.getEntries(operations);
        cursor.moveToPosition(rand.nextInt((int) entries));
        translationText = " " + cursor.getString(TRANSLATION_COLUMN);

        Log.d("TestSigCodeActivity", "generateRandAbbreviation end!");
    }

    @Override
    protected void onResume() {
        Log.d("TestSigCodeActivity", "onResume start!");

        super.onResume();

        display(translationText);

        Log.d("TestSigCodeActivity", "onResume end!");
    }

    /**
     * Checks whether the sig code input match the translation displayed on the screen
     *
     * @param view the view on the screen responsible for drawing and event handling
     */
    public void checkSigCode(View view) {
        Log.d("TestSigCodeActivity", "checkSigCode start!");

        EditText inputEditText = (EditText) findViewById(R.id.sig);
        String answer = inputEditText.getText().toString();
        String correctAnswer = cursor.getString(CommonConstants.KEY_COLUMN);

        if (answer.equalsIgnoreCase(correctAnswer)) {
            Toast.makeText(this,"Well done!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,correctAnswer,Toast.LENGTH_SHORT).show();
        }

        Utils.clearEditText(inputEditText);
        generateRandAbbreviation();
        display(translationText);

        Log.d("TestSigCodeActivity", "checkSigCode end!");
    }

    /**
     * Displays an abbreviation on the screen next to Meaning TextField
     * @param text the text displayed on the screen
     */
    private void display(String text) {
        Log.d("TestSigCodeActivity", "display start!");

        TextView abbTextView = (TextView) findViewById(R.id.sig_meaning);
        abbTextView.setText(text);

        Log.d("TestSigCodeActivity", "display end!");
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
