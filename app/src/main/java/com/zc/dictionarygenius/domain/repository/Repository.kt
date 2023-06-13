package com.zc.dictionarygenius.domain.repository

import com.zc.dictionarygenius.BaseRepository
import com.zc.dictionarygenius.UserLogin

class Repository private constructor(): BaseRepository()  {

    fun getUser(user: String, password: String, callback: (Boolean) -> Unit) = mAppDataBase.getUser(user, password, callback)

    fun searchContact(callback: (UserLogin) -> Unit) = mAppDataBase.searchContact(callback)

    fun saveUser(user: String, contact: String, callback: (Boolean) -> Unit) = mAppDataBase.saveUser(user, contact, callback)

    companion object {
        private var instance: Repository? = null

        fun getInstance(): Repository {
            if (instance == null)
                instance = Repository()
            return instance!!
        }
    }
}