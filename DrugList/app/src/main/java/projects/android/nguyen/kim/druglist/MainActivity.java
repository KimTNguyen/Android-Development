package projects.android.nguyen.kim.druglist;

import android.opengl.EGLExt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final String[] DRUGS = {"z-pak", "azithromycin 250mg",
            "tri-pak", "azithromycin 500mg", "medrol",
            "methylprednisolone","soma", "carisoprodol",
            "peridex", "chlorhexidine", "microbid", "nitrofurantoin",
            "amoxil", "amoxicillin", "keflex", "cephalexcin", "flonase",
            "fluticasone", "cheratussin ac", "guaifenesin + codeine",
            "HCTZ", "hydroclorothyazide"};

    private Map<String, String> brandAndGeneric = new HashMap<>();
    private final ArrayList<String> brandArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int index = 0; index < DRUGS.length; index += 2) {
            brandArray.add(DRUGS[index]);
            brandAndGeneric.put(DRUGS[index], DRUGS[index + 1]);
        }

        pickDrugName();
    }

    protected String generateGeneric() {
        // Set text view with a random brand name
        Random brandPos = new Random();
        String genericGenerate = DRUGS[brandPos.nextInt(brandArray.size()) * 2 + 1];

        TextView genericView = (TextView) findViewById(R.id.generic);
        genericView.setText(genericGenerate);
        return genericGenerate;
    }

    protected void pickDrugName() {

        final String genericGenerate = generateGeneric();

        ListView drugView = (ListView) findViewById(R.id.drug_list);

        final ArrayAdapter<String> drugAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, brandArray);
        drugView.setAdapter(drugAdapter);

        drugView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int brand, long l) {
                Log.d("position", "the user click on: " + brand);
                String genericChosen = brandAndGeneric.get(brandArray.get(brand));

                if (genericChosen.equals(genericGenerate)) {
                    Toast.makeText(MainActivity.this, "You are awesome!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You suck!", Toast.LENGTH_SHORT).show();
                }
                pickDrugName();
            }
        });
    }


}
