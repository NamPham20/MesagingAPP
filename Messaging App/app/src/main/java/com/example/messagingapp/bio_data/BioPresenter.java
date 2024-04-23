package com.example.messagingapp.bio_data;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.messagingapp.module_login.BioUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

public class BioPresenter {
    BioInterface bioInterface;
    Context context;
    FirebaseUser user;
    FirebaseAuth mAuth;
    String imageUrl;
    HandelInterface handelInterface;
    String uid;

    public BioPresenter(BioInterface bioInterface, Context context) {
        this.bioInterface = bioInterface;
        this.context = context;
    }

    public void updateUserBio( String nickName,
                               String phoneNumber, String gender, String dateOfBirth,Bitmap bitmap){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
             uid = currentUser.getUid();
        }
        UUID uuid = UUID.randomUUID();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dataPef = firebaseDatabase.getReference("user_list");
        String id = String.valueOf(uuid);
        upLoadImageToFireStore(uid, bitmap, new HandelInterface() {
            @Override
            public void onDataReady() {
                BioUser bioUser = new BioUser(id,imageUrl,nickName,phoneNumber,gender,dateOfBirth);
                dataPef.child(uid).setValue(bioUser, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        bioInterface.onSuccess();
                    }
                });
            }
        });



    }

    public  void upLoadImageToFireStore(String id,Bitmap bitmap, HandelInterface handelInterface){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        String fileName = "image_" + System.currentTimeMillis() + ".jpg";
        String foldName = "images_" +id+"/";
        StorageReference mountainsRef = storageRef.child(foldName+ fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            Toast.makeText(context," faller",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(context," Success",Toast.LENGTH_SHORT).show();
                mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageUrl = uri.toString();
                        System.out.println(uri);
                        Toast.makeText(context,"Image URL: " + imageUrl,Toast.LENGTH_SHORT).show();
                        handelInterface.onDataReady();
                    }
                });
            }
        });
    }

}
