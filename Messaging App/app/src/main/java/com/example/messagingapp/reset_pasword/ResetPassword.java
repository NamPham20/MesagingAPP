package com.example.messagingapp.reset_pasword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.messagingapp.R;
import com.example.messagingapp.sign_in.SignIn;
import com.google.android.material.textfield.TextInputEditText;

public class ResetPassword extends AppCompatActivity {
    private TextInputEditText textInputEditTextNewPassword;
    private TextInputEditText textInputEditTextRepeatNewPassword;
    private TextView tvErrorMessage;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initUi();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, SignIn.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
            }
        });
    }

    private void initUi() {
          textInputEditTextNewPassword = findViewById(R.id.text_input_edt_new_password);
          textInputEditTextRepeatNewPassword = findViewById(R.id.text_input_edt_repeat_password);
          tvErrorMessage = findViewById(R.id.tv_error_message);
          btnReset = findViewById(R.id.btn_reset);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
    }
}