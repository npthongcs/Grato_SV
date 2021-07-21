package com.example.grato_sv.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.grato_sv.Adapter.truongListSubjectAdapter;
import com.example.grato_sv.Model.truongSubject;
import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TruongListSubjectActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView listSubjectRecyclerview;
    FloatingActionButton fabLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truong_list_subjects);
        addControls();
        addEvents();
    }

    private void addEvents() {
//        setSupportActionBar(toolbar);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true); // icon
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//
//            }
//        });

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
        getData();
    }

    private void getData() {
        ArrayList<truongSubject> lstSubject = new ArrayList<>();
        lstSubject.add(new truongSubject("Principle of Programing Language", "L01"));
        lstSubject.add(new truongSubject("Data Structures and Algorithms", "L02"));
        lstSubject.add(new truongSubject("Object Oriented Programing", "L03"));
        lstSubject.add(new truongSubject("Network System", "L04"));

        // tạo adapter
        truongListSubjectAdapter listSubjectAdapter = new truongListSubjectAdapter(lstSubject);

        // performance
        listSubjectRecyclerview.setHasFixedSize(true);

        // set adapter cho Recycler View
        listSubjectRecyclerview.setAdapter(listSubjectAdapter);
    }

    private void addControls() {
        listSubjectRecyclerview = findViewById(R.id.subjectItemRecyclerview);
        toolbar = findViewById(R.id.examCodeToolbar);
        fabLogout = findViewById(R.id.fabLogout);
    }
}