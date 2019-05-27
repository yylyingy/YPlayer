package com.github.yylyingy.common.widget.systemstatusbar;

import android.app.Activity;
import android.os.Build;

import com.github.yylyingy.common.widget.systemstatusbar.strategy.SystemBar19Strategy;
import com.github.yylyingy.common.widget.systemstatusbar.strategy.SystemBar21Strategy;
import com.github.yylyingy.common.widget.systemstatusbar.strategy.SystemBar23Strategy;

import androidx.annotation.NonNull;

/**
 * <br> ClassName:   SystemBarTool
 * <br> Description: 沉浸式状态栏的工具
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/27 10:30
 */
public class SystemBarTool {
    private static boolean SYSTEM_BAR_SWITCH = true;

    public static void setSystemBarSwitchOn(boolean on) {
        SYSTEM_BAR_SWITCH = on;
    }

    public static boolean setStatusBarColor(Activity activity, @NonNull SystemBarConfig config) {
        if (!SYSTEM_BAR_SWITCH) {
            return false;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return false;
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return new SystemBar19Strategy(activity,config).setStatusBar();
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return new SystemBar21Strategy(activity,config).setStatusBar();
        } else {
            return new SystemBar23Strategy(activity,config).setStatusBar();
        }
    }

    public static boolean setDarkStatusBar(Activity activity,boolean dark) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return false;
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return new SystemBar19Strategy(activity,new SystemBarConfig()).setDarkStatus(dark);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return new SystemBar21Strategy(activity,new SystemBarConfig()).setDarkStatus(dark);
        } else {
            return new SystemBar23Strategy(activity,new SystemBarConfig()).setDarkStatus(dark);
        }
    }
}
