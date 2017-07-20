package com.yxkj.kzzx.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by RaoKui on 2017/7/11.
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    public T mPresenter;

    protected final String TAG = this.getClass().getSimpleName();

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);
        init();
        initData();
        initListener();
        Logger.i("onCreate: ");
    }

    protected abstract T initPresenter();

    protected abstract int getLayoutId();

    protected abstract void init();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    protected void onStart() {
        super.onStart();
        Logger.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "onDestroy: ");
        mUnbinder.unbind();
        mPresenter.detach();
    }


}
