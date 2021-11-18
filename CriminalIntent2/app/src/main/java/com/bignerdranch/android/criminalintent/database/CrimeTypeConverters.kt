package com.bignerdranch.android.criminalintent.database

import androidx.room.TypeConverter
import java.util.*

class CrimeTypeConverters() {

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {    // 将 uuid 转为 string 进入 database;
        return uuid?.toString();
    }
    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid);
    }
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time;
    }
    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        // return if (millisSinceEpoch == null) {
        //     return null;
        // } else {
        //     return Date(millisSinceEpoch);
        // }
        return millisSinceEpoch?.let {
            Date(it);
        }
    }
}
