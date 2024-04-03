package com.peng.project2;

import static com.peng.project2.Common.clearErrorsWhenChanged;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class CheckoutShippingFragment extends Fragment {
    private TextInputLayout firstNameLayout;
    private TextInputLayout lastNameLayout;
    private TextInputLayout emailLayout;
    private TextInputLayout phoneLayout;
    private TextInputLayout addressLayout;
    private TextInputLayout provinceLayout;
    private TextInputLayout cityLayout;
    private TextInputLayout postCodeLayout;
    private Button nextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_checkout_shipping_fragment, container,
                false);

        firstNameLayout = v.findViewById(R.id.firstNameLayout);
        clearErrorsWhenChanged(firstNameLayout);
        lastNameLayout = v.findViewById(R.id.lastNameLayout);
        clearErrorsWhenChanged(lastNameLayout);
        emailLayout = v.findViewById(R.id.emailLayout);
        clearErrorsWhenChanged(emailLayout);
        phoneLayout = v.findViewById(R.id.phoneLayout);
        clearErrorsWhenChanged(phoneLayout);
        addressLayout = v.findViewById(R.id.addressLayout);
        clearErrorsWhenChanged(addressLayout);
        provinceLayout = v.findViewById(R.id.provinceLayout);
        clearErrorsWhenChanged(provinceLayout);
        cityLayout = v.findViewById(R.id.cityLayout);
        clearErrorsWhenChanged(cityLayout);
        postCodeLayout = v.findViewById(R.id.postCodeLayout);
        clearErrorsWhenChanged(postCodeLayout);

        nextButton = v.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(view -> {
            if (!validateForm()) {
                return;
            }
             
        });

        return v;
    }

    private boolean validateForm() {
        boolean valid = Common.validName(firstNameLayout, "First Name");

        if (!Common.validName(lastNameLayout, "Last Name")) {
            valid = false;
        }
        if (!Common.validEmail(emailLayout)) {
            valid = false;
        }
        if (!Common.validPhone(phoneLayout)) {
            valid = false;
        }
        if (!Common.validAddress(addressLayout)) {
            valid = false;
        }
        if (!Common.validField(provinceLayout, "Province", null, null)) {
            valid = false;
        }
        if (!Common.validCity(cityLayout)) {
            valid = false;
        }
        if (!Common.validCity(postCodeLayout)) {
            valid = false;
        }
        return valid;
    }
}