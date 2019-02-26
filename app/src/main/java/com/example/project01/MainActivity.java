package com.example.project01;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        img_0 = findViewById( R.id.img_0 );
        myimageviewsize( img_0, 1.2 );

        mHandler.sendEmptyMessageDelayed( GOTO_MAIN_ACTIVITY, 2500 ); //2秒跳轉
    }

    private static final int GOTO_MAIN_ACTIVITY = 0;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    intent.setClass( MainActivity.this, page01.class );
                    startActivity( intent );
                    finish();
                    break;
            }
        }
    };

    private void myimageviewsize(ImageView imgid, double rate) {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics( dm );
        int ScreenWidth = dm.widthPixels;   //螢幕寬
//        int ScreenHeight = dm.heightPixels;  //螢幕高

        double pictureWidth = ScreenWidth / rate;
//        double pictureHeight = ScreenHeight / rate;

        ViewGroup.LayoutParams params = imgid.getLayoutParams();

        params.width = (int) pictureWidth;
//        params.height = (int) pictureHeight;
        imgid.setLayoutParams( params );
    }
}
