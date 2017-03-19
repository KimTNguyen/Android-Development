package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import projects.android.nguyen.kim.pharmacyTechPractice.R;

public class QuizHintActivity extends AppCompatActivity {

    protected static final String EXTRA_HINT =
            "projects.android.nguyen.kim.pharmacyTechPractice.controller";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_hint);

        TextView hintView = (TextView) findViewById(R.id.quiz_hint);
        hintView.setText(getIntent().getStringExtra(EXTRA_HINT));
    }
}
