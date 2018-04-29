package com.example.abc.medicinetracker.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.medicinetracker.CameraActivity;
import com.example.abc.medicinetracker.MedicinesActivity;
import com.example.abc.medicinetracker.R;
import com.example.abc.medicinetracker.entities.Level0;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

/**
 * Created by developer on 22/2/18.
 */

public class CameraFragment extends Fragment {

    private static final String TAG = Level1SymptomsActivity.class.getSimpleName();
    String symptom_name = "";
    Button btnSubmit;
    private RecyclerView recyclerView;
    View.OnClickListener checkBoxListener;

    String level0_id = "";

    private Level0 singleMenuItem;

    public CameraFragment() {
    }

    SurfaceView camera;
    TextView tv, tv2;
    CameraSource cameraSource;
    final int RequestCameraIntID = 1001;

    FloatingActionButton next;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraIntID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) ;
                {

                    if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // getActivity().setTitle(getString(R.string.restuarant_home));

        View view = inflater.inflate(R.layout.activity_camera, container, false);
        camera = (SurfaceView) view.findViewById(R.id.surfaceview);
        tv = (TextView)view. findViewById(R.id.textview);
        tv2 = (TextView) view.findViewById(R.id.drname);
        next = (FloatingActionButton)view. findViewById(R.id.nextbtn);
        next.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), MedicinesActivity.class);
                startActivity(in);
            }
        });

        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getActivity()).build();

        if (!textRecognizer.isOperational()) {
            Log.w("OCRcamera", "Detector dependencies are not yet availabel");
        } else {
            cameraSource = new CameraSource.Builder(getActivity(), textRecognizer)
                    .setFacing(cameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(5.0f)
                    .setAutoFocusEnabled(true)
                    .build();


            camera.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(getActivity(),
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
                    if (items.size() != 0) {
                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < items.size(); ++i) {
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                    next.setVisibility(View.VISIBLE);
                                    String name = item.getValue().toString();
                                    SharedPreferences sharedrname =getActivity().getSharedPreferences("drname", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedrname.edit();
                                    editor.putString("drname", name);
                                    editor.commit();


                                    /*if (name.length() > 2) {
                                        String start = name.substring(0, 2);
                                        Toast.makeText(getActivity(), "name is" + name + "prefix is" + start, Toast.LENGTH_SHORT).show();
                                        String dr = "DR";
                                        if (start.equals(dr)) {
                                            tv2.setText(stringBuilder.toString());
                                            next.setVisibility(View.VISIBLE);

                                            SharedPreferences sharedrname =getActivity().getSharedPreferences("drname", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedrname.edit();
                                            editor.putString("drname", tv2.getText().toString());
                                            editor.commit();
                                        }
                                    }*/

                                    stringBuilder.append("\n");
                                }
                                tv.setText(stringBuilder.toString());
                            }

                        });
                    }
                }
            });
        }
        return view;


    }
}