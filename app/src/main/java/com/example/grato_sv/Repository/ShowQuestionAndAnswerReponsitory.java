package com.example.grato_sv.Repository;

import android.util.Log;
import com.example.grato_sv.Api.ApiRequest;
import com.example.grato_sv.Api.RetrofitInit;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.QuestionAndAnswer;
import com.example.grato_sv.Model.ShowQuestionAndAnswer;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import okhttp3.ResponseBody;
import retrofit2.Response;

import io.reactivex.rxjava3.core.Maybe;

public class ShowQuestionAndAnswerReponsitory {
    private static ShowQuestionAndAnswerReponsitory mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private ShowQuestionAndAnswerReponsitory(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static ShowQuestionAndAnswerReponsitory getInstance(){
        if(mInstance == null){
            mInstance = new ShowQuestionAndAnswerReponsitory();
        }
        return mInstance;
    }

    public Maybe<List<ShowQuestionAndAnswer>> getShowQuestionAndAnswer(String token, String sub_id, Integer semester_id, String class_id, String quiz_name, Integer question_id){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.getShowQuestionAndAnswer(token, sub_id, semester_id,class_id,quiz_name,question_id);
    }
}