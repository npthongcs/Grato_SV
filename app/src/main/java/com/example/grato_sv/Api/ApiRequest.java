package com.example.grato_sv.Api;


import android.util.Log;

import com.example.grato_sv.Model.AbsentCount;
import com.example.grato_sv.Model.Attend;
import com.example.grato_sv.Model.DateCount;
import com.example.grato_sv.Model.ClassInfor;
import com.example.grato_sv.Model.Group;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.Model.Member;
import com.example.grato_sv.Model.Mark;
import com.example.grato_sv.Model.QuestionAndAnswer;
import com.example.grato_sv.Model.ShowQuestionAndAnswer;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import kotlin.jvm.JvmMultifileClass;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {

    // ------------------------- Local ---------------------------
    // user
    @FormUrlEncoded // parse sang dạng form để gửi lên
    @POST("authenticate/signin")
    Maybe<LoginResponse> login(
            @Field("id") String id,
            @Field("password") String password,
            @Field("job_type") String job_type
    );

    // quiz
    @GET("student/quiz/quizofclass")
    Maybe<List<ListQuiz>> getQuizOfClass(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id
    );
    @POST("student/quiz/submitquiz")
    Maybe<Response<Void>> addQuiz(
            @Header("authorization") String authorization,
            @Field("sub_id") String sub_id,
            @Field("semester_id") Integer semester_id,
            @Field("quiz_name") String quiz_name,
            @Field("answer") String answer,
            @Field("time") Double time,
            @Field("score") Double score
    );
    @GET("student/quiz/showquestion")
    Maybe<List<QuestionAndAnswer>> getQuestionAndAnswer(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id,
            @Query("quiz_name") String quiz_name,
            @Query("question_id") Integer question_id
    );




    //=============================== Thong do ==========================
    @GET("student/subject/inforclass")
    Maybe<List<ClassInfor>> getClassInfor(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id
    );
    @GET("student/group/listgroup")
    Maybe<List<Group>> getListGroup(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id
    );

    @GET("student/quiz/showattendance")
    Maybe<List<DateCount>> getCount(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id
    );

    @GET("student/quiz/countabsent")
    Maybe<List<AbsentCount>> getAbsent(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id
    );

    @GET("student/quiz/attend")
    Maybe<List<Attend>> getAttend(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id
    );
    @GET("student/group/viewmem")
    Maybe<List<Member>> getMemberInGroup(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id,
            @Query("group_name") String group_name
    );
    @FormUrlEncoded
    @POST("student/group/creategroup")
    Maybe<Response<Void>> createGroup(
            @Header("authorization") String authorization,
            @Field("sub_id") String sub_id,
            @Field("semester_id") Integer semester_id,
            @Field("class_id") String class_id,
            @Field("group_name") String group_name,
            @Field("max_student") Integer max_student
    );
    @GET("student/group/findgroup")
    Maybe<String> findGroup(
            @Header("authorization") String authorization,
            @Query("user_id") String user_id,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id
    );
    @FormUrlEncoded
    @POST("student/group/joingroup")
    Maybe<Response<Void>> joinGroup(
            @Header("authorization") String authorization,
            @Field("sub_id") String sub_id,
            @Field("semester_id") Integer semester_id,
            @Field("class_id") String class_id,
            @Field("group_name") String group_name,
            @Field("user_id") String user_id
    );
    @DELETE("student/group/outgroup")
    Maybe<Response<Void>> outgroup(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id,
            @Query("group_name") String group_name,
            @Query("user_id") String user_id
    );
    @DELETE("student/group/deletegroup")
    Maybe<Response<Void>> deleteGroup(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id,
            @Query("group_name") String group_name
    );
    @GET("student/group/getnomax")
    Maybe<List<Integer>> getNoMax(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id,
            @Query("group_name") String group_name
    );

    @FormUrlEncoded
    @POST("student/quiz/addattendance")
    Maybe<Response<Void>> addattend(
            @Header("authorization") String authorization,
            @Field("sub_id") String sub_id,
            @Field("semester_id") Integer semester_id,
            @Field("class_id") String class_id,
            @Field("date") Date date
            );

    @GET("student/quiz/showquiz")
    Maybe<List<ShowQuestionAndAnswer>> getShowQuestionAndAnswer(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id,
            @Query("quiz_name") String quiz_name,
            @Query("question_id") Integer question_id
    );

    // Tung

    @GET("student/quiz/showmark")
    Maybe<List<Mark>> getMark(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id
    );


}
