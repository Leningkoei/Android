package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CrimeListFragment extends Fragment {

    public static CrimeListFragment newInstance() {
        return new CrimeListFragment();
    }

    private CrimeListViewModel crimeListViewModel = null;
    private RecyclerView crimeRecyclerView = null;

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
        LinearLayoutManager linerLayoutManager
            = new LinearLayoutManager(this.getContext());
        return view;
    }

    /**
     * // lang === Kotlin;
     * companion object {
     *     fun newInstance(): CrimeListFragment {
     *         return CrimeListFragment();
     *     }
     * }
     */
}
