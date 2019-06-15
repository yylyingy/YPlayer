package com.github.yylyingy.common.db.update.migrationuser;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/6/10 1:09
 */
public class MiragrationUser_1_2 extends Migration {
    int start;
    int end;
    /**
     * Creates a new migration between {@code startVersion} and {@code endVersion}.
     *
     * @param startVersion The start version of the database.
     * @param endVersion   The end version of the database after this migration is applied.
     */
    public MiragrationUser_1_2(int startVersion, int endVersion) {
        super(startVersion, endVersion);
        start = startVersion;
        end = endVersion;
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        if (start == 1 && end == 2) {
            //do something code;
//            database.execSQL("sql alter table");
        }
    }
}
