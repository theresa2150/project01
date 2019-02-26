package com.example.project01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class page01 extends AppCompatActivity {


    int[] btnId = {R.id.btn1_1, R.id.btn1_2, R.id.btn1_3, R.id.btn1_4, R.id.btn1_5, R.id.btn1_6};
    Button[] btn = new Button[btnId.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.page01 );
        for (int i = 0; i < btnId.length; i++) {
            final int finalI = i;
            btn[i] = findViewById( btnId[i] );
            btn[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        case 0:
                            startActivity( new Intent( page01.this, page02.class ) );
                            break;
                        case 1:
//                            startActivity( new Intent( page01.this, page02.class ) );
                            break;
                        case 2:
                            startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( "https://www.google.com.tw/maps" ) ) );
                            break;
                        case 3:
                            startActivity( new Intent( page01.this, page03.class ) );
                            break;
                        case 4:
//                            startActivity( new Intent( page01.this, page03.class ) );
                            break;
                        case 5:
                            startActivity( new Intent( page01.this, page04.class ) );
                            break;
                    }
                }
            } );
        }
    }
}
