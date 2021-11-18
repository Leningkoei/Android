// package com.bignerdranch.android.criminalintent;
//
// import androidx.annotation.NonNull;
// import androidx.room.Entity;
// import androidx.room.Ignore;
// import androidx.room.PrimaryKey;
//
// import java.util.Date;
// import java.util.UUID;
//
// // 注意使用 Entity 的时候(或者说是使用 androidx.room.* 的时候, 要在 build.gradle
// // (:app) 中添加 androidx.legacy:legacy-support-v[...];
// @Entity(tableName = "Crime")
// public class Crime {
//
//     @NonNull
//     @PrimaryKey
//     public UUID id;
//     public String title;
//     public Date date;
//     public boolean isSolved;
//
//     public Crime() {
//         this.id = UUID.randomUUID();
//         this.title = "";
//         this.date = new Date();
//         this.isSolved = false;
//     }
// }
