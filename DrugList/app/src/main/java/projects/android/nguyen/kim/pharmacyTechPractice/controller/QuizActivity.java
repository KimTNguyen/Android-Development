/**
 * This project displays a generic name of a drug, a user will pick a brand name
 * accordingly from a list of brand name provided.
 *
 * @author Kim Nguyen
 * @version 09-Jan-2017
 *
 * Modified by Kim Nguyen on 3/16/17
 */

package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import projects.android.nguyen.kim.pharmacyTechPractice.R;
import projects.android.nguyen.kim.pharmacyTechPractice.logic.QuizLogic;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String BRAND_SAVE_TAG = "brandName";
    private static final String DIRECTION_SAVE_TAG = "direction";
    private static final String CORRECT_ANSWER_SAVE_TAG = "correctAnswer";
    private static final String GENERATED_LIST_DRUGS_SAVE_TAG = "generatedDrugs";

    private QuizLogic logic;
    private Map<String, String> generatedDrugs = new HashMap<>();
    private ListView drugView;
    private String brandName = null;
    private String direction = null;
    private String correctAnswer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        drugView = (ListView) findViewById(R.id.drug_list);

        logic = new QuizLogic(getApplicationContext());
        setListDrugs();
        if (generatedDrugs.size() == 0) {
            finish();
            Log.e(TAG, "database is empty");
            Toast.makeText(this, "database is empty", Toast.LENGTH_SHORT).show();
        } else {
            setDrugInfo();
        }

        Log.d(TAG, "onCreate end!");
    }

    private void setListDrugs() {
        logic.generateListDrugs();
        generatedDrugs = logic.getGeneratedDrugs();
    }

    private void setDrugInfo() {
        brandName = logic.generateBrandName();
        direction = logic.getFunctionAndUsage().get(brandName);
        correctAnswer = generatedDrugs.get(brandName);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume start!");

        super.onResume();
        ImageView helpImageView = (ImageView) findViewById(R.id.help_image_view);

        display();
        pickDrugName();

        // Shows hint when the user clicks on brand name TextView
        helpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizHintActivity.class);
                intent.putExtra(QuizHintActivity.EXTRA_HINT, correctAnswer + " is the generic of " + brandName);
                startActivity(intent);
            }
        });

        Log.d("generatedBradOnResume", brandName);

        Log.d(TAG, "onResume end!");
    }

    /**
     * Displays brand name and list of generic on the screen
     */
    protected void display() {
        Log.d(TAG, "display start!");

        TextView genericView = (TextView) findViewById(R.id.brand);
        genericView.setText(brandName);

        /* Displays a list of random generic name on the screen */
        ArrayAdapter<String> drugAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, generatedDrugs.values().toArray(new String[0]));
        drugView.setAdapter(drugAdapter);

        /* Displays direction on the screen */
        TextView directionView = (TextView) findViewById(R.id.direction);
        directionView.setText(direction);

        Log.d(TAG, "display end!");
    }

    /**
     * Gives feedback right or wrong when the user clicks on the generic name on the list
     */
    private void pickDrugName() {
        Log.d(TAG, "pickDrugName start!");

        drugView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int brand, long l) {
                Log.d("position", "the user click on: " + brand);
                Log.d("position", "the user click on: " + generatedDrugs.values().toArray()[brand]);
                Log.d("position", "the right answer: " + generatedDrugs.get(brandName));

                /* Compares the name displayed and the name picked from the list */
                if (correctAnswer.equals(generatedDrugs.values().toArray()[brand])) {
                    Toast.makeText(QuizActivity.this, "You are awesome!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, "You suck!", Toast.LENGTH_SHORT).show();
                }
                setListDrugs();
                setDrugInfo();
                display();
            }
        });

        Log.d(TAG, "pickDrugName end!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState start!");

        super.onSaveInstanceState(outState);
        outState.putString(BRAND_SAVE_TAG, brandName);
        outState.putString(DIRECTION_SAVE_TAG, direction);
        outState.putString(CORRECT_ANSWER_SAVE_TAG, correctAnswer);
        outState.putSerializable(GENERATED_LIST_DRUGS_SAVE_TAG, (Serializable) generatedDrugs);

        Log.d(BRAND_SAVE_TAG, brandName);
        Log.d(GENERATED_LIST_DRUGS_SAVE_TAG, generatedDrugs.toString());
        Log.d(CORRECT_ANSWER_SAVE_TAG, correctAnswer);
        Log.d(TAG, "onSaveInstanceState end!");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState start!");

        super.onRestoreInstanceState(savedInstanceState);
            brandName = savedInstanceState.getString(BRAND_SAVE_TAG);
            direction = savedInstanceState.getString(DIRECTION_SAVE_TAG);
            correctAnswer = savedInstanceState.getString(CORRECT_ANSWER_SAVE_TAG);
            generatedDrugs = (HashMap<String, String>)
                    savedInstanceState.getSerializable(GENERATED_LIST_DRUGS_SAVE_TAG);

        Log.d(BRAND_SAVE_TAG, brandName);
        Log.d(GENERATED_LIST_DRUGS_SAVE_TAG, generatedDrugs.toString());
        Log.d(CORRECT_ANSWER_SAVE_TAG, correctAnswer);
        Log.d(TAG, "onRestoreInstanceState end!");
    }

}
