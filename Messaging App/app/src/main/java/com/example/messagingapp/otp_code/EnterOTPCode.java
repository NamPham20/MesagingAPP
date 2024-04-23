package com.example.messagingapp.otp_code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.messagingapp.R;
import com.example.messagingapp.reset_pasword.ResetPassword;

public class EnterOTPCode extends AppCompatActivity {
    private EditText edtOtp1;
    private EditText edtOtp2;
    private EditText edtOtp3;
    private EditText edtOtp4;
    private Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otpcode);
        initUi();

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterOTPCode.this, ResetPassword.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
            }
        });
    }

    public void initUi(){
          edtOtp1 = findViewById(R.id.edt_otp_number_1);
          edtOtp2= findViewById(R.id.edt_otp_number_2);
          edtOtp3= findViewById(R.id.edt_otp_number_3);
          edtOtp4= findViewById(R.id.edt_otp_number_4);
          btnVerify= findViewById(R.id.btn_verify);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
    }
}