package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddDrugActivity extends AppCompatActivity implements AddScreenInterface {

    private EditText brandEditText;
    private EditText genericEditText;
    private EditText functionEditText;
    private EditText directionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AddDrugActivity", "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        brandEditText = (EditText) findViewById(R.id.add_brand);
        genericEditText = (EditText) findViewById(R.id.add_generic);
        functionEditText = (EditText) findViewById(R.id.add_function);
        directionEditText = (EditText) findViewById(R.id.add_direction);

        Log.d("AddDrugActivity", "onCreate end!");
    }

    public void saveData(View view) {
        Log.d("AddDrugActivity", "saveData start!");

        try (PrintStream output = new PrintStream(openFileOutput(CommonConstants.MED_FILE, MODE_APPEND))) {
            String brand = brandEditText.getText().toString().trim();
            String generic = genericEditText.getText().toString().trim();
            String function = functionEditText.getText().toString();
            String direction = directionEditText.getText().toString();

            if (Utils.isEmpty(brand)) {
                brandEditText.setError("brand is required!");
            } else if (Utils.isEmpty(generic)) {
                genericEditText.setError("generic is required!");
            } else {
                output.print(brand + CommonConstants.REGEX_TAB);
                output.print(generic + CommonConstants.REGEX_TAB);
                output.print(Utils.isEmpty(function) ? "to be updated" + CommonConstants.REGEX_TAB : function + CommonConstants.REGEX_TAB);
                output.println(Utils.isEmpty(direction) ? "to be updated" : direction);
            }

            Log.d("add_brand",((EditText) findViewById(R.id.add_brand)).getText().toString());

            clearScreen(view);
            brandEditText.requestFocus();

        } catch (FileNotFoundException e){
            Log.e("FileNotFoundException", "Cannot open DATABASE_FILE", e);
        }

        Log.d("AddDrugActivity", "saveData end!");
    }

    public void clearScreen(View view) {
        Log.d("AddDrugActivity", "clearScreen start!");

        Utils.clearData(brandEditText);
        Utils.clearData(genericEditText);
        Utils.clearData(functionEditText);
        Utils.clearData(directionEditText);

        Log.d("AddDrugActivity", "clearScreen end!");
    }
}
