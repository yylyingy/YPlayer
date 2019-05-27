package com.github.yylyingy.common.grant;

/**
 * <br> ClassName:   OnPermissionCallback
 * <br> Description: 申请回调类
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/27 10:30
 */
public interface OnPermissionCallback {


    /**
     *<br> Description: 允许
     * <br> Author:      Yangyl
     * <br> Date:        2019/5/27 10:30
     */
    void onRequestAllow(String... permissionName);

    /**
     *<br> Description: 拒绝
     * <br> Author:      Yangyl
     * <br> Date:        2019/5/27 10:30
     */
    void onRequestRefuse(String... permissionName);


    /**
     *<br> Description: 不在询问
     * <br> Author:      Yangyl
     * <br> Date:        2019/5/27 10:30
     */
    void onRequestNoAsk(String... permissionName);

}
