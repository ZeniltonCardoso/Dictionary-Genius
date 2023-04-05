package com.zc.dictionarygenius.data

import androidx.room.Entity
import androidx.room.TypeConverters

@Entity(tableName = "Users")
data class UserLocal(
    @TypeConverters(Converter::class) var userData: UserDataLocal,
)