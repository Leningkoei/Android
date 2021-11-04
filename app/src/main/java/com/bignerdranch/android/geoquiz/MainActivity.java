package com.bignerdranch.android.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final Context mainActivityContext = this;
    private final Question[] questionBank = {
        new Question(R.string.question_australia, true),
        new Question(R.string.question_oceans, true),
        new Question(R.string.question_mideast, false),
        new Question(R.string.question_africa, false),
        new Question(R.string.question_america, true),
        new Question(R.string.question_asia, true)
    };
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView questionTextView = findViewById(R.id.question_text_view);
        final Button trueButton = findViewById(R.id.true_button);
        final Button falseButton = findViewById(R.id.false_button);
        final Button nextButton = findViewById(R.id.next_button);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mainActivityContext, R.string.correct_toast,
                    Toast.LENGTH_SHORT).show();
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mainActivityContext, R.string.incorrect_toast,
                        Toast.LENGTH_SHORT).show();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestionIndex = (currentQuestionIndex + 1) %
                    questionTextView.length();
                int questionTextResId = questionBank[currentQuestionIndex]
                    .textResId;
                questionTextView.setText(questionTextResId);
            }
        });

        int questionTextResId = this.questionBank[this.currentQuestionIndex]
            .textResId;
        questionTextView.setText(questionTextResId);
    }
}
