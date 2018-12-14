package com.app.projectone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText ed_mobile, ed_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_mobile = (EditText)findViewById(R.id.ed_mobile);
        ed_password = (EditText)findViewById(R.id.ed_password);

        btn_login = (Button)findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s_mobile = ed_mobile.getText().toString();
                String s_password = ed_password.getText().toString();

                if(s_mobile.length()>0){

                    if(s_password.length()>0){

                        Intent iObject = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(iObject);
                        finish();

                    }else {
                        Toast.makeText(LoginActivity.this,"Please enter password", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this,"Please enter mobile", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
