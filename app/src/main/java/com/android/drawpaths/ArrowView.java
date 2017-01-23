package com.android.drawpaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.Shape;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import static android.R.attr.maxHeight;
import static android.R.attr.maxWidth;

/**
 * Created by New android on 21-01-2017.
 */

public class ArrowView extends View {

    private Region mClipRegion;

    private Paint mArrowPaint;
    private Path mArrowPath;
    private Region mArrowRegion;

    private TextPaint mTextPaint;
    private Rect mBounds;
    private Region mTextRegion;

    private int mWidth;
    private int mHeight;

    private int mMultiplier = 0;

    private int mRotation;

    public ArrowView(Context context) {
        this(context, null, 0);
    }

    public ArrowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mArrowPaint = new Paint();
        mArrowPaint.setColor(Color.BLUE);
        mArrowPaint.setStyle(Paint.Style.FILL);
        mArrowPaint.setAntiAlias(true);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mArrowPath = new Path();
        mBounds = new Rect();

        mClipRegion = new Region();
        mArrowRegion = new Region();
        mTextRegion = new Region();
    }

    public void setMultiplier(int multiplier) {
        mMultiplier = multiplier;

        invalidate();
    }

    public void setArrowRotation(int rotation) {
        mRotation = rotation;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = 500;
        mHeight = 50;
        mClipRegion.set(0, 0, 500, 50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.rotate(mRotation, mWidth / 2, mHeight / 2);

        // Draw an arrow
        mArrowPath.rewind();
        mArrowPath.moveTo(mWidth , mHeight / 2);
        mArrowPath.lineTo(mWidth / 2, mHeight );
        mArrowPath.lineTo(mWidth / 2, mHeight* 3 / 4);
        mArrowPath.lineTo(0, mHeight* 3 / 4);
        mArrowPath.lineTo(0, mHeight/ 4);
        mArrowPath.lineTo(mWidth / 2, mHeight/ 4);
        mArrowPath.lineTo(mWidth / 2,0);
        mArrowPath.lineTo(mWidth , mHeight / 2);
        Shape pathshap = new PathShape(mArrowPath,maxWidth,maxHeight);

        ShapeDrawable shapeD = new ShapeDrawable(pathshap);

        shapeD.draw(canvas);

        canvas.drawPath(mArrowPath, mArrowPaint);

        canvas.restore();

        // Draw a multiplier (if non-zero)
        if (mMultiplier > 0) {
            String text = getContext().getString(R.string.multiplier_TEMPLATE, mMultiplier);

            // We need to adapt the text size to the space we have (and also
            // find out its height so we can properly center align it)
            mTextPaint.setTextSize(Math.min(mWidth, mHeight) / 3.5f);
            mArrowRegion.setPath(mArrowPath, mClipRegion);

            getTextRegion(text);
            while (mArrowRegion.op(mTextRegion, Region.Op.REVERSE_DIFFERENCE) && mTextPaint.getTextSize() > 1) {
                mTextPaint.setTextSize(mTextPaint.getTextSize() - 1);
                getTextRegion(text);
            }

            canvas.drawText(text, mWidth / 2.0f, (mHeight + mBounds.height()) / 2.0f, mTextPaint);
        }
    }

    private void getTextRegion(String text) {
        mTextPaint.getTextBounds(text, 0, text.length(), mBounds);
        mTextRegion.set((int) (mBounds.left + (mWidth / 2.0f) - mBounds.width() / 2.0f),
                (int) (mBounds.top + (mHeight + mBounds.height()) / 2.0f),
                (int) (mBounds.right + (mWidth / 2.0f) - mBounds.width() / 2.0f),
                (int) (mBounds.bottom + (mHeight + mBounds.height()) / 2.0f));
    }
}
