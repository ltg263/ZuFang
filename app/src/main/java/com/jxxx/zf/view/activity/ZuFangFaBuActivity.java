package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.ImageUrlBean;
import com.jxxx.zf.utils.PictureSelectorUtils;
import com.jxxx.zf.view.adapter.AddImageAdapter;
import com.jxxx.zf.view.adapter.ZuFangFaBuXxTextAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ZuFangFaBuActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.photoRv)
    RecyclerView photoRv;

    List<ImageUrlBean> imgListTop = new ArrayList<>();
    List<ImageUrlBean> imgListB = new ArrayList<>();
    @BindView(R.id.rv_list_qtss)
    RecyclerView mRvListQtss;
    @BindView(R.id.rv_list_fwcx)
    RecyclerView mRvListFwcx;
    @BindView(R.id.rv_list_fkfs)
    RecyclerView mRvListFkfs;
    @BindView(R.id.rv_list_fwtp)
    RecyclerView mRvListFwtp;
    private AddImageAdapter addImageAdapterTop,addImageAdapterB;
    ZuFangFaBuXxTextAdapter mAdapter_jcss,mAdapter_fkfs,mAdapter_fwcx;


    private final String[] list_fkcx = new String[]{"东" ,"南","西","北"};
    private final String[] list_jcss = new String[]{"电视", "洗衣服","冰箱","床","WIFI","空调","沙发","热水器","电梯","阳台","桌子"};
    private final String[] list_fkfs = new String[]{"押一付一" ,"押一付三","季付","年付"};

    private boolean isTop = true;
    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_fa_bu;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "录入房源");
        mAdapter_fwcx = new ZuFangFaBuXxTextAdapter(Arrays.asList(list_fkcx));
        mRvListFwcx.setAdapter(mAdapter_fwcx);
        mAdapter_fwcx.setOnItemClickListener((adapter, view, position) -> {
//                mAdapter_fwcx.notifyDataSetChanged();
            ((TextView)view).setTextColor(getResources().getColor(R.color.white));
            ((TextView)view).setBackground(getResources().getDrawable(R.drawable.circle_solid_theme_25));
        });

        mAdapter_jcss = new ZuFangFaBuXxTextAdapter(Arrays.asList(list_jcss));
        mRvListQtss.setAdapter(mAdapter_jcss);
        mAdapter_jcss.setOnItemClickListener((adapter, view, position) -> {
//                mAdapter_jcss.notifyDataSetChanged();
            ((TextView)view).setTextColor(getResources().getColor(R.color.white));
            ((TextView)view).setBackground(getResources().getDrawable(R.drawable.circle_solid_theme_25));
        });

        mAdapter_fkfs = new ZuFangFaBuXxTextAdapter(Arrays.asList(list_fkfs));
        mRvListFkfs.setAdapter(mAdapter_fkfs);

        mAdapter_fkfs.setOnItemClickListener((adapter, view, position) -> {
            ((TextView)view).setTextColor(getResources().getColor(R.color.white));
            ((TextView)view).setBackground(getResources().getDrawable(R.drawable.circle_solid_theme_25));
        });
        imgListTop.add(null);
        addImageAdapterTop = new AddImageAdapter(imgListTop);
        photoRv.setAdapter(addImageAdapterTop);

        addImageAdapterTop.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(addImageAdapterTop.getData().get(position)==null){
                    isTop = true;
                    PictureSelectorUtils.selectImage(ZuFangFaBuActivity.this,5);
                }
            }
        });

        addImageAdapterTop.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    addImageAdapterTop.remove(position);
                    imgListTop.add(null);
                    addImageAdapterTop.notifyDataSetChanged();
                } catch (Exception e) {
                }
            }
        });

        imgListB.add(null);
        addImageAdapterB = new AddImageAdapter(imgListB);
        mRvListFwtp.setAdapter(addImageAdapterB);

        addImageAdapterB.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(addImageAdapterB.getData().get(position)==null){
                    isTop = false;
                    PictureSelectorUtils.selectImage(ZuFangFaBuActivity.this,10);
                }
            }
        });

        addImageAdapterB.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    addImageAdapterB.remove(position);
                    imgListB.add(null);
                    addImageAdapterB.notifyDataSetChanged();
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    public void initData() {

    }


    int imgc = 1;
    int x = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);

//                    addImageAdapter.setNewData(selectList);
//                    imageList.addAll(selectList);
//                    addImageAdapter.notifyDataSetChanged();
//                    if (selectList.size() != 0) {
//                        showLoading("上传图片中...");
//                        imgc = selectList.size();
//                        x = 0;
//                    }
                    for (LocalMedia media : selectList) {
                        String imagepath = media.getCompressPath();
                        if(isTop){
                            imgListTop.add(imgListTop.size()-1,new ImageUrlBean(imagepath));
                        }else{
                            imgListB.add(imgListB.size()-1,new ImageUrlBean(imagepath));
                        }
//                        uploadImage(imagepath);
                    }
                    if(isTop){
                        if(imgListTop.size()>=6){
                            imgListTop.remove(null);
                        }
                        addImageAdapterTop.notifyDataSetChanged();
                    }else{
                        if(imgListB.size()>=11){
                            imgListB.remove(null);
                        }
                        addImageAdapterB.notifyDataSetChanged();
                    }

                    break;
                default:
            }
        }
    }

    private void uploadImage(final String imgPath) {
        File file = new File(imgPath);//访问手机端的文件资源，保证手机端sdcdrd中必须有这个文件
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file1", file.getName(), requestFile);

//        Map<String, RequestBody> map = new HashMap<>();
//        int fileType = 2;
//        map.put("fileType", RequestBody.create(null, String.valueOf(fileType)));

//        RetrofitManager.build().uploadImage(body)
//                .compose(RxSchedulers.<BaseData<String>>compose())
//                .as(AutoDispose.<BaseData<String>>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
//                .subscribe(
//                        new BaseObserver<String>() {
//                            @Override
//                            public void onHandleSuccess(BaseData<String> t) throws Exception {
//                                imgList.add(new ImageUrlBean(t.getData()));
//                                addImageAdapter.notifyDataSetChanged();
//                                ++x;
//                                if (x == imgc)
//                                    hideLoading();
//                            }
//
//                            @Override
//                            public void onHandleError(String msg) {
//                                hideLoading();
//                                super.onHandleError(msg);
//                            }
//                        }
//                );
    }

}
