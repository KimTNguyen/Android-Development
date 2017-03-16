package projects.android.nguyen.kim.pharmacyTechPractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AddDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AddDataActivity","onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        Log.d("AddDataActivity","onCreate end!");
    }

    public void addDrug(View view) {
        Log.d("AddDataActivity","addDrug start!");

        startActivity(new Intent(this, AddDrugActivity.class));

        Log.d("AddDataActivity","addDrug end!");
    }

    public void addPharmacyAbbreviations(View view) {
        Log.d("AddDataActivity","addPharmacyAbbreviations start!");

        startActivity(new Intent(this, AddPharmacyAbbreviationActivity.class));

        Log.d("AddDataActivity","addPharmacyAbbreviations end!");
    }
}
