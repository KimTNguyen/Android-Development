package projects.android.nguyen.kim.pharmacyTechPractice;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddDataActivity extends AppCompatActivity {

    static final String DATABASE_FILE = "drug_data.txt";
    static final String path = Environment.getExternalStorageDirectory().getAbsolutePath();

    private PrintStream output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        Log.d("file_path",path);
    }

    public void saveData(View view) {
        Log.d("saveData", "saveData start!");

        try {
            output = new PrintStream(openFileOutput(AddDataActivity.DATABASE_FILE, MODE_APPEND));
            String brand = ((EditText) findViewById(R.id.add_brand)).getText().toString();
            String generic = ((EditText) findViewById(R.id.add_generic)).getText().toString();
            String function = ((EditText) findViewById(R.id.add_function)).getText().toString();
            String direction = ((EditText) findViewById(R.id.add_direction)).getText().toString();

            if (brand.equals("") || generic.equals("")) {
                Toast.makeText(this,"brand is required!",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"generic is required!",Toast.LENGTH_SHORT).show();
                clearData(view);
            } else {
                output.print(brand +"\t");
                output.print(generic +"\t");

                if (function.equals("")) {
                    output.print("to be updated\t");
                } else {
                    output.print(function +"\t");
                }

                if (direction.equals("")) {
                    output.print("to be updated\t");
                } else {
                    output.println(direction);
                }
            }

            output.close();
            Log.d("add_brand",((EditText) findViewById(R.id.add_brand)).getText().toString());
        } catch (FileNotFoundException e){
            Log.e("FileNotFoundException", "Cannot open DATABASE_FILE", e);
        }

        Log.d("saveData", "saveData end!");
    }

    public void clearData(View view) {
        Log.d("clearData", "clearData start!");

        ((EditText) findViewById(R.id.add_brand)).setText("");
        ((EditText) findViewById(R.id.add_generic)).setText("");
        ((EditText) findViewById(R.id.add_function)).setText("");
        ((EditText) findViewById(R.id.add_direction)).setText("");

        Log.d("clearData", "clearData end!");
    }
}
