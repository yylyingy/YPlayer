package com.github.yylyingy.common.mediafilter;


import com.github.yylyingy.common.mediafilter.callback.FileLoaderCallbacks;
import com.github.yylyingy.common.mediafilter.callback.FilterResultCallback;
import com.github.yylyingy.common.mediafilter.entity.AudioFile;
import com.github.yylyingy.common.mediafilter.entity.ImageFile;
import com.github.yylyingy.common.mediafilter.entity.NormalFile;
import com.github.yylyingy.common.mediafilter.entity.VideoFile;

import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;

import static com.github.yylyingy.common.mediafilter.callback.FileLoaderCallbacks.TYPE_AUDIO;
import static com.github.yylyingy.common.mediafilter.callback.FileLoaderCallbacks.TYPE_FILE;
import static com.github.yylyingy.common.mediafilter.callback.FileLoaderCallbacks.TYPE_IMAGE;
import static com.github.yylyingy.common.mediafilter.callback.FileLoaderCallbacks.TYPE_VIDEO;

/**
 * <br> ClassName:   MediaFileFilter
 * <br> Description: MediaFileFilter
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/22 23:17
 */

public class MediaFileFilter {
    public static void getImages(FragmentActivity activity, FilterResultCallback<ImageFile> callback){
        LoaderManager.getInstance(activity).initLoader(0, null,
                new FileLoaderCallbacks(activity, callback, TYPE_IMAGE));
    }

    public static void getVideos(FragmentActivity activity, FilterResultCallback<VideoFile> callback){
        LoaderManager.getInstance(activity).initLoader(1, null,
                new FileLoaderCallbacks(activity, callback, TYPE_VIDEO));
    }

    public static void getAudios(FragmentActivity activity, FilterResultCallback<AudioFile> callback){
        LoaderManager.getInstance(activity).initLoader(2, null,
                new FileLoaderCallbacks(activity, callback, TYPE_AUDIO));
    }

    public static void getFiles(FragmentActivity activity,
                                FilterResultCallback<NormalFile> callback, String[] suffix){
        LoaderManager.getInstance(activity).initLoader(3, null,
                new FileLoaderCallbacks(activity, callback, TYPE_FILE, suffix));
    }
}
