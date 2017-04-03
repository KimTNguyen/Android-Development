package projects.android.nguyen.kim.criminalintent.controller;

import android.support.v4.app.Fragment;

/**
 * Created by Kimmy on 3/31/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
