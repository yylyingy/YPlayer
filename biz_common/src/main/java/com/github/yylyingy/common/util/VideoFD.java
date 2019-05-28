package com.github.yylyingy.common.util;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/27 19:00
 */
public class VideoFD implements Parcelable {
    String path;
    Uri mUri;

    public static final Creator<VideoFD> CREATOR = new Creator<VideoFD>() {
        @Override
        public VideoFD createFromParcel(Parcel in) {
            VideoFD videoFD = new VideoFD();
            videoFD.setPath(in.readString());
            videoFD.setUri(in.readParcelable(Uri.class.getClassLoader()));
            return videoFD;
        }

        @Override
        public VideoFD[] newArray(int size) {
            return new VideoFD[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeParcelable(mUri, flags);
    }
}
