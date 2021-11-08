package com.bignerdranch.android.geoquiz;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends ViewModel {
    private List<Question> questionBank = new ArrayList();
    public int currentQuestionIndex = 0;
    public boolean isCheater = false;

    // 在 main activity 关联 quiz view model 时好像会自动调用构造函数?
    public QuizViewModel() {
        // region init questionBank;
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
        // endregion
    }

    // // view model instance about to be destroyed;
    // @Override
    // protected void onCleared() {
    //     super.onCleared();
    // }

    public int getCurrentQuestionText() {
        return this.questionBank.get(this.currentQuestionIndex).textResId;
    }
    public boolean getCurrentQuestionAnswer() {
        return this.questionBank.get(this.currentQuestionIndex).answer;
    }
    public void moveToNext() {
        this.currentQuestionIndex = (this.currentQuestionIndex + 1) % this.questionBank.size();
    }
}
