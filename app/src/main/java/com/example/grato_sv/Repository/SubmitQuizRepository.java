package com.example.grato_sv.Repository;

import android.util.Log;
import com.example.grato_sv.Api.ApiRequest;
import com.example.grato_sv.Api.RetrofitInit;
import com.example.grato_sv.Model.AbsentCount;
import com.example.grato_sv.Model.Attend;
import com.example.grato_sv.Model.DateCount;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.QuestionAndAnswer;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import okhttp3.ResponseBody;
import retrofit2.Response;

import io.reactivex.rxjava3.core.Maybe;

public class SubmitQuizRepository {
    private static SubmitQuizRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private SubmitQuizRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static SubmitQuizRepository getInstance(){
        if(mInstance == null){
            mInstance = new SubmitQuizRepository();
        }
        return mInstance;
    }

    public Maybe<Response<Void>> getSubmitQuiz(String token, String sub_id, Integer semester_id, String quiz_name,String answer,Double time,Double score){
        //Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.addQuiz(token, sub_id, semester_id,quiz_name,answer,time,score);
    }
}

