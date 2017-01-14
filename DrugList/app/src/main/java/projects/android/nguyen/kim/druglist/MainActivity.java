/**
 * This project displays a generic name of a drug, a user will pick a brand name
 * accordingly from a list of brand name provided.
 *
 * @author Kim Nguyen
 * @version 09-Jan-2017
 */

package projects.android.nguyen.kim.druglist;

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
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    final int NO_GENERIC_ON_SCREEN = 5;

    private Map<String, String> brandAndGeneric = new HashMap<>();
    private Map<String,String> generatedDrugs = new HashMap<>();
    private String brandName = null;
    private ListView drugView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drugView = (ListView) findViewById(R.id.drug_list);

        initDrugData();
        generateListDrugs();
    }

    /**
     * Creates a list of brand and it's generic
     */
    private void initDrugData() {

        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.druglist));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split("\t");
            if (data.length >= 2) {
                brandAndGeneric.put(data[0],data[1]);
            }
        }

        scanner.close();
    }

    /**
     * Generates a list of random generic and a random brand name to appear on the screen
     */
    private void generateListDrugs() {
        Random randNo = new Random();

        while (generatedDrugs.size() != NO_GENERIC_ON_SCREEN) {
            int randNum = randNo.nextInt(brandAndGeneric.size());
            generatedDrugs.put((String) brandAndGeneric.keySet().toArray()[randNum],
                    (String) brandAndGeneric.values().toArray()[randNum]);
        }

        /* Generates a random number from the set of drug name */
        brandName = (String) generatedDrugs.keySet().toArray()[randNo.nextInt(NO_GENERIC_ON_SCREEN)];
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
        TextView genericView = (TextView) findViewById(R.id.brand);

        genericView.setText(brandName);

        /* Displays a list of random generic name on the screen */
        ArrayAdapter<String> drugAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, generatedDrugs.values().toArray(new String[0]));
        drugView.setAdapter(drugAdapter);
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

                if (generatedDrugs.get(brandName).equals(generatedDrugs.values().toArray()[brand])) {
                    Toast.makeText(MainActivity.this, "You are awesome!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You suck!", Toast.LENGTH_SHORT).show();
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
        outState.putSerializable("generatedDrugs", (Serializable) generatedDrugs);

        Log.d("generatedBradSave",brandName);
        Log.d("generatedDrugsSave",generatedDrugs.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("brandName") && savedInstanceState.containsKey("generatedDrugs")) {
            brandName = savedInstanceState.getString("brandName");
            generatedDrugs = (HashMap<String, String>) savedInstanceState.getSerializable("generatedDrugs");
            Log.d("onRestoreInstanceState", "onRestoreInstanceState start!");
        }
        Log.d("generatedBradRestore",brandName);
        Log.d("generatedDrugsRestore",generatedDrugs.toString());
    }

}
