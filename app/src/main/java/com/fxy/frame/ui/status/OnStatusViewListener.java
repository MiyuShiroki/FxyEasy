package com.fxy.frame.ui.status;

import android.view.View;

/**
 * 状态视图显示监听
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public interface OnStatusViewListener {
    void onShowView(View view, int id);

    void onHideView(View view, int id);
}
