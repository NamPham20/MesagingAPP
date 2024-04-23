package com.example.messagingapp.sign_up;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.messagingapp.share_preference.DataLocalManage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;


public class SignUpPresenter {
     private SignUpInterface signUpInterface;
     private FirebaseAuth mAuth = FirebaseAuth.getInstance();
     Context context;
    private  final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private  final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public SignUpPresenter(SignUpInterface signUpInterface,Context context) {
        this.signUpInterface = signUpInterface;
        this.context=context;
    }

    public  void checkValidPassword(String password){
        if(password.length()>=6){
            signUpInterface.validPassword();
        }else {
            signUpInterface.inValidPassword();
        }
    }

    public void checkMatchPassword(String password,String repeatPassword){
        if (repeatPassword.equals(password)) {
            signUpInterface.isMatchPassword();
        }else {
            signUpInterface.notMatchPassword();
        }
    }

    public void SignUpMethod(String email,String password,boolean flag1,boolean flag2,boolean flag3){
        if( flag1 && flag2 && flag3){
            if(mAuth!=null){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                    DataLocalManage.saveEmail(email);
                                    DataLocalManage.savePassword(password);
                                    signUpInterface.registerSuccess();

                                } else {
                                    Toast.makeText(context, "Authentication Faller OR Email Was Existed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }else {
            Toast.makeText(context, "Email or password invalid.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public  void isValidEmail(String email){
       if(pattern.matcher(email).matches()){
           signUpInterface.isValidEmail();
       }
       else {
           signUpInterface.notValidEmail();
       }
    }

}
