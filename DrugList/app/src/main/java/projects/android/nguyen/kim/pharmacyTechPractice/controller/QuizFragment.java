package projects.android.nguyen.kim.pharmacyTechPractice.controller;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import projects.android.nguyen.kim.pharmacyTechPractice.R;
import projects.android.nguyen.kim.pharmacyTechPractice.logic.QuizLogic;

/**
 * QuizFragment provides a list of drug's generic for the user to match with the brand name displayed
 * accordingly.
 *
 * @author Kim Nguyen
 * @version 20-March-2017
 *
 * Modified by Kim Nguyen on 03/21/2017
 * Modified by Kim Nguyen on 04/9/2017
 */
public class QuizFragment extends Fragment {

    private static final String TAG = "QuizFragment";
    private static final String BRAND_SAVE_TAG = "brandName";
    private static final String DOSE_FORMS_SAVE_TAG = "doseForm";
    private static final String FUNCTION_SAVE_TAG = "function";
    private static final String SIDE_EFFECTS_TAG = "sideEffects";
    private static final String COMMENTS_TAG = "comments";
    private static final String CORRECT_ANSWER_SAVE_TAG = "correctAnswer";
    private static final String GENERATED_LIST_DRUGS_SAVE_TAG = "generatedDrugs";

    private Context context;
    private ListView drugView;
    private TextView brandView;
    private TextView hintView;
    private QuizLogic logic;
    private Map<String, String> generatedDrugs = new HashMap<>();
    private String brandName = null;
    private String doseForm = null;
    private String function = null;
    private String sideEffects = null;
    private String comments = null;
    private String correctAnswer = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "onCreate start!");

        context = getActivity().getApplicationContext();
        drugView = (ListView) getActivity().findViewById(R.id.drug_list);
        brandView = (TextView) getActivity().findViewById(R.id.brand);
        hintView = (TextView) getActivity().findViewById(R.id.quiz_hint);

        logic = new QuizLogic(context);
        setListDrugs();
        if (generatedDrugs.size() == 0) {
            getActivity().finish();
            Log.e(TAG, "database is empty");
            Toast.makeText(getActivity(), "database is empty", Toast.LENGTH_SHORT).show();
        } else {
            setDrugInfo();
            if (savedInstanceState != null) {
                brandName = savedInstanceState.getString(BRAND_SAVE_TAG);
                doseForm = savedInstanceState.getString(DOSE_FORMS_SAVE_TAG);
                function = savedInstanceState.getString(FUNCTION_SAVE_TAG);
                sideEffects = savedInstanceState.getString(SIDE_EFFECTS_TAG);
                comments = savedInstanceState.getString(COMMENTS_TAG);
                correctAnswer = savedInstanceState.getString(CORRECT_ANSWER_SAVE_TAG);
                generatedDrugs = (HashMap<String, String>)
                        savedInstanceState.getSerializable(GENERATED_LIST_DRUGS_SAVE_TAG);
            }
        }

        Log.d(TAG, "onCreate end!");
    }
    
    private void setListDrugs() {
        logic.generateListDrugs();
        generatedDrugs = logic.getGeneratedDrugs();
    }

    private void setDrugInfo() {
        brandName = logic.generateBrandName();
        doseForm = logic.getDoseForms().get(brandName);
        function = logic.getFunction().get(brandName);
        sideEffects = logic.getSideEffects().get(brandName);
        comments = logic.getComments().get(brandName);
        correctAnswer = generatedDrugs.get(brandName);
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume start!");

        super.onResume();

        ImageView helpImageView = (ImageView) getActivity().findViewById(R.id.help_image_view);

        display();
        pickDrugName();

        // Shows answer when the user clicks on icon question mark
        helpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hintView.setText(correctAnswer + " is the generic of " + brandName);
            }
        });


        Log.d("generatedBradOnResume", brandName);

        Log.d(TAG, "onResume end!");
    }

    /**
     * Displays brand name and list of generic on the screen
     */
    protected void display() {
        Log.d(TAG, "display start!");

        brandView.setText(brandName);

        /* Displays a list of random generic name on the screen */
        ArrayAdapter<String> drugAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, generatedDrugs.values().toArray(new String[0]));
        drugView.setAdapter(drugAdapter);

        /* In landscape mode, shows function and direction on the same screen, otherwise, only shows
         * function and direction when the user click on brand name TextView
         */
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            brandView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DrugDetailActivity.class);
                    intent.putExtra(DrugDetailFragment.EXTRA_DOSE_FORM, doseForm);
                    intent.putExtra(DrugDetailFragment.EXTRA_FUNCTION, function);
                    intent.putExtra(DrugDetailFragment.EXTRA_SIDE_EFFECT, sideEffects);
                    intent.putExtra(DrugDetailFragment.EXTRA_COMMENTS, comments);
                    startActivity(intent);
                }
            });
        } else {
            DrugDetailFragment fragment = (DrugDetailFragment) getActivity().getFragmentManager().findFragmentById(R.id.drug_details_fragment);
            fragment.display(doseForm, function, sideEffects, comments);
        }

        Log.d(TAG, "display end!");
    }

    /**
     * Gives feedback right or wrong when the user clicks on the generic name on the list
     */
    private void pickDrugName() {
        Log.d(TAG, "pickDrugName start!");

        drugView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int brand, long l) {
                Log.d("position", "the user click on: " + brand);
                Log.d("position", "the user click on: " + generatedDrugs.values().toArray()[brand]);
                Log.d("position", "the right answer: " + generatedDrugs.get(brandName));

                /* Compares the name displayed and the name picked from the list */
                if (correctAnswer.equals(generatedDrugs.values().toArray()[brand])) {
                    Toast.makeText(getActivity(), "You are awesome!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "You suck!", Toast.LENGTH_SHORT).show();
                }
                hintView.setText("");
                setListDrugs();
                setDrugInfo();
                display();
            }
        }) ;

        Log.d(TAG, "pickDrugName end!");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState start!");

        super.onSaveInstanceState(outState);
        outState.putString(BRAND_SAVE_TAG, brandName);
        outState.putString(DOSE_FORMS_SAVE_TAG, doseForm);
        outState.putString(FUNCTION_SAVE_TAG, function);
        outState.putString(SIDE_EFFECTS_TAG, sideEffects);
        outState.putString(COMMENTS_TAG, comments);
        outState.putString(CORRECT_ANSWER_SAVE_TAG, correctAnswer);
        outState.putSerializable(GENERATED_LIST_DRUGS_SAVE_TAG, (Serializable) generatedDrugs);

        Log.d(BRAND_SAVE_TAG, brandName);
        Log.d(GENERATED_LIST_DRUGS_SAVE_TAG, generatedDrugs.toString());
        Log.d(CORRECT_ANSWER_SAVE_TAG, correctAnswer);

        Log.d(TAG, "onSaveInstanceState end!");
    }


}
