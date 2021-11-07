package com.bignerdranch.android.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_CORRECT_ANSWER = "com.bignerdranch.android.geoquiz.correct_answer";

    private boolean correctAnswer = false;

    public static Intent newIntent(Context packageContext, boolean correctAnswer) {
        return new Intent(packageContext, CheatActivity.class).putExtra(EXTRA_CORRECT_ANSWER, correctAnswer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
    }
}
