package com.example.grato_sv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.Activity.DoQuizActivity;
import com.example.grato_sv.Activity.ShowListQuizActivity;
import com.example.grato_sv.Activity.ViewQuizActivity;
import com.example.grato_sv.Model.Answer;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.QuestionAndAnswer;
import com.example.grato_sv.Model.ShowQuestionAndAnswer;
import com.example.grato_sv.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewQuizItemAdapter extends RecyclerView.Adapter<ViewQuizItemAdapter.AnswerHolder> {
    Context context;
    static int no_question = 0;
    public ArrayList<ShowQuestionAndAnswer> listAnswer;
    ViewQuizItemAdapter.ViewQuizItemListener mViewQuizItemListener;
//    ViewListQuizAdapter.ShowQuizItemListener mShowQuizItemListener;
    public ViewQuizItemAdapter( ArrayList<ShowQuestionAndAnswer> listAnswer){
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
        ShowQuestionAndAnswer showQuestionAndAnswer = listAnswer.get(position);
//        holder.question_id.setText("Question: " + "1");
//        holder.question_content.setText("2");
        holder.answer.setText(showQuestionAndAnswer.getAnswer_content());
        System.out.println(showQuestionAndAnswer.getAnswer_id().charAt(0));
        System.out.println(showQuestionAndAnswer.getStudent_answer().charAt(no_question));
        if (showQuestionAndAnswer.getStudent_answer().charAt(no_question) == 'P'){
            if (showQuestionAndAnswer.getRight_answer() == 1){
//                holder.answer.setTextColor(Integer.parseInt("#04DC19"));
                holder.answer.setTextColor(Color.parseColor("#FF0000"));
            }
        }
        else{
            if (showQuestionAndAnswer.getAnswer_id().charAt(0) == showQuestionAndAnswer.getStudent_answer().charAt(no_question)){
                if (showQuestionAndAnswer.getRight_answer() == 1){
//                holder.answer.setTextColor(Integer.parseInt("#04DC19"));
                    holder.answer.setTextColor(Color.parseColor("#04DC19"));
                }
                else{
//                holder.answer.setTextColor(Integer.parseInt("#FF0000"));
                    holder.answer.setTextColor(Color.parseColor("#FF0000"));
                }

            }
            else{
                if (showQuestionAndAnswer.getRight_answer() == 1){
//                holder.answer.setTextColor(Integer.parseInt("#04DC19"));
                    holder.answer.setTextColor(Color.parseColor("#04DC19"));
                }
//            else{
//                holder.answer.setTextColor(Color.parseColor("#FF0000"));
//            }
            }
        }
        holder.answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, ViewQuizActivity.class);
//                context.startActivity(intent);
                no_question += 1;
                if (showQuestionAndAnswer.getQuestion_id() < showQuestionAndAnswer.getNo_question()){
                    Intent intent = new Intent(context, ViewQuizActivity.class);
                    intent.putExtra("quiz_name",ViewQuizActivity.quiz_name);
                    context.startActivity(intent);
                    mViewQuizItemListener.clickEnd();
                }
                else{
                    ViewQuizActivity.no_question = 0;
                    no_question = 0;
                    mViewQuizItemListener.clickEnd();
//                    Intent intent = new Intent(context, ShowListQuizActivity.class);
//                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listAnswer.size();
    }

    public interface ViewQuizItemListener{
        void clickEnd();
//        void clickSubmit(Double score, String student_answer);
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
    public void setmViewQuizItemListener (ViewQuizItemListener viewQuizItemListener){
        mViewQuizItemListener = viewQuizItemListener;
    }
}
