package com.bignerdranch.android.criminalintent;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CrimeListViewModel extends ViewModel {

    List<Crime> crimes = new ArrayList();

    public CrimeListViewModel() {
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.title = "Crime#" + i;
            crime.isSolved = i % 2 == 0;
            crimes.add(crime);
        }
    }
}
