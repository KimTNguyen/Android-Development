package projects.android.nguyen.kim.pharmacyTechPractice.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projects.android.nguyen.kim.data.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrugDetailFragment extends Fragment {


    public DrugDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drug_detail, container, false);
    }

}
