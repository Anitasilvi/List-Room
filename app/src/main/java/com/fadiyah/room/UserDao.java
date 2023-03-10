package com.fadiyah.room;

import com.fadiyah.room.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
@Dao
public interface UserDao {
    @Insert
    Long insert(User u);

    @Query("SELECT * FROM `User` ORDER BY `id` DESC")
    List<User> getAllUsers();

    @Query("SELECT * FROM `User` WHERE `id` =:id")
    User getUser(int id);

    @Update
    void update(User u);

    @Delete
    void delete(User u);
}
