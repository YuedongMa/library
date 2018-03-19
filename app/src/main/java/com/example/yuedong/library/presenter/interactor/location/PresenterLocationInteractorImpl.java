package com.example.yuedong.library.presenter.interactor.location;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.yuedong.library.R;
import com.example.yuedong.library.base.SupperActivity;
import com.example.yuedong.library.config.LocationConfig;
import com.example.yuedong.library.utils.PaperUtils;
import com.example.yuedong.library.utils.SelectUtils;
import com.example.yuedong.library.views.home.adapters.SlideFragmentAdapter;
import com.example.yuedong.library.views.home.fragments.OneFragment;
import com.example.yuedong.library.views.home.fragments.TwoFragment;
import com.example.yuedong.library.widget.bottombar.W_BottomBar;
import com.example.yuedong.library.widget.bottombar.W_BottomBarTab;
import com.example.yuedong.library.widget.bottombar.W_ViewPager;
import com.example.yuedong.library.widget.dialog.CommentDialog;

import java.util.Date;

/**
 * 本地事件交互类，将处理结果回调给Presenter层
 * Created by YuedongMa on 2018/2/26.
 */

public class PresenterLocationInteractorImpl extends BaseLocationInteractorCallBack implements IPresenterLocationInteractor {
    private OptionsPickerView cityOptions;
    private OptionsPickerView.Builder cityBuilder;
    private TimePickerView.Builder timeBuilder;
    private TimePickerView timePickerView;

    @Override
    public void onCitySelected(Context context, final onLocaionEventFinishListener listener) {
        if (cityBuilder == null)
            cityBuilder = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    callBackSuccessResult(listener, options1 + "", KEY_ONCITYSELECTED);
                }
            });
        if (cityOptions == null) cityOptions = SelectUtils.showCityPicker(cityBuilder, "城市选择");
        cityOptions.setPicker(PaperUtils.getCityOptionOne(), PaperUtils.getCityOptionTwo(), PaperUtils.getCityOptionThree());
        if (!cityOptions.isShowing()) cityOptions.show();
    }

    @Override
    public void onTimeSelected(Context context, final onLocaionEventFinishListener listener) {
        if (timeBuilder == null)
            timeBuilder = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    callBackSuccessResult(listener, date, KEY_ONTIMESELECTED);
                }
            });
        if (timePickerView == null)
            timePickerView = SelectUtils.showTimePicker(timeBuilder, "时间选择");
        if (!timePickerView.isShowing()) timePickerView.show();
    }

    @Override
    public void initBottomMenu(Context context, Fragment[] fragments, int[] icon, String[] titles, final W_BottomBar bottomBar, final W_ViewPager viewPager, boolean isCanSlide, final onLocaionEventFinishListener listener) {
        for (int i = 0; i < fragments.length; i++) {
            bottomBar.addItem(new W_BottomBarTab(context, icon[i], titles[i], fragments[i]));
        }
        viewPager.setCanSlide(isCanSlide);
        viewPager.setAdapter(new SlideFragmentAdapter(((SupperActivity) context).getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.setCurrentItem(position);
                callBackSuccessResult(listener, position, KEY_BOTTOM_MENU_SELECTED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomBar.setOnTabSelectedListener(new W_BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                viewPager.setCurrentItem(position);
                callBackSuccessResult(listener, position, KEY_BOTTOM_MENU_SELECTED);

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public void showCommentDialog(Context context, String content, String hint, final onLocaionEventFinishListener listener) {
      final CommentDialog dialog=((SupperActivity)context).$.showCommentDilog(context,content,hint);
      dialog.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              dialog.dismiss();
              callBackSuccessResult(listener,dialog,KEY_SHOW_COMMENT_DIALOG);

          }
      });
    }
}
