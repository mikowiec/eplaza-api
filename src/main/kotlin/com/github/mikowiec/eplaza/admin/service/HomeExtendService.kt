package com.github.mikowiec.eplaza.admin.service

import com.github.mikowiec.eplaza.model.HomeExtend

interface HomeExtendService {

    fun findAll(): List<HomeExtend>

    fun findByPage(offset: Int, pageSize: Int): List<HomeExtend>

    fun findById(id: Int): HomeExtend?

    fun save(homeExtend: HomeExtend): Int

    fun update(homeExtend: HomeExtend)

    fun delete(homeExtend: HomeExtend)

}
