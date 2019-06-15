package com.github.yylyingy.common.db.user;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/1/20 22:37
 */
@Entity
public class Record {
    @PrimaryKey(autoGenerate = true)
    public int indexId;
    public String path;
    public int progress;
}
