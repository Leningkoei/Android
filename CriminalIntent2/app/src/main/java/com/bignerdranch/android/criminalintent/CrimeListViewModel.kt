package com.bignerdranch.android.criminalintent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CrimeListViewModel() : ViewModel() {

    // val crimes: MutableList<Crime> = mutableListOf<Crime>();
    private val crimeRepository: CrimeRepository = CrimeRepository.get();
    // val crimes: List<Crime> = this.crimeRepository.getCrimes();
    val crimeListLiveData: LiveData<List<Crime>> = crimeRepository.getCrimes();

    // init {
    //     // Kotlin 没有正常的 for 循环写法🐎? 而且 crimes 必须在 init 上?
    //     for (i in 0 until 100) {
    //         val crime = Crime();
    //         crime.title = "Crime #$i";
    //         crime.isSolved = i % 2 == 0;
    //         this.crimes += crime;
    //     }
    // }
}
