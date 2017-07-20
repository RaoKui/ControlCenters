package com.yxkj.kzzx.contract;

import android.content.Context;

import com.yxkj.kzzx.base.BaseView;

/**
 * Created by RaoKui on 2017/7/20.
 */

public interface PPTContract {

    interface View extends BaseView {

    }

    interface Presenter {
        void openWPS(Context context, String path);
    }
}
