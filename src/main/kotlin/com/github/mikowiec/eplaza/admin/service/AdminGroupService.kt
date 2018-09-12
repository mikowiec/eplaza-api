package com.github.mikowiec.eplaza.admin.service

import com.github.mikowiec.eplaza.model.AdminGroup

interface AdminGroupService {

    fun findAll(): List<AdminGroup>

    fun findByPage(offset: Int, pageSize: Int): List<AdminGroup>

    fun findById(id: Int): AdminGroup?

    fun save(adminGroup: AdminGroup): Int

    fun update(adminGroup: AdminGroup)

    fun delete(adminGroup: AdminGroup)

}
