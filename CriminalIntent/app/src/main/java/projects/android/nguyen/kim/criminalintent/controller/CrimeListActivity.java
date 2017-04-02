package projects.android.nguyen.kim.criminalintent.controller;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
