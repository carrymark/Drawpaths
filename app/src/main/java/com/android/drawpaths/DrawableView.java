package com.android.drawpaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by New android on 21-01-2017.
 */
public class DrawableView extends View {
    Context mContext;
    private int centerX;
    private int centerY;
    private int radius;
    private double arrLength;
    private double arrHeading;
    private int margin = 10;

    public DrawableView(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //Paint Background
        Paint background = new Paint();
        background.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);

        //Set vars for Arrow Paint
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(0, -10);
        path.lineTo(5, 0);
        path.lineTo(-5, 0);
        path.close();
        path.offset(10, 40);
        canvas.drawPath(path, paint);
        path.offset(50, 100);
        canvas.drawPath(path, paint);

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        arrLength = radius - 10;

        if(centerX < centerY)
            radius = centerX - margin;
        else
            radius = centerY - margin;

        //Draw Shaft
        int[] xy = findArrowPos(arrLength, arrHeading);
        canvas.drawLine(centerX, centerY, xy[0], xy[1], paint);

        //Draw ArrowHead
        //This is where I'm confused

    }

    private int[] findArrowPos(double length, double angle) {
        int[] points = new int[2];
        double theta = Math.toRadians(angle);
        points[0] = centerX + (int) (length * Math.cos(theta));
        points[1] = centerY + (int) (length * Math.sin(theta));
        return points;
    }

    private void fillArrow(Paint paint, Canvas canvas, float x0, float y0, float x1, float y1) {
        paint.setStyle(Paint.Style.STROKE);

        int arrowHeadLenght = 10;
        int arrowHeadAngle = 45;
        float[] linePts = new float[] {x1 - arrowHeadLenght, y1, x1, y1};
        float[] linePts2 = new float[] {x1, y1, x1, y1 + arrowHeadLenght};
        Matrix rotateMat = new Matrix();

        //get the center of the line
        float centerX = x1;
        float centerY = y1;

        //set the angle
        double angle = Math.atan2(y1 - y0, x1 - x0) * 180 / Math.PI + arrowHeadAngle;

        //rotate the matrix around the center
        rotateMat.setRotate((float) angle, centerX, centerY);
        rotateMat.mapPoints(linePts);
        rotateMat.mapPoints(linePts2);

        canvas.drawLine(linePts [0], linePts [1], linePts [2], linePts [3], paint);
        canvas.drawLine(linePts2 [0], linePts2 [1], linePts2 [2], linePts2 [3], paint);
    }
}