package com.github.mikowiec.eplaza.admin.service.impl

import com.github.mikowiec.eplaza.admin.dao.GoodsCatDao
import com.github.mikowiec.eplaza.admin.service.GoodsCatService
import com.github.mikowiec.eplaza.model.GoodsCat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GoodsCatServiceImpl : GoodsCatService {

    @Autowired
    private val goodsCatDao: GoodsCatDao? = null

    override fun findAll(): List<GoodsCat>? {
        val goodsCats = goodsCatDao!!.findByParentId(0) ?: return null

        setChildrenCats(goodsCats)
        return goodsCats
    }

    override fun findById(id: Int): GoodsCat? {
        return goodsCatDao!!.findById(id)
    }

    override fun save(goodsCat: GoodsCat): Int {
        return goodsCatDao!!.save(goodsCat)
    }

    override fun update(goodsCat: GoodsCat) {
        goodsCatDao!!.update(goodsCat)
    }

    override fun delete(goodsCat: GoodsCat) {
        goodsCatDao!!.delete(goodsCat)
    }

    // Format the result and stitch all sub-categories into the parent category
    private fun setChildrenCats(goodsCats: List<GoodsCat>) {
        System.out.println(goodsCats)
        for (i in 0 until goodsCats.size) {
            val goodsCat = goodsCats[i]

            val childrenCats = goodsCatDao!!.findByParentId(
                    goodsCat.id)
            goodsCat.childrenCats = childrenCats
        }
    }
}
