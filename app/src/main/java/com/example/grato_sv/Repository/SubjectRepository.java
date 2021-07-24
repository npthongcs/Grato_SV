package com.example.grato_sv.Repository;

import android.util.Log;

import com.example.grato_sv.Api.ApiRequest;
import com.example.grato_sv.Api.RetrofitInit;
import com.example.grato_sv.Model.truongSubject;
import com.example.grato_sv.Model.Member;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import retrofit2.Response;

public class SubjectRepository {
    private static SubjectRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private SubjectRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }
    // khởi tạo mInstance
    public static SubjectRepository getInstance(){
        if(mInstance == null){
            mInstance = new SubjectRepository();
        }
        return mInstance;
    }

    public Maybe<List<truongSubject>> getListSubject(String token, String user_id, Integer semester_id){
        Log.d("get list subject:",user_id+" "+semester_id);
        return mApiRequest.getListSubject(token,user_id,semester_id);
    }

}
