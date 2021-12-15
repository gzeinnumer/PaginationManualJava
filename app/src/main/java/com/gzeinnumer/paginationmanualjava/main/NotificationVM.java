package com.gzeinnumer.paginationmanualjava.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gzeinnumer.paginationmanualjava.base.BaseResource;
import com.gzeinnumer.paginationmanualjava.repository.NotificationRepoImpl;

import java.util.List;

@SuppressWarnings("ALL")
public class NotificationVM extends AndroidViewModel {

    private final NotificationRepoImpl notificationRepo;

    public NotificationVM(Application application) {
        super(application);
        notificationRepo = new NotificationRepoImpl(application.getApplicationContext());
        notification = notificationRepo.getNotificationList();
    }

    private final LiveData<BaseResource<List<String>>> notification;

    public void setNotification(int page) {
        notificationRepo.setNotification(page);
    }

    public LiveData<BaseResource<List<String>>> getNotification() {
        return notification;
    }
}