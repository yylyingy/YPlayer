package com.github.yylyingy.common.grant.core;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * <br> ClassName:   PermissionRequestFactory
 * <br> Description: 权限工厂类
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/27 10:30
 */
public class PermissionRequestFactory {


    public static IPermissionRequest create(Activity activity) {
        return create(new WeakReference<>(activity));
    }

    /**
     * 启用弱应用，暂时保留Activity强引用的构造函数
     * @param weakReference
     * @return
     */
    public static IPermissionRequest create(WeakReference<Activity> weakReference) {
        return new PermissionRequestImpl(weakReference);
    }

}
