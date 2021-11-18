package com.bignerdranch.android.criminalintent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class CrimeDetailViewModel() : ViewModel() {

    private val crimeRepository = CrimeRepository.get();
    // 保存当前 crime fragment 显示的 crime 的 id;
    private val crimeIdLiveData = MutableLiveData<UUID>();
    var crimeLiveData: LiveData<Crime?> = Transformations.switchMap(
        this.crimeIdLiveData,
        // 这里应该是回调函数8, 只能用匿名函数? 而且在类中定义1个函数再把函数名作为参数传递
        //      进去也不行🐎?
        fun (crimeId: UUID): LiveData<Crime?> {
            return this.crimeRepository.getCrime(crimeId);
        }
    )

    fun loadCrime(crimeId: UUID) {
        this.crimeIdLiveData.value = crimeId;
    }
    fun saveCrime(crime: Crime) {
        this.crimeRepository.updateCrime(crime);
    }
}