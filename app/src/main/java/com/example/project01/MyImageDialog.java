package com.example.project01;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MyImageDialog extends Dialog {

    private Window window = null;
    private ImageView idv;
    int position;

    public MyImageDialog(Context context, int position) {
        super(context);
        this.position = position;
    }

    public MyImageDialog(Context context) {
        super(context);
    }

    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        //初始化佈局
        View loadingview = LayoutInflater.from(getContext()).inflate(R.layout.page04_2, null);
        idv = loadingview.findViewById(R.id.idv);
        //設定dialog的佈局
        setContentView(loadingview);
        //如果需要放大或者縮小時的動畫，可以直接在此出對loadingview或iv操作，在下面SHOW或者dismiss中操作
        super.onCreate(savedInstanceState);
        idv.setImageResource(page04.fileName[position]);
    }

    public void show() {
        //設定觸控對話方塊意外的地方取消對話方塊
        setCanceledOnTouchOutside(true);
        super.show();
    }

    public void dismiss() {
        super.dismiss();
    }
}