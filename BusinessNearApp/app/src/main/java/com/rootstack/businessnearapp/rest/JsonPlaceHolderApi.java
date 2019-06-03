package com.rootstack.businessnearapp.rest;

import com.rootstack.businessnearapp.model.UserRequest;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface JsonPlaceHolderApi {
    @GET(Endpoints.GET_USERS)
    Observable<UserRequest> getUsers(@Query(value = "results") int count);
}
