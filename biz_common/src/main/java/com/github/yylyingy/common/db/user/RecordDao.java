package com.github.yylyingy.common.db.user;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/1/20 22:44
 */
@Dao
public interface RecordDao {
    @Query("SELECT * FROM Record")
    List<Record> getAll();

    @Query("SELECT * FROM Record WHERE indexId = :id")
    Record getById(int id);

    @Query("SELECT * FROM Record WHERE path = :path")
    Record getByPath(String path);

    @Insert
    void insert(Record... records);
    @Delete
    void delete(Record... records);
    @Update
    void update(Record user);
}
