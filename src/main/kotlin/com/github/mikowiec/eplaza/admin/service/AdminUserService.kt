package com.github.mikowiec.eplaza.admin.service

import com.github.mikowiec.eplaza.model.AdminUser

interface AdminUserService {

    fun findAll(): List<AdminUser>

    fun findByPage(offset: Int, pageSize: Int): List<AdminUser>

    fun findById(id: Int): AdminUser?

    fun save(adminUser: AdminUser): Int

    fun update(adminUser: AdminUser)

    fun delete(adminUser: AdminUser)

}
