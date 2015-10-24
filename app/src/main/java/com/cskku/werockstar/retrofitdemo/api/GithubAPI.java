package com.cskku.werockstar.retrofitdemo.api;

import com.cskku.werockstar.retrofitdemo.model.Github;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Part;
import retrofit.http.Path;

/**
 * Created by Kotchaphan on 24/10/2558.
 */
public interface GithubAPI {
    @GET("/users/{user}")
    public void getFeed(@Path("user") String user, Callback<Github> response);
}
