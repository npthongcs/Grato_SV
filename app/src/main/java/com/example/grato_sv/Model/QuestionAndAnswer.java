package com.example.grato_sv.Model;

public class QuestionAndAnswer {
    Integer question_id;
    String question_content;
    String answer_id;
    String answer_content;
    Integer right_answer;
    Integer no_question;

    public QuestionAndAnswer(Integer question_id, String question_content, String answer_id, String answer_content, Integer right_answer, Integer no_question) {
        this.question_id = question_id;
        this.question_content = question_content;
        this.answer_id = answer_id;
        this.answer_content = answer_content;
        this.right_answer = right_answer;
        this.no_question = no_question;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public Integer getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(Integer right_answer) {
        this.right_answer = right_answer;
    }

    public Integer getNo_question() {
        return no_question;
    }

    public void setNo_question(Integer no_question) {
        this.no_question = no_question;
    }
}
