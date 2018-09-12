package com.github.mikowiec.eplaza.frontend.dao

import com.github.mikowiec.eplaza.model.User

interface UserDao {
    fun saveUser(user: User): Int

    fun getUserByPhone(phone: String): List<User>
}
