package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import projects.android.nguyen.kim.pharmacyTechPractice.R;

/**
 * DrugDetailFragment displays the functions and usage of the drug.
 *
 * @author Kim Nguyen
 * @version 20-March-2017
 */
public class DrugDetailFragment extends Fragment {

    protected static final String EXTRA_DRUG_DETAILS = "drug_details";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drug_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String text = getActivity().getIntent().getStringExtra(EXTRA_DRUG_DETAILS);
        display(text);
    }

    public void display(String text) {
        TextView detail = (TextView) getActivity().findViewById(R.id.direction);
        detail.setText(text);
    }

}
