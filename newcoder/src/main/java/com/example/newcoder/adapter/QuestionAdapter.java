package com.example.newcoder.adapter;

import com.example.newcoder.model.Answer;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class QuestionAdapter implements Serializable {
    private String question;
    private List<Answer> answer;
    private int questionid;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }
}
