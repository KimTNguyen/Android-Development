package projects.android.nguyen.kim.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Kimmy on 3/31/2017.
 */

public class CrimeLab {
    private static CrimeLab mCrimeLab;
    private Context mAppContext;
    private List<Crime> mCrimes;

    private CrimeLab(Context context) {
        mAppContext = context;
        mCrimes = new ArrayList<>();

        for (int i = 0; i < 100; ++i) {
            Crime crime = new Crime();
            crime.setTitle("Crime #: " + i);
            crime.setSolved(i % 2 == 0);
            crime.setRequiresPolice(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public static CrimeLab get(Context context) {
        if (mCrimeLab == null) {
            mCrimeLab = new CrimeLab(context.getApplicationContext());
        }

        return mCrimeLab;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }

        return null;
    }
}
