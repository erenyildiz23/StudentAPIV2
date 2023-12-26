package com.bqubique.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    PageAdapter pageAdapter;
    Button newTabButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = this.getIntent().getData();
        try {
            URL url = new URL(uri.getScheme(), uri.getHost(), uri.getPath());
            System.out.println(url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        viewPager2 = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), getLifecycle());
        pageAdapter.addFragment(new Tab());
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setUserInputEnabled(true);
        viewPager2.setAdapter(pageAdapter);
        newTabButton = findViewById(R.id.newTabButton);
        newTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageAdapter.addFragment(new Tab());
                viewPager2.setCurrentItem(pageAdapter.getItemCount(), true);
            }
        });
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("Tab " + (position + 1));
            }
        }).attach();
    }
}