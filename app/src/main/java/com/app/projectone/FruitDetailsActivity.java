package com.app.projectone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitDetailsActivity extends AppCompatActivity {

    TextView tv_fname, tv_fdetails;
    ImageView img_fimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_details);

        tv_fname = (TextView)findViewById(R.id.tv_fname);
        tv_fdetails = (TextView) findViewById(R.id.tv_fdetails);
        img_fimage = (ImageView)findViewById(R.id.img_fimage);

        Intent intent = getIntent();
        String f_name = (String) intent.getExtras().get("iFname");
        String f_details = (String) intent.getExtras().get("iFdetails");
        int f_image = (Integer) intent.getExtras().get("iFimage");

        img_fimage.setImageResource(f_image);
        tv_fname.setText(f_name);
        tv_fdetails.setText(f_details);


    }
}
