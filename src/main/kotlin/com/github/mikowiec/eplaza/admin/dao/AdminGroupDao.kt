package com.github.mikowiec.eplaza.admin.dao

import com.github.mikowiec.eplaza.model.AdminGroup

interface AdminGroupDao {

    fun findAll(): List<AdminGroup>

    fun findByPage(page: Int, pageSize: Int): List<AdminGroup>

    fun findById(id: Int): AdminGroup?

    fun save(adminGroup: AdminGroup): Int

    fun update(adminGroup: AdminGroup)

    fun delete(adminGroup: AdminGroup)

}
