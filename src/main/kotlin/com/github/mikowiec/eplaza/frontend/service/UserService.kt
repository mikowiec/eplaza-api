package com.github.mikowiec.eplaza.frontend.service

import com.github.mikowiec.eplaza.model.User

interface UserService {
    fun saveUser(user: User): Int
    fun isExist(phone: String): Boolean
    fun isAllowLogin(phone: String, password: String): User?
}
