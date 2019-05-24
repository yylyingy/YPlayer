package com.github.yylyingy.common.widget.systemstatusbar.strategy;

import android.app.Activity;
import android.os.Build;
import android.view.View;

import com.github.yylyingy.common.widget.systemstatusbar.SystemBarConfig;

import androidx.annotation.RequiresApi;

/**
 * <br> ClassName:   SystemBar21Strategy
 * <br> Description: 兼容api21与api22的沉浸式状态栏
 * <br>1.api21与api22不支持修改状态栏字体颜色，目前只有小米跟魅族的ROM兼容
 * <br>2.小米和魅族的状态栏颜色使用参数color，其他机型使用lollipopColor
 * <br> Author:      yexiaochuan
 * <br> Date:        2018/4/27 17:37
 */
public class SystemBar21Strategy extends SystemBarBaseStrategy {

    public SystemBar21Strategy(Activity activity, SystemBarConfig config) {
        super(activity, config);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean setStatusBar() {
        int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        if (isFullScreen()) {
            option = option | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            setFitSystemWindow();
        }
        getActivity().getWindow().getDecorView().setSystemUiVisibility(option);
        int color = getConfig().getColor();
        if (setDarkStatus(getConfig().isFrontDark())) {
            color = getConfig().getLollipopColor();
        }

        getActivity().getWindow().setStatusBarColor(color);
        return true;
    }

    @Override
    public boolean setDarkStatus(boolean dark) {
        boolean result = !MIUISetStatusBarDarkMode(getActivity().getWindow(),dark)
                && !FlymeSetStatusBarDarkMode(getActivity().getWindow(),dark);
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
        return result;

    }
}
