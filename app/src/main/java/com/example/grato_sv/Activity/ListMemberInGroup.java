package com.example.grato_sv.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.grato_sv.Adapter.MemberInGroupAdapter;
import com.example.grato_sv.Fragment.ListGroupInClass;
import com.example.grato_sv.MainActivity;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.Model.Member;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.example.grato_sv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ListMemberInGroup extends AppCompatActivity {

    RecyclerView memberInGroup;
    LoginResponse loginResponse;
    GratoViewModel mGratoViewModel;

    Toolbar toolbar;
    String gname;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_list_member_in_group);
        addControls();
        // get intent
        Intent intent = getIntent();
        gname = intent.getStringExtra("nameGroup");
        Log.d("name group intent",gname);


        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(this);

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponse = gson.fromJson(loginResponseJson, LoginResponse.class);

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        addEvents();
        getData();
    }

    private void addEvents() {
        toolbar.setNavigationOnClickListener(v -> finish());

//        btnMode.setOnClickListener(v -> {
//            if (btnMode.getText().toString().equals("JOIN GROUP")){
//                joinGroup();
//            }
//            else {
//                outGroup();
//            }
//        });

    }

//    private void outGroup() {
//        mGratoViewModel.getResponseOutGroup().observe(this, voidResponse -> {
//            Log.d("out group: ",voidResponse.message());
//            if (voidResponse.isSuccessful()){
//                Context context = getApplicationContext();
//                CharSequence text = "Out group successful";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//            }
//        });
//        mGratoViewModel.outGroup(loginResponse.getToken(),"CO3005",202,"L01",
//                gname,loginResponse.getUser().getId());
//    }
//
//    private void joinGroup() {
//        mGratoViewModel.getResponseGetNoMax().observe(this, integers -> {
//            Log.d("integers fr",integers.toString());
//            noMem = integers.get(0);
//            maxMem = integers.get(1);
//            // insertData(noMem,maxMem);
//        });
//        mGratoViewModel.fetchgetNoMax(loginResponse.getToken(),"CO3005",202,"L01",gname);
//    }
//
//    private void insertData(Integer noMem,Integer maxMem) {
//
//        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
//        if (noMem<maxMem) {
//            mGratoViewModel.getResponseJoinGroup().observe(this, new Observer<Response<Void>>() {
//                @Override
//                public void onChanged(Response<Void> voidResponse) {
//                    Log.d("join group", voidResponse.message());
//                    if (voidResponse.isSuccessful()){
//                        Context context = getApplicationContext();
//                        CharSequence text = "Join group successful";
//                        int duration = Toast.LENGTH_SHORT;
//
//                        Toast toast = Toast.makeText(context, text, duration);
//                        toast.show();
//                    } else {
//                        Context context = getApplicationContext();
//                        CharSequence text = "Join group unsuccessful";
//                        int duration = Toast.LENGTH_SHORT;
//
//                        Toast toast = Toast.makeText(context, text, duration);
//                        toast.show();
//                    }
//                }
//            });
//
//            mGratoViewModel.joinGroup(loginResponse.getToken(), "CO3005", 202, "L01",
//                    gname, loginResponse.getUser().getId());
//        }
//        else {
//            Context context = getApplicationContext();
//            CharSequence text = "The group has enough members. Please join another group";
//            int duration = Toast.LENGTH_LONG;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.setGravity(Gravity.CENTER, 20, 30);
//            toast.show();
//        }
//    }

    private void getData(){

        toolbar.setTitle(gname);

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.getResponseMemberInGroup().observe(this, members -> {
            Log.d("member in group",members.toString());
            MemberInGroupAdapter  memberInGroupAdapter = new MemberInGroupAdapter((ArrayList<Member>) members);
            memberInGroup.setHasFixedSize(true);
            memberInGroup.setAdapter(memberInGroupAdapter);
        });

        mGratoViewModel.fetchMemberInGroup(loginResponse.getToken(), MainActivity.getSubjectID(),
                202,MainActivity.getClassID(),gname);
    }

    private void addControls(){
        //btnMode = findViewById(R.id.btn_mode_inout);
        toolbar = findViewById(R.id.toolbar_list_member);
        memberInGroup = findViewById(R.id.list_member_in_group);
    }
}