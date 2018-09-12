package com.github.mikowiec.eplaza.frontend.service.impl

import com.github.mikowiec.eplaza.frontend.dao.UserDao
import com.github.mikowiec.eplaza.frontend.service.UserService
import com.github.mikowiec.eplaza.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    private val userDao: UserDao? = null

     override fun saveUser(user: User): Int {
        return userDao!!.saveUser(user)
    }

    override fun isExist(phone: String): Boolean {
        val users = userDao!!.getUserByPhone(phone)
        return if (users.size > 0) {
            true
        } else false
    }

    override fun isAllowLogin(phone: String, password: String): User? {
        val users = userDao!!.getUserByPhone(phone)
        val pwd = if (users.size > 0) users.get(0).password else "xasda"
        System.out.println(pwd)
        return if (pwd.equals(password)) {
            users.get(0)
        } else null
    }
}
