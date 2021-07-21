package com.example.grato_sv.Repository;

import android.util.Log;
import com.example.grato_sv.Api.ApiRequest;
import com.example.grato_sv.Api.RetrofitInit;
import com.example.grato_sv.Model.Mark;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public class ListMarkRepository {
    private static ListMarkRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private ListMarkRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static ListMarkRepository getInstance(){
        if(mInstance == null){
            mInstance = new ListMarkRepository();
        }
        return mInstance;
    }

    public Maybe<List<Mark>> getListMark(String token, String sub_id, Integer semester_id, String class_id){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.getMark(token, sub_id, semester_id,class_id);
    }

}
