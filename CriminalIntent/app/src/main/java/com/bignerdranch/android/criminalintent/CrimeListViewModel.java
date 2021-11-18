package com.bignerdranch.android.criminalintent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

// import java.util.ArrayList;
import java.util.List;

public class CrimeListViewModel extends ViewModel {

    private final CrimeRepository crimeRepository = CrimeRepository.get();
    public final LiveData<List<Crime>> crimeListLiveData
        = this.crimeRepository.getCrimes();
}
