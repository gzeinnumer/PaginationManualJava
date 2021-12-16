# PaginationManualJava

- app
  - base
    - [BaseListResponse.java](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/src/main/java/com/gzeinnumer/paginationmanualjava/base/BaseListResponse.java)
    - [BaseResource.java](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/src/main/java/com/gzeinnumer/paginationmanualjava/base/BaseResource.java)
  - main
    - [MainActivity.java](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/src/main/java/com/gzeinnumer/paginationmanualjava/main/MainActivity.java)
    - [NotificationVM.java](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/src/main/java/com/gzeinnumer/paginationmanualjava/main/NotificationVM.java)
  - pager
    - [NotificationPagerV2.java](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/src/main/java/com/gzeinnumer/paginationmanualjava/pager/NotificationPagerV2.java)
  - params
    - [PagingParams.java](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/src/main/java/com/gzeinnumer/paginationmanualjava/params/PagingParams.java)
  - repository
    - [NotificationRepoImpl.java](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/src/main/java/com/gzeinnumer/paginationmanualjava/repository/NotificationRepoImpl.java)
- res
  - layout
    - [activity_main.xml](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/src/main/res/layout/activity_main.xml)
    - [item_notifikasi.xml](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/src/main/res/layout/item_notifikasi.xml)
  - value
    - [themes.xml](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/src/main/res/values/themes.xml)
- gradle
  - [gradle.app](https://github.com/gzeinnumer/PaginationManualJava/blob/master/app/build.gradle)

---

```java
vm.getNotification().observe(this, resource -> {
    switch (resource.status) {
        case STATUS_1_SUCCESS:
            swipe(binding.swipeRefreshLayout, false);
            list = resource.data;
            emptyState(list != null ? list.size() : 0, binding.rv, binding.imgEmpty);
            params.setTotalData(resource.total);
            adapter.addNewItem(resource.data);
            break;
        case STATUS_2_ERROR:
            swipe(binding.swipeRefreshLayout, false);
            emptyState(list != null ? list.size() : 0, binding.rv, binding.imgEmpty);
            break;
        case STATUS_6_LOADING:
            swipe(binding.swipeRefreshLayout, true);
            emptyState(list != null ? list.size() : 0, binding.rv, binding.imgEmpty);
            break;
    }
});
```
 
```xml
<androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    android:id="@+id/swipeRefreshLayout"
    style="@style/swipeRefreshLayoutV1">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rv"
            style="@style/LLEmptyStateRV"
            android:visibility="gone"
            tools:itemCount="3"
            tools:listitem="@layout/item_notifikasi"
            tools:visibility="visible" />

        <LinearLayout
            android:id="@+id/imgEmpty"
            style="@style/LLEmptyState"
            android:visibility="gone"
            tools:visibility="visible">

            <ImageView
                style="@style/LLEmptyStateImage"
                android:src="@drawable/img_no_data_v3" />

        </LinearLayout>
    </LinearLayout>
</androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```

```xml
<style name="swipeRefreshLayoutV1">
    <item name="android:layout_width">match_parent</item>
    <item name="android:layout_height">0dp</item>
    <item name="android:layout_weight">1</item>
</style>

<style name="LLEmptyStateRV">
    <item name="android:layout_width">match_parent</item>
    <item name="android:layout_height">0dp</item>
    <item name="android:layout_weight">1</item>
    <item name="android:overScrollMode">never</item>
</style>

<style name="LLEmptyState">
    <item name="android:layout_width">match_parent</item>
    <item name="android:layout_height">0dp</item>
    <item name="android:layout_weight">1</item>
    <item name="android:gravity">center</item>
    <item name="android:orientation">vertical</item>
</style>

<style name="LLEmptyStateImage">
    <item name="android:layout_width">300dp</item>
    <item name="android:layout_height">wrap_content</item>
    <item name="android:adjustViewBounds">true</item>
    <item name="android:scaleType">fitCenter</item>
    <item name="android:src">@drawable/img_no_data_v3</item>
</style>
```

---

```
Copyright 2021 M. Fadli Zein
```