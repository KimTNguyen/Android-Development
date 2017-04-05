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
        Button mCallPoliceButton;
        private Crime mCrime;

        private CrimeHolder(int res, LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(res, parent, false));

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mCallPoliceButton = (Button) itemView.findViewById(R.id.crime_requires_police_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
                }
            });

            mCallPoliceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "call police", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }

        private void activatedCallButton(int status) {
            mCallPoliceButton.setVisibility(status);
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

            CrimeHolder holder = new CrimeHolder(R.layout.list_item_crime, layoutInflater, parent);

            if (viewType == MISDEMEANOR) {
                holder.activatedCallButton(View.GONE);
            } else {
                holder.activatedCallButton(View.VISIBLE);
            }

            return holder;
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
            return mCrimes.get(position).isRequiresPolice() ? FELONY : MISDEMEANOR;
        }
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        mRecyclerView.setAdapter(new CrimeAdapter(crimeLab.getCrimes()));
    }
}
