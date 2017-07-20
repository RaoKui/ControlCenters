package com.yxkj.kzzx.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.orhanobut.logger.Logger;
import com.yxkj.kzzx.base.BaseFragment;
import com.yxkj.kzzx.contract.PPTContract;
import com.yxkj.kzzx.controlcenter.R;
import com.yxkj.kzzx.presenter.PPTPresenter;

import butterknife.BindView;

/**
 * 打开ppt
 * Created by RaoKui on 2017/7/20.
 */

public class PPTFragment extends BaseFragment<PPTContract.View, PPTPresenter> {

    @BindView(R.id.btn_chose)
    Button btnChose;
    @BindView(R.id.btn_download)
    Button btnDownload;
    @BindView(R.id.et_file_name)
    EditText etFileName;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ppt;
    }

    @Override
    protected void initData() {
        Logger.d("initdata");

    }

    @Override
    protected PPTPresenter initPresenter() {
        return new PPTPresenter();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Logger.d("initview");

    }

    @Override
    protected void initListener() {
        btnChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choseFile();
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String file_name = etFileName.getText().toString().trim();
                String file_path = Environment.getExternalStorageDirectory().getPath() +"/"+ file_name;
                mPresenter.openWPS(getContext(), file_path);
            }
        });
    }

    private void choseFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                String path = uri.getPath().toString();
                mPresenter.openWPS(getContext().getApplicationContext(), path);
            }
        }
    }
}
