package projects.android.nguyen.kim.pharmacyTechPractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainMenuActivity","onCreate start!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Log.d("MainMenuActivity","onCreate end!");
    }

    public void addDrug(View view) {
        Log.d("MainMenuActivity","addDrug start!");

        Intent addDrugIntent = new Intent(this, AddDataActivity.class);
        startActivity(addDrugIntent);

        Log.d("MainMenuActivity","addDrug end!");
    }

    public void takeQuiz(View view) {
        Log.d("MainMenuActivity","takeQuiz start!");

        Intent takeQuizIntent = new Intent(this, QuizActivity.class);
        startActivity(takeQuizIntent);

        Log.d("MainMenuActivity","takeQuiz end!");
    }

    public void searchControlledSubstances(View view) {
        Log.d("MainMenuActivity","searchControlledSubstances start!");

        Intent searchDrugsIntent = new Intent(this, ScheduledDrugActivity.class);
        startActivity(searchDrugsIntent);

        Log.d("MainMenuActivity","searchControlledSubstances end!");
    }

    public void testSigCode(View view) {
        Log.d("MainMenuActivity","testSigCode start!");

        Intent testSigCodeIntent = new Intent(this, TestSigCodeActivity.class);
        startActivity(testSigCodeIntent);

        Log.d("MainMenuActivity","testSigCode end!");
    }
}
