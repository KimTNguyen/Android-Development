/**
 * This project displays a generic name of a drug, a user will pick a brand name
 * accordingly from a list of brand name provided.
 *
 * @author Kim Nguyen
 * @version 09-Jan-2017
 */

package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class QuizActivity extends AppCompatActivity {

    final int NO_GENERIC_ON_SCREEN = 5;
    final int NO_TABLE_COLUMN = 4;

    private Map<String, String> brandAndGeneric = new HashMap<>();
    private Map<String,String> generatedDrugs = new HashMap<>();
    private Map<String,String> functionAndUsage = new HashMap<>();
    private String brandName = null;
    private String direction = null;
    private ListView drugView;
    private TextView directionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        drugView = (ListView) findViewById(R.id.drug_list);

        initDrugData();
        
        if (brandAndGeneric.size() > 0) {
            generateListDrugs();
        } else {
            finish();
            Log.d("brandAndGeneric size", "size: " + brandAndGeneric.size());
            Toast.makeText(this,"data set is empty",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Creates a list of brand and it's generic
     */
    private void initDrugData() {

        try (Scanner scanner = new Scanner(openFileInput(CommonConstants.MED_FILE))){

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("\t");
                Log.d("Data", Arrays.toString(data));

                /* Need to refactor */
                if (data.length >= NO_TABLE_COLUMN) {
                    brandAndGeneric.put(data[0],data[1]);
                    functionAndUsage.put(data[0], data[2]+"\n"+data[3]);
                }
            }

            Log.d("brandAndGeneric", brandAndGeneric.toString());
            Log.d("functionAndUsage", functionAndUsage.toString());

        } catch (FileNotFoundException exception) {
            Log.e("FileNotFoundException", "Cannot open file", exception);
        }
    }

    /**
     * Generates a list of random generic and a random brand name to appear on the screen
     */
    private void generateListDrugs() {
        Log.d("generateListDrugs","start generateListDrugs");

        Random randNo = new Random();

        /* Generate a list of random brand names and its generic */
        if (brandAndGeneric.size() <= NO_GENERIC_ON_SCREEN) {
            for (String ele : brandAndGeneric.keySet()) {
                generatedDrugs.put(ele,brandAndGeneric.get(ele));
            }
            Log.d("generatedDrugs",generatedDrugs.toString());
        } else {
            while (generatedDrugs.size() != NO_GENERIC_ON_SCREEN) {
                int randNum = randNo.nextInt(brandAndGeneric.size());
                generatedDrugs.put((String) brandAndGeneric.keySet().toArray()[randNum],
                        (String) brandAndGeneric.values().toArray()[randNum]);
            }
        }

        /* Generates a random brand name from the set of drug name */
        int randNum = randNo.nextInt(generatedDrugs.size());
        brandName = (String) generatedDrugs.keySet().toArray()[randNum];

        /* Generates the direction of usage according to the brand displayed */
        direction = functionAndUsage.get(brandName);

        Log.d("generateListDrugs","end generateListDrugs");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("onResume", "onResume start!");

        display();
        pickDrugName();
        Log.d("generatedBradOnResume",brandName);
    }

    /**
     * Displays brand name and list of generic on the screen
     */
    protected void display() {
        Log.d("display()", "start display()");

        TextView genericView = (TextView) findViewById(R.id.brand);

        genericView.setText(brandName);

        /* Displays a list of random generic name on the screen */
        ArrayAdapter<String> drugAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, generatedDrugs.values().toArray(new String[0]));
        drugView.setAdapter(drugAdapter);

        /* Displays direction on the screen */
        directionView = (TextView) findViewById(R.id.direction);
        directionView.setText(direction);

        Log.d("display()", "end display()");
    }

    /**
     * Gives feedback right or wrong when the user clicks on the generic name on the list
     */
    protected void pickDrugName() {
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
                generateListDrugs();
                display();
                pickDrugName();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("brandName", brandName);
        outState.putString("direction",direction);
        outState.putSerializable("generatedDrugs", (Serializable) generatedDrugs);

        Log.d("generatedBradSave",brandName);
        Log.d("generatedDrugsSave",generatedDrugs.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("brandName") && savedInstanceState.containsKey("generatedDrugs")) {
            brandName = savedInstanceState.getString("brandName");
            direction = savedInstanceState.getString("direction");
            generatedDrugs = (HashMap<String, String>) savedInstanceState.getSerializable("generatedDrugs");
            Log.d("onRestoreInstanceState", "onRestoreInstanceState start!");
        }
        Log.d("generatedBradRestore",brandName);
        Log.d("generatedDrugsRestore",generatedDrugs.toString());
    }
}
