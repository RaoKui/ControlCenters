package com.yxkj.kzzx.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yxkj.kzzx.base.BaseFragment;
import com.yxkj.kzzx.contract.VideoContract;
import com.yxkj.kzzx.controlcenter.R;
import com.yxkj.kzzx.ijk.media.AndroidMediaController;
import com.yxkj.kzzx.ijk.media.IjkVideoView;
import com.yxkj.kzzx.presenter.VideoPresenter;

import butterknife.BindView;
import tv.danmaku.ijk.media.player.IMediaPlayer;

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
        btnChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choseFile();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = etVideoUrl.getText().toString().trim();
                playVideo(path);
            }
        });
    }

    private void playVideo(String path) {

        videoView.setVideoPath(path);
        videoView.setMediaController(new AndroidMediaController(getContext()));
        videoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                mp.start();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String path = uri.getPath().toString();
            // TODO 播放本地

            videoView.setVideoURI(uri);
            Log.d("uri", "onActivityResult: " + path);
            videoView.setMediaController(new AndroidMediaController(getContext()));

            videoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(IMediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }

    private void choseFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }
}
