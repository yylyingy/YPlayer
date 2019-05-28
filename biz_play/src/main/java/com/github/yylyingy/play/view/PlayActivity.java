package com.github.yylyingy.play.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

@Route(path = PlayRouter.PLAY_ROUTE,name = "播放")
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
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        videoFD = intent.getParcelableExtra(AppConstants.ARG_ONE);
        startPlay();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(AppConstants.ARG_ONE,videoFD);
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
        LoggerManager.d(TAG,"destory");
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
