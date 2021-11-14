package com.bignerdranch.android.criminalintent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

// 注意使用 Entity 的时候(或者说是使用 androidx.room.* 的时候, 要在 build.gradle
// (:app) 中添加 androidx.legacy:legacy-support-v[...];
@Entity(tableName = "Crime")
public class Crime {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    // public UUID id = UUID.randomUUID();
    private UUID id;
    @ColumnInfo(name = "title")
    // private String title = "";
    private String title;
    @ColumnInfo(name = "date")
    // private Date date = new Date();
    private Date date;
    @ColumnInfo(name = "isSolved")
    // private boolean isSolved = false;
    private boolean isSolved;

    @NonNull
    public UUID getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public Date getDate() {
        return this.date;
    }
    public boolean getIsSolved() {
        return this.isSolved;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setIsSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }
}
