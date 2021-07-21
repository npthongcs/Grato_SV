package com.example.grato_sv.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grato_sv.Model.ClassInfor;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.example.grato_sv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.util.List;

public class InforSubject extends Fragment {

    LoginResponse loginResponse;
    View view;
    GratoViewModel mGratoViewModel;
    TextView subID;
    TextView classID;
    TextView room;
    TextView time;
    TextView nameTeacher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_infor_subject, container, false);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(getContext());

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponse = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();
        getData();
        return view;
    }

    private void getData() {
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.getResponseClassInfor().observe(getViewLifecycleOwner(), new Observer<List<ClassInfor>>() {
            @Override
            public void onChanged(List<ClassInfor> classInfors) {
                Log.d("class information",classInfors.toString());
                for (ClassInfor data : classInfors){
                    subID.setText(data.getSubId());
                    classID.setText(data.getClassId());
                    room.setText(data.getRoom());
                    time.setText(data.getStartTime()+" - "+data.getEndTime());
                    nameTeacher.setText(data.getName());
                }
            }
        });

        mGratoViewModel.fetchClassInfor(loginResponse.getToken(),"CO3005",202,"L01");
    }

    private void addControls() {
        subID = view.findViewById(R.id.idSubject);
        classID = view.findViewById(R.id.classID);
        room = view.findViewById(R.id.room);
        time = view.findViewById(R.id.SEtime);
        nameTeacher = view.findViewById(R.id.nameTeacher);
    }
}
