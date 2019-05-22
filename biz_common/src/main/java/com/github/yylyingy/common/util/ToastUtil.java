package com.github.yylyingy.common.util;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.yylyingy.common.R;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/22 12:00
 */
public class ToastUtil {
    private static SparseArray<PopupWindow> list = new SparseArray<>();
    private static Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                try {
                    PopupWindow mPopupWindow = list.get(msg.arg1);
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                    list.remove(msg.arg1);
                    mPopupWindow = null;
                } catch (Exception e) {
                }
            }
        }
    };

    public static void showResultToast(Activity mActivity, String info) {
        showResultToast(mActivity, info, R.drawable.lib_ic_success);
    }
    public static void showResultToast(Activity mActivity, String info, int iconId) {
        PopupWindow mPopupWindow = showToastDialog(mActivity, info, iconId);
        if (mPopupWindow != null) {
            list.put(mPopupWindow.hashCode(), mPopupWindow);
            Message message = new Message();
            message.arg1 = mPopupWindow.hashCode();
            message.what = 1;
            mHandler.sendMessageDelayed(message, 1500);
        }
    }

    private static PopupWindow showToastDialog(Activity mActivity, String info, int iconId) {
        if (mActivity == null || mActivity.isFinishing()) {
            return null;
        }
        try {
            PopupWindow mPopupWindow = new PopupWindow(mActivity);
            View view = LayoutInflater.from(mActivity).inflate(R.layout.lib_toast_dialog, null);
            mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupWindow.setFocusable(true);
            mPopupWindow.setContentView(view);
            ColorDrawable dw = new ColorDrawable(mActivity.getResources().getColor(android.R.color.transparent));
            mPopupWindow.setBackgroundDrawable(dw);
            mPopupWindow.showAtLocation(mActivity.findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
            ((ImageView) view.findViewById(R.id.ivIcon)).setImageResource(iconId);
            ((TextView) view.findViewById(R.id.tv_content)).setText(info);
            return mPopupWindow;
        } catch (Exception e) {
            return null;
        }
    }
}
