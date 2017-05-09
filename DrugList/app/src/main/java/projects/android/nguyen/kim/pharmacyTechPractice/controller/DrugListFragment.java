package projects.android.nguyen.kim.pharmacyTechPractice.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import projects.android.nguyen.kim.pharmacyTechPractice.CommonConstants;
import projects.android.nguyen.kim.pharmacyTechPractice.R;
import projects.android.nguyen.kim.pharmacyTechPractice.logic.DrugLab;
import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugModel;

/**
 * DrugListFragment holds basic drugs info to be displays on the screen as a list.
 *
 * @author Kim Nguyen
 * @version 04/12/2017
 * Modified by Kim Nguyen on 5/9/2017
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

        updateUI();

        return view;
    }

    private void updateUI() {
        DrugLab drugLab = DrugLab.get(getActivity());
        List<DrugModel> drugs = drugLab.getDrugList();
        DrugAdapter adapter = new DrugAdapter(drugs);
        recyclerView.setAdapter(adapter);
    }

    private class DrugHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView brandTextView;
        private TextView genericTextView;
        private TextView scheduledTextView;
        private DrugModel drug;

        DrugHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_drug, parent, false));

            brandTextView = (TextView) itemView.findViewById(R.id.brand);
            genericTextView = (TextView) itemView.findViewById(R.id.generic);
            scheduledTextView = (TextView) itemView.findViewById(R.id.scheduled);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), drug.getBrand() + " clicked", Toast.LENGTH_SHORT).show();
        }

        void bind(DrugModel drug) {
            this.drug = drug;
            brandTextView.setText(this.drug.getBrand());
            genericTextView.setText(this.drug.getGeneric());
            scheduledTextView.setText(this.drug.getScheduled());
            scheduledTextView.setVisibility(drug.getScheduled().equalsIgnoreCase
                    (CommonConstants.NONE_CONTROLLED_SUBSTANCES)? View.GONE : View.VISIBLE);
        }
    }

    private class DrugAdapter extends RecyclerView.Adapter<DrugHolder> {

        private List<DrugModel> drugs;

        DrugAdapter(List<DrugModel> drugs) {
            this.drugs = drugs;
        }

        @Override
        public DrugHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            return new DrugHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(DrugHolder holder, int position) {
            DrugModel drug = drugs.get(position);
            holder.bind(drug);
        }

        @Override
        public int getItemCount() {
            return drugs.size();
        }
    }
}
