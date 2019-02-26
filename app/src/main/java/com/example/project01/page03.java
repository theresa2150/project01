package com.example.project01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class page03 extends AppCompatActivity {

    Spinner sp3_1, sp3_2;
    EditText et3_1, et3_2;
    double jpy = 1, ntd = 0.2825, usd = 0.00909, cny = 0.06145;
    double d1 = 1, d2 = 1, c1 = 1, c2 = 1, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.page03 );
        sp3_1 = findViewById( R.id.sp3_1 );
        sp3_2 = findViewById( R.id.sp3_2 );
        et3_1 = findViewById( R.id.et3_1 );
        et3_2 = findViewById( R.id.et3_2 );

        sp3_1.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        c1 = jpy;
                        break;
                    case 1:
                        c1 = ntd;
                        break;
                    case 2:
                        c1 = usd;
                        break;
                    case 3:
                        c1 = cny;
                        break;
                }
                try {
                    d2 = Double.parseDouble( et3_2.getText().toString() );
                    et3_1.setText( new DecimalFormat( "#.####" ).format( d2 * c1 / c2 ) + "" );
                } catch (Exception e) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
        sp3_2.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        c2 = jpy;
                        break;
                    case 1:
                        c2 = ntd;
                        break;
                    case 2:
                        c2 = usd;
                        break;
                    case 3:
                        c2 = cny;
                        break;
                }
                try {
                    d1 = Double.parseDouble( et3_1.getText().toString() );
                    et3_2.setText( new DecimalFormat( "#.####" ).format( d1 * c2 / c1 ) + "" );
                } catch (Exception e) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        et3_1.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    try {
                        d1 = Double.parseDouble( et3_1.getText().toString() );
                        et3_2.setText( new DecimalFormat( "#.####" ).format( d1 * c2 / c1 ) + "" );
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } );

        et3_2.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    try {
                        d2 = Double.parseDouble( et3_2.getText().toString() );
                        et3_1.setText( new DecimalFormat( "#.####" ).format( d2 * c1 / c2 ) + "" );
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } );
    }
}