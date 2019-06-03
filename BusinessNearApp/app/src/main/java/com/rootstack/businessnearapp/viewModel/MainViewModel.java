package com.rootstack.businessnearapp.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.view.View;

import com.rootstack.businessnearapp.base.BaseApplication;
import com.rootstack.businessnearapp.model.Result;
import com.rootstack.businessnearapp.model.UserRequest;
import com.rootstack.businessnearapp.rest.JsonPlaceHolderApi;

import java.util.ArrayList;
import java.util.List;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private List<Result> userList;
    private Context context;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Realm realm;
    public MutableLiveData<List<Result>> listUser;
    public MutableLiveData<Integer> progressVisibility;
    public MutableLiveData<Integer> listVisibility;
    public int usersCount = 0;

    public MainViewModel() {

        realm = Realm.getDefaultInstance();
        this.context = BaseApplication.getInstance().getApplicationContext();
        this.userList = new ArrayList<>();
        jsonPlaceHolderApi = ((BaseApplication) context).mRetrofit.create(JsonPlaceHolderApi.class);

        listUser = new MutableLiveData<>();
        progressVisibility = new MutableLiveData<>();
        listVisibility = new MutableLiveData<>();

        progressVisibility.setValue(View.VISIBLE);
        listVisibility.setValue(View.INVISIBLE);

        usersCount = 0;

    }

    public void filterList(String charString) {
        RealmResults<Result> realmResults = realm.where(Result.class)
                .beginGroup()
                .contains("name.first", charString, Case.INSENSITIVE)
                .or().contains("name.last", charString, Case.INSENSITIVE)
                .or().contains("email", charString, Case.INSENSITIVE)
                .endGroup()
                .findAll();
        setUserList(realm.copyFromRealm(realmResults));
    }

    public void fetchUsersList() {

        usersCount +=10;

            jsonPlaceHolderApi.getUsers(usersCount)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UserRequest>() {

                        @Override
                        public void onCompleted() {}

                        @Override
                        public void onError(Throwable e) {
                            List<Result> users =  getUserList();
                            setUserList(users);
                        }

                        @Override
                        public void onNext(UserRequest requests) {
                            saveOnRealm(requests.getResults());
                        }
                    });

    }

    public void setUserList(List<Result> users) {
        listUser.setValue(users);
        progressVisibility.setValue(View.GONE);
        listVisibility.setValue(View.VISIBLE);
    }

    public List<Result> getUserList() {
        RealmResults<Result> shopResult = realm.where(Result.class).findAll();
        return shopResult;
    }

    public void saveOnRealm(final List<Result> users) {
        if (realm != null) {
            realm.executeTransaction((realm) -> {
                realm.insert(users);
            });
        }
        setUserList(users);
    }
}
