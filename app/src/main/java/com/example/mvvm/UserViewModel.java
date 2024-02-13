package com.example.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<List<Users>> users = new MutableLiveData<>();
    private final UserRepository userRepository;

    public UserViewModel() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        userRepository = new UserRepository(apiService);
    }

    public LiveData<List<Users>> getUsers() {
        return users;
    }

    public void fetchUsers() {
        userRepository.getUsers(new UserRepository.UserCallback() {
            @Override
            public void onSuccess(List<Users> userList) {
                users.postValue(userList);
            }

            @Override
            public void onError(String errorMessage) {
                // Handle error
            }
        });
    }

}

