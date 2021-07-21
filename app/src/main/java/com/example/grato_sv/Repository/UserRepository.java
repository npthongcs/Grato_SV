package com.example.grato_sv.Repository;

import android.util.Log;

import com.example.grato_sv.Api.ApiRequest;
import com.example.grato_sv.Api.RetrofitInit;
import com.example.grato_sv.Model.LoginResponse;

import io.reactivex.rxjava3.core.Maybe;

public class UserRepository {
    private static UserRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private UserRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static UserRepository getInstance(){
        if(mInstance == null){
            mInstance = new UserRepository();
        }
        return mInstance;
    }

    public Maybe<LoginResponse> login(String id, String password){
        Log.d("repoLogin", id + password);
        return mApiRequest.login(id, password, "SV");
    }

}
