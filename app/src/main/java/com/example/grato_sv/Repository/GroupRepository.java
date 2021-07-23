package com.example.grato_sv.Repository;

import android.util.Log;

import com.example.grato_sv.Api.ApiRequest;
import com.example.grato_sv.Api.RetrofitInit;
import com.example.grato_sv.Model.Group;
import com.example.grato_sv.Model.Member;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import retrofit2.Response;

public class GroupRepository {
    private static GroupRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private GroupRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }
    // khởi tạo mInstance
    public static GroupRepository getInstance(){
        if(mInstance == null){
            mInstance = new GroupRepository();
        }
        return mInstance;
    }

    public Maybe<List<Group>> getListGroup(String token, String sub_id, Integer semester_id, String class_id){
        Log.d("get list group:",sub_id+" "+semester_id+" "+class_id);
        return mApiRequest.getListGroup(token,sub_id,semester_id,class_id);
    }

    public Maybe<List<Member>> getMemberInGroup(String token, String sub_id, Integer semester_id, String class_id, String group_name){
        Log.d("get member in group",sub_id+" "+semester_id+" "+class_id+" "+group_name);
        return mApiRequest.getMemberInGroup(token,sub_id,semester_id,class_id,group_name);
    }

    public Maybe<Response<Void>> createGroup(String token, String sub_id, Integer semester_id,
                                             String class_id, String group_name, Integer max_student){
        return mApiRequest.createGroup(token,sub_id,semester_id,class_id,group_name,max_student);
    }

    public Maybe<String> findGroup(String token, String user_id,String sub_id, Integer semester_id,
                                   String class_id){
        return mApiRequest.findGroup(token,user_id,sub_id,semester_id,class_id);
    }

    public Maybe<Response<Void>> joinGroup(String token, String sub_id, Integer semester_id,
                                           String class_id, String group_name, String user_id){
        return mApiRequest.joinGroup(token,sub_id,semester_id,class_id,group_name,user_id);
    }

    public Maybe<Response<Void>> outGroup(String token, String sub_id, Integer semester_id,
                                           String class_id, String group_name, String user_id){
        return mApiRequest.outgroup(token,sub_id,semester_id,class_id,group_name,user_id);
    }

    public Maybe<Response<Void>> deleteGroup(String token, String sub_id, Integer semester_id,
                                          String class_id, String group_name){
        return mApiRequest.deleteGroup(token,sub_id,semester_id,class_id,group_name);
    }

    public Maybe<List<Integer>> getNoMax(String token, String sub_id, Integer semester_id, String class_id, String group_name){
        return mApiRequest.getNoMax(token,sub_id,semester_id,class_id,group_name);
    }
}
