package com.zc.dictionarygenius.data

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun stringToUserData(string: String): UserDataLocal? {
        if (TextUtils.isEmpty(string))
            return null
        return Gson().fromJson(string, UserDataLocal::class.java)
    }

    @TypeConverter
    fun userDataToString(userData: UserDataLocal): String {
        return Gson().toJson(userData)
    }
}