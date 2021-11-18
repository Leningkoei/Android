package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.bignerdranch.android.criminalintent.database.CrimeDao;
import com.bignerdranch.android.criminalintent.database.CrimeDatabase;

import java.util.Date;
import java.util.List;
import java.util.UUID;

// 这个"单例"给👴整迷糊力, 没学过爪哇和面向对象阿这;
public class CrimeRepository extends SQLiteOpenHelper {

    private CrimeRepository(
        @Nullable Context context,
        String name,
        SQLiteDatabase.CursorFactory factory,
        int version
    ) {
        super(context, name, factory, version);

        this.database = Room.databaseBuilder(
            context.getApplicationContext(),
            CrimeDatabase.class,
            "crime-database"
        ).build();
        this.crimeDao = this.database.crimeDao();
    };

    private static CrimeRepository INSTANCE = null;

    public static void initialize(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CrimeRepository(context, "crime-database", null, 1);
        }
    }
    public static CrimeRepository get() {
        return INSTANCE;
    }

    private CrimeDatabase database = null;
    private CrimeDao crimeDao = null;

    public LiveData<List<Crime>> getCrimes() {
        return this.crimeDao.getCrimes();
    }
    public LiveData<Crime> getCrime(UUID id) {
        return this.crimeDao.getCrime(id);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
