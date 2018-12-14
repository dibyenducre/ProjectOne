package com.app.projectone;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    int MY_CAMERA_REQUEST_CODE = 100;

    EditText ed_name, ed_mobile, ed_email, ed_password, ed_address;
    Spinner spinner_state;
    RadioGroup radio_gender;
    CheckBox check_accept;

    ImageView img_user_image;
    Button btn_register, btn_camera;

    String s_name = "", s_mobile="", s_email="", s_password="", s_address="",
            s_gender = "", s_state = "";

    Bitmap imageData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed_name = (EditText)findViewById(R.id.ed_name);
        ed_mobile = (EditText)findViewById(R.id.ed_mobile);
        ed_email = (EditText)findViewById(R.id.ed_email);
        ed_password = (EditText)findViewById(R.id.ed_password);
        ed_address = (EditText)findViewById(R.id.ed_address);

        radio_gender = (RadioGroup) findViewById(R.id.radio_gender);


        spinner_state = (Spinner) findViewById(R.id.spinner_state);

        check_accept = (CheckBox) findViewById(R.id.check_accept);

        img_user_image = (ImageView)findViewById(R.id.img_user_image);

        btn_register = (Button)findViewById(R.id.btn_register);
        btn_camera = (Button)findViewById(R.id.btn_camera);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                s_name = ed_name.getText().toString();
                s_mobile = ed_mobile.getText().toString();
                s_email = ed_email.getText().toString();
                s_password = ed_password.getText().toString();
                s_address = ed_address.getText().toString();

                s_state = spinner_state.getSelectedItem().toString();

                int radioID = radio_gender.getCheckedRadioButtonId();
                if(radioID ==-1){

                }else {

                RadioButton radioButton = (RadioButton)findViewById(radioID);
                s_gender = radioButton.getText().toString();

                }

                Log.d(">>","--"+s_gender);

                if(s_name.length()>0){

                    if(s_mobile.length()>0){

                        if(s_email.length()>0){

                            if(s_password.length()>0){

                                if(s_address.length()>0){

                                    if(s_gender.length()>0) {

                                        if (check_accept.isChecked()) {

                                            Intent iObject = new Intent(RegisterActivity.this, HomeActivity.class);
                                            iObject.putExtra("iImage", imageData);

                                            startActivity(iObject);
                                            finish();

                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Please accept the terms and Condition", Toast.LENGTH_SHORT).show();
                                        }

                                    }else {
                                        Toast.makeText(RegisterActivity.this,"Please enter Gender", Toast.LENGTH_SHORT).show();
                                    }

                                }else {
                                    Toast.makeText(RegisterActivity.this,"Please enter Address", Toast.LENGTH_SHORT).show();
                                }

                            }else {
                                Toast.makeText(RegisterActivity.this,"Please enter Password", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(RegisterActivity.this,"Please enter Email ID", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(RegisterActivity.this,"Please enter Mobile", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(RegisterActivity.this,"Please enter Full Name", Toast.LENGTH_SHORT).show();
                }


            }
        });



        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(hasCameraPermission()){
                   openCamera();
               }else {
                   requestCameraPermission();
               }
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if(data != null) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                imageData = image;
                //sets imageview as the bitmap
                img_user_image.setImageBitmap(image);
            }
        }
    }

    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA},
                MY_CAMERA_REQUEST_CODE );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 100 :

                openCamera();

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    public void openCamera(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, MY_CAMERA_REQUEST_CODE);
    }

}
