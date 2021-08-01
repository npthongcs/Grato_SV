package com.example.grato_sv.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grato_sv.Adapter.QuizItemAdapter;
import com.example.grato_sv.Adapter.ShowListQuizAdapter;
import com.example.grato_sv.MainActivity;
import com.example.grato_sv.Model.Answer;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.Model.QuestionAndAnswer;
import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.example.grato_sv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;

public class DoQuizActivity extends AppCompatActivity implements QuizItemAdapter.QuizItemListener {
    static int no_question = 0;
    RecyclerView rvList;
//    private static Intent intent;
//    private static Intent intent;
    //    quiz_name = intent.getStringExtra("quiz_name");
//    Intent intent = getIntent();
    public static long COUNTDOWN_IN_MILLIS = 100000;
    Toolbar toolbar;
    LoginResponse loginResponseSession;
    public static int count_time_do = 0;
    TextView question_id;
    TextView question_content;
    static CountDownTimer countDownTimer;
    public static String max_time;
    Button confirm;
    static int timeLeftInMillis;
    GratoViewModel mGratoViewModel;
    String subjectName,subjectID,classId;
    TextView countdown;
    public static String quiz_name = "";
    int a = 0;
    QuizItemAdapter.QuizItemListener quizItemListener = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_quiz);
        SessionManagement sessionManagement = SessionManagement.getInstance(this);
        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);
        subjectName = com.example.grato_sv.MainActivity.getSubjectName();
        subjectID = com.example.grato_sv.MainActivity.getSubjectID();
//        ActionBar toolbar = this.getSupportActionBar();
//        toolbar.setTitle(subjectName);
        Intent intent = getIntent();
        quiz_name = intent.getStringExtra("quiz_name");
        max_time = intent.getStringExtra("max_time");
        COUNTDOWN_IN_MILLIS = Long.parseLong(max_time)*60*1000- count_time_do;
        classId = MainActivity.getClassID();
        addControls();
        toolbar.setTitle(subjectName);
        getData();
        addEvents();
    }

    //    private void addEvents() {
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                i = i -1;
//                finish();
//            }
//        });
//
//    }
    private void addControls() {
        rvList = findViewById(R.id.recycle_2);
        toolbar = findViewById(R.id.dolistquiztoolbar);
        question_id = findViewById(R.id.question);
        question_content = findViewById(R.id.question_content);
//        confirm = findViewById(R.id.confirm);
        countdown = findViewById(R.id.countdown);


    }

    public void getData() {
        no_question = no_question + 1;
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewModel.getResponseQuestionAndAnswer().observe(this, new Observer<List<QuestionAndAnswer>>() {
            @Override
            public void onChanged(List<QuestionAndAnswer> listAnswer) {
                //Log.d("BBB", listQuizs.toString());
                a = listAnswer.get(no_question-1).getNo_question();
                question_id.setText("Question "+listAnswer.get(no_question-1).getQuestion_id().toString() + ":");
                question_content.setText(listAnswer.get(no_question-1).getQuestion_content());
                QuizItemAdapter quizAdapter = new QuizItemAdapter((ArrayList<QuestionAndAnswer>) listAnswer);
//                quizAdapter.setmQuizItemListener(quizItemListener);
                quizAdapter.setmQuizItemListener(quizItemListener);
                rvList.setHasFixedSize(true);
                rvList.setAdapter(quizAdapter);
            }
        });

        mGratoViewModel.fetchQuestionAndAnswer(
                loginResponseSession.getToken(),
                subjectID,
                202,
                classId,
                quiz_name,
                no_question
        );

    }
    public void addEvents(){
//        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        timeLeftInMillis = (int) COUNTDOWN_IN_MILLIS;
        startCountDown();

    }
    public void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUnitFinished) {
                timeLeftInMillis = (int) millisUnitFinished;
                count_time_do += 1000;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                double result = (double)QuizItemAdapter.score/a;
//                if (QuizItemAdapter.student_answer.length() < a){
//                    QuizItemAdapter.student_answer += QuizItemAdapter.student_answer + '*'*(QuizItemAdapter.student_answer.length()-a);
//                }
                if (QuizItemAdapter.student_answer == null){
                    QuizItemAdapter.student_answer = "";
                }
                int b = QuizItemAdapter.student_answer.length();
                for (int i = 0; i < a- b;i++){
                    QuizItemAdapter.student_answer += "P";
                }
                clickSubmit(result,QuizItemAdapter.student_answer);
            }
        }.start();
    }
    public void updateCountDownText(){
        int minutes = (int)(timeLeftInMillis/1000)/60;
        int seconds = (int)(timeLeftInMillis/1000)%60;
        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        countdown.setText(timeFormatted);
    }
    @Override
    public void clickEnd() {
        finish();
    }

    @Override
    public void clickSubmit(Double score, String student_answer) {
        mGratoViewModel.getResponseSubmitQuiz().observe(this, new Observer<retrofit2.Response<Void>>() {
            @Override
            public void onChanged(retrofit2.Response<Void> voidResponse) {
                if (voidResponse.code() == 200) {
                    System.out.println("Confirm");
                } else {
                    System.out.println("Be carefull, not complete");
                }
            }

        });
        Double result = score*10;
//        String answer = "";
//        for (int i = 0; i < student_answer.length(); i++) {
//            if (i % 2 == 0){
//                answer = answer + student_answer.charAt(i);
//            }
//        }
        mGratoViewModel.fetchSubmitQuiz(
                loginResponseSession.getToken(),
                subjectID,
                202,
                quiz_name,
                student_answer,
                (double)count_time_do/(1000*60),
                result


        );
        no_question = 0;
        QuizItemAdapter.score = 0;
        QuizItemAdapter.student_answer = "";
        finish();
    }


//    public void addEvents(){
//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mGratoViewModel.getResponseAddAttend().observe(new LifecycleOwner() {
//                    @NonNull
//                    @Override
//                    public Lifecycle getLifecycle() {
//                        return null;
//                    }
//                }, new Observer<Response<Void>>() {
//                    @Override
//                    public void onChanged(Response<Void> voidResponse) {
//                        if (voidResponse.code() == 200) {
//                            System.out.println("Confirm");
//                        } else {
//                            System.out.println("Be carefull, not complete");
//                        }
//                    }
//                });
//
//                mGratoViewModel.fetchAddAttend(
//                        loginResponseSession.getToken(),
//                        "CO3005",
//                        202,
//                        "L01",
//                        "Quiz1: Lexical",
//                        student_answer
//
//                );
//
//            }
//        });
//    }
}