package com.example.mvvm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("ios-practical.json")
    Call<List<Users>> getUsers();
}
