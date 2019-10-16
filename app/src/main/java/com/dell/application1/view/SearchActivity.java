package com.dell.application1.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dell.application1.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvHuybo, tvAdung, tvTatca1, tvTatca2, tvKhachhangmua, tvNhacungcap, tvDaghd, tvNgunghd;
    RelativeLayout layouTatca2, layoutKhachhang, layoutNhacc;
    EditText edMaten, edEmail;
    ImageView check1, check2, check3;
    int checkCategory,checkActive = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        View decorView = getWindow().getDecorView(); //set status background black
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        init();
        setListener();
    }


    private void init() {
        tvHuybo = findViewById(R.id.tvHuyBo);
        tvAdung = findViewById(R.id.tvAdung);
        tvTatca1 = findViewById(R.id.tvTatca1);
        tvTatca2 = findViewById(R.id.tvTatca2);
        tvKhachhangmua = findViewById(R.id.tvKhachhangmua);
        tvNhacungcap = findViewById(R.id.tvNhacungcap);
        tvDaghd = findViewById(R.id.danghd);
        tvNgunghd = findViewById(R.id.ngunghd);
        edMaten = findViewById(R.id.edMaTenDT);
        edEmail = findViewById(R.id.edEmail);
        layouTatca2 = findViewById(R.id.Tatca2);
        layoutKhachhang = findViewById(R.id.Khachhangmua);
        layoutNhacc = findViewById(R.id.Nhacungcap);
        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
    }
    private void setListener() {
        tvHuybo.setOnClickListener(this);
        tvAdung.setOnClickListener(this);
        layouTatca2.setOnClickListener(this);
        layoutKhachhang.setOnClickListener(this);
        layoutNhacc.setOnClickListener(this);
        tvDaghd.setOnClickListener(this);
        tvNgunghd.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tvHuyBo:
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                checkActive = 1000;
                break;
            case R.id.tvAdung:
                Intent intent2 = new Intent(SearchActivity.this, MainActivity.class);
                intent2.putExtra("Category",checkCategory);
                intent2.putExtra("Active",checkActive);
                intent2.putExtra("Name",edMaten.getText().toString());
                intent2.putExtra("Email",edEmail.getText().toString());
                startActivity(intent2);
                finish();
                break;
            case R.id.Tatca2:
                setUpClickCategory(tvTatca2,tvKhachhangmua,tvNhacungcap,check1,check2,check3);
                checkCategory = 0;
                break;
            case R.id.Khachhangmua:
                setUpClickCategory(tvKhachhangmua,tvTatca2,tvNhacungcap,check2,check1,check3);
                checkCategory = 1;
                break;
            case R.id.Nhacungcap:
                setUpClickCategory(tvNhacungcap,tvKhachhangmua,tvTatca2,check3,check2,check1);
                checkCategory = 2;
                break;
            case R.id.danghd:
                setUpActive(tvNgunghd,tvDaghd);
                checkActive = 10;
                break;
            case R.id.ngunghd:
                setUpActive(tvDaghd,tvNgunghd);
                checkActive = 20;
                break;
        }
    }

    private void setUpClickCategory(TextView tv1, TextView tv2, TextView tv3, ImageView iv1, ImageView iv2, ImageView iv3){
        tv1.setTextColor(getResources().getColor(android.R.color.black));
        tv2.setTextColor(getResources().getColor(R.color.colorText_light));
        tv3.setTextColor(getResources().getColor(R.color.colorText_light));
        iv1.setVisibility(View.VISIBLE);
        iv2.setVisibility(View.INVISIBLE);
        iv3.setVisibility(View.INVISIBLE);
    }
    private void setUpActive(TextView tv1, TextView tv2){
        tv1.setBackgroundResource(R.drawable.draw_item);
        tv2.setBackgroundResource(R.drawable.draw_item_red);
        tv1.setTextColor(getResources().getColor(android.R.color.black));
        tv2.setTextColor(getResources().getColor(android.R.color.white));
    }

}
