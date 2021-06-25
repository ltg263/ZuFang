package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.PickerViewUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineHtNew3Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_fyName)
    TextView mTvFyName;
    @BindView(R.id.tv_zklx)
    TextView mTvZklx;
    @BindView(R.id.tv_zjzp)
    TextView mTvZjzp;
    @BindView(R.id.et_xm)
    EditText mEtXm;
    @BindView(R.id.tv_xb)
    TextView mTvXb;
    @BindView(R.id.et_sjh)
    EditText mEtSjh;
    @BindView(R.id.tv_zjlx)
    TextView mTvZjlx;
    @BindView(R.id.et_zjhm)
    EditText mEtZjhm;
    @BindView(R.id.et_lxr)
    EditText mEtLxr;
    @BindView(R.id.et_lxtdh)
    EditText mEtLxtdh;
    @BindView(R.id.tv_qzsj)
    TextView mTvQzsj;
    @BindView(R.id.tv_zqsc)
    TextView mTvZqsc;
    @BindView(R.id.bnt)
    TextView mBnt;

    private List<String> listStr = new ArrayList<>();
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_3;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "新建合同");
        mTvFyName.setText("名字");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_zklx, R.id.tv_zjzp,R.id.tv_xb, R.id.tv_zjlx, R.id.tv_qzsj, R.id.tv_zqsc, R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_zklx:
                listStr.clear();
                listStr.add("租客类型1");
                listStr.add("租客类型2");
                listStr.add("租客类型3");
                listStr.add("租客类型4");
                PickerViewUtils.selectorCustom(this, listStr, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvZklx.setText(listStr.get(pos));
                    }
                });
                break;
            case R.id.tv_zjzp:
                break;
            case R.id.tv_xb:
                listStr.clear();
                listStr.add("男");
                listStr.add("女");
                PickerViewUtils.selectorCustom(this, listStr, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvXb.setText(listStr.get(pos));
                    }
                });
                break;
            case R.id.tv_zjlx:
                listStr.clear();
                listStr.add("证件类型1");
                listStr.add("证件类型2");
                PickerViewUtils.selectorCustom(this, listStr, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvZjlx.setText(listStr.get(pos));
                    }
                });
                break;
            case R.id.tv_qzsj:
                PickerViewUtils.selectorDate(this, new boolean[]{true, true, true, false, false, false}, new PickerViewUtils.GetTimeInterface() {
                    @Override
                    public void getTime(Date time) {
                        mTvQzsj.setText(time.toString());
                    }
                });
                break;
            case R.id.tv_zqsc:
                listStr.clear();
                listStr.add("租期时长1");
                listStr.add("租期时长2");
                PickerViewUtils.selectorCustom(this, listStr, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvXb.setText(listStr.get(pos));
                    }
                });
                break;
            case R.id.bnt:
                baseStartActivity(MineHtNew4Activity.class,null);
                break;
        }
    }
}
