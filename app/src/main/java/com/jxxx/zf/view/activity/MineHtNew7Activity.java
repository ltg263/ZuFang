package com.jxxx.zf.view.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.MainActivity;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.view.DrawView;

import butterknife.BindView;
import butterknife.OnClick;

public class MineHtNew7Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.draw_view)
    DrawView mDrawView;
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_7;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "合同签名");

//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ){
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
//        }
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bnt_1,R.id.bnt_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_1:
                mDrawView.savek();
                baseStartActivity(MainActivity.class,null);
                break;
            case R.id.bnt_2:
                mDrawView.clearAll();
                break;
        }
    }
}
