package com.example.yuedong.library.views.home;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseActivity_;
import com.vondear.rxtools.SLoadingTool;
import com.vondear.rxtools.SThreadPoolTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.popupwindows.tools.RxPopupView;

import java.io.File;

import butterknife.BindView;
import ha.excited.BigNews;

public class DAndPActivity extends BaseActivity_ {

    private ProgressBar loadding;

    // 成功
    private static final int WHAT_SUCCESS = 1;
    // 失败
    private static final int WHAT_FAIL_PATCH = 0;
    /**
     * 老版本apk
     */
    private String oldApkDir;
    /**
     * 新版本apk
     */
    private String newApkDir;
    /**
     * 根据新老版本差分包生成的新版本apk
     */
    private String hasUpdateApkDir;
    /**
     * 新老版本的增量更新包
     */
    private String oldToNewPatchDir;
    @BindView(R.id.rx_title)
    RxTitle title;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.ll)
    RelativeLayout ll;

    @Override
    public int initLayout(Bundle savedInstanceState) {
        return R.layout.activity_dandp;
    }

    @Override
    public void initData() {
        showLoading();
        title.setLeftFinish(this);
        // title.getLlLeft().setBackgroundResource(R.drawable.bg_titlebar_press);
        loadding = (ProgressBar) findViewById(R.id.loadding);
        oldApkDir = getApplicationContext().getPackageResourcePath();
        newApkDir = Environment.getExternalStorageDirectory().getPath() + File.separator + "new.apk";
        hasUpdateApkDir = getsdpath() + "hasUpdateApkDir.apk";
        oldToNewPatchDir = getsdpath() + "patch.patch";
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                disLoading();
            }
        }, 2000);
    }

    int i = 0;

    @Override
    public void onEvent() {

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (i == 0) {
//                    $.showToast("Normal");
//                } else if (i == 1) {
//                    $.showSuccess("Success");
//                } else if (i == 2) {
//                    $.showError("Error");
//                } else if (i == 3) {
//                    $.showInfo("Info");
//                } else if (i == 4) {
//                    $.showWarn("Warn");
//                }
//                i += 1;
            }
        });
    }

    private String getsdpath() {
        return Environment.getExternalStorageDirectory().getPath() + File.separator + getApplicationContext().getPackageName() + File.separator;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(getApplicationContext(), "复制成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), "复制失败", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "生成增新包成功", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(), "生成增新包失败", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(getApplicationContext(), "合成新版本成功", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    Toast.makeText(getApplicationContext(), "合成新版本失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 生成差分增量包
     *
     * @param view
     */
    public void bsdiff(View view) {

        loadding.setVisibility(View.VISIBLE);
        new DiffTask().execute();
    }

    /**
     * 合成新版本
     *
     * @param view
     */
    public void bspatch(View view) {

        loadding.setVisibility(View.VISIBLE);
        new PatchTask().execute();
    }

    /**
     * 安装新版本
     *
     * @param view
     */
    public void installNew(View view) {
        install(hasUpdateApkDir);
    }

    /**
     * 生成差分包线程
     *
     * @author yuyuhang
     * @date 2016-1-25 下午12:24:34
     */
    private class DiffTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {

            try {
                boolean result = BigNews.diff(oldApkDir, newApkDir, oldToNewPatchDir);
                if (result) {
                    handler.obtainMessage(2).sendToTarget();
                    return WHAT_SUCCESS;
                } else {
                    handler.obtainMessage(3).sendToTarget();
                    return WHAT_FAIL_PATCH;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return WHAT_FAIL_PATCH;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            loadding.setVisibility(View.GONE);
        }
    }

    /**
     * 差分包合成APK线程
     *
     * @author yuyuhang
     * @date 2016-1-25 下午12:24:34
     */
    private class PatchTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {

            try {
                boolean result = BigNews.make(oldApkDir, hasUpdateApkDir, oldToNewPatchDir);
                //  int result = PatchUtils.getInstance().patch(oldApkDir, hasUpdateApkDir, oldToNewPatchDir);
                if (result) {
                    handler.obtainMessage(4).sendToTarget();
                    return WHAT_SUCCESS;
                } else {
                    handler.obtainMessage(5).sendToTarget();
                    return WHAT_FAIL_PATCH;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return WHAT_FAIL_PATCH;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            loadding.setVisibility(View.GONE);
        }
    }


    /**
     * 安装软件
     *
     * @param dir
     */
    private void install(String dir) {
        Uri uri;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT > 23) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(this, "com.example.yuedong.library.fileprovider", new File(dir));
        } else {
            uri = Uri.parse("file://" + dir);
        }
        intent.setDataAndType(uri,
                "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
