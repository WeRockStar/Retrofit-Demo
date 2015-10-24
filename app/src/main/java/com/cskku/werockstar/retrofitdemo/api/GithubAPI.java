package com.cskku.werockstar.retrofitdemo.api;

import com.cskku.werockstar.retrofitdemo.model.Github;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Kotchaphan on 24/10/2558.
 */
public interface GithubAPI {
    @GET("/users/{user}")
    public void getFeed(@Path("user") String user, Callback<Github> response);
}
