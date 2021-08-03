package com.jxxx.zf.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.web)
    WebView mWeb;


    @Override
    public int intiLayout() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        setWebViewClient();
        String type = getIntent().getStringExtra("type");
        setToolbar(myToolbar, "快递协议");
        setWebViewClient();
//        mWeb.loadUrl("https://elastech.nbqichen.com/zhongkeyan/html/download/");
        verifyAudioPermissions(this);
        mWeb.loadUrl("http://csms.nbyjdz.com/employee/#/index?appId=1&userId=463217267497238528&nickname=tom");
    }
    private static final int GET_RECODE_AUDIO = 1;
    private static String[] PERMISSION_AUDIO = {
            Manifest.permission.RECORD_AUDIO
    };
    /*
     * 申请录音权限*/
    public static void verifyAudioPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_AUDIO,
                    GET_RECODE_AUDIO);
        }
    }
    @Override
    public void initData() {

    }

    private void setWebViewClient() {
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持js交互，可以点击网页中按钮链接
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持js可以打开新的页面
        webSettings.setSupportZoom(true);//是否可以缩放，默认true
        webSettings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setAppCacheEnabled(false);//是否使用缓存
        webSettings.setDomStorageEnabled(true);//DOM Storage


//        mWeb.setOnTouchScreenListener(new MyWebView.OnTouchScreenListener() {
//
//            @Override
//            public void onTouchScreen() {
//                Log.w("-->>","++++++");
////                isFlowing = true;
//                if (mRl.getVisibility() == View.GONE) {
//                    mRl.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onReleaseScreen() {
////                isFlowing = false;
//                Log.w("-->>","--->>");
//                if (mRl.getVisibility() == View.VISIBLE) {
//                    mRl.setVisibility(View.GONE);
//                }
//            }
//        });
    }

}