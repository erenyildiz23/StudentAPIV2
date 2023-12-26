package com.bqubique.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentStateAdapter {
    List<Fragment> arrayList = new ArrayList<>();

    public PageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayList.get(position);
    }

    public void addFragment(Fragment fragment) {
        arrayList.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
