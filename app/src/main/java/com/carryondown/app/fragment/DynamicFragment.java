package com.carryondown.app.fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.carryondown.app.R;
import com.carryondown.app.activity.AddDynamicActivity;
import com.carryondown.app.adapter.NineGridAdapter;
import com.carryondown.app.application.MyApplication;
import com.carryondown.app.base.NineGridModel;
import com.carryondown.app.util.UrlUtil;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.getbase.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

public class DynamicFragment  extends Fragment implements View.OnClickListener{

    private List<NineGridModel> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private NineGridAdapter mAdapter;
    private ImageView mImageView,imageView,avatar,ic_back;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private View mCommentView;

    /*与悬浮按钮相关*/
    private FloatingActionsMenu mFloatingActionsMenu;
    private FloatingActionButton mAddDynamic,mScrollToUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic,container,false);
        if(MyApplication.List != null){
            mList = MyApplication.List;
        }else {
            initListData();
        }
        initView(view);
        initFloatButton(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setStackFromEnd( true );  //列表从底部开始展示
        mLayoutManager.setReverseLayout( true );  //列表反转
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NineGridAdapter(getContext());
        mCommentView = view.findViewById( R.id.comment_view );
        mAdapter.setList(mList);
        mAdapter.setCommentListener( new NineGridAdapter.OnCommentListener() {
            @Override
            public void onComment(final int position) {
                mCommentView.setVisibility( View.VISIBLE );
                mCommentView.findViewById( R.id.submit ).setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText et = (EditText) mCommentView.findViewById( R.id.edit );
                        String s = et.getText().toString();
                        if (!TextUtils.isEmpty( s )){

                            et.setText("");
                            mCommentView.setVisibility( View.GONE );
                        }
                    }
                } );
            }
        } );
        mRecyclerView.setAdapter(mAdapter);
}

    private void initFloatButton(View view){
        mFloatingActionsMenu = (FloatingActionsMenu) view.findViewById( R.id.main_actions_menu );
        mAddDynamic = (FloatingActionButton) view.findViewById( R.id.add_dynamic );
        mScrollToUp = (FloatingActionButton) view.findViewById( R.id.scroll_to_up );
        mAddDynamic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult( new Intent(getActivity(),AddDynamicActivity.class ),10);
            }
        } );
        mScrollToUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.scrollToPosition(mList.size() - 1);
            }
        } );
    }


    private String[] mUrls = UrlUtil.getUrl();
    private String[] mAvatar = UrlUtil.getAvator();


    private void initListData() {
        NineGridModel model1 = new NineGridModel();
        model1.urlList.add(mUrls[0]);
        model1.setName( "健身宅" );
        model1.setContent("健身对于我个人而言，可能更多的是健心吧，把我自卑的性格彻底扭转了，反倒觉得健身带来的身材变化倒是其次了，接触健身之后整个人真的！会变的！很自信！");
        model1.setAvatar( mAvatar[0]);
        model1.setTime( "今天 18:40" );
        mList.add(model1);

        NineGridModel model2 = new NineGridModel();
        model2.setName("原来是月夜啊");
        model2.urlList.add(mUrls[4]);
        model2.setAvatar(mAvatar[1]);
        model2.setContent("总之健身锻炼给我感觉，坚持不下去是必然的，而坚持下去就是你自己的选择。");
        model2.setTime("今天 20:41");
        mList.add(model2);

        NineGridModel model3 = new NineGridModel();
        model3.urlList.add(mUrls[2]);
        model3.setAvatar(mAvatar[2]);
        model3.setTime("今天 8:12");
        model3.setName("少年青衣");
        model3.setContent("断断续续15年。累计时间7-8年。变化是体重。我的肌肉记忆让我能很快恢复身材。因为有减脂的经验。");

        mList.add(model3);

        NineGridModel model4 = new NineGridModel();
        for (int i = 0; i < mUrls.length; i++) {
            model4.urlList.add(mUrls[i]);
        }
        model4.isShowAll = false;
        model4.setAvatar(mAvatar[3]);
        model4.setName("\n" +
                "小白鲸");
        model4.setTime("昨天 20:10");
        model4.setContent("肌肉紧实了，不喜欢那种干瘦的人了");
        mList.add(model4);

        NineGridModel model5 = new NineGridModel();
        for (int i = 6; i < mUrls.length; i++) {
            model5.urlList.add(mUrls[i]);
        }
        model5.isShowAll = true;//显示全部图片
        model5.setAvatar(mAvatar[4]);
        mList.add(model5);
        model5.setName("健身欢子");
        model5.setTime("昨天 14:10");
        model5.setContent("精神面貌");

        NineGridModel model6 = new NineGridModel();
        for (int i = 0; i < 9; i++) {
            model6.urlList.add(mUrls[i]);
        }
        model6.setAvatar(mAvatar[5]);
        mList.add(model6);
        model6.setName("黄小贱");
        model6.setTime("昨天 12:10");
        model6.setContent("喜欢上自己的教练了，虽然不确定他对我有没有意思，而且对我的态度也不是很特别，但就是莫名其妙的喜欢。");

        NineGridModel model7 = new NineGridModel();
        for (int i = 3; i < 7; i++) {
            model7.urlList.add(mUrls[i]);
        }
        model7.setAvatar(mAvatar[6]);
        mList.add(model7);
        model7.setName("愤怒的phy");
        model7.setTime("今天 6:10");
        model7.setContent("断断续续的好几年了,从最开始的每天开开心心的去健身房到现在不开心的去");

        NineGridModel model8 = new NineGridModel();
        for (int i = 3; i < 6; i++) {
            model8.urlList.add(mUrls[i]);
        }
        model8.setAvatar(mAvatar[7]);
        model8.setName("喜欢健身的小羊驼");
        model8.setTime("刚刚");
        model8.setContent("说最大的变化，其实并不是体型，而且我自己的心态。");
        mList.add(model8);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 10){
            String content = data.getStringExtra("content");
            ArrayList mUri = data.getStringArrayListExtra("list");
            final NineGridModel nineGridModel = new NineGridModel();
            nineGridModel.setContent(content);
            nineGridModel.setName("WangHao");
            nineGridModel.setAvatar("https://upload-images.jianshu.io/upload_images/9140378-631f73b40227248e.jpg");
            nineGridModel.setTime("刚刚");
            nineGridModel.setUrlList(mUri);
            mList.add(nineGridModel);
            MyApplication.List = mList;
            mAdapter.notifyDataSetChanged();
            Log.e("dynamic","add");
            nineGridModel.save(new SaveListener<String>() {
                @Override
                public void done(String objectId,BmobException e) {
                    if(e == null){
                        Log.e("test","添加数据成功，返回objectId为："+objectId);
                    }else{
                        Log.e("test","创建数据失败：" + e.getMessage());
                    }
                }
            });
            String [] filepaths = (String [])mUri.toArray(new String[mUri.size()]);
            Log.e("dynamic",filepaths.toString());
            BmobFile.uploadBatch(filepaths, new UploadBatchListener() {

                @Override
                public void onSuccess(List<BmobFile> files,List<String> urls) {
                    //1、files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
                    //2、urls-上传文件的完整url地址
                    if(urls.size() == mUrls.length){//如果数量相等，则代表文件全部上传完成
                        //do something
                    }
                }

                @Override
                public void onError(int statuscode, String errormsg) {
                    Log.e("dynamic","错误码"+statuscode +",错误描述："+errormsg);
                }

                @Override
                public void onProgress(int curIndex, int curPercent, int total,int totalPercent) {
                    //1、curIndex--表示当前第几个文件正在上传
                    //2、curPercent--表示当前上传文件的进度值（百分比）
                    //3、total--表示总的上传文件数
                    //4、totalPercent--表示总的上传进度（百分比）
                }
            });
        }
    }
}

