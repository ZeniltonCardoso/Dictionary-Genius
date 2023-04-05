package com.zc.dictionarygenius.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [
    UserLocal::class
], version = 1)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}