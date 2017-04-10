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
 *
 * Modified by Kim Nguyen on 04/09/2017
 */
public class DrugDetailFragment extends Fragment {

    protected static final String EXTRA_DOSE_FORM = "doseForm";
    protected static final String EXTRA_FUNCTION = "function";
    protected static final String EXTRA_SIDE_EFFECT = "sideEffect";
    protected static final String EXTRA_COMMENTS = "comments";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drug_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String doseForm = getActivity().getIntent().getStringExtra(EXTRA_DOSE_FORM);
        String function = getActivity().getIntent().getStringExtra(EXTRA_FUNCTION);
        String sideEffects = getActivity().getIntent().getStringExtra(EXTRA_SIDE_EFFECT);
        String comments = getActivity().getIntent().getStringExtra(EXTRA_COMMENTS);
        display(doseForm, function, sideEffects, comments);
    }

    public void display(String doseFormText, String functionText, String sideEffectsText,
                        String CommentsText) {
        TextView doseForm = (TextView) getActivity().findViewById(R.id.dose_form);
        TextView function = (TextView) getActivity().findViewById(R.id.common_uses);
        TextView sideEffects = (TextView) getActivity().findViewById(R.id.side_effects);
        TextView comments = (TextView) getActivity().findViewById(R.id.comments);

        doseForm.setText(doseFormText);
        function.setText(functionText);
        sideEffects.setText(sideEffectsText);
        comments.setText(CommentsText);
    }

}
