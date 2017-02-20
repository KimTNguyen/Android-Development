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

        Intent addDrugIntent = new Intent(this, AddDrugActivity.class);
        startActivity(addDrugIntent);

        Log.d("AddDataActivity","addDrug end!");
    }

    public void addPharmacyAbbreviations(View view) {
        Log.d("AddDataActivity","addPharmacyAbbreviations start!");

        Intent addAbbreviation = new Intent(this, AddPharmacyAbbreviationsActivity.class);
        startActivity(addAbbreviation);

        Log.d("AddDataActivity","addPharmacyAbbreviations end!");
    }
}
