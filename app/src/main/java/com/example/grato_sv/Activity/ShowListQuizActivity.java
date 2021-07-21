package com.example.grato_sv.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.SessionManagement;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.grato_sv.Adapter.ShowListQuizAdapter;
import com.example.grato_sv.Model.Answer;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.R;
import com.example.grato_sv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ShowListQuizActivity extends AppCompatActivity {
    RecyclerView rvList;
    Toolbar toolbar;
    LoginResponse loginResponseSession;

    GratoViewModel mGratoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_quiz);
        SessionManagement sessionManagement = SessionManagement.getInstance(this);
        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);


        addControls();
        getData();
        addEvents();
    }

    private void addEvents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addControls() {
        rvList = findViewById(R.id.recycle_1);
        toolbar = findViewById(R.id.showlistquiztoolbar);
    }

    public void getData() {
//        ArrayList<ListQuiz> listQuiz = new ArrayList<>();
//        listQuiz.add(new ListQuiz("Question 1: Lexical","Deadline: 21 March, 2021","Not complete","Start"));
//        listQuiz.add(new ListQuiz("Question 2","Deadline: 21 March, 2021","Not complete","Start"));
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.getResponseAllQuizsOfClass().observe(this, new Observer<List<ListQuiz>>() {
            @Override
            public void onChanged(List<ListQuiz> listQuizs) {
                //Log.d("BBB", listQuizs.toString());
                ShowListQuizAdapter quizAdapter = new ShowListQuizAdapter((ArrayList<ListQuiz>) listQuizs);
                rvList.setHasFixedSize(true);
                rvList.setAdapter(quizAdapter);
            }
        });

        mGratoViewModel.fetchAllQuizsOfClass(
                loginResponseSession.getToken(),
                "CO3005",
                202,
                "L01"
        );


    }
}