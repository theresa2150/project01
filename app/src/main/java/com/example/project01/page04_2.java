package com.example.project01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class page04_2 extends AppCompatActivity {

    TextView tv4_2_1, tv4_2_2, tv4_2_3;
    Button btn4_2_1, btn4_2_2;
    static int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.page04_3 );

        Resources res = getResources();
        int[] price = res.getIntArray( R.array.price1 );
        String[] menu = res.getStringArray( R.array.menu );
        String s = "";

        tv4_2_1 = findViewById( R.id.tv4_2_1 );
        tv4_2_2 = findViewById( R.id.tv4_2_2 );
        tv4_2_3 = findViewById( R.id.tv4_2_3 );
        btn4_2_1 = findViewById( R.id.btn4_2_1 );
        btn4_2_2 = findViewById( R.id.btn4_2_2 );

        for (int i = 0; i < page04.fileName.length; i++) {
            if (page04.amount[i] != 0) {
                s += menu[i] + "  數量" + page04.amount[i] + "  小計" + page04.amount[i] * +price[i] + "\n";
            }
        }
        tv4_2_1.setText( s );
        tv4_2_2.setText( "\n總計" + page04.total + "\n" );

        btn4_2_1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        btn4_2_2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder AB = new AlertDialog.Builder( page04_2.this );
                AB.setTitle( "" );
                AB.setMessage( "是確認送出？" );
                AB.setPositiveButton( "確定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn4_2_1.setVisibility( View.GONE );
                        btn4_2_2.setVisibility( View.GONE );
                        for (int i = 0; i < page04.fileName.length; i++) {
                            page04.amount[i] = 0;
                        }

                        SimpleDateFormat sdFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
                        Date current = new Date();
                        String currentTime = sdFormat.format( current );
                        String poNumber = "po#" + currentTime + new DecimalFormat( "000" ).format( count++ );

                        savePackageFile( poNumber );

                        tv4_2_3.setText( "\n訂單成立\n訂單編號" + poNumber + "\n3秒後轉至首頁\n\n" );
                        mHandler.sendEmptyMessageDelayed( GOTO_MAIN_ACTIVITY, 3000 );
                    }
                } );

                AB.setNegativeButton( "取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                } );
//                AB.setCancelable(false);   設定返回鍵不能作用
                AB.show();
            }
        } );
    }

    private static final int GOTO_MAIN_ACTIVITY = 0;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    intent.setClass( page04_2.this, page01.class );
                    startActivity( intent );
                    finish();
                    break;
            }
        }
    };

    private void savePackageFile(String filename) {
        String msg = filename + "\n\n" + tv4_2_1.getText().toString() + tv4_2_2.getText().toString() + "\n";
        FileOutputStream outputStream;
//        保存地址；data/data/包名/files/

        try {
            outputStream = openFileOutput( filename + ".txt", Context.MODE_APPEND );
            outputStream.write( msg.getBytes() );
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
