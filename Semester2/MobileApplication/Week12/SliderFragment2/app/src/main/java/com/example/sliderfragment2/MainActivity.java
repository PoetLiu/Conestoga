package com.example.sliderfragment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private ViewPager2 viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.myViewPager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);

        pagerAdapter.addFragment(ScreenSlidePageFragment.newInstance("FRAGMENT ONE"));
        pagerAdapter.addFragment(ScreenSlidePageFragment.newInstance("FRAGMENT TWO"));
        pagerAdapter.addFragment(ScreenSlidePageFragment.newInstance("FRAGMENT THREE"));

        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }
        else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }



    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {

        private ArrayList<Fragment> fragList = new ArrayList<>();

        public ScreenSlidePagerAdapter(FragmentActivity fa){
            super(fa);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragList.get(position);
        }

        public void addFragment(Fragment fragment){
            fragList.add(fragment);
        }

        @Override
        public int getItemCount() {
            return fragList.size();
        }
    }
}