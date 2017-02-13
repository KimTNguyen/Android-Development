package projects.android.nguyen.kim.pharmacyTechPractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void addDrug(View view) {
        Intent addDrugIntent = new Intent(this, AddDataActivity.class);
        startActivity(addDrugIntent);
    }

    public void takeQuiz(View view) {
        Intent takeQuizIntent = new Intent(this, QuizActivity.class);
        startActivity(takeQuizIntent);
    }

    public void searchControlledSubstances(View view) {
        Intent searchDrugsIntent = new Intent(this, ScheduledDrugActivity.class);
        startActivity(searchDrugsIntent);
    }

    public void testSigCode(View view) {
        Intent testSigCodeIntent = new Intent(this, TestSigCodeActivity.class);
        startActivity(testSigCodeIntent);
    }
}
