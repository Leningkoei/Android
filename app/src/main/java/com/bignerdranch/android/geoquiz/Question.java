package com.bignerdranch.android.geoquiz;

public class Question {

    public int textResId = 0;
    private boolean answer = false;

    public Question(int textResId, boolean answer) {
        this.textResId = textResId;
        this.answer = answer;
    }
}
