package com.android.drawpaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by New android on 21-01-2017.
 */

public class MyView extends View{
    Paint paint;
    Path path;
    public MyView(Context context) {
        super(context);
        //init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
       // init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       // init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
        path.moveTo(50,50);
        path.lineTo(500,50);
        path.lineTo(50,50);
        path.lineTo(50,50);
        path.lineTo(50,50);

    }
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
        path.moveTo(50,50);
        path.lineTo(500,50);
        path.lineTo(50,50);
        path.lineTo(50,50);
        path.lineTo(50,50);
        for(int i=0;i<10;i++){
            canvas.drawPath(path,paint);
        }


    }
}
