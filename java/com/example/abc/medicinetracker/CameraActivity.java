package com.example.abc.medicinetracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity {

    SurfaceView camera;
    TextView tv,tv2;
    CameraSource cameraSource;
    final int RequestCameraIntID = 1001;

    FloatingActionButton next;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case RequestCameraIntID:
            {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED);
                {

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }

                    try {
                        cameraSource.start(camera.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        camera = (SurfaceView) findViewById(R.id.surfaceview);
        tv = (TextView) findViewById(R.id.textview);
        tv2 = (TextView) findViewById(R.id.drname);
        next=(FloatingActionButton)findViewById(R.id.nextbtn);
        next.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(CameraActivity.this,MedicinesActivity.class);
                startActivity(in);
            }
        });

        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!textRecognizer.isOperational()) {
            Log.w("OCRcamera", "Detector dependencies are not yet availabel");
        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(cameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(5.0f)
                    .setAutoFocusEnabled(true)
                    .build();


            camera.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(CameraActivity.this,
                                    new String[]{android.Manifest.permission.CAMERA},
                                    RequestCameraIntID);
                            return;
                        }
                        cameraSource.start(camera.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if (items.size() != 0)
                    {
                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i<items.size();++i){
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());

                                   String name=item.getValue().toString();
                                    if (name.length()>2) {
                                        String start = name.substring(0, 2);
                                        Toast.makeText(CameraActivity.this, "name is" + name + "prefix is" + start, Toast.LENGTH_SHORT).show();
                                        String dr = "DR";
                                        if (start.equals(dr)) {
                                            tv2.setText(stringBuilder.toString());
                                            next.setVisibility(View.VISIBLE);

                                            SharedPreferences sharedrname=getApplicationContext().getSharedPreferences("drname",MODE_PRIVATE);
                                            SharedPreferences.Editor editor=sharedrname.edit();
                                            editor.putString("drname",tv2.getText().toString());
                                            editor.commit();
                                        }
                                    }

                                    stringBuilder.append("\n");
                                }
                                tv.setText(stringBuilder.toString());
                            }

                        });
                    }
                }
            });
        }
    }
}
