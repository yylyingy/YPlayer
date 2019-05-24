package com.github.yylyingy.startpage.view;


import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yylyingy.common.arouter.HomePage;
import com.github.yylyingy.common.arouter.StartPagerRouter;
import com.github.yylyingy.common.grant.callback.CommonPermissionCallBack;
import com.github.yylyingy.common.grant.core.PermissionRequestFactory;
import com.github.yylyingy.common.mvp.base.BaseActivity;
import com.github.yylyingy.startpage.R;


@Route(path = StartPagerRouter.START_PAGE_ROUTER,name = "启动页")
public class SplashScreenActivity extends BaseActivity {

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
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                ARouter.getInstance().build(HomePage.HOME_PAGE).navigation(SplashScreenActivity.this,
                                        new NavCallback() {
                                            @Override
                                            public void onArrival(Postcard postcard) {
                                                finish();
                                            }
                                        });
                            }
                        });
                    }

                }.start();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        requestPermission();
    }

    private void animStart(View view) {

    }

}
