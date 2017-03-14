package com.example.dihimono.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import java.io.IOException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */


/** A basic Camera preview class */

public class FaceDetectionPrototype extends AppCompatActivity{



    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            Log.d("error", e.toString());
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    private Camera mCamera;
    private CameraPreview mPreview;
    public FrameLayout preview;
    public DrawView drawview;
    public FrameLayout dummyPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        drawview = new DrawView(this);
        // Create an instance of Camera
        mCamera = getCameraInstance();
        mCamera.setFaceDetectionListener(drawview);
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);

        preview = (FrameLayout) findViewById(R.id.detection);
        //dummyPreview = (FrameLayout) findViewById(R.id.camera);
        mPreview.setAlpha(0);
        preview.addView(mPreview);
        preview.addView(drawview);

        //preview.addView(cv);
    }

}
