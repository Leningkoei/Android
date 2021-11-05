package com.bignerdranch.android.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView questionTextView = null;   // = findViewById(R.id.question_text_view);
    List<Question> questionBank = new ArrayList();
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context mainActivityContext = this;
        this.questionTextView = findViewById(R.id.question_text_view);
        questionBank.add(new Question(
            R.string.question_australia,
            true
        ));
        questionBank.add(new Question(
            R.string.question_oceans,
            true
        ));
        questionBank.add(new Question(
            R.string.question_mideast,
            false
        ));
        questionBank.add(new Question(
            R.string.question_africa,
            false
        ));
        questionBank.add(new Question(
            R.string.question_americas,
            true
        ));
        questionBank.add(new Question(
            R.string.question_asia,
            true
        ));
        Button trueButton = findViewById(R.id.true_button);
        Button falseButton = findViewById(R.id.false_button);
        Button nextButton = findViewById(R.id.next_button);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(
                //     mainActivityContext,
                //     R.string.correct_toast,
                //     Toast.LENGTH_SHORT
                // ).show();
                checkAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(
                //     mainActivityContext,
                //     R.string.incorrect_toast,
                //     Toast.LENGTH_SHORT
                // ).show();
                checkAnswer(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestionIndex =
                    (currentQuestionIndex + 1) % questionBank.size();
                // int questionTextResId =
                //     questionBank.get(currentQuestionIndex).textResId;
                // questionTextView.setText(questionTextResId);
                updateQuestion();
            }
        });

        // int questionTextResId =
        //     questionBank.get(this.currentQuestionIndex).textResId;
        // questionTextView.setText(questionTextResId);
        this.updateQuestion();
    }
    private void updateQuestion() {
        int questionTextResId =
            this.questionBank.get(this.currentQuestionIndex).textResId;
        this.questionTextView.setText(questionTextResId);
    }
    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = this.questionBank.get(this.currentQuestionIndex).answer;
        int messageResId = userAnswer == correctAnswer
            ? R.string.correct_toast
            : R.string.incorrect_toast;
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
