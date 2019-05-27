package com.github.yylyingy.common.widget.systemstatusbar.strategy;

import android.app.Activity;
import android.os.Build;
import android.view.View;

import com.github.yylyingy.common.widget.systemstatusbar.SystemBarConfig;

import androidx.annotation.RequiresApi;

/**
 * <br> ClassName:   SystemBar23Strategy
 * <br> Description: api23以上兼容沉浸式状态栏
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/27 10:30
 */
public class SystemBar23Strategy extends SystemBarBaseStrategy {
    public SystemBar23Strategy(Activity activity, SystemBarConfig config) {
        super(activity, config);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean setStatusBar() {
        int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        if (isFullScreen()) {
            option = option | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            setFitSystemWindow();
        }

        //小米机型需要使用定制方法设置状态栏字体颜色
//        MIUISetStatusBarDarkMode(getActivity().getWindow(),getConfig().isFrontDark());
//        FlymeSetStatusBarDarkMode(getActivity().getWindow(),getConfig().isFrontDark());
        setDarkStatus(getConfig().isFrontDark());

        if (getConfig().isFrontDark()) {
            option = option | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        getActivity().getWindow().getDecorView().setSystemUiVisibility(option);

        getActivity().getWindow().setStatusBarColor(getConfig().getColor());
        return false;
    }

    @Override
    public boolean setDarkStatus(boolean dark) {
//        //小米机型需要使用定制方法设置状态栏字体颜色
        MIUISetStatusBarDarkMode(getActivity().getWindow(),dark);
        FlymeSetStatusBarDarkMode(getActivity().getWindow(),dark);
        //状态栏字体图标颜色
        View decor = getActivity().getWindow().getDecorView();
        if (dark) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //浅色状态栏(字体图标白色)
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //contentView 全屏(置于statusbar之下)
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
        return true;
    }
}
