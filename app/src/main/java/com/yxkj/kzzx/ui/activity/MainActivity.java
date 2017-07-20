package com.yxkj.kzzx.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.yxkj.kzzx.constant.ReceiverConstant;
import com.yxkj.kzzx.base.BaseActivity;
import com.yxkj.kzzx.contract.MainContract;
import com.yxkj.kzzx.controlcenter.R;
import com.yxkj.kzzx.presenter.MainPresenter;
import com.yxkj.kzzx.ui.fragment.AudioFragment;
import com.yxkj.kzzx.ui.fragment.PPTFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.View, MainPresenter> {

    @BindView(R.id.btn_open_ppt)
    Button btnOpenPPT;
    @BindView(R.id.btn_open_audio)
    Button btnOpenAudio;
    private PPTFragment pptFragment;
    private AudioFragment audioFragment;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        pptFragment = new PPTFragment();
        audioFragment = new AudioFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnOpenPPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!pptFragment.isAdded()) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.main, pptFragment).show(pptFragment).commit();
                } else {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.show(pptFragment).commit();
                }
            }
        });

        btnOpenAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!audioFragment.isAdded()) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.main, audioFragment).show(audioFragment).commit();
                } else {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.show(audioFragment).commit();
                }
            }
        });
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ReceiverConstant.WPS_FILE_CLOSE:
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.hide(pptFragment).commitAllowingStateLoss();
                    break;
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ReceiverConstant.WPS_FILE_CLOSE);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
