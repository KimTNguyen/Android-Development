package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private Map<String, String> sigCodeMap = new HashMap<>();
    private String abbreviationText;

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
                    sigCodeMap.put(data[0],data[1]);
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
        abbreviationText = (String) sigCodeMap.keySet().toArray()[rand.nextInt(sigCodeMap.size())];

        Log.d("method", "generateRandAbbreviation end!");
    }

    @Override
    protected void onResume() {
        super.onResume();

        display(abbreviationText);
    }

    /**
     * Displays an abbreviation on the screen next to Meaning TextField
     * @param text the text displayed on the screen
     */
    private void display(String text) {
        Log.d("method", "display start!");

        TextView abbTextView = (TextView) findViewById(R.id.sig_meaning);
        abbTextView.setText(" " + text);

        Log.d("method", "display end!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("abbreviationText", abbreviationText);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        abbreviationText = savedInstanceState.getString("abbreviationText");
    }
}
