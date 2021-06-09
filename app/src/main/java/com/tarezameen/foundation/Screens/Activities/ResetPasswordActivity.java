package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tarezameen.foundation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tarezameen.foundation.Screens.GlobalClasses.Constants.passwordPattern;

public class ResetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.edtReEnterPassword)
    TextInputEditText edtReEnterPassword;
    @BindView(R.id.edtNewPassword)
    TextInputEditText edtNewPassword;
    @BindView(R.id.btnConfirmResetPassword)
    Button btnConfirmResetPassword;
    @BindView(R.id.layoutNewPassword)
    TextInputLayout layoutNewPassword;
    @BindView(R.id.layoutReenterPassword)
    TextInputLayout layoutReenterPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        idClickListner();
    }

    private void idClickListner() {

        edtNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutNewPassword.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtReEnterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutReenterPassword.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    edtNewPassword.setHint(getResources().getString(R.string.newpasswordhint_text));
                else
                    edtNewPassword.setHint("");
            }
        });
        edtReEnterPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    edtReEnterPassword.setHint(getResources().getString(R.string.reenter_password_text));
                else
                    edtReEnterPassword.setHint("");
            }
        });
        btnConfirmResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!edtNewPassword.getText().toString().trim().matches(passwordPattern)) {
                    layoutNewPassword.setError(getResources().getString(R.string.notvalidpassword_msg));

                }else if (!edtReEnterPassword.getText().toString().trim().matches(passwordPattern)) {
                    layoutReenterPassword.setError(getResources().getString(R.string.notvalidpassword_msg));

                } else if (edtNewPassword.getText().toString().equals(edtReEnterPassword.getText().toString()) && edtReEnterPassword.getText().toString().equals(edtNewPassword.getText().toString())) {
                    Toast.makeText(ResetPasswordActivity.this, "Password Matched", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ResetPasswordActivity.this, getResources().getString(R.string.notmatchpassword_msg), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}