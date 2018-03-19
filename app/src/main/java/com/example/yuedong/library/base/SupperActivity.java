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

import com.example.yuedong.library.R;
import com.example.yuedong.library.base.basepresenter.BaseView;
import com.example.yuedong.library.listener.QuestPermissionListener;
import com.example.yuedong.library.utils.UtilManager;
import com.vondear.rxtools.SLoadingTool;
import com.vondear.rxtools.view.RxTitle;

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
    public UtilManager $;

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

    protected void initTitle(RxTitle title) {
        title.setLeftFinish(this);
        title.getLlLeft().setBackgroundResource(R.drawable.bg_back_press);
    }

    protected void initTitle(RxTitle title, boolean showLeft) {
        if (showLeft) {
            title.setLeftFinish(this);
            title.getLlLeft().setBackgroundColor(R.drawable.bg_back_press);
        }
    }

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
    public boolean toCheckPermission(String[] permission, Context context, QuestPermissionListener listener) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> requestPermissions = new ArrayList<>();
            for (int i = 0; i < permission.length; i++) {
                int checkCallPhonePermission = ContextCompat.checkSelfPermission(context, permission[i]);
                if (checkCallPhonePermission != PackageManager.PERMISSION_DENIED) {
                    return false;
                } else {
                    if (ActivityCompat.checkSelfPermission(context, permission[i]) == PackageManager.PERMISSION_DENIED) {
                        requestPermissions.add(permission[i]);
                    }
                }
            }
            SupperActivity.listener = listener;
            if (!requestPermissions.isEmpty()) {
                ActivityCompat.requestPermissions(SupperActivity.this, requestPermissions.toArray(new String[requestPermissions.size()]), 100);
                return true;
            }
        }
        return false;
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
