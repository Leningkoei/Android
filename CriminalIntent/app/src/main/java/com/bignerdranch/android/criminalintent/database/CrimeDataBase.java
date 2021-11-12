package com.bignerdranch.android.criminalintent.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.bignerdranch.android.criminalintent.Crime;

@Database(entities = Crime.class, version = 1)
@TypeConverters(CrimeTypeConverters.class)
// abstract: 抽象;    -- 👋🐘;
public abstract class CrimeDataBase extends RoomDatabase {
}
