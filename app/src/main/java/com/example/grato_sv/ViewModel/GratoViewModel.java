package com.example.grato_sv.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.grato_sv.Model.AbsentCount;
import com.example.grato_sv.Model.Attend;
import com.example.grato_sv.Model.DateCount;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.Model.Mark;
import com.example.grato_sv.Model.QuestionAndAnswer;
import com.example.grato_sv.Model.ShowQuestionAndAnswer;
import com.example.grato_sv.Repository.AbsentRepository;
import com.example.grato_sv.Repository.AddAttendanceRepository;
import com.example.grato_sv.Repository.AttendRepository;
import com.example.grato_sv.Repository.ListQuestionAndAnswerRepository;
import com.example.grato_sv.Model.ClassInfor;
import com.example.grato_sv.Model.Group;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.LoginResponse;
import com.example.grato_sv.Model.Member;
import com.example.grato_sv.Repository.ClassInforRepository;
import com.example.grato_sv.Repository.GroupRepository;
import com.example.grato_sv.Repository.ListQuizRepository;
import com.example.grato_sv.Repository.ListMarkRepository;
import com.example.grato_sv.Repository.ShowQuestionAndAnswerReponsitory;
import com.example.grato_sv.Repository.TookAttendanceRepository;
import com.example.grato_sv.Repository.UserRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class GratoViewModel extends ViewModel {

    private UserRepository mUserRepository;
    private MutableLiveData<LoginResponse> mLoginResponse;
    private LoginFailListener loginFailListener;

    private ListQuizRepository mListQuizRepository;
    private MutableLiveData<List<ListQuiz>> mAllQuizOfClass;

    private ListQuestionAndAnswerRepository mListQuestionAndAnswerRepository;
    private MutableLiveData<List<QuestionAndAnswer>> mQuestionAndAnswer;

    private ListMarkRepository mListMarkRepository;
    private MutableLiveData<List<Mark>> mMark;

    private TookAttendanceRepository mTookAttendanceRepository;
    private MutableLiveData<List<DateCount>> mDate;

    private AbsentRepository mAbsentRepository;
    private MutableLiveData<List<AbsentCount>> mAbsent;

    private AttendRepository mAttendRepository;
    private MutableLiveData<List<Attend>> mAttend;

    private AddAttendanceRepository mAddAttendRepository;
    private MutableLiveData<Response<Void>> mAddAttend;

    private ShowQuestionAndAnswerReponsitory mShowQuestionAndAnswerRepository;
    private MutableLiveData<List<ShowQuestionAndAnswer>> mShowQuestionAndAnswer;
    private MutableLiveData<List<ClassInfor>> mClassInfor;
    private ClassInforRepository mClassInforReposity;

    private MutableLiveData<List<Group>> mGroup;
    private GroupRepository mGroupRepository;
    private MutableLiveData<List<Member>> mMember;
    private MutableLiveData<Response<Void>> mCreateGroup;
    private MutableLiveData<String> mFindGroup;
    private MutableLiveData<Response<Void>> mJoinGroup;
    private MutableLiveData<Response<Void>> mOutGroup;
    private MutableLiveData<List<Integer>> mNoMax;
    private MutableLiveData<Response<Void>> mDeleteGroup;

    public GratoViewModel() {
        mUserRepository = UserRepository.getInstance();
        mLoginResponse = new MutableLiveData<>();

        mListQuizRepository = ListQuizRepository.getInstance();
        mAllQuizOfClass = new MutableLiveData<>();

        mListQuestionAndAnswerRepository = mListQuestionAndAnswerRepository.getInstance();
        mQuestionAndAnswer = new MutableLiveData<>();

        mListMarkRepository = mListMarkRepository.getInstance();
        mMark = new MutableLiveData<>();

        mTookAttendanceRepository = mTookAttendanceRepository.getInstance();
        mDate = new MutableLiveData<>();

        mAbsentRepository = mAbsentRepository.getInstance();
        mAbsent = new MutableLiveData<>();

        mAttendRepository = mAttendRepository.getInstance();
        mAttend = new MutableLiveData<>();

        mAddAttendRepository = mAddAttendRepository.getInstance();
        mAddAttend = new MutableLiveData<>();

        mShowQuestionAndAnswerRepository = mShowQuestionAndAnswerRepository.getInstance();
        mShowQuestionAndAnswer = new MutableLiveData<>();

        mClassInforReposity = ClassInforRepository.getInstance();
        mClassInfor = new MutableLiveData<>();

        mGroupRepository = GroupRepository.getInstance();
        mGroup = new MutableLiveData<>();
        mMember = new MutableLiveData<>();
        mCreateGroup = new MutableLiveData<>();
        mFindGroup = new MutableLiveData<>();
        mJoinGroup = new MutableLiveData<>();
        mOutGroup = new MutableLiveData<>();
        mNoMax = new MutableLiveData<>();
        mDeleteGroup = new MutableLiveData<>();
    }

    public void login(String id, String password) {
        mUserRepository.login(id, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull LoginResponse loginResponse) {
                        //Log.d("Viewmodel", loginResponse.getUser().getName());
                        mLoginResponse.setValue(loginResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //Log.d("Viewmodel", "Error : " + e.getMessage());
                        loginFailListener.onLoginFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<LoginResponse> getLoginResponse() {
        return mLoginResponse;
    }

    public interface LoginFailListener {
        void onLoginFail();
    }

    public void setLoginFailListener(LoginFailListener loginFailListener) {
        this.loginFailListener = loginFailListener;
    }


    //===================== Thong do =======================

    // subject information
    public void fetchClassInfor(String token, String sub_id, Integer semester_id, String class_id) {
        mClassInforReposity.getClassInfor(token, sub_id, semester_id, class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<ClassInfor>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ClassInfor> classInfors) {
                        mClassInfor.setValue(classInfors);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<ClassInfor>> getResponseClassInfor() {
        return mClassInfor;
    }

    // list group
    public void fetchListGroup(String token, String sub_id, Integer semester_id, String class_id) {
        mGroupRepository.getListGroup(token, sub_id, semester_id, class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Group>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Group> groups) {
                        mGroup.setValue(groups);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Group>> getResponseListGroup() {
        return mGroup;
    }


    // list member in group
    public void fetchMemberInGroup(String token, String sub_id, Integer semester_id, String class_id, String group_name) {
        mGroupRepository.getMemberInGroup(token, sub_id, semester_id, class_id, group_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Member>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Member> members) {
                        mMember.setValue(members);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("View mode error", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Member>> getResponseMemberInGroup() {
        return mMember;
    }

    // create group
    public void createGroup(String token, String sub_id, Integer semester_id,
                            String class_id, String group_name, Integer max_student) {
        mGroupRepository.createGroup(token, sub_id, semester_id, class_id, group_name, max_student)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mCreateGroup.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseCreateGroup() {
        return mCreateGroup;
    }

    // find group
    public void findGroup(String token, String user_id, String sub_id, Integer semester_id,
                          String class_id) {
        mGroupRepository.findGroup(token, user_id, sub_id,semester_id,class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull String s) {
                        mFindGroup.setValue(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<String> getResponseFindGroup() {
        return mFindGroup;
    }

    // join group
    public void joinGroup(String token, String sub_id, Integer semester_id,
                          String class_id, String group_name, String user_id) {
        mGroupRepository.joinGroup(token, sub_id, semester_id, class_id, group_name, user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mJoinGroup.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseJoinGroup() {
        return mJoinGroup;
    }

    // out group
    public void outGroup(String token, String sub_id, Integer semester_id,
                         String class_id, String group_name, String userd_id) {
        mGroupRepository.outGroup(token, sub_id, semester_id, class_id, group_name, userd_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mOutGroup.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseOutGroup() {
        return mOutGroup;
    }

    // delete group
    public void deleteGroup(String token, String sub_id, Integer semester_id,
                         String class_id, String group_name) {
        mGroupRepository.deleteGroup(token, sub_id, semester_id, class_id, group_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mDeleteGroup.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseDeleteGroup() {
        return mDeleteGroup;
    }


    // find no max
    public void fetchgetNoMax(String token, String sub_id, Integer semester_id, String class_id, String group_name) {
        mGroupRepository.getNoMax(token, sub_id, semester_id, class_id, group_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Integer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Integer> integers) {
                        mNoMax.setValue(integers);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Integer>> getResponseGetNoMax() {
        return mNoMax;
    }


    // Tung
    public void fetchAllQuizsOfClass(String token, String sub_id, Integer semester_id, String class_id) {
        mListQuizRepository.getQuizOfClass(token, sub_id, semester_id, class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<ListQuiz>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ListQuiz> quizzes) {
                        mAllQuizOfClass.setValue(quizzes);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<ListQuiz>> getResponseAllQuizsOfClass() {
        return mAllQuizOfClass;
    }

    public void fetchQuestionAndAnswer(String token, String sub_id, Integer semester_id, String class_id, String quiz_name, Integer question_id) {
        mListQuestionAndAnswerRepository.getQuestionAndAnswer(token, sub_id, semester_id, class_id, quiz_name, question_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<QuestionAndAnswer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }


                    @Override
                    public void onSuccess(@NonNull List<QuestionAndAnswer> questionAndAnswers) {
                        mQuestionAndAnswer.setValue(questionAndAnswers);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<QuestionAndAnswer>> getResponseQuestionAndAnswer() {
        return mQuestionAndAnswer;
    }

    public void fetchMark(String token, String sub_id, Integer semester_id, String class_id) {
        mListMarkRepository.getListMark(token, sub_id, semester_id, class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Mark>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }


                    @Override
                    public void onSuccess(@NonNull List<Mark> marks) {
                        mMark.setValue(marks);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Mark>> getResponseMark() {
        return mMark;
    }

    public void fetchCount(String token, String sub_id, Integer semester_id, String class_id) {
        mTookAttendanceRepository.getCountDate(token, sub_id, semester_id, class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<DateCount>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }


                    @Override
                    public void onSuccess(@NonNull List<DateCount> dateCount) {
                        mDate.setValue(dateCount);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<DateCount>> getResponseCountDate() {
        return mDate;
    }

    public void fetchAbsent(String token, String sub_id, Integer semester_id, String class_id) {
        mAbsentRepository.getCountAbsent(token, sub_id, semester_id, class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AbsentCount>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<AbsentCount> absentCounts) {
                        mAbsent.setValue(absentCounts);
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        //Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<AbsentCount>> getResponseCountAbsent() {
        return mAbsent;
    }

    public void fetchAttend(String token, String sub_id, Integer semester_id, String class_id) {
        mAttendRepository.getAttend(token, sub_id, semester_id, class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Attend>>() {


                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Attend> attends) {
                        mAttend.setValue(attends);
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        //Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Attend>> getResponseAttend() {
        return mAttend;
    }

    public void fetchAddAttend(String token, String sub_id, Integer semester_id, String class_id, Date date) {
        mAddAttendRepository.getAddAttend(token, sub_id, semester_id, class_id, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }


                    @Override
                    public void onSuccess(@NonNull Response<Void> attends) {
                        mAddAttend.setValue(attends);
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        //Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseAddAttend() {
        return mAddAttend;
    }

    public void fetchShowQuestionAndAnswer(String token, String sub_id, Integer semester_id, String class_id, String quiz_name, Integer question_id) {
        mShowQuestionAndAnswerRepository.getShowQuestionAndAnswer(token, sub_id, semester_id, class_id, quiz_name, question_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<ShowQuestionAndAnswer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }


                    @Override
                    public void onSuccess(@NonNull List<ShowQuestionAndAnswer> showQuestionAndAnswers) {
                        mShowQuestionAndAnswer.setValue(showQuestionAndAnswers);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<ShowQuestionAndAnswer>> getResponseShowQuestionAndAnswer() {
        return mShowQuestionAndAnswer;
    }


}
