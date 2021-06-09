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

public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.txt_Recivelink)
    TextView txt_Recivelink;

    @BindView(R.id.btnResetPassword)
    Button btnResetPassword;

    @BindView(R.id.edt_Email)
    TextInputEditText edt_Email;

    @BindView(R.id.layoutEmail)
    TextInputLayout layoutEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);

        idClickListner();
    }

    private void idClickListner() {
        txt_Recivelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
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
        edt_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    edt_Email.setHint(getResources().getString(R.string.emailhint_text));
                else
                    edt_Email.setHint("");
            }
        });
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (edt_Email.getText().toString().isEmpty()) {
                    layoutEmail.setError(getResources().getString(R.string.emailerror_msg));
                }  else if (!edt_Email.getText().toString().trim().matches(emailPattern)) {
                    layoutEmail.setError(getResources().getString(R.string.notvalidemail_msg));

                } else {
                    Intent intent = new Intent(ForgotPasswordActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}