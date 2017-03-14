package com.example.dihimono.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.util.Log;
import android.view.View;

/**
 * Created by dihimono on 14/03/2017.
 */

public class DrawView extends View implements Camera.FaceDetectionListener{
    Paint p = new Paint();

    public DrawView(Context context) {
        super(context);
    }

    private Camera.Face[] faceList;

    private RectF adjustRect (Camera.Face r) {
        RectF rr = new RectF();
        rr.bottom = r.rect.bottom;
        rr.left = r.rect.left;
        rr.top = r.rect.top;
        rr.right = r.rect.right;
        //Log.d("Before alter: ", rr.bottom + "," + rr.left + " " + rr.top + "," + rr.right);
        rr.bottom += 1000;
        rr.left += 1000;
        rr.top += 1000;
        rr.right += 1000;
        rr.bottom /= 2000;
        rr.left /= 2000;
        rr.top /= 2000;
        rr.right /= 2000;
        rr.bottom *= getHeight();
        rr.left *= getWidth();
        rr.top *= getHeight();
        rr.right *= getWidth();
        //Log.d("After alter: ", rr.bottom + "," + rr.left + " " + rr.top + "," + rr.right);
    }

    @Override
    public void onDraw (Canvas canvas) {
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);
        p.setColor(Color.CYAN);
        if (faceList == null) canvas.drawRect(0, 0, getWidth(), getHeight(), p);
        else {
            for (Camera.Face f : faceList) {
                RectF rr = adjustRect(f);
                canvas.drawRect(rr, p);
            }

        }
    }

    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        faceList = faces;
        invalidate();
    }
}
