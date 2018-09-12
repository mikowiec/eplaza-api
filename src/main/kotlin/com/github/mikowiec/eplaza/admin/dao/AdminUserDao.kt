package com.github.mikowiec.eplaza.admin.dao

import com.github.mikowiec.eplaza.model.AdminUser

interface AdminUserDao {

    fun findAll(): List<AdminUser>

    fun findByPage(page: Int, pageSize: Int): List<AdminUser>

    fun findById(id: Int): AdminUser?

    fun save(adminUser: AdminUser): Int

    fun update(adminUser: AdminUser)

    fun delete(adminUser: AdminUser)

}
