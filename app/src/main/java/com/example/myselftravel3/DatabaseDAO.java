package com.example.myselftravel3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Account.class},version = 1,exportSchema = false)
public abstract class DatabaseDAO extends RoomDatabase {
    public abstract AccountDAO getAccountDAO();
}
