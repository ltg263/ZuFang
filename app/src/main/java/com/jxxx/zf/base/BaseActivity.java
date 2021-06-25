package com.jxxx.zf.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.utils.StatusBarUtil;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.utils.view.LoadingDialog;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2018/8/25.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    private LoadingDialog mLoading;

    protected String tag = "";

    protected Bundle savedInstanceState;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);
        //设置布局
        setTheme(R.style.AppTheme);//恢复原有的样式
        setContentView(intiLayout());
        ButterKnife.bind(this);
        if(!getLocalClassName().contains("MineHtNew7Activity")){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
        }

        //初始化控件
        initView();
        //设置数据
        initData();
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int intiLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();
    public void setToolbar(Toolbar mToolbar, String title) {
        this.setToolbar(mToolbar, title, true,null);
    }
    public void setToolbar(Toolbar mToolbar, String title,String titleYou) {
        this.setToolbar(mToolbar, title, true,titleYou);
    }

    public void setToolbar(Toolbar mToolbar, String title, Boolean isBack,String titleYou) {
        TextView mViewToolBarTitle = mToolbar.findViewById(R.id.toolbar_title);
        mViewToolBarTitle.setText(title);
        if(StringUtil.isNotBlank(titleYou)){
            TextView tvYou = mToolbar.findViewById(R.id.tv_you);
            tvYou.setVisibility(View.VISIBLE);
            tvYou.setText(titleYou);
            tvYou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTitleRightClickListener();
                }
            });
        }
        if (isBack) {
            mToolbar.setNavigationIcon(R.mipmap.icon_common_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    protected void onTitleRightClickListener(){

    };

    public void readyGoActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void showLoading() {
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.show();
        } else {
            mLoading = LoadingDialog.show(this, R.string.loading_text, false, null);
        }
    }

    public void showLoading(String name) {
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.show();
        } else {
            mLoading = LoadingDialog.show(this, name, false, null);
        }
    }


    public void hideLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }

    /**
     * 点击屏幕隐藏软键盘方法
     * @return
     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if (isShouldHideInput(v, ev)) {
//
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                    //处理Editext的光标隐藏、显示逻辑
//                    v.clearFocus();
//                }
//            }
//            return super.dispatchTouchEvent(ev);
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        if (getWindow().superDispatchTouchEvent(ev)) {
//            return true;
//        }
//        return onTouchEvent(ev);
//    }
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    protected void baseStartActivity(Class<?> cls,String str){
        Intent mIntent = new Intent(this,cls);
        if(StringUtil.isNotBlank(str)){
            mIntent.putExtra("str",str);
        }
        startActivity(mIntent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
    }

}
