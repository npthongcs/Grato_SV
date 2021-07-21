package com.example.grato_sv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.Model.Member;
import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MemberInGroupAdapter extends RecyclerView.Adapter<MemberInGroupAdapter.MemberViewHolder> {
    ArrayList<Member> lstMember;
    Context context;

    LoginResponse loginResponseSession;

    public MemberInGroupAdapter(ArrayList<Member> lstMember){
        this.lstMember = lstMember;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // get session token
        SessionManagement sessionManagement = SessionManagement.getInstance(context);
        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);

        View memberView = inflater.inflate(R.layout.thong_member_item, parent, false);
        return new MemberViewHolder(memberView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = lstMember.get(position);

        holder.vtID.setText(member.getID());
        holder.vtName.setText(member.getName());
    }


    @Override
    public int getItemCount() {
        return lstMember == null ? 0 : lstMember.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder{

        public TextView vtID, vtName;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            vtID = (TextView) itemView.findViewById(R.id.id_student);
            vtName = (TextView) itemView.findViewById(R.id.name_student);
        }
    }
}
