package com.github.yylyingy.home.view;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.yylyingy.common.arouter.HomePage;
import com.github.yylyingy.common.grant.callback.AbstractOnPermissionCallBack;
import com.github.yylyingy.common.grant.core.PermissionRequestFactory;
import com.github.yylyingy.common.log.LoggerManager;
import com.github.yylyingy.common.mediafilter.MediaFileFilter;
import com.github.yylyingy.common.mediafilter.callback.FilterResultCallback;
import com.github.yylyingy.common.mediafilter.entity.Directory;
import com.github.yylyingy.common.mediafilter.entity.VideoFile;
import com.github.yylyingy.common.mvp.base.BaseActivity;
import com.github.yylyingy.common.widget.systemstatusbar.SystemBarConfig;
import com.github.yylyingy.home.R;
import com.github.yylyingy.home.R2;
import com.github.yylyingy.home.adapter.VideoFolderRecycleAdapter;
import com.github.yylyingy.home.adapter.VideoRecycleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

@Route(path = HomePage.HOME_PAGE,name = "主页")
public class MainActivity extends BaseActivity {
    TextView mTvTitle;
    @BindView(R2.id.recycle_view)
    RecyclerView mVideoRecyclerView;
    GridLayoutManager mGridLayoutManager;
    VideoRecycleAdapter mVideoRecycleAdapter;
    VideoFolderRecycleAdapter mVideoFolderRecycleAdapter;
    boolean mOpenFolder = false;
    long mExitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz_home_activity_main);
        mTvTitle = findViewById(R.id.title);
        mVideoRecyclerView = findViewById(R.id.recycle_view);
        mGridLayoutManager = new GridLayoutManager(this,3);
        mVideoRecycleAdapter = new VideoRecycleAdapter(this);
        mVideoFolderRecycleAdapter = new VideoFolderRecycleAdapter(this);
        mVideoRecyclerView.setLayoutManager(mGridLayoutManager);
        mVideoRecyclerView.setAdapter(mVideoFolderRecycleAdapter);
        requestReadPermission();
        initClick();
    }

    @Override
    public void onBackPressed() {
        if (mOpenFolder) {
            mOpenFolder = false;
            mVideoRecyclerView.setAdapter(mVideoFolderRecycleAdapter);
            mGridLayoutManager.setSpanCount(3);
            mTvTitle.setText(R.string.folders);
            return;
        }
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            mExitTime = System.currentTimeMillis();
            Toast.makeText(this, R.string.double_back_press, Toast.LENGTH_SHORT).show();

        } else {
            super.onBackPressed();
        }
    }

    private void initClick() {
        //folder click
        mVideoFolderRecycleAdapter.setOnFolderClickListener(new VideoFolderRecycleAdapter.OnFolderClickListener() {
            @Override
            public void onClick(View view, int position, Directory directory) {
                mVideoRecyclerView.setAdapter(mVideoRecycleAdapter);
                mVideoRecycleAdapter.getDataSet().clear();
                mVideoRecycleAdapter.add(directory.getFiles());
                mGridLayoutManager.setSpanCount(2);
                mOpenFolder = true;
                mTvTitle.setText(directory.getName());
            }
        });
        //video file click
        mVideoRecycleAdapter.setOnItemClickListener(new VideoRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, VideoFile videoFile) {

            }
        });
    }

    private void requestReadPermission() {
        PermissionRequestFactory.create(this).addPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).request(new AbstractOnPermissionCallBack(this, true) {

            @Override
            public void onRequestAllow(String... strings) {
                loadVideoData();
            }
        });
    }


    private void loadVideoData() {
        MediaFileFilter.getVideos(this, new FilterResultCallback<VideoFile>() {
            @Override
            public void onResult(List<Directory<VideoFile>> directories) {
                LoggerManager.d(TAG,"" + directories.size());
                ArrayList<Directory> list = new ArrayList<>(directories);
                Collections.sort(list, new Comparator<Directory>() {
                    @Override
                    public int compare(Directory o1, Directory o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                mVideoFolderRecycleAdapter.add(list);
            }
        });
    }

    /**
     *<br> Description: 沉浸状态栏
     *<br> Author:      Yangyl
     *<br> Date:        2019/5/22 20:15
     */
    @Override
    protected SystemBarConfig getStatusBarConfig() {
        return new SystemBarConfig()
                .setColor(getResources().getColor(com.github.yylyingy.common.R.color.lib_title_base_background))
//                .setLollipopColor(getResources().getColor(com.github.yylyingy.common.R.color.lib_title_base_title_color))
                .setFrontDark(false);
    }
}
