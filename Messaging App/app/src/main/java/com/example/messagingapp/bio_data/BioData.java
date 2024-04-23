package com.example.messagingapp.bio_data;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.messagingapp.main_screen.inbox.MainActivity;
import com.example.messagingapp.R;

import java.io.IOException;

public class BioData extends AppCompatActivity implements BioInterface {
    private ImageView imvAvatar;
    private ImageView imvSetAvatar;
    private EditText edtNickname;
    private EditText edtPhoneNumber;
    private TextView tvDateOfBirth;
    private RadioGroup radioGroupGender;
    private Button btnLetStart;
    private LinearLayout llDate;
    private DatePicker datePicker;
    private Button btnGetDate;
    private Uri mUri;
    private Bitmap bitmap;
    private BioPresenter bioPresenter = new BioPresenter(this,this);
    private String strGender;
    private String strNickName;
    private String strPhoneNumber;
    private String strDateOfBirth;
    private TextView tvError;

    private final ActivityResultLauncher<Intent> mActi = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    System.out.println("onActivityResult is allow");
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imvAvatar.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    );

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_data);
        initUi();

        imvSetAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Choose image is clicked");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    chooseImage();
                }
            }
        });
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                strGender = radioButton.getText().toString();
            }
        });



        btnLetStart. setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                strNickName = edtNickname.getText().toString().trim();
                strPhoneNumber = edtPhoneNumber.getText().toString().trim();
                strDateOfBirth = tvDateOfBirth.getText().toString().trim();
                if(mUri!=null&& strNickName!=null && strPhoneNumber!=null&&strDateOfBirth!=null&& strGender!=null){
                    bioPresenter.updateUserBio(strNickName,strPhoneNumber,strGender,strDateOfBirth,bitmap);
                }else {
                    tvError.setText("May be have a empty");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvError.setText("");
                        }
                    },1000);
                }
            }
        });

        tvDateOfBirth.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                llDate.setVisibility(View.VISIBLE);
                onSetDate();
                return false;
            }
        });
    }

    private void onSetDate() {
        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getDayOfMonth()+1;
                int year = datePicker.getYear();
                tvDateOfBirth.setText(day + "/" + month  + "/" + year);
                llDate.setVisibility(View.GONE);
            }
        });
    }

    public void initUi(){
        imvAvatar = findViewById(R.id.imv_avatar);
        imvSetAvatar= findViewById(R.id.imv_set_avatar);
        edtNickname= findViewById(R.id.edt_nick_name);
        edtPhoneNumber= findViewById(R.id.edt_phone_number);
        tvDateOfBirth = findViewById(R.id.tv_date_of_birth);
        radioGroupGender= findViewById(R.id.radio_gender);
        btnLetStart = findViewById(R.id.btn_let_start);
        tvError = findViewById(R.id.tv_error);
        llDate = findViewById(R.id.ll_date);
        datePicker = findViewById(R.id.date_picker_actions);
        btnGetDate = findViewById(R.id.btn_get_date);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void chooseImage() {
        if(checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("READ_EXTERNAL_STORAGE is allow");
            openGallery();
        }
        else {
            System.out.println("requestPermissions is called");
            String [] permission = {Manifest.permission.READ_MEDIA_IMAGES};
            ActivityCompat.requestPermissions(this,permission,100);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                System.out.println("user allow");
                openGallery();
            }
        }

    }

    private void openGallery() {
        System.out.println("openGallery is called");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        mActi.launch(Intent.createChooser(intent,"selected picture"));
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this,"Update bio is success",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(BioData.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }

}