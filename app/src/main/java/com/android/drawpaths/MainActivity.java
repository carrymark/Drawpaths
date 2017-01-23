package com.android.drawpaths;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout mylayout;
    RelativeLayout relativeLayout;
    MyView2 myView;
    ImageView image;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        mylayout    =   (RelativeLayout) findViewById(R.id.mylayout);
        image       =   (ImageView)findViewById(R.id.imageV);

        // RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
       // relativeLayout.setLayoutParams(layoutParams);

           myView= new MyView2(this);

        mylayout.addView(myView);

      /*  image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(myView.getParent()==null){

                  //  relativeLayout.addView(myView);

                }

                return true;
            }
        });*/


      // MyDrawView2 myDrawView2 = new MyDrawView2(this);


    }


}
