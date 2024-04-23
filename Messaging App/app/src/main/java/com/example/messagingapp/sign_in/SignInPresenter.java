package com.example.messagingapp.sign_in;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInPresenter {
    private SignInInterface signInInterface;
    private Context context ;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    public SignInPresenter(SignInInterface signInInterface, Context context) {
        this.signInInterface = signInInterface;
        this.context = context;
    }

    public void SignInMethod(String email, String password){
        if(mAuth!= null){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                if (currentUser != null) {
                                    String uid = currentUser.getUid();
                                    checkBioUser(uid);
                                }
                            } else {
                                if (task.getException() instanceof FirebaseAuthInvalidUserException
                                        && "ERROR_USER_NOT_FOUND".equals(((FirebaseAuthInvalidUserException) task.getException()).getErrorCode())) {
                                    Toast.makeText(context, "Account does not exist.", Toast.LENGTH_SHORT).show();
                                    signInInterface.SignInFall();
                                } else {
                                    signInInterface.SignInFall();
                                }
                            }
                        }
                    });
        }
    }

    public void checkBioUser(String Uid){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user_list");

        databaseReference.child(Uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    signInInterface.SignSuccess();
                } else {
                    signInInterface.GoToBio();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
