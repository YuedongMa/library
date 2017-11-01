package com.example.yuedong.library.views.home.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.yuedong.library.R;
import com.example.yuedong.library.models.MyModel;

@Route(path = "/test/jump")
public class JumpResultActivity extends AppCompatActivity {
    @Autowired
    boolean key;
@Autowired
    MyModel modle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_result);
        ARouter.getInstance().inject(this);

    }
}
