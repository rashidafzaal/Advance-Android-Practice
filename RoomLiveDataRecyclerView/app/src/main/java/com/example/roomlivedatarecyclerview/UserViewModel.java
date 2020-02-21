package com.example.roomlivedatarecyclerview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomlivedatarecyclerview.Room.AppDatabase;
import com.example.roomlivedatarecyclerview.Room.DatabaseSingletonClient;
import com.example.roomlivedatarecyclerview.Room.User;
import com.example.roomlivedatarecyclerview.Room.UserDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserViewModel extends AndroidViewModel {

    private UserDAO userDao;
    private ExecutorService executorService;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userDao = DatabaseSingletonClient.getInstance(application).getAppDatabase().userDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    LiveData<List<User>> getAllData() {
        return userDao.getAllData();
    }

    void saveUserData(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    void deleteUserData(User user) {
        executorService.execute(() -> userDao.delete(user));
    }
}