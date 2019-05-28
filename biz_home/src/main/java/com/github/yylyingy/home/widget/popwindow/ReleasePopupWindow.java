package com.github.yylyingy.home.widget.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.github.yylyingy.home.R;


/**
 * <br> ClassName:   ReleasePopupWindow
 * <br> Description: 发布信息弹窗
 * <br>
 * <br> Author:      yangyinglong
 * <br> Date:        2017/8/31 18:17
 */

public class ReleasePopupWindow extends PopupWindow implements View.OnClickListener{

    private int popupWidth;
    private int popupHeight;
    private View parentView;

    private LinearLayout mBtnReleaseCarSource;
    private LinearLayout mBtnReleaseFindCar;
    private ImageView mCloseWindow;
    private OnClickListener mListener;
    private Context mContext;
    public ReleasePopupWindow(Context context  ) {
        super(context);
        initView(context);
        setPopConfig();
        mContext = context;
    }
    /**
     *<br> Description: 初始化布局
     *<br> Author:      yangyinglong
     *<br> Date:        2017/9/1 9:54
     */
    private void initView(Context context) {
        parentView = View.inflate(context, R.layout.biz_home_pop_memu, null);
        setContentView(parentView);
    }

    /**
     *<br> Description: drawable 转bitmap
     *<br> Author:      yangyinglong
     *<br> Date:        2017/9/1 11:53
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {



        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }

    /**
     *
     * 配置弹出框属性
     * @version 1.0
     * @createAuthor
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     */
    private void setPopConfig() {
//        this.setContentView(mDataView);//设置要显示的视图
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xcc000000);
        this.setBackgroundDrawable(dw);
        this.setOutsideTouchable(true);// 设置外部触摸会关闭窗口

        //获取自身的长宽高
        parentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = parentView.getMeasuredHeight();
        popupWidth = parentView.getMeasuredWidth();
    }

    private int getScreenHeight(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
    /**
     * 设置显示在v上方(以v的左边距为开始位置)
     * @param v
     */
    public void showUp(View v) {
        //获取需要在其上方显示的控件的位置信息
//        int[] location = new int[2];
//        v.getLocationOnScreen(location);
//        Rect rect = new Rect();
//        v.getGlobalVisibleRect(rect);
////        DisplayMetrics displayMetrics = v.getResources().getDisplayMetrics();
////        int h = displayMetrics.heightPixels - rect.bottom;
////        int h2 = location[1] - rect.top;
//
//        int statusBarHeight1 = -1;
////获取status_bar_height资源的ID
//        int resourceId = v.getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP && resourceId > 0) {
//            //根据资源ID获取响应的尺寸值
//            statusBarHeight1 = v.getResources().getDimensionPixelSize(resourceId);
//            statusBarHeight1 = rect.top - statusBarHeight1;
//            setHeight(statusBarHeight1);//rect.top);
//        } else {
//            setHeight(rect.top);
//        }
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int screenWidth =  dm.widthPixels;
        setWidth(screenWidth * 3 / 5);
        showAsDropDown(v);
    }

    /**
     * 设置显示在v上方（以v的中心位置为开始位置）
     * @param v
     */
    public void showUp2(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //在控件上方显示
        showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }

    public void setListener(OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
    }

    public interface OnClickListener {
        /**
         *<br> Description: 点击事件回调
         *<br> Author:      yangyinglong
         *<br> Date:        2017/9/1 9:58
         * @param v view
         * @param path ARouter路径
         */
        void onClick(View v, String path);
    }
}
