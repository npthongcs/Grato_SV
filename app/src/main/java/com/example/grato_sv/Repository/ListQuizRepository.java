package com.example.grato_sv.Repository;

import android.util.Log;
import com.example.grato_sv.Api.ApiRequest;
import com.example.grato_sv.Api.RetrofitInit;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.QuestionAndAnswer;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import okhttp3.ResponseBody;
import retrofit2.Response;

import io.reactivex.rxjava3.core.Maybe;

public class ListQuizRepository {
    private static ListQuizRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private ListQuizRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static ListQuizRepository getInstance(){
        if(mInstance == null){
            mInstance = new ListQuizRepository();
        }
        return mInstance;
    }

    public Maybe<List<ListQuiz>> getQuizOfClass(String token, String sub_id, Integer semester_id, String class_id){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.getQuizOfClass(token, sub_id, semester_id,class_id);
    }

}
