package com.gzeinnumer.paginationmanualjava.main;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.akiniyalocts.pagingrecycler.PagingDelegate;
import com.gzeinnumer.paginationmanualjava.databinding.ActivityMainBinding;
import com.gzeinnumer.paginationmanualjava.params.PagingParams;
import com.gzeinnumer.paginationmanualjava.pager.NotificationPagerV2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NotificationVM vm;
    private PagingParams params = new PagingParams();
    private List<String> list = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        params = new PagingParams();
        initPager();
        vm.setNotification(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModelProvider(this).get(NotificationVM.class);

        initObserver();
        initOnClick();
    }

    private void initObserver() {
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
    }

    private void initOnClick() {
        binding.swipeRefreshLayout.setOnRefreshListener(this::onResume);
    }

    protected void swipe(SwipeRefreshLayout swipeRefreshLayout, boolean swipe) {
        swipeRefreshLayout.setRefreshing(swipe);
    }

    protected void emptyState(int size, RecyclerView rv, LinearLayout imgEmpty) {
        rv.setVisibility(View.GONE);
        imgEmpty.setVisibility(View.GONE);

        rv.setVisibility(size == 0 ? View.GONE : View.VISIBLE);
        imgEmpty.setVisibility(size == 0 ? View.VISIBLE : View.GONE);
    }

    private NotificationPagerV2 adapter;

    private void initPager() {
        list = new ArrayList<>();
        adapter = new NotificationPagerV2();
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rv.hasFixedSize();

        new PagingDelegate.Builder(adapter)
                .attachTo(binding.rv)
                .listenWith(new PagingDelegate.OnPageListener() {
                    @Override
                    public void onPage(int i) {
                        if (params.getCurrentPage() < params.getTotalPage()) {
                            params.addCurrentPage();
                            vm.setNotification(params.getCurrentPage());
                        } else {
                            onDonePaging();
                        }
                    }

                    @Override
                    public void onDonePaging() {

                    }
                })
                .build();
    }
}