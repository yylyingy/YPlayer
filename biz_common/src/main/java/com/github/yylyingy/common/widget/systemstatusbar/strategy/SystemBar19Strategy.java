package com.github.yylyingy.common.widget.systemstatusbar.strategy;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.github.yylyingy.common.widget.systemstatusbar.SystemBarConfig;
import com.github.yylyingy.common.widget.systemstatusbar.SystemBarTintManager;

import androidx.annotation.RequiresApi;

/**
 * <br> ClassName:   SystemBar19Strategy
 * <br> Description: 兼容api19的沉浸式状态栏
 * <br>1.api19如果不开启FullScreen模式，无法设置状态栏颜色
 * <br>2.api19不支持修改状态栏字体颜色，目前只有小米跟魅族的ROM兼容
 * <br>3.小米和魅族的状态栏颜色使用参数color，其他机型使用lollipopColor
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/27 10:30
 */
public class SystemBar19Strategy extends SystemBarBaseStrategy {

    public SystemBar19Strategy(Activity activity, SystemBarConfig config) {
        super(activity, config);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean setStatusBar() {
        if (!isFullScreen()) {
            return false;
        }

        setFitSystemWindow();

        Window win = getActivity().getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (isFullScreen()) winParams.flags |= bits;
        else winParams.flags &= ~bits;
        win.setAttributes(winParams);

        int color = getConfig().getColor();
        if (setDarkStatus(getConfig().isFrontDark())) {
            color = getConfig().getLollipopColor();
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(false);
        tintManager.setStatusBarTintColor(color);

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
