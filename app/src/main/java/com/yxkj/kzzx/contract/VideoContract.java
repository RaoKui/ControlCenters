package com.yxkj.kzzx.contract;

/**
 * Created by RaoKui on 2017/7/20.
 */

public interface VideoContract {

    interface View {

    }

    interface Presenter {
        void playNetVideo(String video_url);

        void playLocalVideo(String video_path);

        void playRoomVideo();
    }

}
