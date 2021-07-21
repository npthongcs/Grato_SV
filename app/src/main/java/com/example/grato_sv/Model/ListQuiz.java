package com.example.grato_sv.Model;

import java.util.Date;
import java.util.SplittableRandom;

public class ListQuiz {
    public String quiz_name;//đợi xí
    public Date deadline;
    public Double score;
    public Double time;
    public String student_answer;
    public Integer no_question;
    public Integer max_time;

    public ListQuiz(String quiz_name, Date deadline, Double score, Double time, String student_answer, Integer no_question, Integer max_time) {
        this.quiz_name = quiz_name;
        this.deadline = deadline;
        this.score = score;
        this.time = time;
        this.student_answer = student_answer;
        this.no_question = no_question;
        this.max_time = max_time;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }

    public Integer getNo_question() {
        return no_question;
    }

    public void setNo_question(Integer no_question) {
        this.no_question = no_question;
    }

    public Integer getMax_time() {
        return max_time;
    }

    public void setMax_time(Integer max_time) {
        this.max_time = max_time;
    }
}
