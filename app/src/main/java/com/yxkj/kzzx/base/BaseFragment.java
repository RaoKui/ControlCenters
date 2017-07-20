package com.yxkj.kzzx.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by RaoKui on 2017/7/12.
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    public T mPresenter;

    private Unbinder mUnbinder;
    /**
     * Fragment Content view
     */
    private View inflateView;

    /**
     * 所属Activity
     */
    private Activity activity;
    /**
     * 记录是否已经创建了,防止重复创建
     */
    private boolean viewCreated;

    /**
     * 通过ID查找控件
     *
     * @param viewId 控件资源ID
     * @param <VIEW> 泛型参数，查找得到的View
     * @return 找到了返回控件对象，否则返回null
     */
    final public <VIEW extends View> VIEW findViewById(@IdRes int viewId) {
        return (VIEW) inflateView.findViewById(viewId);
    }


    protected abstract int getLayoutId();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d("onActivityCreated");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Logger.d("onAttach");
        this.activity = activity;
    }

    @Override
    final public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        // 防止重复调用onCreate方法，造成在initData方法中adapter重复初始化问题
        if (!viewCreated) {
            viewCreated = true;
        }
    }

    @Nullable
    @Override
    final public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d("onCreateView");
        if (null == inflateView) {
            int layoutResId = getLayoutId();
            if (layoutResId > 0) {
                inflateView = inflater.inflate(getLayoutId(), container, false);
                mUnbinder = ButterKnife.bind(this, inflateView);
            }
            mPresenter = initPresenter();
            mPresenter.attach((V) this);
            // 解决点击穿透问题
            inflateView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
        return inflateView;
    }

    @Override
    final public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.d("onViewCreated");
        if (viewCreated) {
            initView(view, savedInstanceState);
            initData();
            initListener();
            viewCreated = false;
        }
    }

    protected abstract T initPresenter();

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mPresenter.detach();
    }
}

