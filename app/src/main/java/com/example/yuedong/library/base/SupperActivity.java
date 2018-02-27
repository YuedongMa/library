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

import com.example.yuedong.library.base.basepresenter.BaseView;
import com.example.yuedong.library.listener.QuestPermissionListener;
import com.example.yuedong.library.utils.UtilManager;
import com.vondear.rxtools.SLoadingTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by YuedongMa on 2018/2/27.
 */

public abstract class SupperActivity extends AppCompatActivity implements BaseView {
    private SLoadingTool loadingTool;
    protected static QuestPermissionListener listener;
    protected Unbinder unbinder;
    protected UtilManager $;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout(savedInstanceState));
        unbinder = ButterKnife.bind(this);
        if ($ == null) $ = UtilManager.getInstance(this.getApplicationContext());
        if (loadingTool == null) loadingTool = new SLoadingTool(this);
        initSupperData(savedInstanceState);
    }

    public abstract int initLayout(Bundle savedInstanceState);

    public abstract void initSupperData(Bundle bundle);
    public abstract void onActivityDestroy();

    @Override
    public void showLoading() {
        loadingTool.show();
    }

    @Override
    public void disLoading() {
        loadingTool.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
        if (loadingTool != null) loadingTool = null;
        onActivityDestroy();

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
