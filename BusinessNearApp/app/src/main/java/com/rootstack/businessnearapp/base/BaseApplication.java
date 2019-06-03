package com.rootstack.businessnearapp.base;

import android.app.Application;

import com.rootstack.businessnearapp.rest.Endpoints;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApplication  extends Application {


        static BaseApplication instaceCeiba;
        public static Retrofit mRetrofit;

        public static BaseApplication getInstance(){
            return instaceCeiba;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            instaceCeiba = this;
            initialRetrofit();
            initialRalm();
        }

        private void initialRalm() {
            Realm.init(this);
            RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            Realm.setDefaultConfiguration(realmConfiguration);
        }
        private void initialRetrofit() {

            HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
            loggin.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(loggin);

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Endpoints.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        public static Retrofit getRestClient(){
            return mRetrofit;
        }

    }
