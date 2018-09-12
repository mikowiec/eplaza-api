package com.github.mikowiec.eplaza.admin.service.impl

import com.github.mikowiec.eplaza.admin.dao.AdminUserDao
import com.github.mikowiec.eplaza.admin.service.AdminUserService
import com.github.mikowiec.eplaza.model.AdminUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminUserServiceImpl : AdminUserService {

    @Autowired
    private val adminUserDao: AdminUserDao? = null

    override fun findAll(): List<AdminUser> {
        return adminUserDao!!.findAll()
    }

    override fun findByPage(offset: Int, pageSize: Int): List<AdminUser> {
        return adminUserDao!!.findByPage(offset, pageSize)
    }

    override fun findById(id: Int): AdminUser? {
        return adminUserDao!!.findById(id)
    }

    override fun save(adminUser: AdminUser): Int {
        return adminUserDao!!.save(adminUser)
    }

    override fun update(adminUser: AdminUser) {
        adminUserDao!!.update(adminUser)
    }

    override fun delete(adminUser: AdminUser) {
        adminUserDao!!.delete(adminUser)
    }
}
