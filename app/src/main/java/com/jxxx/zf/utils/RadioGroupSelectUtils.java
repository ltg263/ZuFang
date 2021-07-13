package com.jxxx.zf.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jxxx.zf.R;
import com.jxxx.zf.utils.view.CustomPopWindow;
import com.jxxx.zf.view.activity.MineZfSelectActivity;

public class RadioGroupSelectUtils {

    public CustomPopWindow mCustomPopWindow1,mCustomPopWindow2,mCustomPopWindow3,mCustomPopWindow4;

    public void setOnChangeListener(Activity mContext , RadioGroup mMRadioGroup, RadioButton mRbHomeSelect1, RadioButton mRbHomeSelect2,
                                           RadioButton mRbHomeSelect3, RadioButton mRbHomeSelect4,DialogInterface mDialogInterface){
        mMRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home_select1:
//                        if (mCustomPopWindow1 == null)
//                            mCustomPopWindow1 = showDistancePopup(mContext,mRbHomeSelect1,0);
//                        else {
//                            mCustomPopWindow1.showAsDropDown(mRbHomeSelect1);
//                        }
                        break;
                    case R.id.rb_home_select2:
                        if (mCustomPopWindow2 == null)
                            mCustomPopWindow2 = showDistancePopup(mContext,mRbHomeSelect2,1,mDialogInterface);
                        else {
                            mCustomPopWindow2.showAsDropDown(mRbHomeSelect2);
                        }
                        break;
                    case R.id.rb_home_select3:
                        if (mCustomPopWindow3 == null)
                            mCustomPopWindow3 = showDistancePopup(mContext,mRbHomeSelect3,2,mDialogInterface);
                        else {
                            mCustomPopWindow3.showAsDropDown(mRbHomeSelect3);
                        }
                        break;
                    case R.id.rb_home_select4:
//                        if (mCustomPopWindow4 == null)
//                            mCustomPopWindow4 = showDistancePopup(mContext,mRbHomeSelect4,3);
//                        else {
//                            mCustomPopWindow4.showAsDropDown(mRbHomeSelect4);
//                        }
                        mContext.startActivity(new Intent(mContext,MineZfSelectActivity.class));
                        break;
                }
            }
        });
    }

    private CustomPopWindow showDistancePopup(Activity mContext, RadioButton mRbHomeSelect,int pos,DialogInterface mDialogInterface) {
        View view = mContext.getLayoutInflater().inflate(R.layout.pop_home_select, null, false);
        RadioGroup rgb_sort_fs = view.findViewById(R.id.rgb_sort_fs);
        RadioGroup rgb_sort_zj = view.findViewById(R.id.rgb_sort_zj);
        rgb_sort_fs.setVisibility(View.GONE);
        rgb_sort_zj.setVisibility(View.GONE);
        if(pos==1){
            rgb_sort_fs.setVisibility(View.VISIBLE);
        }
        if(pos==2){
            rgb_sort_zj.setVisibility(View.VISIBLE);
        }
        //创建并显示popWindow
        CustomPopWindow mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                .setView(view)
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mRbHomeSelect.setChecked(false);
                    }
                })
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()//创建PopupWindow
                .showAsDropDown(mRbHomeSelect, 0, 0);//显示PopupWindow
        // 设置按钮的点击事件
        CustomPopWindow finalMCustomPopWindow = mCustomPopWindow;
        rgb_sort_fs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb11:
                        mRbHomeSelect.setText("不限");
                        finalMCustomPopWindow.dissmiss();
                        break;
                    case R.id.rb12:
                        mRbHomeSelect.setText("整租");
                        finalMCustomPopWindow.dissmiss();
                        break;
                    case R.id.rb13:
                        mRbHomeSelect.setText("合租");
                        finalMCustomPopWindow.dissmiss();
                        break;
                }
                mDialogInterface.btnConfirm(mRbHomeSelect.getText().toString());
            }
        });
        rgb_sort_zj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb21:
                        mRbHomeSelect.setText("不限 ");
                        finalMCustomPopWindow.dissmiss();
                        break;
                    case R.id.rb22:
                        mRbHomeSelect.setText("500元以下");
                        finalMCustomPopWindow.dissmiss();
                        break;
                    case R.id.rb23:
                        mRbHomeSelect.setText("500-1000元");
                        finalMCustomPopWindow.dissmiss();
                        break;
                    case R.id.rb24:
                        mRbHomeSelect.setText("1000-1500元");
                        finalMCustomPopWindow.dissmiss();
                        break;
                    case R.id.rb25:
                        mRbHomeSelect.setText("1500-2000元");
                        finalMCustomPopWindow.dissmiss();
                        break;
                    case R.id.rb26:
                        mRbHomeSelect.setText("2000-3000元");
                        finalMCustomPopWindow.dissmiss();
                        break;
                    case R.id.rb27:
                        mRbHomeSelect.setText("3000-5000元");
                        finalMCustomPopWindow.dissmiss();
                        break;
                    case R.id.rb28:
                        mRbHomeSelect.setText("5000元以上");
                        finalMCustomPopWindow.dissmiss();
                        break;
                }
                mDialogInterface.btnConfirm(mRbHomeSelect.getText().toString());
            }
        });
        return mCustomPopWindow;
    }

    public interface DialogInterface {
        /**
         * 确定
         */
        public void btnConfirm(String str);
    }

}
