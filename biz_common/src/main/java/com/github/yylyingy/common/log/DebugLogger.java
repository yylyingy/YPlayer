package com.github.yylyingy.common.log;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * <br> ClassName:   LoggerImpl
 * <br> Description: 日志实现类
 * <br>
 * <br> Author:      hehaodong
 * <br> Date:        2017-05-31 15:59
 */
public class DebugLogger implements ILogger {
    
    /**
     *<br> Description:打印详细信息，自定义Tag方法
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 16:16
     * @param tag 标签
     * @param msg 日志信息
     */
    @Override
    public void v(String tag, String msg) {
        Log.v(tag, msg);
    }
    
    /**
     *<br> Description: 打印详细信息，自定义Tag方法，还有异常信息
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 16:19
     * @param tag 标签
     * @param msg 日志信息
     * @param tr 异常信息
     */
    @Override
    public void v(String tag, String msg, Throwable tr) {
        Log.v(tag, msg, tr);
    }
    
    /**
     *<br> Description: 打印debug信息，自定义Tag方法
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 16:20
     * @param tag 标签
     * @param msg 日志信息
     */
    @Override
    public void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    /**
     *<br> Description: 打印debug信息，自定义Tag方法，还有异常信息
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 16:21
     * @param tag 标签
     * @param msg 日志信息
     * @param tr 异常信息
     */
    @Override
    public void d(String tag, String msg, Throwable tr) {
        Log.d(tag, msg, tr);
    }

    /**
     *<br> Description: 打印信息，自定义Tag方法
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 16:21
     * @param tag 标签
     * @param msg 日志信息
     */
    @Override
    public void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    /**
     *<br> Description: 打印信息，自定义Tag方法，还有异常信息
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 16:21
     * @param tag 标签
     * @param msg 日志信息
     * @param tr 异常信息
     */
    @Override
    public void i(String tag, String msg, Throwable tr) {
        Log.i(tag, msg, tr);
    }

    /**
     *<br> Description: 打印警告信息，自定义Tag方法
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 16:21
     * @param tag 标签
     * @param msg 日志信息
     */
    @Override
    public void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    /**
     *<br> Description: 打印警告信息，自定义Tag方法，还有异常信息
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 16:21
     * @param tag 标签
     * @param msg 日志信息
     * @param tr 异常信息
     */
    @Override
    public void w(String tag, String msg, Throwable tr) {
        Log.w(tag, msg, tr);
    }

    /**
     *<br> Description: 打印错误信息，自定义Tag方法
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 16:21
     * @param tag 标签
     * @param msg 日志信息
     */
    @Override
    public void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    /**
     *<br> Description: 打印错误信息，自定义Tag方法，还有异常信息
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 16:21
     * @param tag 标签
     * @param msg 日志信息
     * @param tr 异常信息
     */
    @Override
    public void e(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
    }

    /**
     *<br> Description: JSON格式，打印信息，自定义tag
     *<br> Author:      hehaodong
     *<br> Date:        2017-05-31 17:31
     * @param tag 标签
     * @param msg 日志信息
     */
    @Override
    public void json(String tag, String msg) {
        Logger.t(tag).json(msg);
    }


}
