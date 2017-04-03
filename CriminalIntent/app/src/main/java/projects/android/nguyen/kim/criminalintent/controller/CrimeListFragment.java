package projects.android.nguyen.kim.criminalintent.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import projects.android.nguyen.kim.criminalintent.R;
import projects.android.nguyen.kim.criminalintent.model.Crime;
import projects.android.nguyen.kim.criminalintent.model.CrimeLab;

/**
 * Created by Kimmy on 3/31/2017.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_crime_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;

        private CrimeHolder(int res, LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(res, parent, false));

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }
    }

    private class CrimeHolderRequiredPolice extends CrimeHolder {
        Button mCallPoliceButton;

        private CrimeHolderRequiredPolice(int res, LayoutInflater inflater, ViewGroup parent) {
            super(res, inflater, parent);

            mCallPoliceButton = (Button) itemView.findViewById(R.id.crime_requires_police_button);

            mCallPoliceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "call police", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;
        private static final int MISDEMEANOR = 0;
        private static final int FELONY = 1;

        CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            if (viewType == MISDEMEANOR) {
                return new CrimeHolder(R.layout.list_item_crime, layoutInflater, parent);
            }
            return new CrimeHolderRequiredPolice(R.layout.list_item_crime_requires_police, layoutInflater, parent);

        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (mCrimes.get(position).isRequiresPolice())
            {
                return MISDEMEANOR;
            } else {
                return FELONY;
            }
        }
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        mRecyclerView.setAdapter(new CrimeAdapter(crimeLab.getCrimes()));
    }
}
