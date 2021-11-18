package com.bignerdranch.android.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.criminalintent.database.CrimeDatabase
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class CrimeRepository private constructor(context: Context) {

    companion object {

        private var INSTANCE: CrimeRepository? = null;

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context);
            }
        }
        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException(
                "CrimeRepository must be initialized"
            );
        }
    }

    private val database : CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        "crime-database"
    ).build();
    private val crimeDao = this.database.crimeDao();
    private val executor: Executor = Executors.newSingleThreadExecutor();

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes();
    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id);
    fun updateCrime(crime: Crime) {
        this.executor.execute(object : Runnable {
            override fun run() {
                this@CrimeRepository.crimeDao.updateCrime(crime);
            }
        })
    }
    fun addCrime(crime: Crime) {
        this.executor.execute(object : Runnable {
            override fun run() {
                this@CrimeRepository.crimeDao.addCrime(crime);
            }
        })
    }
}
