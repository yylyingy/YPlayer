package com.github.yylyingy.startpage.view;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yylyingy.common.arouter.HomePage;
import com.github.yylyingy.common.arouter.StartPagerRouter;
import com.github.yylyingy.common.mvp.base.BaseActivity;
import com.github.yylyingy.startpage.R;

@Route(path = StartPagerRouter.START_PAGE_ROUTER,name = "启动页")
public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        ARouter.getInstance().build(HomePage.HOME_PAGE).navigation();
                    }
                });
            }

        }.start();

    }

}
