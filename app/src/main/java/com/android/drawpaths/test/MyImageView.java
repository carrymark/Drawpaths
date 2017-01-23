package com.android.drawpaths.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.android.drawpaths.R;

/**
 * Created by New android on 21-01-2017.
 */

public class MyImageView extends SurfaceView implements SurfaceHolder.Callback
{
    private CanvasThread canvasthread;

    public MyImageView(Context context) {
        super(context);
        getHolder().addCallback(this);
        canvasthread = new CanvasThread(getHolder(), this);
        setFocusable(true);
    }

    public MyImageView(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        getHolder().addCallback(this);
        canvasthread = new CanvasThread(getHolder(), this);
        setFocusable(true);
    }

    protected void onDraw(Canvas canvas) {
        Log.d("ondraw", "ondraw");
        Paint p = new Paint();
        Bitmap mapImg = BitmapFactory.decodeResource(getResources(), R.drawable.car);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mapImg, 0, 0, null);
        p.setColor(Color.RED);
        canvas.drawLine(0, 0, 100, 100, p);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        canvasthread.setRunning(true);
        canvasthread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        canvasthread.setRunning(false);
        while (retry)
        {
            try
            {
                canvasthread.join();
                retry = false;
            }
            catch (InterruptedException e) {
                // TODO: handle exception
            }
        }
    }
}

