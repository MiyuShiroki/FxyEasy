package com.fxy.frame.permission;


/**
 * 申请权限回调
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public interface OnPermissionCallback {
    //允许
    void onRequestAllow(String permissionName);
    //拒绝
    void onRequestRefuse(String permissionName);
    //不在询问
    void onRequestNoAsk(String permissionName);
}
