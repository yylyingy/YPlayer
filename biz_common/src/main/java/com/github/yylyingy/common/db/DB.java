package com.github.yylyingy.common.db;

import android.content.Context;

import com.tencent.wcdb.database.SQLiteCipherSpec;
import com.tencent.wcdb.room.db.WCDBOpenHelperFactory;

import java.util.HashMap;
import java.util.HashSet;

import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/6/10 0:59
 */
public class DB {
    private static WCDBOpenHelperFactory factory;
    private static SQLiteCipherSpec cipherSpec;
    private static HashMap<String,RoomDatabase> roomDatabases = new HashMap<>();
    public static void init() {
        cipherSpec = new SQLiteCipherSpec()  // 指定加密方式，使用默认加密可以省略
                .setPageSize(4096)
                .setKDFIteration(64000);

        factory = new WCDBOpenHelperFactory()
//                .passphrase("passphrase".getBytes())  // 指定加密DB密钥，非加密DB去掉此行
//                .cipherSpec(cipherSpec)               // 指定加密方式，使用默认加密可以省略
                .writeAheadLoggingEnabled(true)       // 打开WAL以及读写并发，可以省略让Room决定是否要打开
                .asyncCheckpointEnabled(true);        // 打开异步Checkpoint优化，不需要可以省略
    }

    public static RecordDataBase getRecordData(Context context) {
        String method = Thread.currentThread() .getStackTrace()[1].getMethodName();
        RecordDataBase db ;
        if ((db = (RecordDataBase) roomDatabases.get(method)) != null) {
            return db;
        }
        db= Room.databaseBuilder(context.getApplicationContext(), RecordDataBase.class, "record")
                .allowMainThreadQueries()   // 允许主线程执行DB操作，一般不推荐
                .openHelperFactory(factory)   // 重要：使用WCDB打开Room
                .addMigrations()
                .build();
        roomDatabases.put(method,db);
        return db;
    }
}
