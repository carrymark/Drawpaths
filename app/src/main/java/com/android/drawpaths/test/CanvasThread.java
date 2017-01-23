package com.android.drawpaths.test;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by New android on 21-01-2017.
 */

public class CanvasThread extends Thread
{
    private SurfaceHolder surfaceHolder;
    private MyImageView myImageView;
    private boolean run = false;

    public CanvasThread(SurfaceHolder s, MyImageView m)
    {
        surfaceHolder = s;
        myImageView = m;
    }

    public void setRunning(boolean r)
    {
        run = r;
    }

    public void run()
    {
        Canvas c;
        while(run)
        {
            c=null;
            try
            {
                c= surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    myImageView.onDraw(c);
                }
            }
            finally
            {
                if(c!=null)
                {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
