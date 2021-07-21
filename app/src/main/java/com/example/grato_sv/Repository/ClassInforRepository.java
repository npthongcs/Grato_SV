package com.example.grato_sv.Repository;

import android.util.Log;

import com.example.grato_sv.Api.ApiRequest;
import com.example.grato_sv.Api.RetrofitInit;
import com.example.grato_sv.Model.ClassInfor;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public class ClassInforRepository {
    private static ClassInforRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private ClassInforRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static ClassInforRepository getInstance(){
        if(mInstance == null){
            mInstance = new ClassInforRepository();
        }
        return mInstance;
    }

    public Maybe<List<ClassInfor>> getClassInfor(String token, String sub_id, Integer semester_id, String class_id){
        Log.d("get class infor:",sub_id+" "+semester_id+" "+class_id);
        return mApiRequest.getClassInfor(token,sub_id,semester_id,class_id);
    }

}
