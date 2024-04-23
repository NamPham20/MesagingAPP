package com.example.messagingapp.recovery_password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.messagingapp.R;
import com.example.messagingapp.otp_code.EnterOTPCode;
import com.google.android.material.textfield.TextInputEditText;

public class RecoveryPassword extends AppCompatActivity {
    private TextInputEditText textInputEditTextEmail;
    private TextView tvErrorMessage;
    private Button btnSendOtpCode;
    private TextView tvToastMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password);
        initUi();

        btnSendOtpCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecoveryPassword.this, EnterOTPCode.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
            }
        });
    }

    private void initUi() {
        textInputEditTextEmail = findViewById(R.id.text_input_edt_email);
        tvErrorMessage = findViewById(R.id.tv_error_message);
        btnSendOtpCode = findViewById(R.id.btn_send_otp_code);
        tvToastMessage = findViewById(R.id.tv_toast_message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
    }
}