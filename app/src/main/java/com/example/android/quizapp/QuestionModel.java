package com.example.android.quizapp;

/**
 * Created by Obinna on 6/28/2018.
 */

public class QuestionModel {

    int Question;
    int Explanation;
    boolean answer;

    public QuestionModel(int question, int explanation, boolean answer) {
        Question = question;
        Explanation = explanation;
        this.answer = answer;
    }

    public int getQuestion() {
        return Question;
    }

    public void setQuestion(int question) {
        Question = question;
    }

    public int getExplanation() {
        return Explanation;
    }

    public void setExplanation(int explanation) {
        Explanation = explanation;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
