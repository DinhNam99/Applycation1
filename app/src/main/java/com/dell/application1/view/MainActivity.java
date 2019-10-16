package com.dell.application1.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dell.application1.R;
import com.dell.application1.adapter.Adapter;
import com.dell.application1.model.People;
import com.dell.application1.presenter.PresenterCompl;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewInterface.MainView {

    //toolbar and navigation
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    //recyclerview
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<People> listPeople;
    ArrayList<People> listSearch;

    TextView tvKhachHang;
    TextView tvTong;

    //bottomsheet
    BottomSheetDialog bottomSheetDialog;
    TextView tvKhach, tvNhacungcap, tvHuy;

    ImageView imageSearch, imageFilter;
    PresenterCompl presenterCompl;

    int categoryIntent,activeIntent,all;
    String name,mail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenterCompl = new PresenterCompl(this);
        init();
        setAdapter();
        setListener();
        setUpSeaching();
    }

    public void init(){

        //toolbar and navigation
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("");
        drawerLayout = findViewById(R.id.drawermain);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        actionbar.setDisplayHomeAsUpEnabled(true);

        //recyclerview
        recyclerView = findViewById(R.id.recyclerView);

        tvKhachHang = findViewById(R.id.tvKhachHang);
        tvTong = findViewById(R.id.tvTong);

        //bottomsheet diaglog ...
        bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.botton_sheet_option,null);
        bottomSheetDialog.setContentView(view);
        tvKhach = view.findViewById(R.id.botton1);
        tvNhacungcap = view.findViewById(R.id.botton_nhacungcap);
        tvHuy = view.findViewById(R.id.tvHuy);

        imageFilter = findViewById(R.id.imageSearch);
        imageSearch = findViewById(R.id.filter);
    }

    public void setListener(){
        tvKhachHang.setOnClickListener(this);
        tvKhach.setOnClickListener(this);
        tvNhacungcap.setOnClickListener(this);
        tvHuy.setOnClickListener(this);
        imageSearch.setOnClickListener(this);
        imageFilter.setOnClickListener(this);
    }

    public void setAdapter(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenterCompl.displayData();

    }
    public void setUpSeaching(){
        categoryIntent = getIntent().getIntExtra("Category",0);
        activeIntent = getIntent().getIntExtra("Active",1000);
        name = getIntent().getStringExtra("Name");
        mail = getIntent().getStringExtra("Email");
        all = categoryIntent+activeIntent;
        Log.e("CHECK",all+"");
        if(all == 10||all == 0){
            presenterCompl.displayDataSearchingAll(listPeople,"Tất cả",name,mail,1);
        }else if(all == 11 || all == 1){
            presenterCompl.displayDataSearchingAll(listPeople,"Khách hàng",name,mail,1);
        }else if(all == 12 || all == 2){
            presenterCompl.displayDataSearchingAll(listPeople,"Nhà cung cấp",name,mail,1);
        }else if(all == 20){
            presenterCompl.displayDataSearchingAll(listPeople,"Tất cả",name,mail,0);
        }else if(all == 21){
            presenterCompl.displayDataSearchingAll(listPeople,"Khách hàng",name,mail,0);
        }else if(all == 22){
            presenterCompl.displayDataSearchingAll(listPeople,"Nhà cung cấp",name,mail,0);
        }


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

     getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tvKhachHang:
                bottomSheetDialog.show();
                break;
            case R.id.botton1:
                presenterCompl.displayDataToSearchingWithCategory(listPeople,"Khách hàng");
                adapter.notifyDataSetChanged();
                setUpTextBottom(tvKhach,tvNhacungcap,tvHuy);
                break;
            case R.id.botton_nhacungcap:
                presenterCompl.displayDataToSearchingWithCategory(listPeople,"Nhà cung cấp");
                adapter.notifyDataSetChanged();
                setUpTextBottom(tvNhacungcap,tvHuy,tvKhach);
                break;
            case R.id.tvHuy:
                setUpTextBottom(tvHuy,tvKhach,tvNhacungcap);
                break;
            case R.id.imageSearch:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.filter:
                break;
        }

    }
    public void setUpTextBottom(TextView tv1, TextView tv2, TextView tv3){
        bottomSheetDialog.dismiss();
        tv1.setTextColor(getResources().getColor(R.color.colorText_Red));
        tv2.setTextColor(getResources().getColor(R.color.colorText_Blue));
        tv3.setTextColor(getResources().getColor(R.color.colorText_Blue));
    }

    @Override
    public void loadData(ArrayList<People> peopleList) {
        this.listPeople = peopleList;
        adapter = new Adapter(MainActivity.this,listPeople);
        recyclerView.setAdapter(adapter);
        tvTong.setText("Tổng số "+listPeople.size()+" khách hàng");
    }

    @Override
    public void searchOption(ArrayList<People> peopleList) {
        this.listSearch = peopleList;
        adapter = new Adapter(MainActivity.this,listSearch);
        recyclerView.setAdapter(adapter);
        tvTong.setText("Tổng số "+listSearch.size()+" khách hàng");
    }
}
