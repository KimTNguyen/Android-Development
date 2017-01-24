package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Scanner;

public class ScheduledDrugActivity extends AppCompatActivity {

    private ArrayAdapter<String> drugAdapter;
    private ArrayList<String> scheduledDrugList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("function", "onCreate start!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_drug);
        initScheduledDrugList();
        display();
        search();
        Log.d("function", "onCreate end!");
    }

    /**
     * Reads data from the raw file and store them in the scheduledDrugList
     */
    private void initScheduledDrugList() {
        Log.d("function", "initScheduledDrugList start!");
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.scheduled_drugs));

        while (scanner.hasNext()) {
            scheduledDrugList.add(scanner.nextLine());
        }

        Log.d("scheduledDrugList", scheduledDrugList.toString());
        Log.d("function", "initScheduledDrugList end!");
    }

    /**
     * Displays the scheduledDrugList on the screen
     */
    private void display() {
        Log.d("function", "display start!");

        ListView listView = (ListView) findViewById(R.id.scheduled_drug_list);

        drugAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scheduledDrugList);
        listView.setAdapter(drugAdapter);

        Log.d("function", "display end!");
    }

    /**
     * Finds elements matching the input
     */
    private void search() {
        Log.d("function","search start!");

        EditText searchScheduledDrug = (EditText) findViewById(R.id.search_scheduled_drug);
        searchScheduledDrug.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ScheduledDrugActivity.this.drugAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Log.d("function", "search end!");
    }
}
