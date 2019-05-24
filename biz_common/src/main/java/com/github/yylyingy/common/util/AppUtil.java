package com.github.yylyingy.common.util;

import android.app.Application;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/23 8:24
 */
public class AppUtil {
    private static Application application;
    public static void init(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("application must not be null.");
        }
        AppUtil.application = application;
        /*synchronized (AppUtil.class) {
            if (DEFAULT_APP != null) {
                throw new IllegalStateException("DEFAULT_APP already exists.");
            }
            DEFAULT_APP = application;
        }*/
    }

    public static Application getContext() {
        if (application != null) {
            return application;
        }
        throw new IllegalStateException("Must init first!");
    }
}
