package com.gzeinnumer.paginationmanualjava.pager;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.akiniyalocts.pagingrecycler.PagingAdapter;
import com.gzeinnumer.paginationmanualjava.R;
import com.gzeinnumer.paginationmanualjava.databinding.ItemNotifikasiBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationPagerV2 extends PagingAdapter {
    private final List<String> list = new ArrayList<>();
    private Context context;

    public NotificationPagerV2() {}

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new MyHolder(ItemNotifikasiBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        MyHolder myHolder = (MyHolder) holder;
        myHolder.bind(list.get(position), position);
        prepareSpace(myHolder.itemBinding.cv, position);
    }

    @Override
    public int getPagingLayout() {
        return R.layout.item_notifikasi;
    }

    @Override
    public int getPagingItemCount() {
        return list.size();
    }

    public void addNewItem(List<String> data) {
        int lastSize = list.size();
        for (String d: data){
            if (!list.contains(d))
                list.add(d);
        }
        notifyItemInserted(lastSize);
    }

    public int intToDp(int sizeInDPH) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDPH, context.getResources().getDisplayMetrics());
    }

    private void prepareSpace(CardView parent, int position) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) parent.getLayoutParams();

        int topBottomRv = 10;
        int leftRightItem = 10;
        int spaceBetween = 10 / 2;
        if (position == 0) {
            layoutParams.setMargins(intToDp(leftRightItem), intToDp(topBottomRv), intToDp(leftRightItem), intToDp(spaceBetween));
        } else if (position == list.size() - 1) {
            layoutParams.setMargins(intToDp(leftRightItem), intToDp(spaceBetween), intToDp(leftRightItem), intToDp(topBottomRv));
        } else {
            layoutParams.setMargins(intToDp(leftRightItem), intToDp(spaceBetween), intToDp(leftRightItem), intToDp(spaceBetween));
        }
        parent.setLayoutParams(layoutParams);
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public ItemNotifikasiBinding itemBinding;

        public MyHolder(@NonNull ItemNotifikasiBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }

        public void bind(String data, int position) {
            itemBinding.tvTitle.setText(data);
        }
    }
}
