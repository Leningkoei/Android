package com.bignerdranch.android.criminalintent.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.bignerdranch.android.criminalintent.Crime;

import java.util.List;
import java.util.UUID;

@Dao
public interface CrimeDao {

    @Query("select * from Crime")
    LiveData<List<Crime>> getCrimes();
    @Query("select * from Crime where id = :id")
    LiveData<Crime> getCrime(UUID id);
}
