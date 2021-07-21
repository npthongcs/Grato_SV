package com.example.grato_sv.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.example.grato_sv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Response;

public class CreateGroupActivity extends AppCompatActivity {

    EditText etNewName;
    EditText etMaxMem;
    String newName;
    Integer maxMember;
    Button createGroup;
    LoginResponse loginResponse;
    GratoViewModel mGratoViewModel;
    Toolbar toolbar;
    Integer noMem, maxMem;
    String gname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(this);

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponse = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        newName = etNewName.getText().toString();
        maxMember = Integer.parseInt(etMaxMem.getText().toString());
        Log.d("new name",newName);
        Log.d("new max member", maxMember.toString());

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.getResponseCreateGroup().observe(this, new Observer<Response<Void>>() {
            @Override
            public void onChanged(Response<Void> voidResponse) {
                Log.d("create group",voidResponse.message());

                if (voidResponse.isSuccessful()){
                    Context context = getApplicationContext();
                    CharSequence text = "Create group successful";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    //joinGroup();
                }
            }
        });

        mGratoViewModel.createGroup(loginResponse.getToken(),"CO3005",202,"L01",newName,maxMember);

    }

    // ==============
    private void joinGroup() {
       // mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewModel.getResponseGetNoMax().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                Log.d("integers",integers.toString());
                noMem = integers.get(0);
                maxMem = integers.get(1);
                insertData(noMem,maxMem);
            }
        });
        mGratoViewModel.fetchgetNoMax(loginResponse.getToken(),"CO3005",202,"L01",newName);
    }

    private void insertData(Integer noMem,Integer maxMem) {
//        Log.d("noMem",noMem.toString());
//        Log.d("maxMem",maxMem.toString());
        Log.d("##############","########");
      //  mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
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
                    newName, loginResponse.getUser().getId());
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

    // ==============

    private void addControls() {
        etNewName = findViewById(R.id.newNameGroup);
        etMaxMem = findViewById(R.id.newMaxMember);
        toolbar = findViewById(R.id.tbCreateGroup);
        createGroup = findViewById(R.id.button_create_group);
    }
}