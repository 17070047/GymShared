package com.carryondown.app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carryondown.app.R;
import com.carryondown.app.adapter.SeckillRecycleViewAdapter;
import com.carryondown.app.base.Product;
import com.carryondown.app.view.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment{


    private List<String> images = new ArrayList();
    private List<String> titles = new ArrayList();
    private RecyclerView recyclerView,recyclerView1;
    private List<Product> products = new ArrayList<>();
    private List<Product> productss = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);
        initData();
        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_seckill);
        recyclerView1 = (RecyclerView) view.findViewById(R.id.rv_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        SeckillRecycleViewAdapter adapter = new SeckillRecycleViewAdapter(getContext(),products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManagerr = new LinearLayoutManager(getContext());
        linearLayoutManagerr.setOrientation(LinearLayoutManager.HORIZONTAL);
        SeckillRecycleViewAdapter adapterr = new SeckillRecycleViewAdapter(getContext(),productss);
        recyclerView1.setAdapter(adapterr);
        recyclerView1.setLayoutManager(linearLayoutManagerr);
        return view;
    }

    private void initData(){
        images.add("https://upload-images.jianshu.io/upload_images/9140378-12c28f9bad9e1d6e.png");
        images.add("https://upload-images.jianshu.io/upload_images/9140378-55e5f471bc046265.png");
        images.add("https://upload-images.jianshu.io/upload_images/9140378-bfbe5fec4d13944c.png");
        images.add("https://upload-images.jianshu.io/upload_images/9140378-1216130fc1331a4e.png");

        titles.add("男女健身便携塑形增肌粉");
        titles.add("Muscletech肌肉科技运动乳清蛋白质粉");
        titles.add("蛋白增肌粉健身男健肌粉2磅");
        titles.add("健身即食速食代餐轻食低脂增肌高蛋白鸡肉食品");

        products.add(new Product("https://upload-images.jianshu.io/upload_images/9140378-7cf4663cb42eb7d7.png","26.9","19.9"));
        products.add(new Product("https://upload-images.jianshu.io/upload_images/9140378-12ba96cc9214480e.png","89","59"));
        products.add(new Product("https://upload-images.jianshu.io/upload_images/9140378-3863ac76d8608469.png","499","299"));
        products.add(new Product("https://upload-images.jianshu.io/upload_images/9140378-b2b2ad394c9f6f6e.png","300","179"));
        products.add(new Product("https://upload-images.jianshu.io/upload_images/9140378-50bc627a36130fa2.png","29.9","16.9"));


        productss.add(new Product("https://upload-images.jianshu.io/upload_images/9140378-cb042b9393511cdb.png","39.0","27.9"));
        productss.add(new Product("https://upload-images.jianshu.io/upload_images/9140378-88daea00940c0069.png","99.0","79.9"));
        productss.add(new Product("https://upload-images.jianshu.io/upload_images/9140378-8e9b5d745a4e36e1.png","159.9","129.0"));
        productss.add(new Product("https://upload-images.jianshu.io/upload_images/9140378-e7bce6e9ed8d3736.png","59.9","29.9"));
        productss.add(new Product("https://upload-images.jianshu.io/upload_images/9140378-861b5d560b922bfc.png","39.9","36.9"));
    }
}
