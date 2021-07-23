package com.example.grato_sv.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.grato_sv.Activity.CreateGroupActivity;
import com.example.grato_sv.Adapter.ListGroupInClassAdapter;
import com.example.grato_sv.Model.Group;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.example.grato_sv.ViewModel.GratoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Response;


public class ListGroupInClass extends Fragment implements ListGroupInClassAdapter.GroupItemListener {

    RecyclerView listGroupRecycleView;
    View view;
    Integer noMem, maxMem;
    Boolean isJoin = false, isOut = false;
    Integer positionJoin;

    static GratoViewModel mGratoViewModel;
    static LoginResponse loginResponse;
    String group_name, gname;
    Integer position;
    FloatingActionButton fab;
    ListGroupInClassAdapter.GroupItemListener groupItemListener = this;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_group_in_class, container, false);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(getContext());

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponse = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CreateGroupActivity.class);
            requireActivity().startActivity(intent);
        });

        // Inflate the layout for this fragment
        makeObserver();
        getData();
        return view;
    }

    public void makeObserver(){
        mGratoViewModel.getResponseGetNoMax().observe(getViewLifecycleOwner(), integers -> {
            Log.d("integers", integers.toString());
            noMem = integers.get(0);
            maxMem = integers.get(1);
            insertData(noMem, maxMem, group_name, position);
        });

        mGratoViewModel.getResponseDeleteGroup().observe(getViewLifecycleOwner(), voidResponse -> {
            if (voidResponse.isSuccessful()){
                CharSequence text = "Delete group successful";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getContext(), text, duration);
                toast.show();
                mGratoViewModel.findGroup(loginResponse.getToken(), loginResponse.getUser().getId(), "CO3005", 202, "L01");
            } else {
                CharSequence text = "Delete group unsuccessful";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getContext(), text, duration);
                toast.show();
            }
        });
    }

    private void getData() {
        mGratoViewModel.getResponseFindGroup().observe(getViewLifecycleOwner(), s -> {
            gname = s;
            Log.d("name finded", gname);
            mGratoViewModel.fetchListGroup(loginResponse.getToken(), "CO3005", 202, "L01");
        });

        mGratoViewModel.getResponseListGroup().observe(getViewLifecycleOwner(), groups -> {
            Log.d("list group", groups.toString());
            ListGroupInClassAdapter listGroupClassAdapter = new ListGroupInClassAdapter((ArrayList<Group>) groups, gname);
            listGroupClassAdapter.setGroupItemListener(groupItemListener);
            listGroupRecycleView.setHasFixedSize(true);
            listGroupRecycleView.setAdapter(listGroupClassAdapter);
            if (isJoin) {
                listGroupClassAdapter.notifyItemChanged(positionJoin);
                isJoin = false;
            }
            if (isOut) {
                listGroupClassAdapter.notifyItemChanged(positionJoin);
                isOut = false;
            }
            listGroupClassAdapter.notifyDataSetChanged();
        });

        mGratoViewModel.findGroup(loginResponse.getToken(), loginResponse.getUser().getId(), "CO3005", 202, "L01");

    }

    private void addControls() {
        fab = view.findViewById(R.id.floatingNewGroup);
        listGroupRecycleView = view.findViewById(R.id.list_group);
    }

    @Override
    public void clickOutGroup(String group_name, Integer position) {
        mGratoViewModel.getResponseOutGroup().observe(this, new Observer<Response<Void>>() {
            @Override
            public void onChanged(Response<Void> voidResponse) {
                Log.d("out group: ", voidResponse.message());
                if (voidResponse.isSuccessful()) {
                    CharSequence text = "Out group successful";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getContext(), text, duration);
                    toast.show();

                    isOut = true;
                    positionJoin = position;
                    mGratoViewModel.findGroup(loginResponse.getToken(), loginResponse.getUser().getId(), "CO3005", 202, "L01");
                }
            }
        });
        mGratoViewModel.outGroup(loginResponse.getToken(), "CO3005", 202, "L01",
                group_name, loginResponse.getUser().getId());
    }

    @Override
    public void clickJoinGroup(String group_name, Integer position) {
        this.group_name = group_name;
        this.position = position;
        mGratoViewModel.fetchgetNoMax(loginResponse.getToken(), "CO3005", 202, "L01", group_name);
    }

    @Override
    public void clickDeleteGroup(String group_name, Integer noNow) {
        if (noNow==0){
            mGratoViewModel.deleteGroup(loginResponse.getToken(),"CO3005",202,"L01",group_name);
            mGratoViewModel.findGroup(loginResponse.getToken(), loginResponse.getUser().getId(), "CO3005", 202, "L01");
        } else if (noNow>1){
            CharSequence text = "You can't this group because numbers of member > 1";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();
        } else if (!group_name.equals(gname)){
            CharSequence text = "You can't this group because you isn't member of group";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();
        } else {
            mGratoViewModel.deleteGroup(loginResponse.getToken(),"CO3005",202,"L01",group_name);
        }

    }

    public void insertData(Integer noMem, Integer maxMem, String group_name, Integer position) {
        if (noMem < maxMem) {
            mGratoViewModel.getResponseJoinGroup().observe(getViewLifecycleOwner(), voidResponse -> {
                Log.d("join group", voidResponse.message());
                if (voidResponse.isSuccessful()) {
                    CharSequence text = "Join group successful";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getContext(), text, duration);
                    toast.show();
                    isJoin = true;
                    positionJoin = position;
                    mGratoViewModel.findGroup(loginResponse.getToken(), loginResponse.getUser().getId(), "CO3005", 202, "L01");
                } else {
                    CharSequence text = "Join group unsuccessful";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getContext(), text, duration);
                    toast.show();
                }
            });

            mGratoViewModel.joinGroup(loginResponse.getToken(), "CO3005", 202, "L01",
                    group_name, loginResponse.getUser().getId());
        } else {
            CharSequence text = "The group has enough members. Please join another group";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.setGravity(Gravity.CENTER, 20, 30);
            toast.show();
        }
    }
}