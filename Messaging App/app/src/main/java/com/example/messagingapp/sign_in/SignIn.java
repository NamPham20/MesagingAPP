package com.example.messagingapp.sign_in;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.messagingapp.main_screen.inbox.MainActivity;
import com.example.messagingapp.R;
import com.example.messagingapp.bio_data.BioData;
import com.example.messagingapp.recovery_password.RecoveryPassword;
import com.example.messagingapp.share_preference.DataLocalManage;
import com.example.messagingapp.sign_up.SignUp;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SignIn extends AppCompatActivity implements SignInInterface {
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextView tvRecoveryPassword;
    private Button btnSignIn;
    private LinearLayout linearLayoutSignInWithGoogle;
    private LinearLayout linearLayoutSignInWithFaceBook;
    private LinearLayout linearLayoutSignInWithPhone;
    private TextView tvCreateAccount;
    private SignInPresenter signInPresenter;
    private String email;
    private String password;
    AlertDialog.Builder builder ;
    AlertDialog dialog ;

    private DataLocalManage dataLocalManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initUi();
       dataLocalManage = DataLocalManage.getInstance();
        if(DataLocalManage.getEmail()!=null && DataLocalManage.getPasword()!=null){
            textInputEditTextEmail.setText(DataLocalManage.getEmail());
            textInputEditTextPassword.setText(DataLocalManage.getPasword());
        }
        signInPresenter = new SignInPresenter(this,this);

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
            }
        });

        tvRecoveryPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, RecoveryPassword.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Loading....");
                dialog.show();
                email = Objects.requireNonNull(textInputEditTextEmail.getText()).toString().trim();
                password =Objects.requireNonNull(textInputEditTextPassword.getText()).toString().trim();

                if (!email.isEmpty() && !password.isEmpty()){
                    signInPresenter.SignInMethod(email,password);

                }else {
                    SignInFall();
                }
            }
        });

    }



    private void initUi() {
          textInputEditTextEmail = findViewById(R.id.text_input_edt_email);
          textInputEditTextPassword= findViewById(R.id.text_input_edt_password);
          tvRecoveryPassword= findViewById(R.id.tv_recovery_password);
          btnSignIn= findViewById(R.id.btn_sign_in);
          linearLayoutSignInWithGoogle= findViewById(R.id.ll_sign_in_with_google);
          linearLayoutSignInWithFaceBook= findViewById(R.id.ll_sign_in_with_facebook);
          linearLayoutSignInWithPhone= findViewById(R.id.ll_sign_in_with_phone);
          tvCreateAccount = findViewById(R.id.tv_create_account);
         builder = new AlertDialog.Builder(this);
         dialog = builder.create();
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
    }

    @Override
    public void SignSuccess() {
        dialog.dismiss();
        Intent intent = new Intent(SignIn.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
        finishAffinity();
    }

    @Override
    public void SignInFall() {
        dialog.dismiss();
        Toast.makeText(this,"Email or Password is Empty!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void GoToBio() {
        dialog.dismiss();
        Intent intent = new Intent(SignIn.this, BioData.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
    }
}