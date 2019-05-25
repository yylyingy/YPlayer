package com.github.yylyingy.play.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.yylyingy.common.arouter.PlayRouter;
import com.github.yylyingy.common.constant.AppConstants;
import com.github.yylyingy.common.mediafilter.entity.VideoFile;
import com.github.yylyingy.common.mvp.base.BaseActivity;
import com.github.yylyingy.common.widget.media.AndroidMediaController;
import com.github.yylyingy.common.widget.media.IjkVideoView;
import com.github.yylyingy.play.R;

@Route(path = PlayRouter.PLAY_ROUTE,name = "播放")
public class PlayActivity extends BaseActivity {
    VideoFile mVideoFile;
    IjkVideoView mIjkVideoView;
    private AndroidMediaController mMediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz_play_activity_play);
        mIjkVideoView = findViewById(R.id.play_view);
        if (null == savedInstanceState) {
            mVideoFile = getIntent().getParcelableExtra(AppConstants.ARG_ONE);
        } else {
            mVideoFile = savedInstanceState.getParcelable(AppConstants.ARG_ONE);
        }
        if (null == mVideoFile) {
            Toast.makeText(this, R.string.biz_play_video_path_problem, Toast.LENGTH_SHORT).show();
            finish();
        }
        mMediaController = new AndroidMediaController(this, false);
        mIjkVideoView.setMediaController(mMediaController);
        mIjkVideoView.setVideoPath(mVideoFile.getPath());
        mIjkVideoView.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(AppConstants.ARG_ONE,mVideoFile);
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
    }
}
