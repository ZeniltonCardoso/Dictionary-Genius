package com.zc.dictionarygenius

import com.google.firebase.auth.FirebaseAuth

class MainAuthentic private constructor() {
    private val authentic = FirebaseAuth.getInstance()
    private val dbRootRef = authentic.firebaseAuthSettings
}