package com.github.mikowiec.eplaza.admin.service.impl

import com.github.mikowiec.eplaza.admin.dao.HomeExtendDao
import com.github.mikowiec.eplaza.admin.service.HomeExtendService
import com.github.mikowiec.eplaza.model.HomeExtend
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HomeExtendServiceImpl : HomeExtendService {

    @Autowired
    private val homeExtendDao: HomeExtendDao? = null

    override fun findAll(): List<HomeExtend> {
        return homeExtendDao!!.findAll()
    }

    override fun findByPage(offset: Int, pageSize: Int): List<HomeExtend> {
        return homeExtendDao!!.findByPage(offset, pageSize)
    }

    override fun findById(id: Int): HomeExtend? {
        return homeExtendDao!!.findById(id)
    }

    override fun save(homeExtend: HomeExtend): Int {
        return homeExtendDao!!.save(homeExtend)
    }

    override fun update(homeExtend: HomeExtend) {
        homeExtendDao!!.update(homeExtend)
    }

    override fun delete(homeExtend: HomeExtend) {
        homeExtendDao!!.delete(homeExtend)
    }
}
