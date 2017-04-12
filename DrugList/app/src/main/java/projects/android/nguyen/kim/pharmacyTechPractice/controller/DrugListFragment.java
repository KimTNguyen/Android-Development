package projects.android.nguyen.kim.pharmacyTechPractice.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projects.android.nguyen.kim.pharmacyTechPractice.R;

/**
 * DrugListFragment holds basic drugs info to be displays on the screen as a list.
 *
 * @author Kim Nguyen
 * @version 04/12/2017
 */
public class DrugListFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drug_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.drug_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
