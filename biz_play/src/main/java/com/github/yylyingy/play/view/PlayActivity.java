package com.github.yylyingy.play.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yylyingy.common.arouter.PlayRouter;
import com.github.yylyingy.common.constant.AppConstants;
import com.github.yylyingy.common.log.LoggerManager;
import com.github.yylyingy.common.mediafilter.entity.VideoFile;
import com.github.yylyingy.common.mvp.base.BaseActivity;
import com.github.yylyingy.common.util.VideoFD;
import com.github.yylyingy.common.widget.media.AndroidMediaController;
import com.github.yylyingy.common.widget.media.IjkVideoView;
import com.github.yylyingy.play.R;

@Route(path = PlayRouter.PLAY_ROUTE, name = "播放")
public class PlayActivity extends BaseActivity {
    IjkVideoView mIjkVideoView;
    private AndroidMediaController mMediaController;
    VideoFD videoFD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz_play_activity_play);
        mIjkVideoView = findViewById(R.id.play_view);
        if (null == savedInstanceState) {
            videoFD = getIntent().getParcelableExtra(AppConstants.ARG_ONE);
        } else {
            videoFD = savedInstanceState.getParcelable(AppConstants.ARG_ONE);
        }
        startPlay();
        mIjkVideoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
                return false;
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        videoFD = intent.getParcelableExtra(AppConstants.ARG_ONE);
        startPlay();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(AppConstants.ARG_ONE, videoFD);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIjkVideoView.stopPlayback();
        LoggerManager.d(TAG, "destory");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onBackPressed() {
        if (mMediaController.isShowing()) {
            mMediaController.hide();
        } else {
            super.onBackPressed();
        }
    }

    private void startPlay() {
        if (null == videoFD) {
            Toast.makeText(this, R.string.biz_play_video_path_problem, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        mIjkVideoView.stopPlayback();
        mMediaController = new AndroidMediaController(this, false);
        mIjkVideoView.setMediaController(mMediaController);
        if (!TextUtils.isEmpty(videoFD.getPath())) {
            mIjkVideoView.setVideoPath(videoFD.getPath());
        } else {
            mIjkVideoView.setVideoURI(videoFD.getUri());
        }
        mIjkVideoView.start();
    }
}
