package com.example.yuedong.library.listener.activitymanager;

import android.app.Activity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Activity 管理类
 *
 *
 */

public class ActivityManager {

    private static ActivityManager sManager;

    /**
     * 存放 Activity
     */
    private List<Activity> mActivityList = null;

    private ActivityManager(){
        mActivityList = Collections.synchronizedList(new LinkedList<Activity>());
    }

    public static synchronized ActivityManager getInstance(){
        if (sManager == null){
            sManager = new ActivityManager();
        }
        return sManager;
    }

    public List<Activity> getAllActivity(){
        return mActivityList;
    }

    public void addActivity(Activity activity){
        if (mActivityList == null || mActivityList.isEmpty()){
            mActivityList = Collections.synchronizedList(new LinkedList<Activity>());
        }
        mActivityList.add(activity);
    }

    public void removeActivity(Activity activity){
        if (null == mActivityList && mActivityList.isEmpty()){
            return;
        }
        if (mActivityList.contains(activity)){
            mActivityList.remove(activity);
        }
    }

    /**
     * 获取当前 Activity
     *
     * @return
     */
    public Activity getCurActivity(){
        if (mActivityList == null || mActivityList.isEmpty()){
            return null;
        }
        return mActivityList.get(mActivityList.size() - 1);
    }

    public void finishCurActivity(){
        if (mActivityList == null || mActivityList.isEmpty()){
            return;
        }
        finishActivity(mActivityList.get(mActivityList.size() - 1));
    }

    /**
     * finish 指定 Activity
     *
     * @param activity
     */
    private void finishActivity(Activity activity) {
        if (mActivityList == null || mActivityList.isEmpty()){
            return;
        }
        if (activity != null){
            mActivityList.remove(activity);
            activity.finish();
        }
    }

    /**
     * finish 指定类名 Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls){
        if (mActivityList == null || mActivityList.isEmpty()){
            return;
        }
        for (int i = 0; i < mActivityList.size(); i++) {
            if (mActivityList.get(i).getClass().getName().equals(cls.getName())){
                finishActivity(mActivityList.get(i));
            }
        }
    }

    /**
     * 根据类名获取 Activity
     *
     * @param cls
     * @return
     */
    public Activity getTargetActivity(Class<?> cls){
        Activity targetActivity = null;
        if (mActivityList != null){
            for (Activity activity : mActivityList) {
                if (activity.getClass().equals(cls)){
                    targetActivity = activity;
                    break;
                }
            }
        }
        return targetActivity;
    }

    /**
     * 获取栈顶 Activity
     *
     * @return
     */
    public Activity getTopActivity(){
        Activity topActivity = null;
        synchronized (mActivityList) {
            if (mActivityList.size() - 1 < 0){
                return null;
            }
            topActivity = mActivityList.get(mActivityList.size() - 1);
        }
        return topActivity;
    }

    /**
     * 结束所有 Activity
     */
    public void finishAllActivity(){
        if (mActivityList == null || mActivityList.isEmpty()){
            return;
        }
        for (int i = 0; i < mActivityList.size(); i++) {
            if (null != mActivityList.get(i)){
                mActivityList.get(i).finish();
            }
        }
        mActivityList.clear();
    }

    public void clearList(){
        if (mActivityList == null || mActivityList.isEmpty()){
            return;
        }
        mActivityList.clear();
    }

}
