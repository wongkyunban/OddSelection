package com.wong.odd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        mRV = findViewById(R.id.rv);
        // 设置RecyclerView使用的布局
        mRV.setLayoutManager(new LinearLayoutManager(this));
        // 设置RecyclerView分割线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        mRV.addItemDecoration(itemDecoration);
        // 设置RecyclerView适配器
        RvAdapter adapter = new RvAdapter();
        mRV.setAdapter(adapter);
        // 填充数据
        List<DataBean> list = new ArrayList<>();
        for(int i = 0;i < 30;i++){
            list.add(new DataBean("name"+i));
        }
        adapter.setList(list);
        // 通知RecyclerView更新视图
        adapter.notifyDataSetChanged();
    }
}