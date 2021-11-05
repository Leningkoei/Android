package com.bignerdranch.android.geoquiz;

public class Question {

    int textResId = 0;
    boolean answer = false;

    public Question(int textResId, boolean answer) {
        this.textResId = textResId;
        this.answer = answer;
    }
}
