package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CrimeListFragment extends Fragment {

    public static CrimeListFragment newInstance() {
        return new CrimeListFragment();
    }

    private CrimeListViewModel crimeListViewModel = null;
    private RecyclerView crimeRecyclerView = null;
    private CrimeAdapter crimeAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.crimeListViewModel
            = new ViewModelProvider(this).get(CrimeListViewModel.class);
    }
    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        View view = inflater.inflate(
            R.layout.fragment_crime_list,
            container,
            false
        );

        this.crimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        // LinearLayoutManager linerLayoutManager
        //     = new LinearLayoutManager(this.getContext());
        // this.crimeRecyclerView.setLayoutManager(linerLayoutManager);
        this.crimeRecyclerView.setLayoutManager(
            new LinearLayoutManager(this.getContext())
        );
        // this.crimeRecyclerView.setAdapter(this.crimeAdapter);
        this.crimeRecyclerView.setAdapter(new CrimeAdapter(
            new ArrayList<Crime>()
        ));

        // this.updateUI();

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.crimeListViewModel.crimeListLiveData.observe(
            this.getViewLifecycleOwner(),
            crimes -> {
                Log.d("nmsl", crimes.toString());
                this.updateUI(crimes);
            }
        );
    }

    private void updateUI(List<Crime> crimes) {
        // List<Crime> crimes = this.crimeListViewModel.crimes;
        this.crimeAdapter = new CrimeAdapter(crimes);
        this.crimeRecyclerView.setAdapter(this.crimeAdapter);
        Log.d("nmsl", crimes.toString());
    }

    // 外部类和内部类可以互相访问私有成员;
    private class CrimeHolder extends RecyclerView.ViewHolder {

        private Crime crime = null;
        private TextView titleTextView = null;
        private TextView dateTextView = null;
        private ImageView solvedImageView = null;

        // Constructor matching super;
        private CrimeHolder(@NonNull View itemView) {
            super(itemView);

            this.titleTextView = itemView.findViewById(R.id.crime_title);
            this.dateTextView = itemView.findViewById(R.id.crime_date);
            this.solvedImageView = itemView.findViewById(R.id.crime_solved);

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(
                        CrimeListFragment.this.getContext(),
                        // crime.title + "pressed!",
                        crime.title + "pressed!",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            });
        }

        private void bind(Crime crime) {
            this.crime = crime;
            // this.titleTextView.setText(this.crime.title);
            this.titleTextView.setText(this.crime.title);
            // this.dateTextView.setText(this.crime.date.toString());
            this.dateTextView.setText(this.crime.date.toString());
            this.solvedImageView.setVisibility(
                // crime.isSolved
                crime.isSolved
                    ? View.VISIBLE
                    : View.GONE
            );
        }
    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> crimes = null;

        private CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        // 至少要覆盖3个函数;
        // 必须修饰为 public;
        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
        ) {
            View view = CrimeListFragment.this.getLayoutInflater().inflate(
                // 只能通过 外部类类名.this.成员 来有修饰地访问外部类的成员吗?
                R.layout.list_item_crime,
                parent,
                false
            );
            return new CrimeHolder(view);
        }
        // 注意使这个函数"轻巧高效";
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = this.crimes.get(position);
            // holder.titleTextView.setText(crime.title);
            // holder.dateTextView.setText(crime.date.toString());
            holder.bind(crime);
        }
        @Override
        public int getItemCount() {
            return this.crimes.size();
        }
    }
}
