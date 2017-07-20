package com.yxkj.kzzx.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yxkj.kzzx.base.BaseFragment;
import com.yxkj.kzzx.contract.VideoContract;
import com.yxkj.kzzx.controlcenter.R;
import com.yxkj.kzzx.ijk.media.IjkVideoView;
import com.yxkj.kzzx.presenter.VideoPresenter;

import butterknife.BindView;

/**
 * 播放视频
 * Created by RaoKui on 2017/7/20.
 */

public class VideoFragment extends BaseFragment<VideoContract.View, VideoPresenter> implements VideoContract.View {

    @BindView(R.id.video_view)
    IjkVideoView videoView;
    @BindView(R.id.btn_chose)
    Button btnChose;
    @BindView(R.id.btn_play)
    Button btnPlay;
    @BindView(R.id.et_video_url)
    EditText etVideoUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected VideoPresenter initPresenter() {
        return new VideoPresenter();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

}
