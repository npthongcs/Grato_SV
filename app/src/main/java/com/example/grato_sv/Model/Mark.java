package com.example.grato_sv.Model;

import java.util.Date;
import java.util.SplittableRandom;

public class Mark {
    public String quiz_name;
    public Date deadline;
    public String no_question;
    public String score;

    public Mark(String quiz_name, Date deadline, String no_question, String score) {
        this.quiz_name = quiz_name;
        this.deadline = deadline;
        this.no_question = no_question;
        this.score = score;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getNo_question() {
        return no_question;
    }

    public void setNo_question(String no_question) {
        this.no_question = no_question;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}