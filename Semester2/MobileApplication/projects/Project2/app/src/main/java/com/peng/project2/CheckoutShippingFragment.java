package com.peng.project2;

import static com.peng.project2.Common.clearErrorsWhenChanged;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

public class CheckoutShippingFragment extends Fragment {
    public static final String REQUEST_KEY = "next";
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
            getParentFragmentManager().setFragmentResult(REQUEST_KEY, buildBundle());
        });

        initViews(getArguments());
        return v;
    }

    private static String getText(TextInputLayout layout) {
        return layout.getEditText().getText().toString();
    }
    private static void setText(TextInputLayout layout, String text) {
        layout.getEditText().setText(text);
    }
    private String getResStr(int id) {
       return getResources().getString(id);
    }

    private Bundle buildBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(getResStr(R.string.first_name), getText(firstNameLayout));
        bundle.putString(getResStr(R.string.last_name), getText(lastNameLayout));
        bundle.putString(getResStr(R.string.email), getText(emailLayout));
        bundle.putString(getResStr(R.string.phone), getText(phoneLayout));
        bundle.putString(getResStr(R.string.address), getText(addressLayout));
        bundle.putString(getResStr(R.string.province), getText(provinceLayout));
        bundle.putString(getResStr(R.string.city), getText(cityLayout));
        bundle.putString(getResStr(R.string.post_code), getText(postCodeLayout));
        return bundle;
    }

    private void initViews(Bundle bundle) {
        if (bundle == null || bundle.size() == 0) {
            return;
        }

        setText(firstNameLayout, bundle.getString(getResStr(R.string.first_name)));
        setText(lastNameLayout, bundle.getString(getResStr(R.string.last_name)));
        setText(emailLayout, bundle.getString(getResStr(R.string.email)));
        setText(phoneLayout, bundle.getString(getResStr(R.string.phone)));
        setText(addressLayout, bundle.getString(getResStr(R.string.address)));

        setText(provinceLayout, bundle.getString(getResStr(R.string.province)));
        ((MaterialAutoCompleteTextView) provinceLayout.getEditText())
               .setSimpleItems(R.array.Provinces);

        setText(cityLayout, bundle.getString(getResStr(R.string.city)));
        setText(postCodeLayout, bundle.getString(getResStr(R.string.post_code)));
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
        if (!Common.validPostcode(postCodeLayout)) {
            valid = false;
        }
        return valid;
    }
}