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
    Integer maxMem=-1;
    Integer noMem=-1;
    Button btnMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_list_member_in_group);
        addControls();
        // get intent
        Intent intent = getIntent();
        gname = intent.getStringExtra("nameGroup");
        mode = intent.getStringExtra("modeButton");
        Log.d("name group intent",gname);
        Log.d("mode intent",mode);

        // set mode button
        if (mode.equals("JOIN")){
            btnMode.setText("JOIN GROUP");
        }
        else {
            btnMode.setText("OUT GROUP");
        }

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(this);

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponse = gson.fromJson(loginResponseJson, LoginResponse.class);

        addEvents();
        getData();
    }

    private void addEvents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnMode.getText().toString().equals("JOIN GROUP")){
                    joinGroup();
                }
                else {
                    outGroup();
                }
            }
        });

    }

    private void outGroup() {
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewModel.getResponseOutGroup().observe(this, new Observer<Response<Void>>() {
            @Override
            public void onChanged(Response<Void> voidResponse) {
                Log.d("out group: ",voidResponse.message());
                if (voidResponse.isSuccessful()){
                    Context context = getApplicationContext();
                    CharSequence text = "Out group successful";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        mGratoViewModel.outGroup(loginResponse.getToken(),"CO3005",202,"L01",
                gname,loginResponse.getUser().getId());
    }

    private void joinGroup() {
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewModel.getResponseGetNoMax().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                Log.d("integers",integers.toString());
                noMem = integers.get(0);
                maxMem = integers.get(1);
                insertData(noMem,maxMem);
            }
        });
        mGratoViewModel.fetchgetNoMax(loginResponse.getToken(),"CO3005",202,"L01",gname);
    }

    private void insertData(Integer noMem,Integer maxMem) {
//        Log.d("noMem",noMem.toString());
//        Log.d("maxMem",maxMem.toString());

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
        if (noMem<maxMem) {
            mGratoViewModel.getResponseJoinGroup().observe(this, new Observer<Response<Void>>() {
                @Override
                public void onChanged(Response<Void> voidResponse) {
                    Log.d("join group", voidResponse.message());
                    if (voidResponse.isSuccessful()){
                        Context context = getApplicationContext();
                        CharSequence text = "Join group successful";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Join group unsuccessful";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            });

            mGratoViewModel.joinGroup(loginResponse.getToken(), "CO3005", 202, "L01",
                    gname, loginResponse.getUser().getId());
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "The group has enough members. Please join another group";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 20, 30);
            toast.show();
        }
    }

    private void getData(){

        toolbar.setTitle(gname);

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.getResponseMemberInGroup().observe(this, new Observer<List<Member>>() {
            @Override
            public void onChanged(List<Member> members) {
                Log.d("member in group",members.toString());
                MemberInGroupAdapter  memberInGroupAdapter = new MemberInGroupAdapter((ArrayList<Member>) members);
                memberInGroup.setHasFixedSize(true);
                memberInGroup.setAdapter(memberInGroupAdapter);
            }
        });

        mGratoViewModel.fetchMemberInGroup(loginResponse.getToken(),"CO3005",202,"L01",gname);
    }

    private void addControls(){
        btnMode = findViewById(R.id.btn_mode_inout);
        toolbar = findViewById(R.id.toolbar_list_member);
        memberInGroup = findViewById(R.id.list_member_in_group);
    }
}