package com.zc.dictionarygenius

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue

class MainDataBase private constructor() {
    private val database = FirebaseDatabase.getInstance()
    private val dbRootRef = database.reference
    private val userNode = dbRootRef.child("users")
    private val userContact = dbRootRef.child("contacts")

    fun saveUser(user: String, contact: String, callback: ((Boolean) -> Unit)? = null) {
        val salvaContacts = UserLogin(
            user,
            contact
        )
        userContact.setValue(salvaContacts)
            .addOnCompleteListener {
                callback?.invoke(it.isSuccessful)
            }
    }

    fun getUser(user: String, password: String, callback: (Boolean) -> Unit) {
        userNode.get().addOnSuccessListener {
            val value = it.getValue<UserLogin>()
            val test = value?.user == user && value.password == password
            callback(test)
        }.addOnFailureListener {
            callback(false)
        }
    }

    fun searchContact(callback: (UserLogin) -> Unit) {
        userContact.get().addOnSuccessListener {
            val value = it.getValue<UserLogin>()
            if (value != null) {
                callback(value)
            }
        }.addOnFailureListener {
            callback(UserLogin())
        }
    }

    companion object {
        private var mainDatabase: MainDataBase? = null

        fun getInstance(): MainDataBase {
            if (mainDatabase == null)
                mainDatabase =
                    MainDataBase()
            return mainDatabase!!
        }
    }
}

class UserLogin {
    var user: String = ""
    var password: String = ""

    constructor(user: String, password: String) {
        this.user = user
        this.password = password
    }

    constructor()
}