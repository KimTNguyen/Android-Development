package projects.android.nguyen.kim.pharmacyTechPractice;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddDrugActivity extends AppCompatActivity {

    private PrintStream output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("function", "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        Log.d("function", "onCreate end!");
    }

    public void saveMedData(View view) {
        Log.d("saveData", "saveData start!");

        try {
            output = new PrintStream(openFileOutput(CommonConstants.MED_FILE, MODE_APPEND));
            String brand = ((EditText) findViewById(R.id.add_brand)).getText().toString();
            String generic = ((EditText) findViewById(R.id.add_generic)).getText().toString();
            String function = ((EditText) findViewById(R.id.add_function)).getText().toString();
            String direction = ((EditText) findViewById(R.id.add_direction)).getText().toString();

            if (isEmpty(brand)) {
                Toast.makeText(this, "brand is required!", Toast.LENGTH_SHORT).show();
            } else if (isEmpty(generic)) {
                Toast.makeText(this, "generic is required!", Toast.LENGTH_SHORT).show();
            } else {
                output.print(brand + CommonConstants.REGEX);
                output.print(generic + CommonConstants.REGEX);
                output.print(isEmpty(function) ? "to be updated" + CommonConstants.REGEX : function + CommonConstants.REGEX);
                output.println(isEmpty(direction) ? "to be updated" : direction);
            }

            clearData((EditText) findViewById(R.id.add_brand));
            clearData((EditText) findViewById(R.id.add_generic));
            clearData((EditText) findViewById(R.id.add_function));
            clearData((EditText) findViewById(R.id.add_direction));

            output.close();
            Log.d("add_brand",((EditText) findViewById(R.id.add_brand)).getText().toString());
        } catch (FileNotFoundException e){
            Log.e("FileNotFoundException", "Cannot open DATABASE_FILE", e);
        }

        Log.d("saveData", "saveData end!");
    }

    /**
     * Compares the string with an empty string
     * @param str the String to be compared
     * @return true if the String is empty, false otherwise
     */
    private boolean isEmpty(String str) {
        if ("".equals(str)) {
            return true;
        }

        return false;
    }

    /**
     * Sets the EditText field to blank
     * @param editText the EditText that will be clear
     */
    public void clearData(EditText editText) {
        Log.d("clearData", "clearData start!");

        editText.setText("");

        Log.d("clearData", "clearData end!");
    }
}
