package com.example.myselftravel3;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AccountDAO {
    @Query("select * from Account")
    List<Account> getAllAccount();

    @Insert
    long insert(Account account);

    @Delete
    void delete(Account account);
    @Update
    void update(Account account);
}
