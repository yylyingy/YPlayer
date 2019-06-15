package com.github.yylyingy.common.db;



import com.github.yylyingy.common.db.user.Record;
import com.github.yylyingy.common.db.user.RecordDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/1/20 22:50
 */
@Database(entities = {Record.class}, version = 1,exportSchema= false)
public abstract class RecordDataBase extends RoomDatabase {
    public abstract RecordDao getRecordDao();
}
