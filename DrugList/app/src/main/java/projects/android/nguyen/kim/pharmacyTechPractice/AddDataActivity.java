package projects.android.nguyen.kim.pharmacyTechPractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
    }

    public void addDrug(View view) {
        Intent addDrugIntent = new Intent(this, AddDrugActivity.class);
        startActivity(addDrugIntent);
    }

    public void addPharmacyAbbreviations(View view) {
        Intent addAbbreviation = new Intent(this, AddPharmacyAbbreviationsActivity.class);
        startActivity(addAbbreviation);
    }
}
