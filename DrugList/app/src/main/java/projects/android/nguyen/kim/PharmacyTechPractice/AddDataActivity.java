package projects.android.nguyen.kim.pharmacyTechPractice;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddDataActivity extends AppCompatActivity {

    static final String DATABASE_FILE = "drug_data.txt";
    static final String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        Log.d("file_path",path);
    }

    public void saveData(View view) {
        try {
            PrintStream output = new PrintStream(openFileOutput(AddDataActivity.DATABASE_FILE, MODE_APPEND));
            output.print(((EditText) findViewById(R.id.add_brand)).getText().toString()+"\t");
            output.print(((EditText) findViewById(R.id.add_generic)).getText().toString()+"\t");
            output.print(((EditText) findViewById(R.id.add_function)).getText().toString()+"\t");
            output.println(((EditText) findViewById(R.id.add_direction)).getText().toString());
            output.close();
            finish();
            Log.d("add_brand",((EditText) findViewById(R.id.add_brand)).getText().toString());
        } catch (FileNotFoundException e){
            Log.e("FileNotFoundException", "Cannot open DATABASE_FILE", e);
        }

    }
}
