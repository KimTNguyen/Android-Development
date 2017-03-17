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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import projects.android.nguyen.kim.pharmacyTechPractice.R;
import projects.android.nguyen.kim.pharmacyTechPractice.logic.QuizLogic;

public class QuizActivity extends AppCompatActivity {

    private QuizLogic logic;

    private Map<String, String> generatedDrugs = new HashMap<>();
    private ListView drugView;
    private TextView genericView;
    private String brandName = null;
    private String direction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("QuizActivity", "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        drugView = (ListView) findViewById(R.id.drug_list);
        genericView = (TextView) findViewById(R.id.brand);

        logic = new QuizLogic(getApplicationContext());
        setListDrugs();
        if (generatedDrugs.size() > 0) {
            setBrandAndDirection();

            // Shows hint when the user clicks on brand name TextView
            genericView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), direction, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            finish();
            Toast.makeText(this, "database is empty", Toast.LENGTH_SHORT).show();
        }

        Log.d("QuizActivity", "onCreate end!");
    }

    private void setListDrugs() {
        logic.generateListDrugs();
        generatedDrugs = logic.getGeneratedDrugs();
    }

    private void setBrandAndDirection() {
        brandName = logic.generateBrandName();
        direction = logic.getFunctionAndUsage().get(brandName);
    }

    @Override
    protected void onResume() {
        Log.d("QuizActivity", "onResume start!");

        super.onResume();

        Log.d("onResume", "onResume start!");

        display();
        pickDrugName();
        Log.d("generatedBradOnResume", brandName);

        Log.d("QuizActivity", "onResume end!");
    }

    /**
     * Displays brand name and list of generic on the screen
     */
    protected void display() {
        Log.d("QuizActivity", "display start!");

        genericView.setText(brandName);

        /* Displays a list of random generic name on the screen */
        ArrayAdapter<String> drugAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, generatedDrugs.values().toArray(new String[0]));
        drugView.setAdapter(drugAdapter);

        /* Displays direction on the screen */
        TextView directionView = (TextView) findViewById(R.id.direction);
        directionView.setText(direction);

        Log.d("QuizActivity", "display end!");
    }

    /**
     * Gives feedback right or wrong when the user clicks on the generic name on the list
     */
    private void pickDrugName() {
        Log.d("QuizActivity", "pickDrugName start!");

        drugView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int brand, long l) {
                Log.d("position", "the user click on: " + brand);
                Log.d("position", "the user click on: " + generatedDrugs.values().toArray()[brand]);
                Log.d("position", "the user click on: " + generatedDrugs.get(brandName));

                /* Compares the name displayed and the name picked from the list */
                if (generatedDrugs.get(brandName).equals(generatedDrugs.values().toArray()[brand])) {
                    Toast.makeText(QuizActivity.this, "You are awesome!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, "You suck!", Toast.LENGTH_SHORT).show();
                }
                setListDrugs();
                setBrandAndDirection();
                display();
            }
        });

        Log.d("QuizActivity", "pickDrugName end!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("QuizActivity", "onSaveInstanceState start!");

        super.onSaveInstanceState(outState);
        outState.putString("brandName", brandName);
        outState.putString("direction", direction);
        outState.putSerializable("generatedDrugs", (Serializable) generatedDrugs);

        Log.d("generatedBradSave", brandName);
        Log.d("generatedDrugsSave", generatedDrugs.toString());

        Log.d("QuizActivity", "onSaveInstanceState end!");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("QuizActivity", "onRestoreInstanceState start!");

        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("brandName") &&
                savedInstanceState.containsKey("generatedDrugs")) {
            brandName = savedInstanceState.getString("brandName");
            direction = savedInstanceState.getString("direction");
            generatedDrugs = (HashMap<String, String>) savedInstanceState.getSerializable("generatedDrugs");
            Log.d("onRestoreInstanceState", "onRestoreInstanceState start!");
        }
        Log.d("generatedBradRestore", brandName);
        Log.d("generatedDrugsRestore", generatedDrugs.toString());

        Log.d("QuizActivity", "onRestoreInstanceState end!");
    }

}
