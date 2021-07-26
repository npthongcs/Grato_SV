package com.example.grato_sv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.Activity.ListMemberInGroup;
import com.example.grato_sv.Model.Group;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.R;
import com.example.grato_sv.SessionManagement;
import com.example.grato_sv.ViewModel.GratoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Response;

public class ListGroupInClassAdapter extends RecyclerView.Adapter<ListGroupInClassAdapter.GroupViewHolder> {
    ArrayList<Group> lstGroup;
    String gname="";
    Context context;

    GroupItemListener mGroupItemListener;

    LoginResponse loginResponseSession;

    public ListGroupInClassAdapter(ArrayList<Group> lstGroup, String gname){
        this.lstGroup = lstGroup;
        this.gname = gname;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View groupView = inflater.inflate(R.layout.thong_group_item, parent, false);

        // get session token
        SessionManagement sessionManagement = SessionManagement.getInstance(context);
        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);

        return new GroupViewHolder(groupView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        Group mgroup = lstGroup.get(position);

        if (mgroup.getGname().equals(gname)){
            holder.btnMode.setText("OUT");
        }
        else {
            holder.btnMode.setText("JOIN");
        }

        holder.vtNameGroup.setText(mgroup.getGname());
        holder.vtWholesale.setText(mgroup.getNoStudent()+"");
        holder.vtMaxStudent.setText(mgroup.getMaxStudent()+"");

        holder.btnMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListMemberInGroup.class);
                Log.d("name group adapter",mgroup.getGname());
                intent.putExtra("nameGroup",mgroup.getGname());
                context.startActivity(intent);
            }
        });

        holder.btnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mode;
                mode = holder.btnMode.getText().toString();
                Log.d("Mode",mode);
                if (mode.equals("OUT")) mGroupItemListener.clickOutGroup(mgroup.getGname(), position);
                else if (mode.equals("JOIN")) mGroupItemListener.clickJoinGroup(mgroup.getGname(), position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                mGroupItemListener.clickDeleteGroup(mgroup.getGname(),mgroup.getNoStudent());
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return lstGroup == null ? 0 : lstGroup.size();
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder{

        public TextView vtNameGroup, vtMaxStudent, vtWholesale;
        Button btnMember,btnMode;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            vtNameGroup = itemView.findViewById(R.id.name_group);
            vtMaxStudent = itemView.findViewById(R.id.maxStudent);
            vtWholesale = itemView.findViewById(R.id.wholesale);
            btnMember = itemView.findViewById(R.id.button_view_member);
            btnMode = itemView.findViewById(R.id.button_mode);
        }
    }

    public interface GroupItemListener{
        void clickOutGroup(String group_name, Integer position);
        void clickJoinGroup(String group_name, Integer position);
        void clickDeleteGroup(String group_name, Integer noNow);
    }

    public void setGroupItemListener (GroupItemListener groupItemListener){
        mGroupItemListener = groupItemListener;
    }
}

