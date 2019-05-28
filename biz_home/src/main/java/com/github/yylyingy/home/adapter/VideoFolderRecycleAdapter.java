package com.github.yylyingy.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.yylyingy.common.adapter.BaseAdapter;
import com.github.yylyingy.common.mediafilter.entity.Directory;
import com.github.yylyingy.common.util.AppUtil;
import com.github.yylyingy.home.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/23 1:05
 */
public class VideoFolderRecycleAdapter extends BaseAdapter<Directory,
        VideoFolderRecycleAdapter.VideoFolderViewHolder> {
    private OnFolderClickListener mOnFolderClickListener;
    public VideoFolderRecycleAdapter(Context ctx) {
        super(ctx, new ArrayList<>());
    }
    public VideoFolderRecycleAdapter(Context ctx, List<Directory> list) {
        super(ctx, list);
    }

    @NonNull
    @Override
    public VideoFolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.biz_home_video_folder_pick_item,parent,false);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            params.height = width / 3;
        }
        VideoFolderViewHolder videoFolderViewHolder = new VideoFolderViewHolder(view);
        return videoFolderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoFolderViewHolder holder, int position) {
        holder.mTvFolder.setText(mList.get(position).getName());
        String count = mList.get(position).getFiles().size() +
                AppUtil.getContext().getResources().getString(R.string.video_count);
        holder.mTvFileCount.setText(count);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnFolderClickListener) {
                    mOnFolderClickListener.onClick(v,position,mList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnFolderClickListener(OnFolderClickListener onFolderClickListener) {
        mOnFolderClickListener = onFolderClickListener;
    }

    @Override
    public void add(List<Directory> list) {
        //use hashmap to help remove repeat Directory
        HashMap<String,Directory> hashSet = new HashMap<>();
        for (int i = 0;i < getDataSet().size();i ++) {
            hashSet.put(getDataSet().get(i).getPath(),getDataSet().get(i));
        }
        int j = 0;
        while (j < list.size()) {
            if (null != hashSet.get(list.get(j).getPath())) {
                list.remove(j);
            } else {
                j ++;
            }
        }
        getDataSet().addAll(list);
        Collections.sort(getDataSet(), new Comparator<Directory>() {
            @Override
            public int compare(Directory o1, Directory o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        notifyDataSetChanged();
    }

    public interface OnFolderClickListener {
        void onClick(View view,int position,Directory directory);
    }

    static class VideoFolderViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        ImageView mIvIcon;
        TextView mTvFolder;
        TextView mTvFileCount;
        public VideoFolderViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mIvIcon = itemView.findViewById(R.id.iv_icon);
            mTvFolder = itemView.findViewById(R.id.tv_folder);
            mTvFileCount = itemView.findViewById(R.id.tv_file_count);
        }
    }
}
