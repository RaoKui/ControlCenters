package com.yxkj.kzzx.base;

/**
 * Created by RaoKui on 2017/7/12.
 */

public abstract class BasePresenter<V> {
    public V mView;

    public void attach(V mView) {
        this.mView = mView;
    }

    public void detach() {
        this.mView = null;
    }


}
