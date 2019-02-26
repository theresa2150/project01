package com.example.project01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class page02 extends AppCompatActivity {
    int[] imgId = {R.id.img2_1, R.id.img2_2, R.id.img2_3, R.id.img2_4};
    int[] pic = {R.drawable.food03, R.drawable.food04, R.drawable.food05, R.drawable.food06};
    ImageView[] img = new ImageView[pic.length];

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page02);

        for (int i = 0; i < img.length; i++) {
            final int finalI = i;
            img[i] = findViewById(imgId[i]);
            img[i].setImageResource(pic[i]);
            img[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MyURI(finalI).forward();
                }
            });
        }
    }

    public class MyURI {
        int id;
        String uri;

        public MyURI(int id) {
            this.id = id;
        }

        void forward() {
            switch (id) {
                case 0:
                    uri = "http://mr-farmer.jp";
                    break;
                case 1:
                    uri = "https://www.lapaz-tokyo.com";
                    break;
                case 2:
                    uri = "https://www.ballontokyo.com";
                    break;
                case 3:
                    uri = "http://ain-soph.jp";
                    break;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        }
    }
}
