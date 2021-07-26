package com.example.grato_sv.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grato_sv.Fragment.ListGroupInClass;
import com.example.grato_sv.MainActivity;
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
    String subID, classID, subName;

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
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        Intent intent = getIntent();
        subID = intent.getStringExtra("subject_id");
        classID = intent.getStringExtra("class_id");
        subName = intent.getStringExtra("subject_name");

        toolbar.setNavigationOnClickListener(v -> finish());

        createGroup.setOnClickListener(v -> {
            check();
        });
    }

    private void check(){
        mGratoViewModel.getResponseFindGroup().observe(this, s -> {
            Log.d("name in create group",s);
            if (!s.equals("-1")) {
                Context context = getApplicationContext();
                CharSequence text = "Create group unsuccessful. You joined another group.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else getData();
        });
        mGratoViewModel.findGroup(loginResponse.getToken(), loginResponse.getUser().getId(), subID, 202, classID);
    }

    private void getData() {
        newName = etNewName.getText().toString();
        String s = etMaxMem.getText().toString();
        if (newName.equals("") || s.equals("")){
            Context context = getApplicationContext();
            CharSequence text = "Group name or max number is empty";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            maxMember = Integer.parseInt(etMaxMem.getText().toString());
            Log.d("new name", newName);
            Log.d("new max member", maxMember.toString());

            mGratoViewModel.getResponseCreateGroup().observe(this, voidResponse -> {
                Log.d("create group", voidResponse.message());

                if (voidResponse.isSuccessful()) {
                    Context context = getApplicationContext();
                    CharSequence text = "Create group successful";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    joinGroup();
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("subject_id",subID);
                    intent.putExtra("class_id",classID);
                    intent.putExtra("subject_name",subName);
                    startActivity(intent);
                }
            });

            mGratoViewModel.createGroup(loginResponse.getToken(), subID, 202, classID,
                    newName, maxMember);
        }
    }

    // ==============
    private void joinGroup() {
        mGratoViewModel.getResponseGetNoMax().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                Log.d("integers activity",integers.toString());
                noMem = integers.get(0);
                maxMem = integers.get(1);
                insertData(noMem,maxMem);
            }
        });
        mGratoViewModel.fetchgetNoMax(loginResponse.getToken(),subID,202,classID,newName);
    }

    private void insertData(Integer noMem,Integer maxMem) {
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

            mGratoViewModel.joinGroup(loginResponse.getToken(), subID, 202, classID,
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