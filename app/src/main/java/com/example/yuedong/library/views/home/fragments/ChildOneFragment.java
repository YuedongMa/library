package com.example.yuedong.library.views.home.fragments;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseFragment_;
import com.example.yuedong.library.listener.QuestPermissionListener;
import com.example.yuedong.library.utils.FileUtils;
import com.example.yuedong.library.views.home.MainActivity;
import com.example.yuedong.library.views.home.activitys.SlideHomeActivity;
import com.example.yuedong.library.widget.dialog.CommentDialog;
import com.project.fixbug.IFixbug;
import com.project.fixbug.TestBug;
import com.vondear.rxtools.model.ActionItem;
import com.vondear.rxtools.view.popupwindows.RxPopupSingleView;
import com.vondear.rxtools.view.rippleview.MyView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import dalvik.system.DexClassLoader;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildOneFragment extends BaseFragment_ {

    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.parent_layout)
    RelativeLayout parentLayout;
    public static ChildOneFragment newInstance() {
        Bundle args = new Bundle();
        ChildOneFragment fragment = new ChildOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout(Bundle savedInstanceState) {
        return R.layout.fragment_child_one;
    }

    @Override
    protected void initData(Bundle bundle) {
        String s = TestBug.showBug();
        try {
            Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.text_view, R.id.parent_layout,R.id.mSLideMenu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_view:

                final CommentDialog view1 = $.showCommentDilog(getActivity(), R.layout.dialog_sure_false);
                view1.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view1.dismiss();
                    }
                });
//              $.showSelectedPop(view,new String[]{"标清","高清","超清"}).setItemOnClickListener(new RxPopupSingleView.OnItemOnClickListener() {
//                  @Override
//                  public void onItemClick(ActionItem actionItem, int i) {
//                      $.showToast(actionItem.mTitle.toString());
//                  }
//              });
                  getSupperActivity(). toCheckPermission(new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, getActivity(), new QuestPermissionListener() {
                      @Override
                      public void onPermissionAccept() {
                         $.showToast("ok");
                      }

                      @Override
                      public void onPermissionRefuse() {
                          $.showToast("refuse");
                      }
                  });
                break;
            case R.id.parent_layout:
                break;
            case R.id.mSLideMenu:
              startActivity(new Intent(getActivity(),SlideHomeActivity.class));
                 break;
        }
    }

    void getDex() {
        File cashFile = FileUtils.getCacheDir(getActivity());
        String path = cashFile.getAbsolutePath() + File.separator + "fixbug.dex";
        File desFile = new File(path);
        try {
            if (!desFile.exists()) {
                desFile.createNewFile();

            }
            FileUtils.copyFiles(getActivity(), "fixbug_dex.jar", desFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DexClassLoader dexClassLoader = new DexClassLoader(path, cashFile.getAbsolutePath(), null, getActivity().getClassLoader());
        try {
            Class c = dexClassLoader.loadClass("com.project.fixbug.FixBugImpl");
            Log.e("sdfsfsfsfs", "c===" + c);
            IFixbug fixbug = (IFixbug) c.newInstance();
            Log.e("sdfsfsfsfs", "c===" + fixbug);
            if (fixbug != null) {
                $.showToast(fixbug.showMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("sdfsfsfsfs", "c===" + e.getMessage());
        }

    }
}
