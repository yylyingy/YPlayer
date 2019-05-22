package com.github.yylyingy.yplayer;

import com.alibaba.android.arouter.launcher.ARouter;

import androidx.multidex.MultiDexApplication;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/22 9:10
 */
public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (com.github.yylyingy.yplayer.BuildConfig.DEBUG) {
                ARouter.openLog();     // ARouter打印日志
            ARouter.openDebug();   // ARouter开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }
}
