package com.example.grato_sv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.Activity.ViewQuizActivity;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.Activity.DoQuizActivity;
import com.example.grato_sv.SessionManagement;
import com.example.grato_sv.Activity.ShowListQuizActivity;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ShowListQuizAdapter extends RecyclerView.Adapter<ShowListQuizAdapter.QuizHolder> {
    Context context;
    public ArrayList<ListQuiz> listQuiz;
    LoginResponse loginResponseSession;

    //QuizItemClickListener mQuizItemClickListener;

    public ShowListQuizAdapter(ArrayList<ListQuiz> listQuiz){
        this.listQuiz = listQuiz;
    }
    @NonNull
    @Override
    public QuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_listquiz, parent, false);

        SessionManagement sessionManagement = SessionManagement.getInstance(context);
        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);

        return new QuizHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull QuizHolder holder, int position) {

        ListQuiz quiz = listQuiz.get(position);
        holder.quizname.setText(quiz.getQuiz_name());
        holder.deadline.setText(quiz.getDeadline().toString());
        holder.score.setText(quiz.getScore() == null ? "Score: Not completed": "Score: " + quiz.getScore().toString());
        holder.button.setText( quiz.getScore() == null ? "Start": "View");
        holder.num_question.setText("No_question: "+quiz.getNo_question().toString());
        holder.max_time.setText("Time: "+quiz.getMax_time().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (holder.button.getText() == "Start"){
                    Intent intent = new Intent(context, DoQuizActivity.class);
                    context.startActivity(intent);
                }
                else{
                    Intent intent = new Intent(context, ViewQuizActivity.class);
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listQuiz.size();
    }

    public class QuizHolder extends RecyclerView.ViewHolder {

        public TextView quizname;
        public TextView deadline;
        public TextView button;
        public TextView num_question;
        public TextView max_time;
        public TextView score;
        public QuizHolder(@NonNull View itemView) {
            super(itemView);
            quizname =(TextView) itemView.findViewById(R.id.quiz_name);
            deadline =(TextView) itemView.findViewById(R.id.deadline);
            button = (TextView) itemView.findViewById(R.id.buttonquiz);
            num_question = (TextView) itemView.findViewById(R.id.num_question);
            max_time = (TextView) itemView.findViewById(R.id.time_do_quiz);
            score = (TextView) itemView.findViewById(R.id.score);
        }
    }
}
