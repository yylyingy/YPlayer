package com.github.yylyingy.play.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.yylyingy.common.arouter.PlayRouter;
import com.github.yylyingy.common.widget.media.IjkVideoView;
import com.github.yylyingy.play.R;

@Route(path = PlayRouter.PLAY_ROUTE,name = "播放")
public class PlayActivity extends AppCompatActivity {

    IjkVideoView mIjkVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz_play_activity_play);
        mIjkVideoView = findViewById(R.id.play_view);
    }
}
