package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tarezameen.foundation.R;
import com.tarezameen.foundation.Screens.GlobalClasses.Constants;
import com.tarezameen.foundation.Screens.restApi.ApiManager;
import com.tarezameen.foundation.Screens.restApi.ApiResponseInterface;
import com.tarezameen.foundation.Screens.restApi.AppConstant;
import com.tarezameen.foundation.Screens.restApi.Response.BaseReponseBody;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.edt_Email)
    TextInputEditText edt_Email;
    @BindView(R.id.edt_Password)
    TextInputEditText edt_Password;
    @BindView(R.id.edt_referralcode)
    TextInputEditText edt_referralcode;
    @BindView(R.id.btn_Signup)
    Button btn_Signup;
    @BindView(R.id.referalCheckbox)
    CheckBox referalCheckbox;
    @BindView(R.id.layoutRefferalCode)
    TextInputLayout layoutRefferalCode;
    @BindView(R.id.txtLogin)
    TextView txtLogin;
    @BindView(R.id.layoutEmail)
    TextInputLayout layoutEmail;
    @BindView(R.id.layoutPassword)
    TextInputLayout layoutPassword;
    private ApiManager mApiManager;
    private ApiResponseInterface mInterFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        idClickListner();
    }

    private void idClickListner() {
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passwordPattern = "^" +
                        "(?=.*[0-9])" +         //at least 1 digit
                        "(?=.*[a-z])" +         //at least 1 lower case letter
                        "(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{8,}" +               //at least 8 characters
                        "$";

                if (edt_Email.getText().toString().isEmpty()) {
                    layoutEmail.setError(getResources().getString(R.string.emailerror_msg));
                } else if (edt_Password.getText().toString().isEmpty()) {
                    layoutPassword.setError(getResources().getString(R.string.passworderror_msg));
                } else if (!edt_Email.getText().toString().trim().matches(emailPattern)) {
                    layoutEmail.setError(getResources().getString(R.string.notvalidemail_msg));

                } else if (!edt_Password.getText().toString().trim().matches(passwordPattern)) {
                    layoutPassword.setError(getResources().getString(R.string.notvalidpassword_msg));

                } else if (referalCheckbox.isChecked()) {
                    if (edt_referralcode.getText().toString().isEmpty()) {
                        layoutEmail.setError(getResources().getString(R.string.notvalireferalcode_msg));
                    }

                } else {
                    Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

        referalCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked == true) {
                    layoutRefferalCode.setVisibility(View.VISIBLE);
                } else {
                    layoutRefferalCode.setVisibility(View.GONE);

                }
            }
        });

        edt_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutEmail.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutPassword.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_referralcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutRefferalCode.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    edt_Email.setHint(getResources().getString(R.string.emailhint_text));
                else
                    edt_Email.setHint("");
            }
        });
        edt_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    edt_Password.setHint(getResources().getString(R.string.passwordhint_text));
                else
                    edt_Password.setHint("");
            }
        });
        edt_referralcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    edt_referralcode.setHint(getResources().getString(R.string.referalcode_text));
                else
                    edt_referralcode.setHint("");
            }
        });
    }

    private void setupNetwork() {
        mInterFace = new ApiResponseInterface() {

            @Override
            public void isError(String errorMsg, int errorCode) {
                if (errorCode == AppConstant.ERROR_CODE) {
                    // error from server
                    //showStatusDialog(errorMsg);
                } else if (errorCode == AppConstant.NO_NETWORK_ERROR_CODE) {
                    // show no network screen with refresh button
                    Constants.showNoInternetDialog(SignUpActivity.this);
                }
            }

            @Override
            public void isUserDisabled(String errorMsg, int errorCode) {

            }

            @Override
            public void isSuccess(Object response, int ServiceCode) {
                if (ServiceCode == AppConstant.REGISTER) {
                    System.out.println("Register Response:" + String.valueOf(response.toString()));
                    BaseReponseBody res = (BaseReponseBody) response;
                    Toast.makeText(SignUpActivity.this, res.getMsg().toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                    intent.putExtra("from", "register");
                    startActivity(intent);
                }
            }
        };
        mApiManager = new ApiManager(this, mInterFace);
    }

}