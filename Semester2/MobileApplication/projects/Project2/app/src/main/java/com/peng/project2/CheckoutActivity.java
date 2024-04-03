package com.peng.project2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class CheckoutActivity extends AppCompatActivity {
    private static final String TAG = CheckoutActivity.class.getName();
    private TabLayout tabLayout;
    private Bundle data = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Common.initToolBar(this);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG, String.format("Tab:%s is selected", tab.getText().toString()));
                if (tab.getPosition() == 0) {
                    showShippingFrag();
                } else {
                    showPaymentFrag();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getSupportFragmentManager().setFragmentResultListener(
                CheckoutShippingFragment.REQUEST_KEY,
                this,
                (requestKey, result) -> {
                    data.putAll(result);
                    showPaymentFrag();
                });
    }


    private void showShippingFrag() {
        getSupportFragmentManager().beginTransaction()
                .replace(
                        R.id.fragmentContainerView,
                        CheckoutShippingFragment.class, data
                )
                .commit();
    }

    private void showPaymentFrag() {
        tabLayout.getTabAt(1).select();
        getSupportFragmentManager().beginTransaction()
                .replace(
                        R.id.fragmentContainerView,
                        CheckoutPaymentFragment.class, data
                )
                .commit();
    }
}