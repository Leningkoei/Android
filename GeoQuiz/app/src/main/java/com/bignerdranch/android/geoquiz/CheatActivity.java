package com.bignerdranch.android.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_CORRECT_ANSWER
        = "com.bignerdranch.android.geoquiz.correct_answer";

    public static Intent newIntent(Context packageContext, boolean correctAnswer) {
        return new Intent(
            packageContext,
            CheatActivity.class).putExtra(EXTRA_CORRECT_ANSWER, correctAnswer
        );
    }

    private boolean correctAnswer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        TextView answerTextView = findViewById(R.id.answer_text_view);
        Button showAnswerButton = findViewById(R.id.show_answer_button);

        this.correctAnswer = getIntent().getBooleanExtra(
            EXTRA_CORRECT_ANSWER,
            false
        );  // get current answer from intent from main activity;

        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answerText = correctAnswer ? R.string.true_button : R.string.false_button;
                answerTextView.setText(answerText);
                setAnswerShownResult(true);
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        setResult(Activity.RESULT_OK, new Intent(this, MainActivity.class).putExtra("isCheater", isAnswerShown));
    }
}
