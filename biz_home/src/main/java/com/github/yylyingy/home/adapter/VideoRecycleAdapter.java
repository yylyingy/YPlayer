package com.github.yylyingy.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.yylyingy.common.adapter.BaseAdapter;
import com.github.yylyingy.common.mediafilter.entity.VideoFile;
import com.github.yylyingy.common.util.Util;
import com.github.yylyingy.home.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/22 23:59
 */
public class VideoRecycleAdapter extends BaseAdapter<VideoFile,VideoRecycleAdapter.VideoViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    public VideoRecycleAdapter(Context ctx) {
        super(ctx, new ArrayList<>());
    }
    public VideoRecycleAdapter(Context ctx, ArrayList<VideoFile> list) {
        super(ctx, list);
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).
                inflate(R.layout.biz_home_video_pick_item,parent,false);
        ViewGroup.LayoutParams params = itemView.getLayoutParams();
        if (params != null) {
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            params.height = width / 2;
        }
        VideoViewHolder videoViewHolder = new VideoViewHolder(itemView);
        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoFile file = mList.get(position);
        RequestOptions options = new RequestOptions();
        Glide.with(mContext)
                .load(file.getPath())
                .apply(options.centerCrop())
                .transition(withCrossFade())
//                    .transition(new DrawableTransitionOptions().crossFade(500))
                .into(holder.mIvThumbnail);
        holder.mTvFile.setText(file.getName());
        holder.mDuration.setText(Util.getDurationString(file.getDuration()));
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onClick(v,position,file);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(View v,int position,VideoFile videoFile);
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        private View mItemView;
        private TextView mTvFile;
        private ImageView mIvThumbnail;
        private TextView mDuration;
        private RelativeLayout mDurationLayout;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mTvFile = itemView.findViewById(R.id.tv_file);
            mIvThumbnail = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
            mDuration = (TextView) itemView.findViewById(R.id.txt_duration);
            mDurationLayout = (RelativeLayout) itemView.findViewById(R.id.layout_duration);
        }
    }
}
