package com.example.yuedong.library.views.home.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseFragment_;
import com.example.yuedong.library.utils.PaperUtils;
import com.example.yuedong.library.utils.SelectUtils;

import java.util.Date;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends BaseFragment_ {
    @BindView(R.id.button)
     Button button;
    @BindView(R.id.bttime)
     Button bttime;
    @BindView(R.id.btclTest)
     Button btclTest;
    private OptionsPickerView options;
    private OptionsPickerView.Builder obuilder;
    private TimePickerView.Builder builder;
    private TimePickerView pickerView;

    public static TwoFragment newInstance() {
        Bundle args = new Bundle();
        TwoFragment fragment = new TwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout(Bundle savedInstanceState) {
        return R.layout.fragment_two;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onEvent() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (obuilder == null)
                    obuilder = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {

                        }
                    });
                if (options == null) options = SelectUtils.showCityPicker(obuilder, "城市选择");
                options.setPicker(PaperUtils.getCityOptionOne(), PaperUtils.getCityOptionTwo(), PaperUtils.getCityOptionThree());
                if (!options.isShowing()) options.show();


            }
        });
        bttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (builder == null)
                    builder = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {

                        }
                    });
                if (pickerView == null) pickerView = SelectUtils.showTimePicker(builder, "时间选择");
                if (!pickerView.isShowing()) pickerView.show();
            }
        });
        btclTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/test/jump").withBoolean("key", true).navigation();
            }
        });
    }

}
