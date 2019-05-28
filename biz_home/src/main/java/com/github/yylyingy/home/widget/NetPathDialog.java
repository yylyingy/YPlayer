package com.github.yylyingy.home.widget;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import com.github.yylyingy.home.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/28 11:56
 */
public class NetPathDialog extends AlertDialog {
    private DialogCallBack mCallBack;
    OnCancelListener mOnCancelListener;
    public NetPathDialog(@NonNull Context context) {
        super(context,R.style.net_path_dialog);
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.biz_home_dialog_net_path);
        if (getWindow() != null) {
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mCallBack) {
                    mCallBack.onConfirm();
                }
            }
        });
        findViewById(R.id.cannel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mCallBack) {
                    mCallBack.onCancel();
                }
                if (null != mOnCancelListener) {
                    mOnCancelListener.onCancel(NetPathDialog.this);
                }
            }
        });

    }

    @Override
    public void setOnCancelListener(@Nullable OnCancelListener listener) {
        mOnCancelListener = listener;
    }

    public void setDialogCallback(DialogCallBack callback) {
        mCallBack = callback;
    }

    public interface DialogCallBack {
        void onConfirm();
        void onCancel();
    }
}
