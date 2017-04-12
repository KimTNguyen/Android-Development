package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import projects.android.nguyen.kim.pharmacyTechPractice.R;

public class MainMenuActivity extends AppCompatActivity {

    private static final String TAG = "MainMenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button searchDrug = (Button) findViewById(R.id.search_drug);
        searchDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), DrugListActivity.class));
            }
        });

        Log.d(TAG, "onCreate end!");
    }

    public void addDrug(View view) {
        Log.d(TAG, "addDrug start!");

        startActivity(new Intent(this, AddDataActivity.class));

        Log.d(TAG, "addDrug end!");
    }

    public void takeQuiz(View view) {
        Log.d(TAG, "takeQuiz start!");

        startActivity(new Intent(this, QuizActivity.class));

        Log.d(TAG, "takeQuiz end!");
    }

    public void testSigCode(View view) {
        Log.d(TAG, "testSigCode start!");

        startActivity(new Intent(this, TestSigCodeActivity.class));

        Log.d(TAG, "testSigCode end!");
    }
}
