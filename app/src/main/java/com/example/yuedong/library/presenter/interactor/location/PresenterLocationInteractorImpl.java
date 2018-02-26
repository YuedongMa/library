package com.example.yuedong.library.presenter.interactor.location;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.yuedong.library.config.LocationConfig;
import com.example.yuedong.library.utils.PaperUtils;
import com.example.yuedong.library.utils.SelectUtils;

import java.util.Date;

/**
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
}
