package com.github.yylyingy.common.mvp.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.github.yylyingy.common.R;
import com.github.yylyingy.common.widget.systemstatusbar.SystemBarConfig;
import com.github.yylyingy.common.widget.systemstatusbar.SystemBarTool;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/22 17:24
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initSystemStatusBar();

    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initSystemStatusBar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initSystemStatusBar();
    }
    public void initSystemStatusBar() {
        SystemBarTool.setStatusBarColor(this, getStatusBarConfig());
    }

    protected SystemBarConfig getStatusBarConfig() {
        return new SystemBarConfig()
                .setColor(getResources().getColor(R.color.lib_title_base_background))
                .setLollipopColor(getResources().getColor(R.color.lib_title_base_title_color))
                .setFrontDark(true);
    }
}
