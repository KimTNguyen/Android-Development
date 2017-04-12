package projects.android.nguyen.kim.pharmacyTechPractice.logic;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import projects.android.nguyen.kim.pharmacyTechPractice.R;
import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugModel;

/**
 * Drug Lab is a singleton class responsible for generating a list of drugs.
 *
 * Created by Kimmy on 4/10/2017.
 */

public class DrugLab {
    private final String TAG = "DrugLab";
    private static DrugLab drugLab;

    private List<DrugModel> drugList;

    private DrugLab(Context context) {
        drugList = new ArrayList<>();
        initDrugList(context);
    }

    public static DrugLab get(Context context) {
        if (drugLab == null) {
            return new DrugLab(context);
        }

        return drugLab;
    }

    public List<DrugModel> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<DrugModel> drugList) {
        this.drugList = drugList;
    }

    /**
     * Reads data from the raw file and store them in the scheduledDrugList
     */
    private void initDrugList(Context context) {
        Log.d(TAG, "initDrugList start!");

        Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.scheduled_drugs));

        while (scanner.hasNext()) {
            final int NAME = 0;
            final int GENERIC = 1;
            final int SCHEDULED = 2;

            DrugModel drug = new DrugModel();
            String[] line = scanner.nextLine().split("\t");
            drug.setBrand(line[NAME]);
            drug.setGeneric(line[GENERIC]);
            drug.setScheduled(line[SCHEDULED]);
            drugList.add(drug);
        }

        Log.d(TAG, "scheduled drug list: " + drugList.toString());

        Log.d(TAG, "initDrugList end!");
    }

}
