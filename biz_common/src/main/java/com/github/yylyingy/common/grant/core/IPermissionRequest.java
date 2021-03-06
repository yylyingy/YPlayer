package com.github.yylyingy.common.grant.core;

import com.github.yylyingy.common.grant.OnPermissionCallback;
import com.github.yylyingy.common.grant.PermissionResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * <br> ClassName:   IPermissionRequest
 * <br> Description: 权限请求的接口
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/27 10:30
 */
public interface IPermissionRequest {

    /**
     *<br> Description: 添加一个请求的权限
     * <br> Author:      Yangyl
     * <br> Date:        2019/5/27 10:30
     */
    IPermissionRequest addPermission(String permission);

    /**
     *<br> Description: 添加多个请求的权限
     * <br> Author:      Yangyl
     * <br> Date:        2019/5/27 10:30
     */
    IPermissionRequest addPermission(String... permissions);

    /**
     *<br> Description: 设置CallBack
     * <br> Author:      Yangyl
     * <br> Date:        2019/5/27 10:30
     */
    IPermissionRequest addPermissionCallBack(OnPermissionCallback callback);

   /**
    *<br> Description: 进行请求，并监听结果
    * <br> Author:      Yangyl
    * <br> Date:        2019/5/27 10:30
    */
    void request(OnPermissionCallback callback);

    /**
     *<br> Description: 进行请求
     * <br> Author:      Yangyl
     * <br> Date:        2019/5/27 10:30
     */
    void request();

   /**
    *<br> Description: 进行请求，并将请求结果用Observable发射
    * <br> Author:      Yangyl
    * <br> Date:        2019/5/27 10:30
    */
    Observable<List<PermissionResult>> observable();
}
