package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import projects.android.nguyen.kim.pharmacyTechPractice.R;

/**
 * DrugListActivity allows the user to check whether a brand or generic is scheduled.
 *
 * @author Kim Nguyen
 * @version 20-March-2017
 *
 * Modified by Kim Nguyen on 04/10/2017
 * Modified by Kim Nguyen on 04/12/2017
 */
public class DrugListActivity extends AppCompatActivity {

    private static final String TAG = "DrugListActivity";
//    private ArrayAdapter<String> drugAdapter;
//    private DrugLab drugLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new DrugListFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

        Log.d(TAG, "onCreate end!");
    }

//
//
//    /**
//     * Displays the scheduledDrugList on the screen
//     */
//    private void display() {
//        Log.d(TAG, "display start!");
//
//        ListView listView = (ListView) findViewById(R.id.scheduled_drug_list);
//
//        drugAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
//                scheduledDrugList);
//        listView.setAdapter(drugAdapter);
//
//        Log.d(TAG, "display end!");
//    }
//
//    /**
//     * Finds elements matching the input
//     */
//    private void search() {
//        Log.d(TAG, "search start!");
//
//        EditText searchScheduledDrug = (EditText) findViewById(R.id.search_scheduled_drug);
//        searchScheduledDrug.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                DrugListActivity.this.drugAdapter.getFilter().filter(charSequence);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        Log.d(TAG, "search end!");
//    }
}
