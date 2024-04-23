package com.example.messagingapp.sign_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.messagingapp.R;
import com.example.messagingapp.Utilities;
import com.example.messagingapp.sign_in.SignIn;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SignUp extends AppCompatActivity implements SignUpInterface{
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextRepeatPassword;
    private TextView tvErrorMessage;
    private Button btnSignUp;
    private LinearLayout linearLayoutSignInWithGoogle;
    private LinearLayout linearLayoutSignInWithFaceBook;
    private LinearLayout linearLayoutSignInWithPhone;
    private TextView tvMessagePassword;
    private TextView tvEmailMessage;
    private EditText  focusedEditText;
    private String id;
    private String email;
    private String password;
    private SignUpPresenter signUpPresenter;
    private boolean emailFlag = false;
    private boolean passwordFlag =false;
    private boolean isMatchFlag = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUi();
        signUpPresenter= new SignUpPresenter(this,this);

        textInputEditTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String strS = String.valueOf(s);
                signUpPresenter.isValidEmail(strS);
            }
        });

        textInputEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String strS = String.valueOf(s);
                signUpPresenter.checkValidPassword(strS);
            }
        });

        textInputEditTextRepeatPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String strS = String.valueOf(s);
                String strPassword = Objects.requireNonNull(textInputEditTextPassword.getText()).toString().trim();
                signUpPresenter.checkMatchPassword(strPassword,strS);
            }
        });


        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utilities.hideKeyboard(SignUp.this);
                View focusedView = getCurrentFocus();
                if (focusedView instanceof EditText) {
                    focusedEditText = (EditText) focusedView;
                }
                if (focusedEditText != null) {
                    focusedEditText.clearFocus();
                }
                return false;
            }
        });



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = Objects.requireNonNull(textInputEditTextEmail.getText()).toString().trim();
                password = Objects.requireNonNull(textInputEditTextPassword.getText()).toString().trim();
                signUpPresenter.SignUpMethod(email,password,emailFlag,passwordFlag,isMatchFlag);
            }
        });

    }

    private void initUi() {
        textInputEditTextEmail = findViewById(R.id.text_input_edt_email);
        textInputEditTextPassword= findViewById(R.id.text_input_edt_password);
        textInputEditTextRepeatPassword = findViewById(R.id.text_input_edt_repeat_password);
        tvErrorMessage = findViewById(R.id.tv_error_message);
        btnSignUp= findViewById(R.id.btn_sign_up);
        linearLayoutSignInWithGoogle= findViewById(R.id.ll_sign_in_with_google);
        linearLayoutSignInWithFaceBook= findViewById(R.id.ll_sign_in_with_facebook);
        linearLayoutSignInWithPhone= findViewById(R.id.ll_sign_in_with_phone);
        tvMessagePassword = findViewById(R.id.tv_message_password);
        tvEmailMessage = findViewById(R.id.tv_email_message);

    }
    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void validPassword() {
        passwordFlag = true;
        tvMessagePassword.setVisibility(View.VISIBLE);
        tvMessagePassword.setText("Ok");
        tvMessagePassword.setTextColor(ContextCompat.getColor(this, R.color.green));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void inValidPassword() {
        passwordFlag =false;
        tvMessagePassword.setVisibility(View.VISIBLE);
        tvMessagePassword.setText("Password must have more 6 characters");
        tvMessagePassword.setTextColor(ContextCompat.getColor(this, R.color.red));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void isMatchPassword() {
        isMatchFlag =true;
        tvErrorMessage.setVisibility(View.VISIBLE);
        tvErrorMessage.setText("OK");
        tvErrorMessage.setTextColor(ContextCompat.getColor(this, R.color.green));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void notMatchPassword() {
        isMatchFlag = false;
        tvErrorMessage.setVisibility(View.VISIBLE);
        tvErrorMessage.setText("Repeat password is not match! ");
        tvErrorMessage.setTextColor(ContextCompat.getColor(this, R.color.red));
    }

    @Override
    public void registerSuccess() {
        Intent intent = new Intent(SignUp.this, SignIn.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
        finishAffinity();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void isValidEmail() {
        emailFlag = true;
        tvEmailMessage.setVisibility(View.VISIBLE);
        tvEmailMessage.setText("OK");
        tvEmailMessage.setTextColor(ContextCompat.getColor(this, R.color.green));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void notValidEmail() {
        emailFlag = false;
        tvEmailMessage.setVisibility(View.VISIBLE);
        tvEmailMessage.setText("Email Invalid");
        tvEmailMessage.setTextColor(ContextCompat.getColor(this, R.color.red));
    }
}