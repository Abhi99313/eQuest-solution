package com.example.mvvm;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private final ApiService apiService;

    public UserRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getUsers(final UserCallback callback) {
        Call<List<Users>> call = apiService.getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public interface UserCallback {
        void onSuccess(List<Users> users);

        void onError(String errorMessage);
    }
}
