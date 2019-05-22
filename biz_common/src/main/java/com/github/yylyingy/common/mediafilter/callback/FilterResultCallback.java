package com.github.yylyingy.common.mediafilter.callback;

import com.github.yylyingy.common.mediafilter.entity.BaseFile;
import com.github.yylyingy.common.mediafilter.entity.Directory;

import java.util.List;

/**
 * <br> ClassName:   FilterResultCallback
 * <br> Description: FilterResultCallback
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/22 23:16
 */

public interface FilterResultCallback<T extends BaseFile> {
    void onResult(List<Directory<T>> directories);
}
