package com.github.yylyingy.play.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yylyingy.common.arouter.PlayRouter;
import com.github.yylyingy.common.constant.AppConstants;
import com.github.yylyingy.common.mvp.base.BaseActivity;
import com.github.yylyingy.common.util.VideoFD;
import com.github.yylyingy.play.R;



public class OtherAppPlay extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz_play_activity_other_app_play);
        Intent intent = getIntent();
        VideoFD videoFD = new VideoFD();
        videoFD.setUri(intent.getData());
        ARouter.getInstance().build(PlayRouter.PLAY_ROUTE).withParcelable(
                AppConstants.ARG_ONE,videoFD
        ).navigation(this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                finish();
            }
        });
    }
}
