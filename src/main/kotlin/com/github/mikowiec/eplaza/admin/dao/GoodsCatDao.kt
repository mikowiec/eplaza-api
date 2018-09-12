package com.github.mikowiec.eplaza.admin.dao


import com.github.mikowiec.eplaza.model.GoodsCat

interface GoodsCatDao {

    fun findAll(): List<GoodsCat>

    fun findByParentId(catId: Int): List<GoodsCat>?

    fun findById(id: Int): GoodsCat?

    fun save(goodsCat: GoodsCat): Int

    fun update(goodsCat: GoodsCat)

    fun delete(goodsCat: GoodsCat)

}
