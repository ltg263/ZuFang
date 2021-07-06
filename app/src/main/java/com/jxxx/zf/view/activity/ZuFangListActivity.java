package com.jxxx.zf.view.activity;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.RadioGroupSelectUtils;
import com.jxxx.zf.utils.view.CustomPopWindow;
import com.jxxx.zf.view.adapter.HomeFyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ZuFangListActivity extends BaseActivity {

    @BindView(R.id.rb_home_select1)
    RadioButton mRbHomeSelect1;
    @BindView(R.id.rb_home_select2)
    RadioButton mRbHomeSelect2;
    @BindView(R.id.rb_home_select3)
    RadioButton mRbHomeSelect3;
    @BindView(R.id.rb_home_select4)
    RadioButton mRbHomeSelect4;
    @BindView(R.id.mRadioGroup)
    RadioGroup mMRadioGroup;
    @BindView(R.id.rv_home_list)
    RecyclerView mRvHomeList;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;


    private HomeFyAdapter mHomeFyAdapter;



    @Override
    public int intiLayout() {
        return R.layout.activity_zufang_list;
    }

    @Override
    public void initView() {
        iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        new RadioGroupSelectUtils().setOnChangeListener(this,mMRadioGroup,mRbHomeSelect1,mRbHomeSelect2,mRbHomeSelect3,mRbHomeSelect4);
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mHomeFyAdapter = new HomeFyAdapter(null);
        mRvHomeList.setAdapter(mHomeFyAdapter);

        mHomeFyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }


}



