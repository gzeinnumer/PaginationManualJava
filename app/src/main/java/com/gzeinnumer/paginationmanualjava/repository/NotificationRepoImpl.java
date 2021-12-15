package com.gzeinnumer.paginationmanualjava.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.gzeinnumer.paginationmanualjava.base.BaseListResponse;
import com.gzeinnumer.paginationmanualjava.base.BaseResource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class NotificationRepoImpl {

//    private final NotificationRepoImpl notificationRepo;
//    notificationRepo = new NotificationRepoImpl(application.getApplicationContext());

    private final Context context;
    private final CompositeDisposable compositeDisposable;

    public NotificationRepoImpl(Context applicationContext) {
        context = applicationContext;
        compositeDisposable = new CompositeDisposable();
        notificationList = new MutableLiveData<>();
    }

    private final MutableLiveData<BaseResource<List<String>>> notificationList;

    public void setNotification(int page) {
        notificationList.postValue(BaseResource.loading());

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");

        BaseListResponse<String> res = new BaseListResponse<>();
        res.setData(list);
        notificationList.postValue(BaseResource.success(res.getTitle(), res.getMessage(), list, res.getTotal()));
    }

    public MutableLiveData<BaseResource<List<String>>> getNotificationList() {
        return notificationList;
    }
}
