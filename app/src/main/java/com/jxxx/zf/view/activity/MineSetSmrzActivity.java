package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 实名认证
 * 编号：6140
 */
public class MineSetSmrzActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.et_sgmm_jmm)
    EditText mEtSgmmJmm;
    @BindView(R.id.et_sgmm_xmm)
    EditText mEtSgmmXmm;
    @BindView(R.id.tv_sgsjh_qr)
    TextView mTvSgsjhQr;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_set_smrz;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "实名认证");
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.tv_sgsjh_qr})
    public void onViewClicked(View view) {
        String jmm = mEtSgmmJmm.getText().toString();
        String xmm = mEtSgmmXmm.getText().toString();

        switch (view.getId()) {
            case R.id.tv_sgsjh_qr:
                if (StringUtil.isBlank(jmm) || StringUtil.isBlank(xmm)) {
                    ToastUtils.showLong("请输入完整信息");
                    return;
                }
                if(!StringUtil.checkNum(xmm)){
                    ToastUtils.showLong("请输入有效的证件号码");
                    return;
                }
                Intent mIntent = new Intent(this,MineSetSmrz2Activity.class);
                mIntent.putExtra("smrz_name",jmm);
                mIntent.putExtra("smrz_number",xmm);
                startActivity(mIntent);

                break;
        }
    }


}
