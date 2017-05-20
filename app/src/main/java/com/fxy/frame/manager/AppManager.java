package com.fxy.frame.manager;

import android.app.Activity;
import android.content.Context;
import com.fxy.frame.ui.BaseActivity;
import com.vise.log.ViseLog;
import java.util.Stack;


/**
 * Activity堆栈管理
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public class AppManager {
    public static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加 Activity
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public boolean isActivityExist(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束 Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束Activity
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 移除Activity
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束Activity
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }
    /**
     * 结束AllActivity
     *
     */
    public void finishAllActivity() {
        if (activityStack == null) return;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 结束AllActivity
     * @param exceptAct
     */
    public void finishAllActivity(BaseActivity exceptAct) {
        while (!activityStack.isEmpty()) {
            BaseActivity act = (BaseActivity) activityStack.pop();
            if (act != exceptAct) {
                act.finish();
            }
        }
        activityStack.push(exceptAct);
    }

    /**
     * app退出
     * @param context
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            ViseLog.e(e);
        }
    }

}
