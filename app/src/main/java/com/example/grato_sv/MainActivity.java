package com.example.grato_sv;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.grato_sv.Adapter.MainViewPagerAdapter;
import com.example.grato_sv.Fragment.Show_Mark;
import com.example.grato_sv.Fragment.InforSubject;
import com.example.grato_sv.Fragment.quiz;
import com.example.grato_sv.Fragment.ListGroupInClass;
import com.example.grato_sv.Fragment.took_attendance;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    static String subjectName="-1", subID="-1", classID="-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.tbCreateGroup);

        Intent intent = getIntent();
        subjectName = intent.getStringExtra("subject_name");
        subID = intent.getStringExtra("subject_id");
        classID = intent.getStringExtra("class_id");

        toolbar.setNavigationOnClickListener(v -> finish());
        init();
    }

    static public String getSubjectName(){
        return subjectName;
    }
    static public String getSubjectID(){
        return subID;
    }
    static public String getClassID(){
        return classID;
    }

    private void init(){
//        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new InforSubject());
        adapter.addFragment(new ListGroupInClass());
        adapter.addFragment(new quiz());
        adapter.addFragment(new took_attendance());
        adapter.addFragment(new Show_Mark());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.thong_icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.thong_icon_group);
        tabLayout.getTabAt(2).setIcon(R.drawable.thong_icon_quiz);
        tabLayout.getTabAt(3).setIcon(R.drawable.thong_icon_attendance);
        tabLayout.getTabAt(4).setIcon(R.drawable.tung_present);
//        tabLayout.getTabAt(4).setIcon(R.drawable.thong_icon_grade);

    }
}