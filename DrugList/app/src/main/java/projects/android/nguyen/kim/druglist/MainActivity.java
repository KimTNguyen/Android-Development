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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Map<String, String> brandAndGeneric = new HashMap<>();
    private final ArrayList<String> genericArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brandAndGeneric = initDrugData();

        for (String value:brandAndGeneric.values()) {
            genericArray.add(value);
        }

        pickDrugName();
    }

    /**
     * Sets text for the TextView with the random brand name
     *
     * @return the random brand name
     */
    protected String displayBrandName() {
        Random brandPos = new Random();
        TextView genericView = (TextView) findViewById(R.id.generic);

        /* Generates a random number from the set of drug name */
        String brandName = (String) brandAndGeneric.keySet().toArray()[brandPos.nextInt(genericArray.size())];

        genericView.setText(brandName);
        return brandName;
    }

    /**
     * Gives feedback right or wrong when the user clicks on the generic name on the list
     */
    protected void pickDrugName() {
        final String nameGenerated = displayBrandName();
        ListView drugView = (ListView) findViewById(R.id.drug_list);
        final ArrayAdapter<String> drugAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genericArray);

        drugView.setAdapter(drugAdapter);

        drugView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int brand, long l) {
                Log.d("position", "the user click on: " + brand);
                Log.d("position", "the user click on: " + genericArray.get(brand));
                Log.d("position", "the user click on: " + brandAndGeneric.get(nameGenerated));

                if (brandAndGeneric.get(nameGenerated).equals(genericArray.get(brand))) {
                    Toast.makeText(MainActivity.this, "You are awesome!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You suck!", Toast.LENGTH_SHORT).show();
                }
                pickDrugName();
            }
        });
    }

    /**
     * Initializes the drug data in the form of Map data structure
     *
     * @return the map of drug's brand name and its generic
     */
    private Map<String, String> initDrugData() {

        Map<String, String> drugData = new HashMap<>();
        drugData.put("z-pak", "azithromycin 250mg");
        drugData.put("tri-pak", "azithromycin 500mg");
        drugData.put("medrol", "methylprednisolone");
        drugData.put("soma", "carisoprodol");
        drugData.put("peridex", "chlorhexidine");
        drugData.put("microbid", "nitrofurantoin");
        drugData.put("amoxil", "amoxicillin");
        drugData.put("keflex", "cephalexcin");
        drugData.put("flonase", "fluticasone");
        drugData.put("cheratussin ac", "guaifenesin + codeine");
        drugData.put("HCTZ", "hydroclorothyazide");

        return drugData;
    }
}
