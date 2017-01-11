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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    final int NO_GENERIC_ON_SCREEN = 5;
    private Map<String,String> generatedDrugArray = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pickDrugName();
    }

    /**
     * Sets text for the TextView with the random brand name
     *
     * @return the random brand name
     */
    protected String displayBrandName() {
        Random brandPos = new Random();
        TextView genericView = (TextView) findViewById(R.id.brand);

        Log.d("nameList", generatedDrugArray.keySet().toString());

        /* Generates a random number from the set of drug name */
        String brandName = (String) generatedDrugArray.keySet().toArray()[brandPos.nextInt(NO_GENERIC_ON_SCREEN)];
        Log.d("generatedBrad",brandName);

        genericView.setText(brandName);
        return brandName;
    }

    /**
     * Gives feedback right or wrong when the user clicks on the generic name on the list
     */
    protected void pickDrugName() {
        generatedDrugArray.clear();
        generatedDrugArray = generateListDrugs();
        final String nameGenerated = displayBrandName();
        ListView drugView = (ListView) findViewById(R.id.drug_list);

        Log.d("genericList", generatedDrugArray.values().toString());

        /* Displays a list of random generic name on the screen */
        ArrayAdapter<String> drugAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, generatedDrugArray.values().toArray(new String[0]));
        drugView.setAdapter(drugAdapter);

        drugView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int brand, long l) {
                Log.d("position", "the user click on: " + brand);
                Log.d("position", "the user click on: " + generatedDrugArray.values().toArray()[brand]);
                Log.d("position", "the user click on: " + generatedDrugArray.get(nameGenerated));

                if (generatedDrugArray.get(nameGenerated).equals(generatedDrugArray.values().toArray()[brand])) {
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
//        drugData.put("z-pak", "azithromycin 250mg");
//        drugData.put("tri-pak", "azithromycin 500mg");
//        drugData.put("medrol", "methylprednisolone");
//        drugData.put("soma", "carisoprodol");
//        drugData.put("peridex", "chlorhexidine");
//        drugData.put("microbid", "nitrofurantoin");
//        drugData.put("amoxil", "amoxicillin");
//        drugData.put("keflex", "cephalexcin");
//        drugData.put("flonase", "fluticasone");
//        drugData.put("cheratussin ac", "guaifenesin + codeine");
//        drugData.put("HCTZ", "hydroclorothyazide");
//        drugData.put("glucophage", "metformin");
//        drugData.put("actos", "pioglitazone");
//        drugData.put("tamiflu", "oseltamivir");
//        drugData.put("synthroid", "levothyroxine");
//        drugData.put("advil", "ibuprofen");
//        drugData.put("mobic", "meloxicam");
//        drugData.put("celebrex", "celecoxib");
//        drugData.put("augmentin", "amoxicillin + clavulanate");
//        drugData.put("lipitor", "atorvastatin");
//        drugData.put("zocor", "simvastatin");
//        drugData.put("crestor", "rosuvastatin");
//        drugData.put("vytorin", "ezetimibe + simvastatin");
//        drugData.put("prinivil", "lisinopril");
//        drugData.put("altace", "ramipril");
//        drugData.put("vasotec", "enalapril");
//        drugData.put("vicotin", "hydrocodone");
//        drugData.put("oxycotin", "oxycodone");
//        drugData.put("ultram", "tramadol");
//        drugData.put("tylenol3", "codeine + APAP");

        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.druglist));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split("\t");
            if (data.length >= 2) {
                drugData.put(data[0],data[1]);
            }
        }

        scanner.close();

        Log.d("drug list",drugData.toString());

        return drugData;
    }

    /**
     * Generates random drugs list to appear on the screen
     *
     * @return the map of the random key, value drugs'name and its generic
     */
    private Map<String,String> generateListDrugs() {
        Map<String,String> generatedDrugs = new HashMap<>();
        Map<String, String> brandAndGeneric = initDrugData();

        while (generatedDrugs.size() != NO_GENERIC_ON_SCREEN) {
            Random randNo = new Random();
            int randNum = randNo.nextInt(brandAndGeneric.size());
            generatedDrugs.put((String) brandAndGeneric.keySet().toArray()[randNum],
                    (String) brandAndGeneric.values().toArray()[randNum]);
        }

        Log.d("generatedDrugs",generatedDrugs.toString());
        return generatedDrugs;
    }
}
