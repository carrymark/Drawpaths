package com.android.drawpaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by New android on 21-01-2017.
 */

public class MyView3 extends View{
    Paint paint;
    Path path;
    public MyView3(Context context) {
        super(context);
        paint = new Paint();
       // init();
    }

    public MyView3(Context context, AttributeSet attrs) {
        super(context, attrs);
      //  init();
    }

    public MyView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init();
    }

    public MyView3(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getPaddingLeft(), getPaddingTop() - getPaddingBottom());
        canvas.drawPath(makeArrow(140,140), paint);
       // drawOvalAndArrow(canvas);
       // canvas.drawPath(path,paint);

    }

    private void drawOvalAndArrow(Canvas canvas) {
        Paint circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(2);
        circlePaint.setColor(Color.CYAN);

        float centerWidth = canvas.getWidth()/2; //get center x of display
        float centerHeight = canvas.getHeight()/2; //get center y of display
        float circleRadius = 20; //set radius
        float circleDistance = 200; //set distance between both circles

        //draw circles
        canvas.drawCircle(centerWidth, centerHeight, circleRadius, circlePaint);
        canvas.drawCircle(centerWidth+circleDistance, centerHeight, circleRadius, circlePaint);


        //to draw an arrow, just lines needed, so style is only STROKE
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(Color.RED);

        //create a path to draw on
        Path arrowPath = new Path();

        //create an invisible oval. the oval is for "behind the scenes" ,to set the path´
        //area. Imagine this is an egg behind your circles. the circles are in the middle of this egg
        final RectF arrowOval = new RectF();
        arrowOval.set(centerWidth,
                centerHeight-80,
                centerWidth + circleDistance,
                centerHeight+80);

        //add the oval to path
        arrowPath.addArc(arrowOval,-180,180);

        //draw path on canvas
        canvas.drawPath(arrowPath, circlePaint);


        //draw arrowhead on path start
        arrowPath.moveTo(centerWidth,centerHeight ); //move to the center of first circle
        arrowPath.lineTo(centerWidth-circleRadius, centerHeight-circleRadius);//draw the first arrowhead line to the left
        arrowPath.moveTo(centerWidth,centerHeight );//move back to the center
        arrowPath.lineTo(centerWidth+circleRadius, centerHeight-circleRadius);//draw the next arrowhead line to the right

        //same as above on path end
        arrowPath.moveTo(centerWidth+circleDistance,centerHeight );
        arrowPath.lineTo((centerWidth+circleDistance)-circleRadius, centerHeight-circleRadius);
        arrowPath.moveTo(centerWidth+circleDistance,centerHeight );
        arrowPath.lineTo((centerWidth+circleDistance)+circleRadius, centerHeight-circleRadius);

        //draw the path
        canvas.drawPath(arrowPath,circlePaint);

    }

    private static Path makeArrow(float length, float height) {
        Path p = new Path();
        p.moveTo(-2.0f, 0.0f);
        p.lineTo(length, height / 2.0f);
        p.lineTo(-2.0f, height);
        p.lineTo(-2.0f, 0.0f);
        p.close();
        return p;
    }

}
