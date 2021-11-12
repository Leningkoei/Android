// 类型转换器;

package com.bignerdranch.android.criminalintent.database;

import androidx.room.TypeConverter;

import java.util.Date;
import java.util.UUID;

public class CrimeTypeConverters {

    @TypeConverter
    public String fromUUID(UUID uuid) {
        return uuid.toString();
    }
    @TypeConverter
    public UUID toUUID(String uuid) {
        return UUID.fromString(uuid);
    }
    @TypeConverter
    public long fromDate(Date date) {
        return date.getTime();
    }
    @TypeConverter
    public Date toDate(long millisSinceEpoch) {
        return new Date(millisSinceEpoch);
    }
}
