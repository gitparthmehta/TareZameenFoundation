package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tarezameen.foundation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tarezameen.foundation.Screens.GlobalClasses.Constants.emailPattern;
import static com.tarezameen.foundation.Screens.GlobalClasses.Constants.passwordPattern;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.edt_Email)
    TextInputEditText edt_Email;
    @BindView(R.id.edt_Password)
    TextInputEditText edt_Password;
    @BindView(R.id.btn_Login)
    Button btn_Login;
    @BindView(R.id.txt_Forgotpassword)
    TextView txt_Forgotpassword;
    @BindView(R.id.txt_Signup)
    TextView txt_Signup;
    @BindView(R.id.layoutEmail)
    TextInputLayout layoutEmail;
    @BindView(R.id.layoutPassword)
    TextInputLayout layoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        idClickListner();
    }

    private void idClickListner() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edt_Email.getText().toString().isEmpty()) {
                    layoutEmail.setError(getResources().getString(R.string.emailerror_msg));
                } else if (edt_Password.getText().toString().isEmpty()) {
                    layoutPassword.setError(getResources().getString(R.string.passworderror_msg));
                } else if (!edt_Email.getText().toString().trim().matches(emailPattern)) {
                    layoutEmail.setError(getResources().getString(R.string.notvalidemail_msg));

                }else if (!edt_Password.getText().toString().trim().matches(passwordPattern)) {
                    layoutPassword.setError(getResources().getString(R.string.notvalidpassword_msg));

                } else {
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }


            }
        });
        txt_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });
        txt_Forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);

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

    }
}