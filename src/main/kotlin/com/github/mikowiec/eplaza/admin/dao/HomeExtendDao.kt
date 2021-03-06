package com.github.mikowiec.eplaza.admin.dao


import com.github.mikowiec.eplaza.model.HomeExtend

interface HomeExtendDao {

    fun findAll(): List<HomeExtend>

    fun findByPage(page: Int, pageSize: Int): List<HomeExtend>

    fun findById(id: Int): HomeExtend?

    fun save(homeExtend: HomeExtend): Int

    fun update(homeExtend: HomeExtend)

    fun delete(homeExtend: HomeExtend)

}
