package com.example.broadcast.broadcast;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    Button btnCamera;
    ImageView showCameraImage;
    Uri uri;
    private final int CAPTURE_PHOTO = 102;

    private Integer MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        showCameraImage = (ImageView) findViewById(R.id.imageview_show);
        btnCamera = (Button) findViewById(R.id.btncamera);
        //set permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotobyCamera();
            }
        });
    }


        public void takePhotobyCamera (){

            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()+"propic.png";
            uri = Uri.parse(root);
            startActivityForResult(i, CAPTURE_PHOTO);
    }

    @Override
        protected void onActivityResult (int requestCode, int resultCode, Intent in){

    }
}
