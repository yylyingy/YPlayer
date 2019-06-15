package com.github.yylyingy.home.view;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yylyingy.common.arouter.HomePage;
import com.github.yylyingy.common.arouter.PlayRouter;
import com.github.yylyingy.common.constant.AppConstants;
import com.github.yylyingy.common.grant.callback.CommonPermissionCallBack;
import com.github.yylyingy.common.grant.core.PermissionRequestFactory;
import com.github.yylyingy.common.log.LoggerManager;
import com.github.yylyingy.common.mediafilter.MediaFileFilter;
import com.github.yylyingy.common.mediafilter.callback.FilterResultCallback;
import com.github.yylyingy.common.mediafilter.entity.Directory;
import com.github.yylyingy.common.mediafilter.entity.VideoFile;
import com.github.yylyingy.common.mvp.base.BaseActivity;
import com.github.yylyingy.common.util.VideoFD;
import com.github.yylyingy.common.widget.systemstatusbar.SystemBarConfig;
import com.github.yylyingy.home.R;
import com.github.yylyingy.home.R2;
import com.github.yylyingy.home.adapter.VideoFolderRecycleAdapter;
import com.github.yylyingy.home.adapter.VideoRecycleAdapter;
import com.github.yylyingy.home.widget.NetPathDialog;
import com.github.yylyingy.home.widget.popwindow.ReleasePopupWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
/***
 *
 *
 *                                                    __----~~~~~~~~~~~------___
 *                                   .  .   ~~//====......          __--~ ~~
 *                   -.            \_|//     |||\\  ~~~~~~::::... /~
 *                ___-==_       _-~o~  \/    |||  \\            _/~~-
 *        __---~~~.==~||\=_    -_--~/_-~|-   |\\   \\        _/~
 *    _-~~     .=~    |  \\-_    '-~7  /-   /  ||    \      /
 *  .~       .~       |   \\ -_    /  /-   /   ||      \   /
 * /  ____  /         |     \\ ~-_/  /|- _/   .||       \ /
 * |~~    ~~|--~~~~--_ \     ~==-/   | \~--===~~        .\
 *          '         ~-|      /|    |-~\~~       __--~~
 *                      |-~~-_/ |    |   ~\_   _-~            /\
 *                           /  \     \__   \/~                \__
 *                       _--~ _/ | .-~~____--~-/                  ~~==.
 *                      ((->/~   '.|||' -_|    ~~-/ ,              . _||
 *                                 -_     ~\      ~~---l__i__i__i--~~_/
 *                                 _-~-__   ~)  \--______________--~~
 *                               //.-~~~-~_--~- |-------~~~~~~~~
 *                                      //.-~~~--\
 *                               神兽保佑
 *                              代码无BUG!
 */
@Route(path = HomePage.HOME_PAGE,name = "主页")
public class MainActivity extends BaseActivity {
    SharedPreferences sp ;
    TextView mTvTitle;
    View mMemu;
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
        mMemu = findViewById(R.id.memu);
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
        mMemu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReleasePopupWindow popupWindow = new ReleasePopupWindow(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.biz_home_pop_memu,null);
                popupWindow.setContentView(view);
                popupWindow.showUp(v);
                view.findViewById(R.id.net_path).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        NetPathDialog netPathDialog = new NetPathDialog(MainActivity.this);
                        netPathDialog.show();
                        netPathDialog.setDialogCallback(new NetPathDialog.DialogCallBack() {
                            @Override
                            public void onConfirm() {
                                EditText mUrlEdit = netPathDialog.findViewById(R.id.net_path);
                                if (!TextUtils.isEmpty(mUrlEdit.getText())) {
                                    VideoFD videoFD = new VideoFD();
                                    videoFD.setPath(mUrlEdit.getText().toString());
                                    ARouter.getInstance().build(PlayRouter.PLAY_ROUTE).withParcelable(
                                            AppConstants.ARG_ONE,videoFD
                                    ).navigation();
                                } else {
                                    mUrlEdit.setError("请输入链接");
                                }
                            }

                            @Override
                            public void onCancel() {
                                netPathDialog.dismiss();
                            }
                        });
                    }
                });
            }
        });
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
                VideoFD videoFD = new VideoFD();
                videoFD.setPath(videoFile.getPath());
                ARouter.getInstance().build(PlayRouter.PLAY_ROUTE).withParcelable(
                        AppConstants.ARG_ONE,videoFD
                ).navigation();
            }
        });
        sp = getSharedPreferences("tt", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("56535e61-7709-4e03-b6e4-bcaf1d02833e",true);
        edit.putBoolean("366f2a25-8920-43f6-accc-3ae3630369e6",true);
        edit.putBoolean("84215d09-bd64-4fcd-93fe-5d8e3b838445",true);
        edit.apply();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0;i < 100000;i ++) {
//                    SharedPreferences sp = getSharedPreferences("tt", MODE_PRIVATE);
//                    SharedPreferences.Editor edit = sp.edit();
//                    edit.putBoolean(UUID.randomUUID().toString(), false);
//                    edit.commit();
//                }
//            }
//        }).start();
    }

    private void requestReadPermission() {
        PermissionRequestFactory.create(this).addPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).request(new CommonPermissionCallBack(this, true) {

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
                List<Directory> videoFiles = mVideoFolderRecycleAdapter.getDataSet();
                for (int i = 0;i < directories.size();i ++) {
                    for (Directory directory : videoFiles) {
                        if (directories.get(i).equals(directory)) {
                            directories.remove(i);
                            i --;
                            break;
                        }
                    }
                }
                list.addAll(videoFiles);
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
