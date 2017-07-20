package com.yxkj.kzzx.presenter;

import android.content.Context;

import com.yxkj.kzzx.base.BasePresenter;
import com.yxkj.kzzx.contract.PPTContract;
import com.yxkj.kzzx.utils.OpenWPSHelper;

import java.util.zip.CheckedOutputStream;

/**
 * Created by RaoKui on 2017/7/20.
 */

public class PPTPresenter extends BasePresenter<PPTContract.View> implements PPTContract.Presenter {

    @Override
    public void openWPS(Context context, String path) {
        OpenWPSHelper.openFile(context, path);
    }
}
