package com.example.group.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.group.R;
import com.example.group.base.BaseRecAdapter;
import com.example.group.base.BaseRecViewHolder;
import com.example.group.widget.MyVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 视频列表
 */
public class View8Fragment extends Fragment {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private ArrayList<String> urlList;
    private ListVideoAdapter videoAdapter;
    private int firstVisibleItem;
    private int lastVisibleItem;
    protected View mView;
    protected Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.view8_fragment,container,false);
        ButterKnife.bind(this,mView);

        urlList = new ArrayList<>();
        urlList.add("https://ip4241946660.mobgslb.tbcache.com/youku/6773C29A5F039716F67852448/03000801005C3F0A0C6E34B003E880FAB4F6BE-B031-456D-A02A-20D2E095B9C6.mp4?sid=054788512983518352426_00_Ae1eea41461b277798155c29d758baa9e&sign=b1b90681af688b012f74577d67905894&ctype=50&hd=1&ali_redirect_domain=vali.cp31.ott.cibntv.net&ali_redirect_ex_ftag=7851028d6a752c25afe496eda34384a70c50dc4c278a94c5&ali_redirect_ex_tmining_ts=1547885435&ali_redirect_ex_tmining_expire=3600&ali_redirect_ex_hot=11");
        urlList.add("https://ip4241946660.mobgslb.tbcache.com/youku/6773C29A5F039716F67852448/03000801005C3F0A0C6E34B003E880FAB4F6BE-B031-456D-A02A-20D2E095B9C6.mp4?sid=054788512983518352426_00_Ae1eea41461b277798155c29d758baa9e&sign=b1b90681af688b012f74577d67905894&ctype=50&hd=1&ali_redirect_domain=vali.cp31.ott.cibntv.net&ali_redirect_ex_ftag=7851028d6a752c25afe496eda34384a70c50dc4c278a94c5&ali_redirect_ex_tmining_ts=1547885435&ali_redirect_ex_tmining_expire=3600&ali_redirect_ex_hot=11");
        urlList.add("http://111.230.34.50:8080/oldcar/img/test.mp4");
        urlList.add("http://111.230.34.50:8080/oldcar/img/test.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150920130302.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803141625005241.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803141624378522.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803131546119319.mp4");

        videoAdapter = new ListVideoAdapter(urlList);
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.setAdapter(videoAdapter);

        addListener();
        return mView;

    }

    private void addListener() {


        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        /**在这里执行，视频的自动播放与停止*/
                        autoPlayVideo(recyclerView);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        autoPlayVideo(recyclerView);
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        JZVideoPlayer.releaseAllVideos();
                        break;
                }

            }
        });
    }

    /**
     * 自动播放
     */
    private void autoPlayVideo(RecyclerView recyclerView) {

        if (firstVisibleItem == 0 && lastVisibleItem == 0 && recyclerView.getChildAt(0) != null) {

            MyVideoPlayer videoView = null;
            if (recyclerView != null && recyclerView.getChildAt(0) != null) {
                videoView = recyclerView.getChildAt(0).findViewById(R.id.mp_video);
            }
            if (videoView != null) {
                if (videoView.currentState == JZVideoPlayer.CURRENT_STATE_NORMAL || videoView.currentState == JZVideoPlayer.CURRENT_STATE_PAUSE) {
                    videoView.startVideo();
                }
            }
        }

        for (int i = 0; i <= lastVisibleItem; i++) {
            if (recyclerView == null || recyclerView.getChildAt(i) == null) {
                return;
            }


            MyVideoPlayer
                    videoView = recyclerView.getChildAt(i).findViewById(R.id.mp_video);
            if (videoView != null) {

                Rect rect = new Rect();
                //获取视图本身的可见坐标，把值传入到rect对象中
                videoView.getLocalVisibleRect(rect);
                //获取视频的高度
                int videoHeight = videoView.getHeight();

                if (rect.top <= 100 && rect.bottom >= videoHeight) {
                    if (videoView.currentState == JZVideoPlayer.CURRENT_STATE_NORMAL || videoView.currentState == JZVideoPlayer.CURRENT_STATE_PAUSE) {
                        videoView.startVideo();
                    }
                    return;
                }

                JZVideoPlayer.releaseAllVideos();

            } else {
                JZVideoPlayer.releaseAllVideos();
            }

        }

    }


    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }


    class ListVideoAdapter extends BaseRecAdapter<String, VideoViewHolder> {


        public ListVideoAdapter(List<String> list) {
            super(list);
        }

        @Override
        public void onHolder(VideoViewHolder holder, String bean, int position) {
            holder.mp_video.setUp(bean, JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
            if (position == 0) {
                holder.mp_video.startVideo();
            }
            Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
            holder.tv_title.setText("评论  2");
        }

        @Override
        public VideoViewHolder onCreateHolder() {
            return new VideoViewHolder(getViewByRes(R.layout.item_video));

        }


    }

    public class VideoViewHolder extends BaseRecViewHolder {
        public View rootView;
        public MyVideoPlayer mp_video;
        public TextView tv_title;

        public VideoViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mp_video = rootView.findViewById(R.id.mp_video);
            this.tv_title = rootView.findViewById(R.id.tv_title);
        }

    }
}
