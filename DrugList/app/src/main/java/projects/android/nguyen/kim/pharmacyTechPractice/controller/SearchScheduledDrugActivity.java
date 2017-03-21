package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Scanner;

import projects.android.nguyen.kim.pharmacyTechPractice.R;

/**
 * SearchScheduledDrugActivity allows the user to check whether a brand or generic is scheduled.
 *
 * @author Kim Nguyen
 * @version 20-March-2017
 */
public class SearchScheduledDrugActivity extends AppCompatActivity {

    private static final String TAG = "ScheduledDrugActivity";
    private ArrayAdapter<String> drugAdapter;
    private ArrayList<String> scheduledDrugList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_drug);
        initScheduledDrugList();
        display();
        search();

        Log.d(TAG, "onCreate end!");
    }

    /**
     * Reads data from the raw file and store them in the scheduledDrugList
     */
    private void initScheduledDrugList() {
        Log.d(TAG, "initScheduledDrugList start!");

        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.scheduled_drugs));

        while (scanner.hasNext()) {
            scheduledDrugList.add(scanner.nextLine());
        }

        Log.d(TAG, "scheduled drug list: " + scheduledDrugList.toString());

        Log.d(TAG, "initScheduledDrugList end!");
    }

    /**
     * Displays the scheduledDrugList on the screen
     */
    private void display() {
        Log.d(TAG, "display start!");

        ListView listView = (ListView) findViewById(R.id.scheduled_drug_list);

        drugAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                scheduledDrugList);
        listView.setAdapter(drugAdapter);

        Log.d(TAG, "display end!");
    }

    /**
     * Finds elements matching the input
     */
    private void search() {
        Log.d(TAG, "search start!");

        EditText searchScheduledDrug = (EditText) findViewById(R.id.search_scheduled_drug);
        searchScheduledDrug.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SearchScheduledDrugActivity.this.drugAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Log.d(TAG, "search end!");
    }
}
