package com.github.mikowiec.eplaza.admin.service.impl

import com.github.mikowiec.eplaza.admin.dao.AdminGroupDao
import com.github.mikowiec.eplaza.admin.service.AdminGroupService
import com.github.mikowiec.eplaza.model.AdminGroup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminGroupServiceImpl : AdminGroupService {

    @Autowired
    private val adminGroupDao: AdminGroupDao? = null

    override fun findAll(): List<AdminGroup> {
        return adminGroupDao!!.findAll()
    }

    override fun findByPage(offset: Int, pageSize: Int): List<AdminGroup> {
        return adminGroupDao!!.findByPage(offset, pageSize)
    }

    override fun findById(id: Int): AdminGroup? {
        return adminGroupDao!!.findById(id)
    }

    override fun save(adminGroup: AdminGroup): Int {
        return adminGroupDao!!.save(adminGroup)
    }

    override fun update(adminGroup: AdminGroup) {
        adminGroupDao!!.update(adminGroup)
    }

    override fun delete(adminGroup: AdminGroup) {
        adminGroupDao!!.delete(adminGroup)
    }
}
