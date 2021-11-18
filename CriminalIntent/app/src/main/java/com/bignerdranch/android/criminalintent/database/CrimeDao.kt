package com.bignerdranch.android.criminalintent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.bignerdranch.android.criminalintent.Crime
import java.util.*

@Dao
interface CrimeDao {

    @Query("select * from Crime")
    fun getCrimes(): LiveData<List<Crime>>;
    @Query("select * from crime where id = (:id)")
    fun getCrime(id: UUID): LiveData<Crime?>;
}
