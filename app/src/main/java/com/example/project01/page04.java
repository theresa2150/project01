package com.example.project01;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class page04 extends AppCompatActivity {
    static int[] fileName = {R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4, R.drawable.f5, R.drawable.f6};
    static int[] amount = new int[fileName.length];
    static int total = 0;
    ViewHolder viewHolder;
    ListView lv;
    Button btn4_1, btn4_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.page04 );
        lv = findViewById( R.id.lv4_1 );
        btn4_1 = findViewById( R.id.btn4_1 );
        btn4_2 = findViewById( R.id.btn4_2 );

        lv.setAdapter( new MyAdapter( page04.this ) );

        //------------------------設定listview長度

        ListAdapter listAdapter = lv.getAdapter();

        int totalHeight = 0;
        for (int i = 0; i < lv.getCount(); i++) {
            View listItem = listAdapter.getView( i, null, lv );
            listItem.measure( 0, 0 );
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight + (lv.getDividerHeight() * (listAdapter.getCount() - 1));
        ((ViewGroup.MarginLayoutParams) params).setMargins( 10, 10, 10, 10 );
        lv.setLayoutParams( params );

        //------------------------

        btn4_1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 0;
                try {
                    Resources res = getResources();
                    int[] price = res.getIntArray( R.array.price1 );
                    for (int i = 0; i < fileName.length; i++) {
                        total += amount[i] * price[i];
                    }
                    if (total == 0)
                        Toast.makeText( page04.this, "尚未點餐", Toast.LENGTH_SHORT ).show();
                    else startActivity( new Intent( page04.this, page04_2.class ) );
                } catch (Exception e) {
                    Toast.makeText( page04.this, e.toString(), Toast.LENGTH_LONG ).show();
                }
            }
        } );

        btn4_2.setVisibility( View.GONE );
//        btn4_2.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int i = 0; i < fileName.length; i++) {
//                    amount[i] = 0;
//                }
//                viewHolder.editText.setText( 0 );
//            }
//        } );
    }

    protected class MyAdapter extends BaseAdapter {
        LayoutInflater layoutInflater;
        Resources res = getResources();
        String[] menu = res.getStringArray( R.array.menu );
        int[] price = res.getIntArray( R.array.price1 );

        MyAdapter(Context context) {
            layoutInflater = (LayoutInflater) context.getSystemService( LAYOUT_INFLATER_SERVICE );
        }

        @Override
        public int getCount() {
            return fileName.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate( R.layout.page04_1, null );
                viewHolder = new ViewHolder();
                viewHolder.iv4_1 = convertView.findViewById( R.id.iv4_1 );
                viewHolder.editText = convertView.findViewById( R.id.editText );
                viewHolder.tv_menu1 = convertView.findViewById( R.id.tv_menu1 );
                viewHolder.lv4_1 = convertView.findViewById( R.id.lv4_1 );
                viewHolder.tv_unitprice = convertView.findViewById( R.id.tv_unitprice );
//                viewHolder.tv_subtotalprice = convertView.findViewById( R.id.tv_subtotalprice );


//                viewHolder.spinner = convertView.findViewById( R.id.spinner );
                convertView.setTag( viewHolder );
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.iv4_1.setImageResource( fileName[position] );
            viewHolder.tv_menu1.setText( menu[position] );
            viewHolder.tv_unitprice.setText( price[position] + "" );
            viewHolder.editText.addTextChangedListener( new myTextWatcher( position, price ) );
            viewHolder.iv4_1.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    new MyImageDialog( page04.this, position ).show();
                }
            } );

//            viewHolder.spinner.setOnItemSelectedListener( new mySpinner( position, price ) );

//            viewHolder.editText.setOnFocusChangeListener( new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    if (hasFocus) {
//                        viewHolder.editText.addTextChangedListener( new myTextWatcher( position, price ) );
//                    }
//                }
//            } );
//            viewHolder.spinner.setOnFocusChangeListener( new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    if (true) {
//                        viewHolder.spinner.setOnItemSelectedListener( new mySpinner( position, price ) );
//                    }
//                }
//            } );

            return convertView;
        }
    }

    class ViewHolder {
        ImageView iv4_1;
        EditText editText;
        TextView tv_menu1, tv_unitprice, tv_subtotalprice;
        ListView lv4_1;
        Spinner spinner;
    }

    class myTextWatcher implements TextWatcher {
        int position;
        int[] price;

        myTextWatcher(int position, int[] price) {
            super();
            this.position = position;
            this.price = price;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                amount[position] = Integer.parseInt( s.toString() );
                int sTotal = amount[position] * price[position];
                int total = 0;
                for (int i = 0; i < fileName.length; i++) {
                    total += amount[i] * price[i];
                }
                Toast.makeText( page04.this, "小計：" + sTotal + "\n總計：" + total, Toast.LENGTH_SHORT ).show();
            } catch (Exception e) {
                Toast.makeText( page04.this, e + "", Toast.LENGTH_LONG ).show();
            }
        }
    }

    class mySpinner implements AdapterView.OnItemSelectedListener {
        int position;
        int[] price;

        mySpinner(int position, int[] price) {
            super();
            this.position = position;
            this.price = price;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int choose, long id) {
            try {
                amount[position] = choose;
                int sTotal = amount[position] * price[position];
                int total = 0;
                for (int i = 0; i < fileName.length; i++) {
                    total += amount[i] * price[i];
                }
                Toast.makeText( page04.this, "小計：" + sTotal + "\n總計：" + total, Toast.LENGTH_SHORT ).show();
            } catch (Exception e) {
                Toast.makeText( page04.this, e + "", Toast.LENGTH_LONG ).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}