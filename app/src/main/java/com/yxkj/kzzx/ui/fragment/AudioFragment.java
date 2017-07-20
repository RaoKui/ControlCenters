package com.yxkj.kzzx.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.orhanobut.logger.Logger;
import com.yxkj.kzzx.base.BaseFragment;
import com.yxkj.kzzx.contract.AudioContract;
import com.yxkj.kzzx.controlcenter.R;
import com.yxkj.kzzx.presenter.AudioPresenter;
import com.yxkj.kzzx.ui.widget.MyAudioPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 播放音频
 * Created byRaoKui  on 2017/7/20.
 */

public class AudioFragment extends BaseFragment<AudioContract.View, AudioPresenter> implements AudioContract.View {


    @BindView(R.id.btn_chose)
    Button btnChose;
    @BindView(R.id.btn_play)
    Button btnPlay;
    @BindView(R.id.et_audio_url)
    EditText etAudioUrl;
    @BindView(R.id.seekBar)
    SeekBar seekBar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_audio;
    }

    @Override
    protected void initData() {
        Logger.d("initdata");

    }

    @Override
    protected AudioPresenter initPresenter() {
        return new AudioPresenter();
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

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = etAudioUrl.getText().toString().trim();
                MyAudioPlayer audioPlayer = new MyAudioPlayer(url, seekBar);
                audioPlayer.start();
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
                mPresenter.playLocalAudio(path);
            }
        }
    }

}
