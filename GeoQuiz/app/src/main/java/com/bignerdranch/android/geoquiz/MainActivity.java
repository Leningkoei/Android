package com.bignerdranch.android.geoquiz;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String KEY_INDEX = "index";

    private QuizViewModel quizViewModel = null;
    private TextView questionTextView = null;   // = findViewById(R.id.question_text_view);

    ActivityResultLauncher launcher = registerForActivityResult(
        new ResultContract(),
        new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            quizViewModel.isCheater = result;
        }
    });

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
        Button cheatButton = findViewById(R.id.cheat_button);
        Button nextButton = findViewById(R.id.next_button);
        int currentQuestionIndex = savedInstanceState == null
            ? 0
            : savedInstanceState.getInt(KEY_INDEX, 0)
        ;
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
            @Override public void onClick(View view) {
                // Toast.makeText(
                //     mainActivityContext,
                //     R.string.incorrect_toast,
                //     Toast.LENGTH_SHORT
                // ).show();
                checkAnswer(false);
            }
        });
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                // start cheat activity;
                // startActivity(CheatActivity.newIntent(
                //     MainActivity.this,
                //     quizViewModel.getCurrentQuestionAnswer()
                // ));
                launcher.launch(quizViewModel.getCurrentQuestionAnswer());
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

        savedInstanceState.putInt(
            this.KEY_INDEX,
            this.quizViewModel.currentQuestionIndex
        );
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
        int messageResId = this.quizViewModel.isCheater
            ? R.string.judgment_toast
            : userAnswer == correctAnswer
                ? R.string.correct_toast
                : R.string.incorrect_toast
        ;
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    class ResultContract extends ActivityResultContract<Boolean, Boolean> {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Boolean input) {
            return CheatActivity.newIntent(
                MainActivity.this,
                quizViewModel.getCurrentQuestionAnswer()
            );
        }
        @Override
        public Boolean parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode == Activity.RESULT_OK) {
                boolean isCheater = intent.getBooleanExtra(
                "isCheater",
                false
                );
                return isCheater;
            } else {
                return false;
            }
        }
    }
}
