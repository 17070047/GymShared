package com.carryondown.app.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carryondown.app.R;
import com.carryondown.app.base.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabFragment extends Fragment implements View.OnClickListener{

    private static String UserID = "11";
    private Context mContext;
    private static final String PAGE = "page";
    private static int OPENAGAIN = 0;
    private Button countryButton;
    //TabLayout的页数
    private int mPAge;
    //先获取联系人列表
    private  ArrayList<Contact> contactList = new ArrayList<Contact>(  );
    //下拉刷新
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;
    public static final String CONTENT = "content";

    public static Fragment newInstance(){
        Bundle bundle = new Bundle();
        TabFragment messageFragment = new TabFragment();
        messageFragment.setArguments(bundle);
        return messageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        View view = inflater.inflate( R.layout.fragment_teacher,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.contact_recycler_view_message);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration( new DividerItemDecoration( getContext(),DividerItemDecoration.VERTICAL ) );
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_contact);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContacts();
            }
        });
        SearchView searchView = (SearchView) view.findViewById( R.id.searchView_message );
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        } );
        OPENAGAIN = 1;
        //在创建View的同时更新UI
        updateUI();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPAge = getArguments().getInt(PAGE);
        refreshContacts();
    }

    @Override
    public void onClick(View v) {

    }

    private class ContactsHolder extends RecyclerView.ViewHolder{
        View contactView;
        //加载文字
        public TextView mTextView;
        //加载图片
        public ImageView mImageView;
        //在学语言的水平
        private TextView language_level;
        //联系人的母语
        private TextView mother_language;
        //正在学习的语言
        private TextView learning_language;
        @SuppressLint({"ResourceType", "CheckResult"})
        public ContactsHolder(View itemView) {
            super(itemView);
            contactView = itemView;
            mTextView = (TextView) itemView.findViewById(R.id.contact_name_text);
            mImageView = (ImageView) itemView.findViewById(R.id.contact_image);
            language_level = (TextView) itemView.findViewById( R.id.language_level );
            mother_language = (TextView) itemView.findViewById( R.id.mother_tongue );
            learning_language = (TextView) itemView.findViewById( R.id.learn_language );
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactsHolder>{
        private List<Contact> mContacts;
        public ContactAdapter(List<Contact> contactList){
            mContacts = contactList;
        }
        @NonNull
        @Override
        public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (mContext == null){
                mContext = parent.getContext();
            }
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_contact_item,parent,false);
            final ContactsHolder holder = new ContactsHolder(view);

            holder.contactView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    //获取点击位置的position
//                    int position = holder.getAdapterPosition();
//                    Contact contact = contactList.get(position);
//                    Intent intent = new Intent(mContext, ContactActivity.class);
//                    mContext.startActivity(intent);
                }
            });
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Contact contact = contactList.get(position);
                }
            });
            return holder;
        }
        @Override
        public void onBindViewHolder(@NonNull ContactsHolder holder, int position) {
            Contact contact = mContacts.get(position);
            Glide.with(getContext()).load(contact.getImageUri()).into(holder.mImageView);
//            holder.mImageView.setImageResource(contact.getImagrId());
            holder.mTextView.setText(contact.getName());
            holder.language_level.setText( contact.getLanguage_level() );
            holder.learning_language.setText( contact.getLearn_language() );
            holder.mother_language.setText( contact.getMother_language() );

        }
        @Override
        public int getItemCount() {
            return mContacts.size();
        }
    }

    //创建方法用于更新UI
    private void updateUI() {
        //绑定联系人的adapter
        mAdapter = new ContactAdapter(contactList);
        mRecyclerView.setAdapter(mAdapter);

    }
    private Contact[] mContacts= {
            new Contact("Marco.Lee","https://upload-images.jianshu.io/upload_images/9140378-013259ce9c88f81f.jpg",R.drawable.fab_bg_mini,
                    "爱生活 爱运动","专业 细心 负责","1对1 塑性基础体能课"),
            new Contact("Peter.Huang","https://upload-images.jianshu.io/upload_images/9140378-75c20c1258a2e41e.jpg",R.drawable.fab_bg_mini,
                    "健身是一种生活态度","强壮 耐心 负责","1对1 健身基础体能课"),
            new Contact("Abraham.Li","https://upload-images.jianshu.io/upload_images/9140378-98e8d5c4a45363ef.jpg",R.drawable.fab_bg_mini,
                    "生命在于运动","专业 幽默 好人","1对1 健身基础体能课"),
            new Contact("Baron.Zhang","https://upload-images.jianshu.io/upload_images/9140378-9ad40331573022f9.jpg",R.drawable.fab_bg_mini,
                    "爱生活爱运动","专业 细心 负责","1对1 健身基础体能课"),
            new Contact("Bruno.Lee","https://upload-images.jianshu.io/upload_images/9140378-dce027cdb43c78d7.jpg",R.drawable.fab_bg_mini,
                    "生命在于运动","强壮 耐心 负责","1对1 塑性基础体能课"),
            new Contact("Borg.Bao","https://upload-images.jianshu.io/upload_images/9140378-ab1296312626e410.jpg",R.drawable.fab_bg_mini,
                    "健身是一种生活态度","专业 幽默 好人","1对1 健身基础体能课"),
            new Contact("Christopher.Wang","https://upload-images.jianshu.io/upload_images/9140378-c2b39c7cadd46b8a.jpg",R.drawable.fab_bg_mini,
                    "生命在于运动","专业 细心 负责","1对1 健身基础体能课"),
            new Contact("Derrick.Ma","https://upload-images.jianshu.io/upload_images/9140378-499408b609c70e75.jpg",R.drawable.fab_bg_mini,
                    "健身是一种生活态度","专业 幽默 好人","1对1 健身基础体能课"),
            new Contact("Winifre.Tan","https://upload-images.jianshu.io/upload_images/9140378-a65dbc4adf46f314.jpg",R.drawable.fab_bg_mini,
                    "生命在于运动","专业 幽默 好人","1对1 塑性基础体能课"),
            new Contact("Brandon.Ding","https://upload-images.jianshu.io/upload_images/9140378-e9811680f3f565bf.jpg",R.drawable.fab_bg_mini,
                    "爱生活爱运动","专业 细心 负责","1对1 健身基础体能课"),
            new Contact("Emily.Song","https://upload-images.jianshu.io/upload_images/9140378-0fe80649f6598f30.jpg",R.drawable.fab_bg_mini,
                    "爱生活爱运动","强壮 耐心 负责","1对1 健身基础体能课"),
            new Contact("Hannah.Zhong","https://upload-images.jianshu.io/upload_images/9140378-dafb7a88712aa6b1.jpg",R.drawable.fab_bg_mini,
                    "健身是一种生活态度","专业 细心 负责","1对1 塑性基础体能课")
    };
    private void refreshContacts(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //线程沉睡以便看到刷新的效果
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contactList.clear();
                        mRecyclerView.getLayoutManager().scrollToPosition( 0 );
                        for (int i = 0; i < 10; i++) {
                            Random random = new Random(  );
                            int index = random.nextInt(mContacts.length);
                            contactList.add(mContacts[index]);
                        }
                        mAdapter.notifyDataSetChanged();
                        //刷新结束后隐藏进度条
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}