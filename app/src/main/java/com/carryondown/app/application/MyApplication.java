package com.carryondown.app.application;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.carryondown.app.lib.NineGridModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.List;

import cn.bmob.v3.Bmob;

public class MyApplication extends MultiDexApplication{

    private static MyApplication myApplication;
    public static MyApplication getInstance() {
        return myApplication;
    }
    public static List<NineGridModel> List;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        ZXingLibrary.initDisplayOpinion(this);
        initImageLoader();
        Bmob.initialize(this, "c70b96aeb24de17681c5ab7e9fe637a0");
    }


    private void initImageLoader(){
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault( this );
        ImageLoader.getInstance().init( configuration );
    }
}
