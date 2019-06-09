package com.carryondown.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.carryondown.app.R;
import com.carryondown.app.util.CommonUtils;

import static com.carryondown.app.util.CommonUtils.HANDLER_SPLASH;

public class SplashActivity extends AppCompatActivity {

    private TextView tv_splash;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case HANDLER_SPLASH:
                    //判断程序是不是第一次运行
//                    if(isFirst()){
//                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
//                    }else{
//                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                    }
                    finish();
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//设置透明导航栏
        }
    }
    //初始化View
    private void initView() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(HANDLER_SPLASH,1000);

        tv_splash = findViewById(R.id.tv_splash);
        //设置字体
        CommonUtils.setFont(this,tv_splash);
    }

    //判断程序是不是第一运行
    private boolean isFirst(){
        boolean isFirst = CommonUtils.getBoolean(this,CommonUtils.SHARE_IS_FIRST,true);
        if(isFirst){
            CommonUtils.putBoolean(this,CommonUtils.SHARE_IS_FIRST,false);//变为false
            //是第一次运行
            return true;
        }else{
            return false;
        }
    }
}
