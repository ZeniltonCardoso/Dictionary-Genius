package com.zc.dictionarygenius.data

class UserDataLocal(
    val cpf: String,
    val digitalPassword: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserDataLocal

        if (cpf != other.cpf) return false
        if (digitalPassword != other.digitalPassword) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cpf.hashCode()
        result = 31 * result + digitalPassword.hashCode()

        return result
    }
}