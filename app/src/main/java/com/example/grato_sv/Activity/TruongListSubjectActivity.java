package com.example.grato_sv.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.grato_sv.Adapter.ListGroupInClassAdapter;
import com.example.grato_sv.Adapter.truongListSubjectAdapter;
import com.example.grato_sv.Model.Group;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.Model.truongSubject;
import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.example.grato_sv.ViewModel.GratoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TruongListSubjectActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView listSubjectRecyclerview;
    FloatingActionButton fabLogout;
    LoginResponse loginResponse;
    GratoViewModel mGratoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truong_list_subjects);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(this);

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponse = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();
        addEvents();
        getData();
    }

    private void addEvents() {

        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove session
                SessionManagement sessionManagement = SessionManagement.getInstance(TruongListSubjectActivity.this);
                sessionManagement.removeSession();
                // Move to LoginActivity
                Intent intent = new Intent(TruongListSubjectActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void getData() {
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        ArrayList<truongSubject> lstSubject = new ArrayList<>();

        mGratoViewModel.getResponseListSubject().observe(this, subjects -> {
            Log.d("list subject", subjects.toString());
            for(truongSubject subject : subjects){
                lstSubject.add(subject);
            }
            // tạo adapter
            truongListSubjectAdapter listSubjectAdapter = new truongListSubjectAdapter(lstSubject);

            // performance
            listSubjectRecyclerview.setHasFixedSize(true);

            // set adapter cho Recycler View
            listSubjectRecyclerview.setAdapter(listSubjectAdapter);

        });

        mGratoViewModel.fetchListSubject(loginResponse.getToken(), loginResponse.getUser().getId(), 202);

    }

    private void addControls() {
        listSubjectRecyclerview = findViewById(R.id.subjectItemRecyclerview);
        toolbar = findViewById(R.id.examCodeToolbar);
        fabLogout = findViewById(R.id.fabLogout);
    }
}