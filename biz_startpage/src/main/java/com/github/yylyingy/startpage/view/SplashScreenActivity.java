package com.github.yylyingy.startpage.view;


import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yylyingy.common.arouter.HomePage;
import com.github.yylyingy.common.arouter.StartPagerRouter;
import com.github.yylyingy.common.grant.callback.CommonPermissionCallBack;
import com.github.yylyingy.common.grant.core.PermissionRequestFactory;
import com.github.yylyingy.common.mvp.base.BaseActivity;
import com.github.yylyingy.common.util.ScreenUtil;
import com.github.yylyingy.common.widget.systemstatusbar.SystemBarTool;
import com.github.yylyingy.startpage.R;


@Route(path = StartPagerRouter.START_PAGE_ROUTER,name = "启动页")
public class SplashScreenActivity extends BaseActivity {
    AnimatorSet mAnimationSet = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        darkStatusBar();
        setContentView(R.layout.biz_start_page_activity_splash_screen);
        requestPermission();
    }

    private void requestPermission() {
        PermissionRequestFactory.create(this).addPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).request(new CommonPermissionCallBack(this, true) {
            @Override
            protected void onSettingDialogCancel() {
                super.onSettingDialogCancel();
                finish();
            }

            @Override
            public void onRequestAllow(String... strings) {
                animStart(findViewById(R.id.yplayer_layout));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        requestPermission();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mAnimationSet) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mAnimationSet.pause();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mAnimationSet) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mAnimationSet.resume();
            }
        }
    }

    private void animStart(View view) {
        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(view, "scaleX"
                , 1.0f, 0.98f);
        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(view, "scaleY"
                , 1.0f, 0.98f);
        ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(view, "scaleX"
                , 0.98f, 2f);
        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(view, "scaleY"
                , 0.98f, 2f);
        ObjectAnimator pvhAlpha = ObjectAnimator.ofFloat(view,"alpha", 1f, 0f);
        AnimatorSet scaleSmall = new AnimatorSet();
        scaleSmall.playTogether(scaleX1,scaleY1);
        scaleSmall.setDuration(300);
        AnimatorSet scaleBig = new AnimatorSet();
        scaleBig.setDuration(1000);
        scaleBig.playTogether(scaleX2,scaleY2,pvhAlpha);
        AnimatorSet mAnimatorSetBack = new AnimatorSet();
        mAnimationSet = mAnimatorSetBack;
        mAnimatorSetBack.setInterpolator(new AccelerateInterpolator());
        mAnimatorSetBack.playSequentially(scaleSmall,scaleBig);
        mAnimatorSetBack.start();
        mAnimatorSetBack.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ARouter.getInstance().build(HomePage.HOME_PAGE).navigation(SplashScreenActivity.this,
                        new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                                finish();
                            }
                        });
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
