package com.peng.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.peng.project2.dao.AppDatabase;
import com.peng.project2.entity.Cart;

public class CheckoutActivity extends AppCompatActivity {
    private static final String TAG = CheckoutActivity.class.getName();
    private TabLayout tabLayout;
    private Bundle data = new Bundle();
    private AppDatabase db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        db = AppDatabase.getInstance(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        // disable touching to select tabs, user can only navigate next after that form is validated.
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.getTouchables().forEach(view -> view.setEnabled(false));

        getSupportFragmentManager().setFragmentResultListener(
                CheckoutShippingFragment.REQUEST_KEY_NEXT,
                this,
                (requestKey, result) -> {
                    data.putAll(result);
                    showPaymentFrag();
                });

        getSupportFragmentManager().setFragmentResultListener(
                CheckoutPaymentFragment.REQUEST_KEY_BACK,
                this,
                (requestKey, result) -> {
                    data.putAll(result);
                    showShippingFrag();
                });

        getSupportFragmentManager().setFragmentResultListener(
                CheckoutPaymentFragment.REQUEST_KEY_ORDER,
                this,
                (requestKey, result) -> {
                    data.putAll(result);
                    placeOrder();
                });

        Common.initToolBar(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Common.checkLogin(mAuth, this);
    }

    private void placeOrder() {
        Log.i(TAG, "Got order info: " + data);
        Cart cart = db.cartDao().selectOne(mAuth.getCurrentUser().getUid());
        db.cartItemDao().deleteByCartId(cart.getId());

        Intent myIntent = new Intent(this, ThankYouActivity.class);
        startActivity(myIntent);
    }

    private void showShippingFrag() {
        tabLayout.getTabAt(0).select();
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