package projects.android.nguyen.kim.pharmacyTechPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddDrugActivityInterface extends AppCompatActivity implements AddScreenInterface {

    private EditText brandEditText;
    private EditText genericEditText;
    private EditText functionEditText;
    private EditText directionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("function", "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        brandEditText = (EditText) findViewById(R.id.add_brand);
        genericEditText = (EditText) findViewById(R.id.add_generic);
        functionEditText = (EditText) findViewById(R.id.add_function);
        directionEditText = (EditText) findViewById(R.id.add_direction);

        Log.d("function", "onCreate end!");
    }

    public void saveData(View view) {
        Log.d("saveData", "saveData start!");

        try (PrintStream output = new PrintStream(openFileOutput(CommonConstants.MED_FILE, MODE_APPEND))) {
            String brand = brandEditText.getText().toString();
            String generic = genericEditText.getText().toString();
            String function = functionEditText.getText().toString();
            String direction = directionEditText.getText().toString();

            if (Utils.isEmpty(brand)) {
                Toast.makeText(this, "brand is required!", Toast.LENGTH_SHORT).show();
            } else if (Utils.isEmpty(generic)) {
                Toast.makeText(this, "generic is required!", Toast.LENGTH_SHORT).show();
            } else {
                output.print(brand + CommonConstants.REGEX);
                output.print(generic + CommonConstants.REGEX);
                output.print(Utils.isEmpty(function) ? "to be updated" + CommonConstants.REGEX : function + CommonConstants.REGEX);
                output.println(Utils.isEmpty(direction) ? "to be updated" : direction);
            }

            Log.d("add_brand",((EditText) findViewById(R.id.add_brand)).getText().toString());

            clearScreen(view);

        } catch (FileNotFoundException e){
            Log.e("FileNotFoundException", "Cannot open DATABASE_FILE", e);
        }

        Log.d("saveData", "saveData end!");
    }

    public void clearScreen(View view) {
        Utils.clearData(brandEditText);
        Utils.clearData(genericEditText);
        Utils.clearData(functionEditText);
        Utils.clearData(directionEditText);
    }
}
