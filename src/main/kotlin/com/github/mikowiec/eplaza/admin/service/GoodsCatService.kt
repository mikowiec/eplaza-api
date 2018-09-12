package com.github.mikowiec.eplaza.admin.service

import com.github.mikowiec.eplaza.model.GoodsCat

interface GoodsCatService {

    fun findAll(): List<GoodsCat>?

    fun findById(id: Int): GoodsCat?

    fun save(goodsCat: GoodsCat): Int

    fun update(goodsCat: GoodsCat)

    fun delete(goodsCat: GoodsCat)

}
