package com.bignerdranch.android.criminalintent;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

// 注意使用 Entity 的时候(或者说是使用 androidx.room.* 的时候, 要在 build.gradle
// (:app) 中添加 androidx.legacy:legacy-support-v[...];
@Entity
public class Crime {

    @PrimaryKey
    public UUID id = UUID.randomUUID();
    public String title = "";
    public Date date = new Date();
    public boolean isSolved = false;
}
