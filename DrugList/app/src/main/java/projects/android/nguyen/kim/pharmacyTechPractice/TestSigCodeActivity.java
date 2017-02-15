package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TestSigCodeActivity extends AppCompatActivity {

    final int NO_TABLE_COLUMN = 2;
    final String SPACE = " ";

    private Map<String, String> sigCodeMap = new HashMap<>();
    private String translationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sig_code);
        initSigData();

        if (sigCodeMap.size() > 0 ) {
            generateRandAbbreviation();
        } else {
            finish();
            Log.d("sigCodeMap size", "size: " + sigCodeMap.size());
            Toast.makeText(this,"data set is empty",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Creates a map of a sig and it's abbreviation accordingly
     */
    private void initSigData() {
        Log.d("initSigData", "initSigData start!");

        try (Scanner scanner = new Scanner(openFileInput(CommonConstants.SIG_CODE_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("\t");
                Log.d("Data", Arrays.toString(data));

                /* Need to refactor */
                if (data.length == NO_TABLE_COLUMN) {
                    sigCodeMap.put(data[1],data[0]);
                }
            }

        } catch (FileNotFoundException exception) {
            Log.e("FileNotFoundException", "Cannot open file", exception);
        }

        Log.d("sigCodeMap", sigCodeMap.toString());
        Log.d("initSigData", "initSigData end!");
    }


    /**
     * Chooses a random text in the key set values of the map
     */
    private void generateRandAbbreviation() {
        Log.d("method", "generateRandAbbreviation start!");

        Random rand = new Random();
        translationText = (String) sigCodeMap.keySet().toArray()[rand.nextInt(sigCodeMap.size())];

        Log.d("method", "generateRandAbbreviation end!");
    }

    @Override
    protected void onResume() {
        super.onResume();

        display(translationText);
    }

    /**
     * Checks whether the sig code input match the translation displayed on the screen
     *
     * @param view the view on the screen responsible for drawing and event handling
     */
    public void checkSigCode(View view) {
        EditText inputEditText = (EditText) findViewById(R.id.sig);
        String answer = inputEditText.getText().toString();
        String correctAnswer = sigCodeMap.get(translationText);

        if (answer.equalsIgnoreCase(correctAnswer)) {
            Toast.makeText(this,"Well done!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,correctAnswer,Toast.LENGTH_SHORT).show();
        }

        Utils.clearData(inputEditText);
        generateRandAbbreviation();
        display(translationText);
    }

    /**
     * Displays an abbreviation on the screen next to Meaning TextField
     * @param text the text displayed on the screen
     */
    private void display(String text) {
        Log.d("method", "display start!");

        TextView abbTextView = (TextView) findViewById(R.id.sig_meaning);
        String textToSet = SPACE + text;

        abbTextView.setText(textToSet);

        Log.d("method", "display end!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("abbreviationText", translationText);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        translationText = savedInstanceState.getString("abbreviationText");
    }

}
