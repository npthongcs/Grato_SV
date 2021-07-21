package com.example.grato_sv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.Activity.DoQuizActivity;
import com.example.grato_sv.Model.Answer;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.QuestionAndAnswer;
import com.example.grato_sv.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuizItemAdapter extends RecyclerView.Adapter<QuizItemAdapter.AnswerHolder> {
    static int score;
    static String student_answer = "";
    Context context;
    public ArrayList<QuestionAndAnswer> listAnswer;
    public QuizItemAdapter( ArrayList<QuestionAndAnswer> listAnswer){
        this.listAnswer = listAnswer;
    }
    @NonNull
    @Override
    public AnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tung_quiz_item, parent, false); //đổi chỗ này lại khoan
        return new AnswerHolder(view);
    }
//ok, mà sao nhiều fule adapter vậy, quizitemadapter rồi còn quizadapter
    // do t làm nhiều mục khác nhau nhiều recycle.  à để tên sao cho dễ hiểu chớ nhiều file quiz quá đọc khó, tìm khó
    //ok thanks
    @Override
    public void onBindViewHolder(@NonNull AnswerHolder holder, int position) {
        QuestionAndAnswer questionAndAnswer = listAnswer.get(position);
//        holder.question_id.setText("Question: " + "1");
//        holder.question_content.setText("2");
        holder.answer.setText(questionAndAnswer.getAnswer_content());
        holder.answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionAndAnswer.getRight_answer() == 1){
                    score += 1;
                }
                student_answer += questionAndAnswer.getAnswer_id();
                if (questionAndAnswer.getQuestion_id() < questionAndAnswer.getNo_question()){
                    Intent intent = new Intent(context, DoQuizActivity.class);
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listAnswer.size();
    }


    public class AnswerHolder extends RecyclerView.ViewHolder {

        public TextView answer;
//        public TextView question_content;
//        public TextView question_id;
        public AnswerHolder(@NonNull View itemView) {
            super(itemView);
            answer =(TextView) itemView.findViewById(R.id.answer);
//            question_content = (TextView) itemView.findViewById(R.id.question_content);
//            question_id = (TextView)itemView.findViewById(R.id.question);
        }
    }
}
