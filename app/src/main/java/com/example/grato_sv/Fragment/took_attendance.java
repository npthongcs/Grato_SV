package com.example.grato_sv.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SymbolTable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grato_sv.Activity.DoQuizActivity;
import com.example.grato_sv.Activity.ShowListQuizActivity;
import com.example.grato_sv.Activity.tungtookattendance;
import com.example.grato_sv.Adapter.MarkAdapter;
import com.example.grato_sv.Model.AbsentCount;
import com.example.grato_sv.Model.Attend;
import com.example.grato_sv.Model.DateCount;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.Model.Mark;
import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.example.grato_sv.ViewModel.GratoViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;

public class took_attendance extends Fragment {
    View view;
    Context context;
    LoginResponse loginResponseSession;
    //    TextView question_id;
//    TextView question_content;
    GratoViewModel mGratoViewModel;
    GratoViewModel mGratoViewmodelabsent;
    GratoViewModel mGratoViewModelclick;
    GratoViewModel mGratoViewModeladd;
    TextView num_of_attend;
    TextView absent;
    Button button1;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tungtookattendance, container, false);
        SessionManagement sessionManagement = SessionManagement.getInstance(getContext());
        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);
        num_of_attend = (TextView) view.findViewById(R.id.some_id_3);
        absent = (TextView) view.findViewById(R.id.some_id_4);
        button1 = (Button) view.findViewById(R.id.btntook);
        getData();
        getEvent();
        return view;
    }

    public void getData() {
//        ArrayList<Mark> listMark = new ArrayList<>();
//        listMark.add(new Mark("Nguyen Phi Thuong","Deadline: 13 March, 2021","Completed in 12 March, 2021","9.00"));
//        listMark.add(new Mark("Nguoi Phi Thuong","Deadline: 13 March, 2021","Complete in 12 March, 2021","9.00"));
//        MarkAdapter markAdapter = new MarkAdapter(listMark);
//        rvList.setAdapter(markAdapter);
        int a = 0;
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewmodelabsent = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewModelclick = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewModeladd = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewModel.getResponseCountDate().observe(getViewLifecycleOwner(), new Observer<List<DateCount>>() {
            @Override
            public void onChanged(List<DateCount> dateCount) {
                //Log.d("BBB", listQuizs.toString());
//                question_id.setText(listAnswer.get(0).getQuestion_id().toString());
//                question_content.setText(listAnswer.get(0).getQuestion_content());
                num_of_attend.setText(dateCount.get(0).getCount().toString());
            }
        });

        mGratoViewModel.fetchCount(
                loginResponseSession.getToken(),
                "CO3005",
                202,
                "L01"
        );
        mGratoViewmodelabsent.getResponseCountAbsent().observe(getViewLifecycleOwner(), new Observer<List<AbsentCount>>() {
            @Override
            public void onChanged(List<AbsentCount> dateCount) {
                //Log.d("BBB", listQuizs.toString());
//                question_id.setText(listAnswer.get(0).getQuestion_id().toString());
//                question_content.setText(listAnswer.get(0).getQuestion_content());

                Integer a = Integer.parseInt(String.valueOf(num_of_attend.getText()));
                Integer b = dateCount.get(0).getCounttime() - a;
                absent.setText(b.toString());
            }
        });

        mGratoViewmodelabsent.fetchAbsent(
                loginResponseSession.getToken(),
                "CO3005",
                202,
                "L01"
        );

//        num_of_attend.setText("2");
    }

    public void getEvent() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGratoViewModelclick.getResponseAttend().observe(getViewLifecycleOwner(), new Observer<List<Attend>>() {
                    @Override
                    public void onChanged(List<Attend> attends) {
                        //Log.d("BBB", listQuizs.toString());
//                question_id.setText(listAnswer.get(0).getQuestion_id().toString());
//                question_content.setText(listAnswer.get(0).getQuestion_content());

                        if (attends.get(0).getLasttook().compareTo(attends.get(0).getStart_time()) < 0) {
                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
                            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                                getLocation();
                            } else {
                                ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                            }
                        }

                    }
                });

                mGratoViewModelclick.fetchAttend(
                        loginResponseSession.getToken(),
                        "CO3005",
                        202,
                        "L01"
                );
            }
        });
    }

    public void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {

                    try {
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        double a = 10.881346;
                        double b = 106.808827;
                        double c = addresses.get(0).getLatitude();
                        double d = addresses.get(0).getLongitude();
                        double earthRadius = 6371000; //meters
                        double dLat = Math.toRadians(a - c);
                        double dLng = Math.toRadians(b - d);
                        double cal = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                                Math.cos(Math.toRadians(a) * Math.cos(Math.toRadians(c)) *
                                        Math.sin(dLng / 2) * Math.sin(dLng / 2));
                        double result = 2 * Math.atan2(Math.sqrt(cal), Math.sqrt(1 - cal));
                        float dist = (float) (earthRadius * c);
                        if (dist < 10) {
                            Date date = new Date();
                            SimpleDateFormat timeFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
                            System.out.println(timeFormat.format(date));

                            mGratoViewModeladd.getResponseAddAttend().observe(getViewLifecycleOwner(), new Observer<Response<Void>>() {
                                @Override
                                public void onChanged(Response<Void> voidResponse) {
                                    if (voidResponse.code() == 200) {
                                        Toast.makeText(getActivity(), "Add exam successful!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), "Add exam fail! Please try again!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            mGratoViewModeladd.fetchAddAttend(
                                    loginResponseSession.getToken(),
                                    "CO3005",
                                    202,
                                    "L01",
                                    date
                            );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
