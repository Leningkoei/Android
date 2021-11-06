package com.bignerdranch.android.geoquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuizViewModel quizViewModel = null;
    private TextView questionTextView = null;   // = findViewById(R.id.question_text_view);
    private String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // region init vars;
        // Context mainActivityContext = this;
        // ViewModelProvider provider = new ViewModelProvider(this);
        // ViewModel quizViewModel = provider.get(QuizViewModel.class);
        this.quizViewModel = new ViewModelProvider(this)
            .get(QuizViewModel.class);
        this.questionTextView = findViewById(R.id.question_text_view);
        Button trueButton = findViewById(R.id.true_button);
        Button falseButton = findViewById(R.id.false_button);
        Button nextButton = findViewById(R.id.next_button);
        int currentQuestionIndex = savedInstanceState == null?
            0:
            savedInstanceState.getInt(KEY_INDEX, 0);
        this.quizViewModel.currentQuestionIndex = currentQuestionIndex;
        // endregion

        // region set buttons' on click listener;
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
                // currentQuestionIndex =
                //     (currentQuestionIndex + 1) % questionBank.size();
                // // int questionTextResId =
                // //     questionBank.get(currentQuestionIndex).textResId;
                // // questionTextView.setText(questionTextResId);
                quizViewModel.moveToNext();
                updateQuestion();
            }
        });
        // endregion

        // int questionTextResId =
        //     questionBank.get(this.currentQuestionIndex).textResId;
        // questionTextView.setText(questionTextResId);
        this.updateQuestion();
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(this.KEY_INDEX, this.quizViewModel.currentQuestionIndex);
    }

    private void updateQuestion() {

        // int questionTextResId =
        //     questionBank.get(currentQuestionIndex).textResId;
        int questionTextResId = this.quizViewModel.getCurrentQuestionText();
        this.questionTextView.setText(questionTextResId);
    }
    private void checkAnswer(boolean userAnswer) {

        // boolean correctAnswer = questionBank.get(currentQuestionIndex).answer;
        boolean correctAnswer = this.quizViewModel.getCurrentQuestionAnswer();
        int messageResId = userAnswer == correctAnswer?
            R.string.correct_toast:
            R.string.incorrect_toast;
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
