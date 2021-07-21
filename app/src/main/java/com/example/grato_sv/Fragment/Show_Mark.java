package com.example.grato_sv.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.Adapter.MarkAdapter;
import com.example.grato_sv.Adapter.QuizItemAdapter;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.Model.Mark;
import com.example.grato_sv.Model.QuestionAndAnswer;
import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.example.grato_sv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Show_Mark extends Fragment {
    View view;
    RecyclerView rvList;
    LoginResponse loginResponseSession;
    //    TextView question_id;
//    TextView question_content;
    GratoViewModel mGratoViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tung_xem_diem, container, false);
        rvList = view.findViewById(R.id.recycle_3);
        SessionManagement sessionManagement = SessionManagement.getInstance(getContext());
        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);
        getData();
        return view;
    }

    public void getData(){
//        ArrayList<Mark> listMark = new ArrayList<>();
//        listMark.add(new Mark("Nguyen Phi Thuong","Deadline: 13 March, 2021","Completed in 12 March, 2021","9.00"));
//        listMark.add(new Mark("Nguoi Phi Thuong","Deadline: 13 March, 2021","Complete in 12 March, 2021","9.00"));
//        MarkAdapter markAdapter = new MarkAdapter(listMark);
//        rvList.setAdapter(markAdapter);
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.getResponseMark().observe(getViewLifecycleOwner(), new Observer<List<Mark>>() {
            @Override
            public void onChanged(List<Mark> listMarks) {
                //Log.d("BBB", listQuizs.toString());
//                question_id.setText(listAnswer.get(0).getQuestion_id().toString());
//                question_content.setText(listAnswer.get(0).getQuestion_content());
                MarkAdapter markAdapter = new MarkAdapter((ArrayList<Mark>) listMarks);
                rvList.setHasFixedSize(true);
                rvList.setAdapter(markAdapter);
            }
        });

        mGratoViewModel.fetchMark(
                loginResponseSession.getToken(),
                "CO3005",
                202,
                "L01"
        );
    }
}
