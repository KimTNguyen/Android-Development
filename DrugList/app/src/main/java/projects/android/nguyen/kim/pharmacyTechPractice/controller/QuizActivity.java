/*
 * QuizActivity provides a list of drug's generic for the user to match with the brand name displayed
 * accordingly.
 *
 * @author Kim Nguyen
 * @version 09-Jan-2017
 * Modified by Kim Nguyen on 3/16/17
 * Modified by Kim Nguyen on 3/20/17
 */

package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import projects.android.nguyen.kim.pharmacyTechPractice.R;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }
}
