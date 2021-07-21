package com.example.grato_sv.Repository;

import android.util.Log;
import com.example.grato_sv.Api.ApiRequest;
import com.example.grato_sv.Api.RetrofitInit;
import com.example.grato_sv.Model.AbsentCount;
import com.example.grato_sv.Model.Attend;
import com.example.grato_sv.Model.DateCount;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.QuestionAndAnswer;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import okhttp3.ResponseBody;
import retrofit2.Response;

import io.reactivex.rxjava3.core.Maybe;

public class AttendRepository {
    private static AttendRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private AttendRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static AttendRepository getInstance(){
        if(mInstance == null){
            mInstance = new AttendRepository();
        }
        return mInstance;
    }

    public Maybe<List<Attend>> getAttend(String token, String sub_id, Integer semester_id, String class_id){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.getAttend(token, sub_id, semester_id,class_id);
    }
}