package com.peng.project2;

import static com.peng.project2.Common.clearErrorsWhenChanged;
import static com.peng.project2.Common.getResStr;
import static com.peng.project2.Common.setText;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class CheckoutPaymentFragment extends Fragment {
    public static final String REQUEST_KEY_BACK = "payment_back";
    public static final String REQUEST_KEY_ORDER = "payment_order";
    private TextInputLayout nameOnCardLayout;
    private TextInputLayout cardNumberLayout;
    private TextInputLayout expireDateLayout;
    private TextInputLayout cvvLayout;
    private TextInputLayout notesLayout;
    private Button goBackBtn;
    private Button placeOrderBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_checkout_payment_fragment, container,
                false);

        nameOnCardLayout = v.findViewById(R.id.nameOnCardLayout);
        clearErrorsWhenChanged(nameOnCardLayout);
        cardNumberLayout = v.findViewById(R.id.cardNumberLayout);
        clearErrorsWhenChanged(cardNumberLayout);
        expireDateLayout = v.findViewById(R.id.expireDateLayout);
        clearErrorsWhenChanged(expireDateLayout);
        cvvLayout = v.findViewById(R.id.cvvLayout);
        clearErrorsWhenChanged(cvvLayout);
        notesLayout = v.findViewById(R.id.notesLayout);
        clearErrorsWhenChanged(notesLayout);

        goBackBtn = v.findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(view ->
                getParentFragmentManager().setFragmentResult(REQUEST_KEY_BACK, buildBundle()));

        placeOrderBtn = v.findViewById(R.id.orderNowBtn);
        placeOrderBtn.setOnClickListener(view -> {
                if (!validateForm()) {
                    return;
                }
                getParentFragmentManager().setFragmentResult(REQUEST_KEY_ORDER, buildBundle());
        });

        initViews(getArguments());
        return v;
    }

    private boolean validateForm() {
        boolean valid = Common.validName(nameOnCardLayout, "Name On Card");

        if (!Common.validCardNumber(cardNumberLayout)) {
            valid = false;
        }
        if (!Common.validExpireDate(expireDateLayout)) {
            valid = false;
        }
        if (!Common.validCvv(cvvLayout)) {
            valid = false;
        }
        if (!Common.validNotes(notesLayout)) {
            valid = false;
        }

        return valid;
    }
    private Bundle buildBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(getResStr(this, R.string.name_on_card), Common.getText(nameOnCardLayout));
        bundle.putString(getResStr(this, R.string.card_number), Common.getText(cardNumberLayout));
        bundle.putString(getResStr(this, R.string.expire_date), Common.getText(expireDateLayout));
        bundle.putString(getResStr(this, R.string.cvv), Common.getText(cvvLayout));
        bundle.putString(getResStr(this, R.string.notes), Common.getText(notesLayout));
        return bundle;
    }

    private void initViews(Bundle bundle) {
        if (bundle == null || bundle.size() == 0) {
            return;
        }

        setText(nameOnCardLayout, bundle, this, R.string.name_on_card);
        setText(cardNumberLayout, bundle, this, R.string.card_number);
        setText(expireDateLayout, bundle, this, R.string.expire_date);
        setText(cvvLayout, bundle, this, R.string.cvv);
        setText(notesLayout, bundle, this, R.string.notes);
    }
}