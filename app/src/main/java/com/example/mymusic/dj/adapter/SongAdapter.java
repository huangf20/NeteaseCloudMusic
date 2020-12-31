package com.example.mymusic.dj.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.mymusic.base.BaseAdapter;
import com.example.mymusic.manager.SongPlayManager;
import com.example.mymusic.song.mvp.view.SongActivity;
import com.example.mymusic.widget.CustomRoundRectView;
import com.lzx.starrysky.model.SongInfo;
import com.example.mymusic.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SongAdapter extends BaseAdapter<RecyclerView.ViewHolder, SongInfo> {
    private static final String TAG = "SongAdapter";

    private List<SongInfo> list = new ArrayList<>();
    private Context mContext;

    public SongAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void notifyDataSetChanged(List<SongInfo> dataList) {
        list = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(getInflater().inflate(R.layout.item_song, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder != null) {
            ViewHolder vh = (ViewHolder) viewHolder;
            SongInfo songInfo = list.get(i);
            vh.setBean(songInfo);
            vh.setListener(songInfo);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvArtist, tvName;
        private CircleImageView ivAvatar;
        private CustomRoundRectView ivCover;
        private RelativeLayout rlSong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvArtist = itemView.findViewById(R.id.tv_artist);
            tvName = itemView.findViewById(R.id.tv_name);
            ivAvatar = itemView.findViewById(R.id.iv_creator_avatar);
            ivCover = itemView.findViewById(R.id.iv_cover);
            rlSong = itemView.findViewById(R.id.rl_song);
        }

        public void setBean(SongInfo songInfo) {
            Glide.with(mContext)
                    .load(songInfo.getSongCover())
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(ivCover);
            Glide.with(mContext)
                    .load(songInfo.getArtistKey())
                    .into(ivAvatar);
            tvArtist.setText(songInfo.getArtist());
            tvName.setText(songInfo.getSongName());
        }

        public void setListener(SongInfo songInfo) {
            rlSong.setOnClickListener(v -> {
                SongPlayManager.getInstance().clickASong(songInfo);
                Intent intent = new Intent(mContext, SongActivity.class);
                intent.putExtra(SongActivity.SONG_INFO, songInfo);
                mContext.startActivity(intent);
            });
        }
    }
}
