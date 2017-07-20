package com.yxkj.kzzx.contract;

/**
 * Created by RaoKui on 2017/7/20.
 */

public interface AudioContract {

    interface View {

    }

    interface Presenter {

        void playNetAudio(String audio_url);

        void playLocalAudio(String audio_path);

        void playRoomAudio();

    }
}
