package com.example.grato_sv.Repository;

import android.util.Log;
import com.example.grato_sv.Api.ApiRequest;
import com.example.grato_sv.Api.RetrofitInit;
import com.example.grato_sv.Model.AbsentCount;
import com.example.grato_sv.Model.DateCount;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.QuestionAndAnswer;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import okhttp3.ResponseBody;
import retrofit2.Response;

import io.reactivex.rxjava3.core.Maybe;

public class AbsentRepository {
    private static AbsentRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private AbsentRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static AbsentRepository getInstance(){
        if(mInstance == null){
            mInstance = new AbsentRepository();
        }
        return mInstance;
    }

    public Maybe<List<AbsentCount>> getCountAbsent(String token, String sub_id, Integer semester_id, String class_id){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.getAbsent(token, sub_id, semester_id,class_id);
    }
}