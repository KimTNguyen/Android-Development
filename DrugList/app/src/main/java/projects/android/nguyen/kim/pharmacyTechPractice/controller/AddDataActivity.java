package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import projects.android.nguyen.kim.pharmacyTechPractice.R;

public class AddDataActivity extends AppCompatActivity {

    private static final String TAG = "AddDataActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AddDataActivity", "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        Log.d(TAG, "onCreate end!");
    }

    public void addDrug(View view) {
        Log.d("AddDataActivity", "addDrug start!");

        startActivity(new Intent(this, AddDrugActivity.class));

        Log.d(TAG, "addDrug end!");
    }

    public void addPharmacyAbbreviations(View view) {
        Log.d(TAG, "addPharmacyAbbreviations start!");

        startActivity(new Intent(this, AddPharmacyAbbreviationActivity.class));

        Log.d(TAG, "addPharmacyAbbreviations end!");
    }
}
