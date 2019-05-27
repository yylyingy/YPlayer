package com.github.yylyingy.common.grant.callback;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.Html;

import com.github.yylyingy.common.grant.PermissionUtils;
import com.github.yylyingy.common.util.ToastUtil;
import com.github.yylyingy.common.widget.dialog.CommonTipDialog;

import java.util.Arrays;

/**
 * <br> ClassName:   CommonPermissionCallBack
 * <br> Description: 团贷网定制权限CallBack
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/27 10:30
 */
public class CommonPermissionCallBack extends AbstractOnPermissionCallBack {

    private Activity mActivity;
    private DialogInterface.OnKeyListener mListener;
    public CommonPermissionCallBack(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

    public CommonPermissionCallBack(Activity activity, boolean showRefuseDialog) {
        super(activity, showRefuseDialog);
        this.mActivity = activity;
    }

    public CommonPermissionCallBack(Activity activity, boolean showRefuseDialog, DialogInterface.OnKeyListener listener) {
        super(activity, showRefuseDialog);
        this.mActivity = activity;
        this.mListener = listener;
    }


    @Override
    public void onRequestAllow(String... permissionName) {

    }

    @Override
    public void onRequestRefuse(String... permissionName) {
        if (mShowRefuseDialog) {
            String message = PermissionUtils.getStringBuilder(Arrays.asList(permissionName))+"权限已被拒绝,去设置中打开";
            showDialog(message);
        } else {
            ToastUtil.showResultToast(mActivity,PermissionUtils.getStringBuilder(Arrays
                    .asList(permissionName)) + "权限申请失败!");
        }
    }

    @Override
    public void onRequestNoAsk(String... permissionName) {
        String message = PermissionUtils.getStringBuilder(Arrays.asList(permissionName))
                + "权限已关闭,去设置中打开";
        showDialog(message);

    }
    /**
     *<br> Description: 弹出Dialog
     * <br> Author:      Yangyl
     * <br> Date:        2019/5/27 10:30
     */
    private void  showDialog(String message)  {
        if (mActivity == null || mActivity.isFinishing()) {
            return;
        }
      CommonTipDialog dialog=  new CommonTipDialog(mActivity).init(true,false)
                .setContent(message)
                .setConfirm("确定")
                .setCancel(Html.fromHtml("<font color=\"#999999\">取消</font>"))
                .setOnClickListener(new CommonTipDialog.OnConfirmListener() {
                    @Override
                    public void clickConfirm() {
                        PermissionUtils.openPermissionSettings(mActivity,mActivity.getPackageName());
                    }

                    @Override
                    public void clickCancel() {
                        onSettingDialogCancel();
                    }
                });
       if (mListener != null) {
           dialog.setOnKeyListener(mListener);
       }
    }

    public void setOnKeyListener(DialogInterface.OnKeyListener mListener) {
        this.mListener = mListener;
    }
}
