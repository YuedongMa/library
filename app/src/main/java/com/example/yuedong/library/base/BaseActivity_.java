package com.example.yuedong.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.yuedong.library.listener.QuestPermissionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mayuedong on 2017/10/25.
 */

public abstract class BaseActivity_ extends AppCompatActivity implements BaseView {
    protected static QuestPermissionListener listener;
    protected Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout(savedInstanceState));
        unbinder = ButterKnife.bind(this);
        initData();
        onEvent();
    }


    public abstract int initLayout(Bundle savedInstanceState);

    public abstract void initData();

    public abstract void onEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();

    }

    /**
     * 返回是否需要检查权限
     *
     * @param permission
     * @param context
     * @param listener
     */
    public static boolean toCheckPermission(String[] permission, Context context, QuestPermissionListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(context, permission[0]);
            if (checkCallPhonePermission != PackageManager.PERMISSION_DENIED) {
                return false;
            } else requestRuntimePermission((Activity) context,
                    permission,
                    listener);
            return true;
        }
        return false;
    }

    /**
     * 运行时请求权限
     *
     * @param activity
     * @param permissions
     * @param permissionListener
     */
    public static void requestRuntimePermission(Activity activity, String[] permissions, QuestPermissionListener permissionListener) {
        if (activity == null) {
            return;
        }
        List<String> requestPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions.add(permission);
            }
        }

        BaseActivity_.listener = permissionListener;
        if (!requestPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(activity, requestPermissions.toArray(new String[requestPermissions.size()]), 100);
        }
    }

    /**
     * 返回用户操作结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            for (int i = 0; i < grantResults.length; i++) {
                int granRequest = grantResults[i];
                if (granRequest == PackageManager.PERMISSION_DENIED) {
                    if (listener != null) {
                        listener.onPermissionRefuse();
                    }
                    return;
                }
            }
            listener.onPermissionAccept();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void disLoading() {

    }

    public void showShort(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 隐藏软键盘
     */
    public void hideInput(Activity context) {
        View view = context.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            boolean isOpen = imm.isActive();
            if (isOpen) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
